package com.ex.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ex.exception.ResourceNotFoundExeption;
import com.ex.model.Admin;
import com.ex.model.ErrorDetailsResponse;
import com.ex.model.Instance;
import com.ex.model.RequestTracker;
import com.ex.model.Server;
import com.ex.repository.ErrorDetailsRepository;
import com.ex.repository.ServerStatusRepository;
import com.ex.service.UreService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UreController {

	@Autowired
	private UreService service;

	// for checking login credentials
	// working
	@PostMapping("/login")
	public Admin userLogin(@RequestBody Admin admin) throws Exception {
		int tempId = admin.getEmpId();
		String tempPassword = admin.getPassword();

		// put validations in angular form for login
		Admin result = new Admin();

		result = null;
		result = service.fetchByEmpIdAndPassword(tempId, tempPassword);
		if (result == null) {
			System.out.println("incorrect credentials");
		} else {
			if (result.getUserStatus() == 0) {
				System.out.println("admin login  successfull");

			} else {
				System.out.println("URE login successfull");
			}
		}
		return result;
	}

	// view all the users
	// working
	@GetMapping("/login")
	public List<Admin> viewUsers() {
		List<Admin> admin = new ArrayList<>();
		admin = service.fetchAllUsers();
		return admin;
	}

	// view user with a particular id
	// working
	@GetMapping("/login/{id}")
	public Admin viewUser(@PathVariable int id) {
		Admin admin = new Admin();
		admin = null;
		admin = service.fetchById(id);
		return admin;
	}

	// view all servers assigned to a particular member
	// working
	@GetMapping("/server/{id}")
	public List<Server> getServerOfUser(@PathVariable int id) {
		Admin admin = new Admin();
		admin = null;
		admin = service.fetchById(id);
		List<Server> servers = new ArrayList<>();
		// write loop to remove error
		servers = admin.getServer();
		return servers;

	}

	// view all servers
	// working
	@GetMapping("/server")
	public List<Server> viewServers() {
		List<Server> servers = new ArrayList<>();
		servers = service.fetchAllServers();
		return servers;
	}

	// view server having particular ip
	// no i/p required
	// working
	@PostMapping("/server/{ip}")
	public Server viewServerByIp(@PathVariable String ip) {
		Server server = new Server();
		server = null;
		server = service.fetchByIp(ip);
		return server;

	}

	// assign server to a user
	// take i/p server/ip + category
	// i/p json request(enter the server ip u want to assign {
	// "serverIp":"25:64:23:55" }
	// working
	@PostMapping("/login/{id}")
	public Admin assignServer(@PathVariable int id, @RequestBody Server server) {

		Admin admin = new Admin();
		admin = null;
		admin = service.fetchById(id);

		String serverIp = server.getServerIp();
		Server tempServer = new Server();
		tempServer = null;
		tempServer = service.fetchByIp(serverIp);

		if (tempServer != null) {// to check if server already assigned or not
			Server s = service.checkServer(serverIp, admin);
			if (s == null) {
				admin.getServer().add(tempServer);
				System.out.println("server assigned successfully");
				service.saveAdmin(admin);
				return admin;
			}
			System.out.println("server already assigned");
			return admin;

		} else {
			System.out.println("server not found !!!");
			return admin;
		}
	}

	// new
	// delete assigned server
	// enter server ip to delete
	@DeleteMapping("/serveredit/{id}/{serverIp}")
	public Admin removeUserServer(@PathVariable int id, @PathVariable String serverIp) {
		// String tempIp=server.getServerIp();
		Admin admin = service.fetchById(id);
		// to check if this server allocated to user or not
		Server tempServer = service.checkServer(serverIp, admin);

		if (tempServer != null) {
			admin = service.removeAssignedServer(admin, tempServer);
			return admin;
		}
		return null;

	}

	// create new user
	// i/p { "empId": 1810011 } enter employee id as json request
	// working
	@PostMapping("/newuser/{id}")
	public Admin registerNewUser(@PathVariable int id, @RequestBody Admin admin) {
		// check if user already exists , if not then only register

		Admin tempAdmin = new Admin();
		admin.setEmpId(id);

		tempAdmin = null;
		tempAdmin = service.fetchById(id);

		if (tempAdmin == null) {
			admin.setUserStatus(1);

			// default password set for all the new user as abcde
			admin.setPassword("abcdefgh");
			service.saveAdmin(admin);
			System.out.println("user saved!!");
			return admin;
		}
		System.out.println("user already exists");
		return null;
	}

	// delete user
	// working
	@DeleteMapping("/login/{id}")
	public Admin deleteUser(@PathVariable int id) {
		Admin admin = new Admin();
		admin = service.fetchById(id);
		if (admin != null) {
			System.out.println("user record deleted");
			return service.deleteUser(admin);
		} else {
			System.out.println("user not exists !!!");
			return admin;
		}

	}

	// ----------------------------------------------------------------------------------------------

	// to get all the users that are assigned a particular server
	@GetMapping("/getuserbyip/{serverIp}")
	public List<Admin> getUserByIp(@PathVariable String serverIp) {
		Server server = new Server();
		server = null;
		server = service.fetchByIp(serverIp);

		List<Admin> adminList = new ArrayList<>();
		adminList = service.fetchServerAdmin(server);
		return adminList;
	}

	// create new instance
	@PostMapping("/addinstance")
	public Instance assignInstance(@RequestBody Instance instance) {
		return service.addInstance(instance);

	}

	// create new server
	@PostMapping("/newserver")
	public Server addServer(@RequestBody Server server) {
		return service.addServer(server);
	}

	// assign instance to server
	// 1 instance can be part of only 1 server
	@PostMapping("/assigninstance/{serverIp}/{instanceIp}")
	public Server assignInstance(@PathVariable String serverIp, @PathVariable String instanceIp) {
		Server server = service.fetchByIp(serverIp);
		Instance instance = service.fetchByInstanceIp(instanceIp);
		System.out.println(instance);
		System.out.println(server);
		if (instance != null && server != null) {
			service.addInstanceToServer(server, instance);
			System.out.println("instance added successfully");
			return server;
		}

		System.out.println("incorrect info entered");
		return null;

	}
	//--------------------------------------------------------------------------------------------------
	// restart
	@Autowired
	private ServerStatusRepository ssr;
	@PostMapping("/serverStatus")
	public RequestTracker saveServerStatus(@RequestBody RequestTracker requestStatus) {
		// RestTemplate rt=new RestTemplate();
		// String serverIp=requestStatus.getServerIp();
		// String domain=requestStatus.getDomain();
		// String category=requestStatus.getCategory();
		// String requestType=requestStatus.getRequestType();
		RequestTracker requestForScriptingTeam = requestForScripting(requestStatus);
		System.out.println("scrrrrrrr" + requestForScriptingTeam);
		return ssr.save(requestStatus);
	}

	@GetMapping("/scriptingTeam")
	private RequestTracker requestForScripting(RequestTracker rt) {
		return rt;

	}
	

	// here we have to write for response
	// depend on there url
	// this will be given by scripting team
	// error start
	@Autowired
	ErrorDetailsRepository edr;
	 @PostMapping("/serverResponse")
	 public ErrorDetailsResponse getServerResponse(@RequestBody ErrorDetailsResponse Ed ) {
		 ServerResponseToUIPage(Ed);
		 System.out.println("uuuuuiiii "+Ed.getServer());
		 
		 return Ed;
	 }
	 @GetMapping("/sendUIPageResponse")
	 public ErrorDetailsResponse ServerResponseToUIPage(ErrorDetailsResponse Ed) {
		 System.out.println("aaaaaaaaaa"+ Ed.getErrorDetails());
		 return Ed;
	 }

	@GetMapping("/serverStatus")
	public List<RequestTracker> getAllstatus() {
		return ssr.findAll();
	}
	
	
	
	
	
	//end
	@GetMapping("/serverStatus/{empId}")
	public ResponseEntity<RequestTracker> getEmployeeById(@PathVariable(value = "empId") Integer empId)
			throws ResourceNotFoundExeption {
		RequestTracker requestTracker = ssr.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundExeption("Server status not found :: " + empId));

		return ResponseEntity.ok().body(requestTracker);
	}

}

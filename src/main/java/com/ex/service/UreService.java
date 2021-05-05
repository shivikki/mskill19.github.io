package com.ex.service;
import com.ex.model.Server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.model.Admin;
import com.ex.model.Instance;
import com.ex.model.Server;
import com.ex.repository.AdminRepository;
import com.ex.repository.InstanceRepository;
import com.ex.repository.ServerRepository;

@Service
public class UreService {

		@Autowired
		AdminRepository repo;
		
		@Autowired
		ServerRepository srepo;
		
		@Autowired
		InstanceRepository irepo;
		
		
		public Admin fetchByEmpIdAndPassword(int emp_id,String password)
		{
			Admin admin=new Admin();
			admin=repo.findByEmpIdAndPassword(emp_id,password);
			return admin;
		}
		
		public List<Admin> fetchAllUsers()
		{
			List<Admin> admin=new ArrayList<>();
			admin=repo.findAll();
			return admin;
		}
		
		public List<Server> fetchAllServers()
		{
			List<Server> server=new ArrayList<>();
			server=srepo.findAll();
			return server;
			
		}
		
		public Admin fetchById(int id)
		{
			Admin admin=new Admin();
			admin=null;
			admin=repo.findById(id).orElse(null);
			return admin;
		}
	
		public Server fetchByIp(String serverIp)
		{
			Server server=new Server();
			server=srepo.findByServerIp(serverIp);
			return server;
			
		}
		public Admin saveAdmin(Admin admin)
		{
			repo.save(admin);
			return admin;
			
		}
		public Server saveServer(Server server)
		{
			srepo.save(server);
			return server;
		}
		
		
		
		public Admin deleteUser(Admin admin)
		{
			repo.delete(admin);
			return admin;
		}
		
		//to check the server is allocated to the user or not
		public Server checkServer(String ip,Admin admin)
		{
			Server server=srepo.findByServerIp(ip);
			List<Server> serverList=new ArrayList<>();
			serverList=admin.getServer();
			for(int i=0;i<serverList.size();i++)
			{
				if(serverList.get(i).equals(server))
				{
					return server;
				}
			}
			return null;
		}
		
		public Admin removeAssignedServer(Admin admin,Server server)
		{
			List<Server> tempAdmin=new ArrayList<>();
			tempAdmin=admin.getServer();
			
			for(int i=0;i<tempAdmin.size();i++)
			{
				if(tempAdmin.get(i).equals(server))
				{
					admin.removeServer(server);
					repo.save(admin);
					System.out.println("server removed");
					return admin;
					
				}
				
			}
			return admin;
		}
		//----------------------------------------------------------------------------
		public Instance addInstance(Instance instance)
		{
			
			irepo.save(instance);
			return instance;
		}
		
		public Server addServer(Server server)
		{
			srepo.save(server);
			return server;
		}
		
		public Instance fetchByInstanceIp(String instanceIp)
		{
			Instance instance=new Instance();
			instance=irepo.findByInstanceIp(instanceIp);
			return instance;
			
		}
		
		
		public Server addInstanceToServer(Server server,Instance instance)
		{
			 server.getInstance().add(instance);
			 srepo.save(server);
			 return server;
			
		}
		
		//see admin to which a server is allocated
		public List<Admin> fetchServerAdmin(Server server)
		{
			
			
			List<Admin> adminList=new ArrayList<>();
			adminList=repo.findAll();   //list of all admin
			
			List<Server> serverList=new ArrayList<>(); //store list of servers assigned to admin
			
			List<Admin> resultList=new ArrayList<>();
			
				//check the servers of all the admins and if it matches store it in resultList
				for(int i=0;i<adminList.size();i++)
				{
						serverList=adminList.get(i).getServer();
						if(serverList!=null)
						{
							for(int j=0;j<serverList.size();j++)
							{
								if(serverList.get(j).equals(server))
								{
								resultList.add(adminList.get(i));
								}
							}
						}
						
						
				}
				return resultList;
			

			
		}
		
		
}

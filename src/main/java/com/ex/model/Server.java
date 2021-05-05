package com.ex.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Server {
	
	@Id
	private String serverIp;
	private String category;
	private String status;
	private String domain;
	private String serverPassword;
	
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="server")
	private List<Admin> admin;

	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name ="server_instance",
	joinColumns= {@JoinColumn(name="serverIp")},
	inverseJoinColumns= {@JoinColumn(name="instanceIp")})
	Set<Instance> instance;
	

	/*
	//add or remove instance
	//m/d to add and remove servers
	public void addInstance(Instance i)
	{
		//add server in admin
		//add admin in server
		this.instance.add(i);
		i.setServer(this);
				
	}
	
	public void removeServer(Instance i)
	{
		this.instance.remove(i);
		i.getAdmin().remove(this);
	}
	*/
	
	
	

	public Server() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Server(String serverIp, String category, String status, String domain, String serverPassword,
			List<Admin> admin, Set<Instance> instance) {
		super();
		this.serverIp = serverIp;
		this.category = category;
		this.status = status;
		this.domain = domain;
		this.serverPassword = serverPassword;
		this.admin = admin;
		this.instance = instance;
	}


	@Override
	public String toString() {
		return "Server [serverIp=" + serverIp + ", category=" + category + ", status=" + status + ", domain=" + domain
				+ ", serverPassword=" + serverPassword + ", admin=" + admin + ", instance=" + instance + "]";
	}


	public String getServerIp() {
		return serverIp;
	}


	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getServerPassword() {
		return serverPassword;
	}


	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}


	public List<Admin> getAdmin() {
		return admin;
	}


	public void setAdmin(List<Admin> admin) {
		this.admin = admin;
	}


	public Set<Instance> getInstance() {
		return instance;
	}


	public void setInstance(Set<Instance> instance) {
		this.instance = instance;
	}

	
	
	
}

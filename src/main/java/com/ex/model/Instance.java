package com.ex.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Instance {

	@Id
	String instanceIp;
	String category;
	String domain;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private Server server;

	public String getInstanceIp() {
		return instanceIp;
	}

	public void setInstanceIp(String instanceIp) {
		this.instanceIp = instanceIp;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	@Override
	public String toString() {
		return "Instance [instanceIp=" + instanceIp + ", category=" + category + ", domain=" + domain + ", server="
				+ server + ", getInstanceIp()=" + getInstanceIp() + ", getCategory()=" + getCategory()
				+ ", getDomain()=" + getDomain() + ", getServer()=" + getServer() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public Instance(String instanceIp, String category, String domain, Server server) {
		super();
		this.instanceIp = instanceIp;
		this.category = category;
		this.domain = domain;
		this.server = server;
	}

	public Instance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}


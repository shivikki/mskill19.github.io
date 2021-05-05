package com.ex.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


	@Entity
	@Table(name="employee")
	public class Admin {
		
		@Id
		//@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int empId;
		private String password;
		private int userStatus;
		private String userName;
		private String emailId;
		
		
		@ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
		@JoinTable(name ="allocation",
				joinColumns= {@JoinColumn(name="empId")},
				inverseJoinColumns= {@JoinColumn(name="serverIp")})
		private List<Server> server;

		
		//m/d to add and remove servers
		public void addServer(Server s)
		{
			//add server in admin
			//add admin in server
			this.server.add(s);
			s.getAdmin().add(this);
					
		}
		
		public void removeServer(Server s)
		{
			this.server.remove(s);
			s.getAdmin().remove(this);
		}
		
				
		
		
		public Admin() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Admin(int empId, String password, int userStatus, String userName, String emailId, List<Server> server) {
			super();
			this.empId = empId;
			this.password = password;
			this.userStatus = userStatus;
			this.userName = userName;
			this.emailId = emailId;
			this.server = server;
		}

		@Override
		public String toString() {
			return "Admin [empId=" + empId + ", password=" + password + ", userStatus=" + userStatus + ", userName="
					+ userName + ", emailId=" + emailId + ", server=" + server + "]";
		}

		
		public int getEmpId() {
			return empId;
		}
		public void setEmpId(int empId) {
			this.empId = empId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getUserStatus() {
			return userStatus;
		}
		public void setUserStatus(int userStatus) {
			this.userStatus = userStatus;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public List<Server> getServer() {
			return server;
		}
		public void setServer(List<Server> server) {
			this.server = server;
		}
		

		
		
		 
		
	}


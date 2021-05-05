package com.ex.model;

import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class RequestTracker {

	public RequestTracker() {

	}

	@Id
	private int empId;
	@Column(nullable = false)
	private String serverIp;
	// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	// LocalDateTime now = LocalDateTime.now();
	//
	// String sample=dtf.format(now);
	// @Value("${RequestTracker.date:sample}")
	// @ColumnDefault("now")

	@Column(name = "date")
	private Date date;

	public RequestTracker(int empId, String serverIp, Date date, String domain, String category, String requestType) {
		super();
		this.empId = empId;
		this.serverIp = serverIp;
		this.date = date;
		this.domain = domain;
		this.category = category;
		this.requestType = requestType;
	}

	@Override
	public String toString() {
		return "RequestTracker [empId=" + empId + ", serverIp=" + serverIp + ", date=" + date + ", domain=" + domain
				+ ", category=" + category + ", requestType=" + requestType + "]";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(nullable = false)
	private String domain;
	private String category;

	public String getDomain() {
		return domain;
	}

	public String getCategory() {
		return category;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	private String requestType;

	public int getEmpId() {
		return empId;
	}

	public String getServerIp() {
		return serverIp;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}

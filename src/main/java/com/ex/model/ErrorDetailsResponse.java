package com.ex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ErrorDetailsResponse {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int SrNo;
	public ErrorDetailsResponse() {
		
	}
	public int getSrNo() {
		return SrNo;
	}
	public void setSrNo(int srNo) {
		SrNo = srNo;
	}
	private String errorDetails;
	
	private String server;
	public String getErrorDetails() {
		return errorDetails;
	}
	public String getServer() {
		return server;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public void setServer(String server) {
		this.server = server;
	}
	@Override
	public String toString() {
		return "ErrorDetails [SrNo=" + SrNo + ", errorDetails=" + errorDetails + ", server=" + server + "]";
	}
	public ErrorDetailsResponse(int srNo, String errorDetails, String server) {
		super();
		SrNo = srNo;
		this.errorDetails = errorDetails;
		this.server = server;
	}
	

}

package com.kunal.updateCustomerKyc;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Customer {
	@PrimaryKey
	private String aadhar;
	private String username;
    private boolean kycstatus;
	
	@Override
	public String toString() {
		return "Customer [aadhar=" + aadhar + ", username=" + username + ", kycstatus=" + kycstatus + "]";
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isKycstatus() {
		return kycstatus;
	}
	public void setKycstatus(boolean kycstatus) {
		this.kycstatus = kycstatus;
	}
	
	public Customer(String aadhar, String username, boolean kycstatus) {
		super();
		this.aadhar = aadhar;
		this.username = username;
		this.kycstatus = kycstatus;
	}
	public Customer() {
		super();
	}
	
	

}

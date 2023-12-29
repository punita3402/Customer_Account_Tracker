package com.bank.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int phoneNo;
	private String emailId;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Account> account;

	public Customer(int id, String name, int phoneNo, String emailId, Set<Account> account) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
		this.account = account;
	}

	public Customer(int id, String name, int phoneNo, String emailId) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
	}

	public Customer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Set<Account> getAccount() {
		return account;
	}

	public void setAccount(Set<Account> account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phoneNo=" + phoneNo + ", emailId=" + emailId + ", account="
				+ account + "]";
	}
	
	
}

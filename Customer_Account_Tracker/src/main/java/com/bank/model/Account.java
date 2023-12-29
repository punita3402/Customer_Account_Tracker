package com.bank.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accId;
	private int accNo;
	private String accType;
	private double balance;
	
	@ManyToOne
	private Customer cutomer;

	public Account(int accId, int accNo, String accType, double balance, Customer cutomer) {
		super();
		this.accId = accId;
		this.accNo = accNo;
		this.accType = accType;
		this.balance = balance;
		this.cutomer = cutomer;
	}

	public Account(int accId, int accNo, String accType, double balance) {
		super();
		this.accId = accId;
		this.accNo = accNo;
		this.accType = accType;
		this.balance = balance;
	}

	public Account() {
		super();
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCutomer() {
		return cutomer;
	}

	public void setCutomer(Customer cutomer) {
		this.cutomer = cutomer;
	}

	@Override
	public String toString() {
		return "Account [accId=" + accId + ", accNo=" + accNo + ", accType=" + accType + ", balance=" + balance
				+ ", cutomer=" + cutomer + "]";
	}
	
}

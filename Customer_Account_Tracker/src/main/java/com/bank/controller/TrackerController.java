package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.exceptions.AccountNotFound;
import com.bank.exceptions.CustomerNotFound;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.service.AccountService;
import com.bank.service.CustomerService;

@RestController
public class TrackerController {
	
	@Autowired
	CustomerService custService;
	
	@Autowired
	AccountService accService;
	
	
	//Customer
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
		Customer cust = custService.saveOrUpdate(customer);
		return new ResponseEntity<Customer>(cust,HttpStatus.CREATED);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerNotFound {
		List<Customer> cs = custService.getAllCustomers();
		if(cs != null) {
			return new ResponseEntity<>(cs,HttpStatus.OK);
		}
		else {
			throw new CustomerNotFound("No customer is added yet");
		}
	}
	
	@GetMapping("customer/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") int id) throws CustomerNotFound{
		Customer cust = custService.getCustomerById(id);
		if(cust != null) {
			return new ResponseEntity<>(cust, HttpStatus.OK);
		}
		else {
			throw new CustomerNotFound("Customer with id "+id+" not found!!");
		}	
	}
	
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable("id") int id){
		custService.deleteById(id);
		return new ResponseEntity<>("Customer with id "+id+" deleted successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/customer")
	public ResponseEntity<String> deleteAll(){
		custService.deleteAll();
		return new ResponseEntity<>("All customers deleted successfully", HttpStatus.OK);
	}
	
	@PutMapping("customer/{id}")
	public ResponseEntity<Customer> update(@PathVariable("id") int id, @RequestBody Customer customer){
		Customer cust = custService.updateCustomer(id, customer);
		return new ResponseEntity<Customer>(cust, HttpStatus.ACCEPTED);
	}
	
	
	//Accounts
	
	
	@PostMapping("/account")
	public ResponseEntity<Account> saveAccount(@RequestBody Account account){
		Account acc = accService.saveOrUpdate(account);
		return new ResponseEntity<Account>(acc,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/account")
	public ResponseEntity<List<Account>> getAllAccounts() throws AccountNotFound {
		List<Account> ac = accService.getAllAccounts();
		if(ac != null) {
			return new ResponseEntity<>(ac,HttpStatus.OK);
		}
		else {
			throw new AccountNotFound("No Account is added yet");
		}
	}
	
	@GetMapping("account/{accid}")
	public ResponseEntity<?> getAccountById(@PathVariable("accid") int accId) throws AccountNotFound{
		Account acc = accService.getAccountByid(accId);
		if(acc != null) {
			return new ResponseEntity<>(acc, HttpStatus.OK);
		}
		else {
			throw new AccountNotFound("Account with id "+accId+" not found!!");
		}	
	}
	
	@DeleteMapping("/account/{accid}")
	public ResponseEntity<String> deleteAccountById(@PathVariable("accid") int accId){
		custService.deleteById(accId);
		return new ResponseEntity<>("Account with id "+accId+" deleted successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/account")
	public ResponseEntity<String> deleteAccounts(){
		accService.deleteAllAccount();
		return new ResponseEntity<>("All accounts deleted successfully", HttpStatus.OK);
	}
	
	@PutMapping("account/{accid}")
	public ResponseEntity<Account> update(@PathVariable("accid") int accId, @RequestBody Account account){
		Account acc = accService.updateAccount(accId, account);
		return new ResponseEntity<Account>(acc, HttpStatus.ACCEPTED);
	}
	
	//Fund Transfer
	
	@GetMapping("/fundtransfer/{from}/{to}/{amount}")
	public String transferFund(@PathVariable("from") int from, 
			@PathVariable("to") int to, @PathVariable("amount") double amount) throws AccountNotFound{
		Account acc1 = accService.getAccountByid(from);
		Account acc2 = accService.getAccountByid(to);
		if(acc1 == null || acc2 == null) {
			throw new AccountNotFound("Account not found");
		}
		else {
			return accService.tranferFunds(from, to, amount);
		}
	}
}

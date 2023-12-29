package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.Customer;
import com.bank.repository.AccountRepository;
import com.bank.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	
	public List<Customer> getAllCustomers(){
		List<Customer> customers = new ArrayList<Customer>();
		customerRepository.findAll().forEach(customers1 -> customers.add(customers1));
		if(customers.isEmpty()) {
			return null;
		}
		else {
			return customers;
		}
	}
	
	
	public Customer getCustomerById(int id) {
		Optional<Customer> cus = customerRepository.findById(id);
		if(cus.isPresent()) {
			return customerRepository.findById(id).get();
		}
		else {
			return null;
		}
	}
	
	
	public Customer saveOrUpdate(Customer customer) {
		return customerRepository.save(customer);
	}
	
	
	public String deleteById(int id) {
		Optional<Customer> cus = customerRepository.findById(id);
		if(cus.isPresent()) {
			customerRepository.deleteById(id);
			return "Customer with Id "+id+" is deleted Successfully";
		}
		else {
			return null;
		}
	}
	
	
	public String deleteAll() {
		List<Customer> cus = new ArrayList<Customer>();
		cus = (List<Customer>)(customerRepository.findAll());
		if(!(cus.isEmpty())) {
			customerRepository.deleteAll();
			return "All customer data has been deleted";
		}
		return null;
	}
	
	
	public Customer updateCustomer(int id, Customer customer) {
		Optional<Customer> cus = customerRepository.findById(id);
		Customer cust = cus.get();
		cust.setName(customer.getName());
		cust.setPhoneNo(customer.getPhoneNo());
		cust.setEmailId(customer.getEmailId());
		customerRepository.save(cust);
		return cust;
	}
}

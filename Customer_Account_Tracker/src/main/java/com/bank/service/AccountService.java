package com.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import com.bank.repository.CustomerRepository;

@Service
public class AccountService {
	
	@Autowired
	public AccountRepository accountRepository;
	
	@Autowired
	public CustomerRepository customerRepository;
	
	
	public List<Account> getAllAccounts(){
		List<Account> account = accountRepository.findAll();
		if(account.isEmpty()) {
			return null;
		}
		else {
			return (List<Account>)accountRepository.findAll();
		}
	}
	
	
	public Account getAccountByid(int accId) {
		Optional<Account> acc = accountRepository.findById(accId);
		if(acc.isPresent()) {
			return accountRepository.findById(accId).get();
		}
		else {
			return null;
		}
	}
	
	
	public Account updateAccount(int accId, Account account) {
		Optional<Account> acc = accountRepository.findById(accId);
		if(acc.isPresent()) {
			Account updateAcc = acc.get();
			updateAcc.setAccId(account.getAccId());
			updateAcc.setAccNo(account.getAccNo());
			updateAcc.setAccType(account.getAccType());
			updateAcc.setBalance(account.getBalance());
			
			accountRepository.save(updateAcc);
			return updateAcc;
		}
		return null;
	}
	
	
	public String deleteAccountById(int accId) {
		Optional<Account> acc = accountRepository.findById(accId);
		if(acc.isPresent()) {
			accountRepository.deleteById(accId);
			return "Account with Id "+accId+" is deleted!!"; 
		}
		else {
			return "Account with Id " +accId+ " is not present!!";
		}
	}
	
	
	public String deleteAllAccount() {
		accountRepository.deleteAll();
		return "All Accounts deleted Successfully!!";
	}
	
	
	public String tranferFunds(int from, int to, double amount) {
		Optional<Account> acc1 = accountRepository.findById(from);
		Optional<Account> acc2 = accountRepository.findById(to);
		
		if(from == to) {
			return "Invalid Transaction";
		}
		if(amount<0) {
			return "Invalid Amount";
		}
		if(acc1.isEmpty()) {
			return null;
		}
		if(acc2.isEmpty()) {
			return null;
		}
		
		if(acc1.get().getBalance( )> amount) {
			acc1.get().setBalance(acc1.get().getBalance()-(amount));
			acc2.get().setBalance(acc2.get().getBalance()+(amount));
			accountRepository.save(acc1.get());
			accountRepository.save(acc2.get());
			return "Amount is Transfered successfully from Account Id "
			+acc1.get().getAccId()+" to Acount Id "+acc2.get().getAccId();
		}
		else {
			return "Insufficient Balance!!";
		}
	}
	
	public Account saveOrUpdate(Account account) {
		return accountRepository.save(account);
	}
}

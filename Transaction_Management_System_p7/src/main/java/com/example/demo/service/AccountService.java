package com.example.demo.service;
import com.example.demo.entity.Account;
public interface AccountService {
	
	void  transferFunds(int fromId,int toId,double amount);
	
	Account getAccount(int id);
	
	Account updateAccountHolder(int id, String accountHolder);
	void deleteAccount(int id);

}

package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.exception.InsufficientBalanceException;
import com.example.demo.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService{
	
	private final AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	@Transactional
	public void transferFunds(int fromId,int toId,double amount)
	{
		 // Step 2: Fetch source account
		Account fromAccount=accountRepository.findById(fromId)
			.orElseThrow(()-> new RuntimeException("Source account not found"));
		
		// Step 3: Check balance
		if(fromAccount.getBalance()<amount)
		{
			throw new InsufficientBalanceException("Insufficient balance");
		}
		 // Step 4: Debit source account
		fromAccount.setBalance(fromAccount.getBalance()-amount);
		
		
		// Step 5: Fetch destination account
		Account toAccount=accountRepository.findById(toId)
				.orElseThrow(()->new RuntimeException("Destination account not found"));
		
		 // Step 6: Credit destination
		toAccount.setBalance(toAccount.getBalance()+amount);
		
		 // Step 7: Save both
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		// Deliberately throw exception to test rollback
		throw new InsufficientBalanceException("Testing transaction rollback");
	}
	
	@Override
	public Account getAccount(int id) {

	    return accountRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Account not found"));
	}
	
	@Override
	public Account updateAccountHolder(int id, String accountHolder) {

	    Account account = accountRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Account not found"));

	    account.setAccountHolder(accountHolder);

	    return accountRepository.save(account);
	}
	
	@Override
	public void deleteAccount(int id) {

	    Account account = accountRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Account not found"));

	    if (account.getBalance() != 0) {
	        throw new RuntimeException("Account balance must be zero to delete");
	    }

	    accountRepository.delete(account);
	}
	
}

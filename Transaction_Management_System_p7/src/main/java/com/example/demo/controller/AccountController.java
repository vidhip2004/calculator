package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.TransferRequest.TransferRequest;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
		private AccountService accountService;
		
		
		public AccountController(AccountService accountService) {
			super();
			this.accountService = accountService;
		}

		@PostMapping("/transfer")
		public void transfer(@RequestBody TransferRequest transferRequest) {

		    accountService.transferFunds(
		            transferRequest.getFromId(),
		            transferRequest.getToId(),
		            transferRequest.getAmount()
		    );
		}
		
		@GetMapping("/{id}")
		public Account getAccount(@PathVariable int id)
		{
			return accountService.getAccount(id);
		}
		
		@PutMapping("/{id}")
		public Account updateAccount(
		        @PathVariable int id,
		        @RequestBody Account account) {

		    return accountService.updateAccountHolder(
		            id,
		            account.getAccountHolder()
		    );
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteAccount(@PathVariable int id) {

		    accountService.deleteAccount(id);

		    return ResponseEntity.noContent().build();
		}
}

package com.jumpstartbackone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpstartbackone.entity.Transactions;
import com.jumpstartbackone.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public Transactions saveTransaction(Transactions transaction) {
        return transactionRepository.save(transaction);
    }
}

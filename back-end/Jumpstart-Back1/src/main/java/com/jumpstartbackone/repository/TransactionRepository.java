package com.jumpstartbackone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpstartbackone.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Long>{

}

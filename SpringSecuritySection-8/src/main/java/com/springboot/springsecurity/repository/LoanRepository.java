package com.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.springsecurity.entity.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans,Long> {
	
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
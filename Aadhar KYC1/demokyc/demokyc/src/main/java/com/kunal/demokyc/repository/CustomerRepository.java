package com.kunal.demokyc.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.kunal.demokyc.entity.Customer;

public interface CustomerRepository  extends CassandraRepository<Customer, String>{


	Customer save(Customer customer);

	Iterable<Customer> findByAadharContaining(String aadhar);



}

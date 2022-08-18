package com.kunal.updateCustomerKyc;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface CustomerRepository extends CassandraRepository<Customer, String>{


	Customer save(Customer customer);

	Iterable<Customer> findByAadharContaining(String aadhar);


}


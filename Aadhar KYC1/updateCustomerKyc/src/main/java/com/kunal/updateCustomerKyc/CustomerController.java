package com.kunal.updateCustomerKyc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	
	 
	

	 
	@PutMapping("/verifyKyc/{aadhar}")
	 public ResponseEntity<Object> updateCustomer(@PathVariable("aadhar") String aadhar, @RequestBody Customer customer) {
	  
		 Optional<Customer> customerData = customerRepository.findById(aadhar);
	 
		 if (customerData.isPresent()) {
	    
			 Customer _customer = customerData.get();
	     _customer.setAadhar(customer.getAadhar());
	     _customer.setUsername(customer.getUsername());
	     _customer.setKycstatus(customer.isKycstatus());
	     customerRepository.save(_customer);
	     KycResponse kycRes = new KycResponse();

	    kycRes.setStatus("0");
	    kycRes.setStatusDesc("KYC Done!");
	     return new ResponseEntity<Object>(kycRes, HttpStatus.OK);
	   } else {
		   KycResponse kycRes = new KycResponse();

	       kycRes.setStatus("-1");
	       kycRes.setStatusDesc("KYC not completed ");
	       
	     return new ResponseEntity<>(kycRes,HttpStatus.NOT_FOUND);
	   }
	 
}
}

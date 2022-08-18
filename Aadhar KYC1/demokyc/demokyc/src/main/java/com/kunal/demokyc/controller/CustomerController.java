package com.kunal.demokyc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kunal.demokyc.entity.Customer;
import com.kunal.demokyc.entity.KycResponse;
import com.kunal.demokyc.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:8087")
@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	 @PostMapping("/add-customer")
	  public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
	    try {
	      Customer _customer = customerRepository.save( new Customer(customer.getAadhar(), customer.getUsername(), false));
	      KycResponse kycRes = new KycResponse();

	    	kycRes.setStatus("0");
	    	kycRes.setStatusDesc("User created Successfully");
	      
	      return new ResponseEntity<>(kycRes, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	
	    	KycResponse kycRes = new KycResponse();

	    	kycRes.setStatus("-1");
	    	kycRes.setStatusDesc("User not created");
	    	return new ResponseEntity<>(kycRes, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 
	 @GetMapping("/customers/{aadhar}")
	  public ResponseEntity<Customer> getTutorialById(@PathVariable("aadhar") String aadhar) {
	   
		 Optional<Customer> customerData = customerRepository.findById(aadhar);

	    if (customerData.isPresent()) {
	      return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
//	 @GetMapping("/customers")
//	 public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String aadhar) {
//	   try {
//	     List<Customer> customers = new ArrayList<Customer>();
//	     if (aadhar == null)
//	       customerRepository.findAll().forEach(customers::add);
//	     else
//	       customerRepository.findByAadharContaining(aadhar).forEach(customers::add);
//	     if (customers.isEmpty()) {
//	       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	     }
//	     return new ResponseEntity<>(customers, HttpStatus.OK);
//	   } catch (Exception e) {
//	     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//	   }
//	 }

	 
//	 @PutMapping("/verifyKyc/{aadhar}")
//	 public ResponseEntity<Object> updateCustomer(@PathVariable("aadhar") String aadhar, @RequestBody Customer customer) {
//	  
//		 Optional<Customer> customerData = customerRepository.findById(aadhar);
//	 
//		 if (customerData.isPresent()) {
//	    
//			 Customer _customer = customerData.get();
//	     _customer.setAadhar(customer.getAadhar());
//	     _customer.setUsername(customer.getUsername());
//	     _customer.setKycstatus(customer.isKycstatus());
//	     
//	     KycResponse kycRes = new KycResponse();
//
//	    kycRes.setStatus("0");
//	    kycRes.setStatusDesc("KYC Done!");
//	     return new ResponseEntity<>(kycRes, HttpStatus.OK);
//	   } else {
//		   KycResponse kycRes = new KycResponse();
//
//	       kycRes.setStatus("-1");
//	       kycRes.setStatusDesc("KYC not completed ");
//	       
//	     return new ResponseEntity<>(kycRes,HttpStatus.NOT_FOUND);
//	   }
//	 }
}

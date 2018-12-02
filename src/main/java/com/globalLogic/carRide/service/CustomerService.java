package com.globalLogic.carRide.service;

import com.globalLogic.carRide.dto.CustomerDto;
import com.globalLogic.carRide.model.Customer;
import com.globalLogic.carRide.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public String registerCustomer(CustomerDto customerDto) {
        Customer customer = setCustomer(customerDto);
        Customer persistedCustomer = customerRepo.save(customer);
        return persistedCustomer.getCid();
    }

    private Customer setCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNo(Long.parseLong(customerDto.getPhoneNo()));
        customer.setCid();
        return customer;
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
}

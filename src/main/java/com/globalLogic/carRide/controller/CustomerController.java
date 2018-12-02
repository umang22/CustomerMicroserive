package com.globalLogic.carRide.controller;

import com.globalLogic.carRide.dto.CustomerDto;
import com.globalLogic.carRide.model.Customer;
import com.globalLogic.carRide.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    // TODO:create a servbice layer amd movev the businmess logic over therer
    @Autowired
    CustomerService customerService;

    @GetMapping("/getCustomer")
    public List<Customer> getAllCustomer() {
        return customerService.findAll();
    }

    @PostMapping(value = "/register")
    public String registerCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.registerCustomer(customerDto);
    }

}
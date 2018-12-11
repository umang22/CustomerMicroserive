package com.globalLogic.carRide.controller;

import com.globalLogic.carRide.dto.CustomerDto;
import com.globalLogic.carRide.model.BookingEntity;
import com.globalLogic.carRide.model.CustomerEntity;
import com.globalLogic.carRide.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //To fetch all the customers
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<CustomerEntity> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    //To fetch a customer by id
    @RequestMapping(value = "/{cid}",method = RequestMethod.GET)
    public Optional<CustomerEntity> getCustomer(@PathVariable("cid") String id) {
        return customerService.findCustomerByCid(id);
    }

    //To cancel a ride (we have to give customerid and booking id in the URI)
    @RequestMapping(value = "/{cid}/cancel/{bId}",method = RequestMethod.POST)
    public void cancelBooking(@PathVariable("bId") String bookingId, @PathVariable("cid") String customerId) {
        customerService.cancelBooking(bookingId, customerId);
    }

    //To register a new customer
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.registerCustomer(customerDto);
    }

    //To get the history of booking of the given cutomer
    @RequestMapping(value = "/{cid}/history",method = RequestMethod.GET)
    public List<BookingEntity> customerBookings(@PathVariable("cid") String customerId) {
        return customerService.customerBookings(customerId);
    }

}
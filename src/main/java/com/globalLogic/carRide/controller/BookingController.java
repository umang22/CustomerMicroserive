package com.globalLogic.carRide.controller;

import com.globalLogic.carRide.dto.BookingDto;
import com.globalLogic.carRide.model.BookingEntity;
import com.globalLogic.carRide.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    //To book a ride
    @RequestMapping(value = "/{cId}", method = RequestMethod.POST)
    public String addBooking(@PathVariable("cId") String customerId, @RequestBody BookingDto bookingDto) {
        bookingService.bookCab(customerId, bookingDto);
        return "Cab booked";
    }
}

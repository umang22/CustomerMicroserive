package com.globalLogic.carRide.controller;

import com.globalLogic.carRide.dto.BookingDto;
import com.globalLogic.carRide.model.Booking;
import com.globalLogic.carRide.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService booking;

    @RequestMapping(value = "/{cId}", method = RequestMethod.POST)
    public Booking addBooking(@PathVariable("cId") String customerId, @RequestBody BookingDto bookingDto) {

        booking.bookCab(customerId, bookingDto);
        return null;
    }
}

package com.globalLogic.carRide.service;

import com.globalLogic.carRide.dto.BookingDto;
import com.globalLogic.carRide.dto.BookingStatus;
import com.globalLogic.carRide.model.Booking;
import com.globalLogic.carRide.model.Customer;
import com.globalLogic.carRide.repository.BookingRepo;
import com.globalLogic.carRide.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    CustomerRepo customerRepo;

    public void bookCab(String customerId, BookingDto bookingDto) {
        Customer customerPersisted = customerRepo.findByCid(customerId);
        Booking booking = setBooking(customerPersisted);
        bookingRepo.save(booking);
    }

    private Booking setBooking(Customer customerPersisted) {
        Booking booking = new Booking();
        booking.setCustomer(customerPersisted);
        booking.setStatus(BookingStatus.BOOKED);
        booking.setBooking_time(String.valueOf(LocalDateTime.now()));
        return booking;
    }

}

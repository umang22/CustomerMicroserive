package com.globalLogic.carRide.service;

import com.globalLogic.carRide.dto.BookingDto;
import com.globalLogic.carRide.dto.BookingStatus;
import com.globalLogic.carRide.model.BookingEntity;
import com.globalLogic.carRide.model.CustomerEntity;
import com.globalLogic.carRide.repository.BookingRepo;
import com.globalLogic.carRide.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
   private CustomerRepo customerRepo;

    public String bookCab(String customerId, BookingDto bookingDto) {
        Optional<CustomerEntity> persistedCustomer = customerRepo.findByCid(customerId);
        BookingEntity bookingEntity = setBooking(persistedCustomer.get(),bookingDto);
        bookingRepo.save(bookingEntity);
        BookingStatus status = bookingEntity.getStatus();
        return String.valueOf(status);
    }

    private BookingEntity setBooking(CustomerEntity customerEntityPersisted,BookingDto bookingDto) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setCustomerEntity(customerEntityPersisted);
        bookingEntity.setStatus(BookingStatus.BOOKED);
        bookingEntity.setBooking_time(LocalDateTime.now());
        bookingEntity.setBid();
        bookingEntity.setCabType(bookingDto.getCabType());
        bookingEntity.setStartAddress(bookingDto.getStartAddress());
        bookingEntity.setDestination(bookingDto.getDestination());
        return bookingEntity;
    }
}

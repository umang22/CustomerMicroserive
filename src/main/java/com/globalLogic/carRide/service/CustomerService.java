package com.globalLogic.carRide.service;

import com.globalLogic.carRide.dto.BookingStatus;
import com.globalLogic.carRide.dto.CustomerDto;
import com.globalLogic.carRide.model.BookingEntity;
import com.globalLogic.carRide.model.CustomerEntity;
import com.globalLogic.carRide.repository.BookingRepo;
import com.globalLogic.carRide.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private BookingRepo bookingRepo;
    private static final int FIVE_MINUTES = 5 * 60;


    //To find the all the customers
    public List<CustomerEntity> getAllCustomer() {
        return customerRepo.findAll();
    }

    //To fetch a customer by id
    public Optional<CustomerEntity> findCustomerByCid(String id) {
        return customerRepo.findByCid(id);
    }

    //To cancel a booking by a customer
    public void cancelBooking(String bookingId, String customerId) {
        Optional<CustomerEntity> customerById = findCustomerByCid(customerId);
        if (customerById.isPresent()) {
            BookingEntity persistedBookingEntity;
            CustomerEntity customerEntity = customerById.get();
            List<BookingEntity> bookingEntityList = customerEntity.getBookingEntityList();
            Optional<BookingEntity> booking = bookingEntityList.stream().filter(b -> b.getBid().equalsIgnoreCase(bookingId)).findFirst();
            if (booking.isPresent()) {
                persistedBookingEntity = booking.get();
                LocalDateTime currentDateTime = LocalDateTime.now();
                LocalDateTime booking_time = persistedBookingEntity.getBooking_time();
                Duration duration = Duration.between(currentDateTime, booking_time);
                persistedBookingEntity = calculateTimeDifference(persistedBookingEntity, duration);
                bookingRepo.save(persistedBookingEntity);
            }
        }
    }

    private BookingEntity calculateTimeDifference(BookingEntity persistedBookingEntity, Duration duration) throws RuntimeException {
        if (Long.compare(duration.getSeconds(), FIVE_MINUTES) > 0) {
            throw new RuntimeException("searchTimestamp is older than 5 minutes");
        } else {
            persistedBookingEntity.setStatus(BookingStatus.CANCELLED);
            return persistedBookingEntity;
        }
    }

    //To register a customer details for first time (sign up )
    public String registerCustomer(CustomerDto customerDto) {
        CustomerEntity customerEntity = setCustomer(customerDto);
        CustomerEntity persistedCustomerEntity = customerRepo.save(customerEntity);
        return persistedCustomerEntity.getCid();
    }

    //To set the details of a customer for registeration
    private CustomerEntity setCustomer(@RequestBody CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerDto.getFirstName());
        customerEntity.setLastName(customerDto.getLastName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setPhoneNo(Long.parseLong(customerDto.getPhoneNo()));
        customerEntity.setCid();
        return customerEntity;
    }

    public List<BookingEntity> customerBookings(String customerId) {
        Optional<CustomerEntity> customerById = findCustomerByCid(customerId);
        List<BookingEntity> bookingEntityList = null;
        if (customerById.isPresent()) {
            bookingEntityList = customerById.get().getBookingEntityList();
            if (bookingEntityList.isEmpty()) {
                System.out.println("No booking done by this customer");
            }
        }
        return bookingEntityList;
    }
}

package com.globalLogic.carRide.service;

import com.globalLogic.carRide.dto.BookingDto;
import com.globalLogic.carRide.dto.BookingStatus;
import com.globalLogic.carRide.model.BookingEntity;
import com.globalLogic.carRide.model.CustomerEntity;
import com.globalLogic.carRide.repository.BookingRepo;
import com.globalLogic.carRide.repository.CustomerRepo;
import com.globalLogic.carRide.strategy.CalculationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private CustomerRepo customerRepo;

    /**
     * Below map is the data structure which holds static data of destinations and distances.
     */
    static Map<String, Map<String, Integer>> distances = new HashMap<>();
    {
        Map<String, Integer> destination = new HashMap<>();
        destination.put("ghaziabad", 10);
        destination.put("sharanpur", 100);
        destination.put("seohara", 1000);
        distances.put("delhi", destination);
    }

    /**
     * This ,model calculates fare based upon peak hours fare=distance*20.
     */
    private static CalculationModel peakHourCalculationModel = (currentLocation, destination) -> {
        Integer distance = distances.get(currentLocation).get(destination);
        return new BigInteger(String.valueOf(distance * 20)) ;
    };

    /**
     * This ,model calculates fare based upon non peak hours fare=distance*10.
     */
    private static CalculationModel nonPeakHourCalculationModel = (currentLocation, destination) -> {
        Integer distance = distances.get(currentLocation).get(destination);
        return new BigInteger(String.valueOf(distance * 10)) ;
    };


    public String bookCab(String customerId, BookingDto bookingDto) {
        Optional<CustomerEntity> persistedCustomer = customerRepo.findByCid(customerId);
        BookingEntity bookingEntity = setBooking(persistedCustomer.get(),bookingDto);
        bookingRepo.save(bookingEntity);
        BookingStatus status = bookingEntity.getStatus();
        return String.valueOf(status);
    }

    private BookingEntity setBooking(CustomerEntity customerEntityPersisted,BookingDto bookingDto) {
        BigInteger calculatedFare;
        BookingEntity bookingEntity = new BookingEntity();
        LocalDateTime now = initializeBasicBooking(customerEntityPersisted, bookingDto, bookingEntity);
        // Below logic is for calculation of fare.
        //We have two fare calculation models.
        calculatedFare = applyfarecalulationmodel(bookingDto, now);
        bookingEntity.setFare(calculatedFare);
        return bookingEntity;
    }

    /**
     * Below Method applies the logic for calculation of fare of a booked ride.
     * @param bookingDto
     * @param now
     * @return
     */
    private BigInteger applyfarecalulationmodel(BookingDto bookingDto, LocalDateTime now) {
        BigInteger calculatedFare;
        if(now.isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.of(9,0)))
                && now.isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.of(12,0))))
            calculatedFare = peakHourCalculationModel.calculateFare(bookingDto.getStartAddress(), bookingDto.getDestination());
        else
            calculatedFare=nonPeakHourCalculationModel.calculateFare(bookingDto.getStartAddress(), bookingDto.getDestination());
        return calculatedFare;
    }

    private LocalDateTime initializeBasicBooking(CustomerEntity customerEntityPersisted, BookingDto bookingDto, BookingEntity bookingEntity) {
        bookingEntity.setCustomerEntity(customerEntityPersisted);
        bookingEntity.setStatus(BookingStatus.BOOKED);
        LocalDateTime now = LocalDateTime.now();
        bookingEntity.setBooking_time(now);
        bookingEntity.setBid();
        bookingEntity.setCabType(bookingDto.getCabType());
        bookingEntity.setStartAddress(bookingDto.getStartAddress());
        bookingEntity.setDestination(bookingDto.getDestination());
        return now;
    }
}

package com.globalLogic.carRide.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.globalLogic.carRide.dto.BookingStatus;
import org.apache.commons.lang.RandomStringUtils;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "Booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_time")
    private LocalDateTime booking_time;

    private String bid;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private BigInteger fare;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid", referencedColumnName = "id")
    @JsonBackReference
    private CustomerEntity customerEntity;


    private String cabType;

    private String startAddress;

    private String destination;

    public BigInteger getFare() {
        return fare;
    }

    public void setFare(BigInteger fare) {
        this.fare = fare;
    }

    public String getCabType() {
        return cabType;
    }

    public void setCabType(String cabType) {
        this.cabType = cabType;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(LocalDateTime booking_time) {
        this.booking_time = booking_time;
    }

    public String getBid() {
        return bid;
    }

    public void setBid() {
        this.bid = RandomStringUtils.randomAlphanumeric(10);
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public BookingEntity() {
    }
}

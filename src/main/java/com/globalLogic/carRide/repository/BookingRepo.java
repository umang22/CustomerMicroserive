package com.globalLogic.carRide.repository;

import com.globalLogic.carRide.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, String> {

}

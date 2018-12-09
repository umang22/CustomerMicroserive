package com.globalLogic.carRide.repository;

import com.globalLogic.carRide.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByCid(String cId);

}

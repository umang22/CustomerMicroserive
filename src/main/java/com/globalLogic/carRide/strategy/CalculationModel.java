package com.globalLogic.carRide.strategy;

import java.math.BigInteger;

/**
 * Parent interface for calculating fares.
 */
@FunctionalInterface
public interface CalculationModel {

    BigInteger calculateFare(String currentLocation,String destination);
}

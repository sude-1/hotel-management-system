
package com.hotel.hotel_service.strategy;

import com.hotel.hotel_service.entity.Room;

public class SeasonalPricingStrategy implements PricingStrategy {

    private static final double SEASONAL_MULTIPLIER = 1.20;

    @Override
    public double calculatePrice(Room room, int numberOfNights) {

        if (numberOfNights <= 0) {
            throw new IllegalArgumentException(
                "Number of nights must be greater than zero"
            );
        }

        double standardPrice =
            room.getPricePerNight() * numberOfNights;

        return standardPrice * SEASONAL_MULTIPLIER;
    }
}

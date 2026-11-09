
package com.hotel.hotel_service.strategy;

import com.hotel.hotel_service.entity.Room;

public class StandardPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(Room room, int numberOfNights) {

        if (numberOfNights <= 0) {
            throw new IllegalArgumentException(
                "Number of nights must be greater than zero"
            );
        }

        return room.getPricePerNight() * numberOfNights;
    }
}


package com.hotel.hotel_service.strategy;

import com.hotel.hotel_service.entity.Room;

public interface PricingStrategy {

    double calculatePrice(Room room, int numberOfNights);

}

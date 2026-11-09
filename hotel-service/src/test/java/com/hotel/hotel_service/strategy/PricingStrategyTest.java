
package com.hotel.hotel_service.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.hotel.hotel_service.entity.Room;

class PricingStrategyTest {

    @Test
    void standardPricingShouldCalculateCorrectTotal() {
        Room room = new Room("101", "Standard", 100.0, true);

        PricingStrategy strategy = new StandardPricingStrategy();

        double result = strategy.calculatePrice(room, 3);

        assertEquals(300.0, result);
    }

    @Test
    void seasonalPricingShouldApplyTwentyPercentIncrease() {
        Room room = new Room("102", "Deluxe", 100.0, true);

        PricingStrategy strategy = new SeasonalPricingStrategy();

        double result = strategy.calculatePrice(room, 3);

        assertEquals(360.0, result);
    }

    @Test
    void pricingShouldRejectInvalidNumberOfNights() {
        Room room = new Room("103", "Standard", 100.0, true);

        PricingStrategy strategy = new StandardPricingStrategy();

        assertThrows(
            IllegalArgumentException.class,
            () -> strategy.calculatePrice(room, 0)
        );
    }
}

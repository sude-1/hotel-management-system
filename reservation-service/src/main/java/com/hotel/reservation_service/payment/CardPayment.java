
package com.hotel.reservation_service.payment;

public class CardPayment implements Payment {

    @Override
    public boolean processPayment(double amount) {

        if (amount <= 0) {
            return false;
        }

        System.out.println(
            "Card payment processed successfully. Amount: " + amount
        );

        return true;
    }
}

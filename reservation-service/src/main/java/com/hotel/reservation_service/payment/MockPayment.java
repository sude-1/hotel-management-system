
package com.hotel.reservation_service.payment;

public class MockPayment implements Payment {

    private final boolean paymentSuccessful;

    public MockPayment(boolean paymentSuccessful) {
        this.paymentSuccessful = paymentSuccessful;
    }

    @Override
    public boolean processPayment(double amount) {

        if (amount <= 0) {
            return false;
        }

        if (paymentSuccessful) {
            System.out.println(
                "Mock payment successful. Amount: " + amount
            );
            return true;
        }

        System.out.println("Mock payment failed.");
        return false;
    }
}

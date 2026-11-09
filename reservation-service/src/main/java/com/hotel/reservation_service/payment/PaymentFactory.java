
package com.hotel.reservation_service.payment;

public class PaymentFactory {

    public static Payment createPayment(String paymentType) {

        switch (paymentType.toUpperCase()) {

            case "CARD":
                return new CardPayment();

            case "MOCK_SUCCESS":
                return new MockPayment(true);

            case "MOCK_FAILURE":
                return new MockPayment(false);

            default:
                throw new IllegalArgumentException(
                    "Invalid payment type: " + paymentType
                );
        }
    }
}

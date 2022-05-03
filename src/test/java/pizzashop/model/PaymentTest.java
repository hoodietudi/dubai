package pizzashop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTest {
    @Test
    public void testGetters() {
        Payment payment = new Payment(1, PaymentType.Cash, 50d);

        assertEquals(1, payment.getTableNumber());
        assertEquals(PaymentType.Cash, payment.getType());
        assertEquals(50d, payment.getAmount());
    }

    @Test
    public void testSetters() {
        Payment payment = new Payment(0, PaymentType.Card, 0d);

        payment.setTableNumber(1);
        payment.setType(PaymentType.Cash);
        payment.setAmount(50d);

        assertEquals(1, payment.getTableNumber());
        assertEquals(PaymentType.Cash, payment.getType());
        assertEquals(50d, payment.getAmount());

    }
}

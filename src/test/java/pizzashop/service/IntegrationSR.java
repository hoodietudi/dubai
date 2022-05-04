package pizzashop.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import static org.mockito.Mockito.mock;

class IntegrationSR {
    Payment payment;
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        PaymentRepository paymentRepository = new PaymentRepository();
        MenuRepository menuRepository = new MenuRepository();
        this.pizzaService = new PizzaService(menuRepository, paymentRepository);
        payment = mock(Payment.class);
    }

    @Test
    void addPayment() {
        Mockito.when(payment.getTableNumber()).thenReturn(1);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment.getAmount()).thenReturn(100d);
        try {
            pizzaService.addPayment(1, PaymentType.Cash, 100d);
            Payment insertedPayment = pizzaService.getPayments().get(0);
            assert insertedPayment.getTableNumber() == payment.getTableNumber();
            assert insertedPayment.getType() == payment.getType();
            assert insertedPayment.getAmount() == payment.getAmount();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTotalAmount() {

        Mockito.when(payment.getType()).thenReturn(PaymentType.Cash);
        payment.setType(PaymentType.Card);

        try {
            pizzaService.addPayment(1, payment.getType(), 100d);
            assert pizzaService.getTotalAmount(PaymentType.Card) == 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void Payment() {
        Mockito.doNothing().when(payment).setTableNumber(1);
        Mockito.doNothing().when(payment).setType(PaymentType.Card);
        Mockito.doNothing().when(payment).setAmount(100d);

        payment.setTableNumber(1);
        payment.setType(PaymentType.Card);
        payment.setAmount(100d);

        Mockito.verify(payment).setTableNumber(1);
        Mockito.verify(payment).setType(PaymentType.Card);
        Mockito.verify(payment).setAmount(100d);
    }
}


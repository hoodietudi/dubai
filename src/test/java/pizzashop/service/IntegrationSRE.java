package pizzashop.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
public class IntegrationSRE {
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        PaymentRepository paymentRepository = new PaymentRepository();
        MenuRepository menuRepository = new MenuRepository();
        this.pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    void addPayment() {

        try {
            pizzaService.addPayment(1, PaymentType.Cash, 100d);
            Payment insertedPayment = pizzaService.getPayments().get(0);
            assert insertedPayment.getTableNumber() == 1;
            assert insertedPayment.getType() == PaymentType.Cash;
            assert insertedPayment.getAmount() == 100d;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTotalAmount() {

        try {
            pizzaService.addPayment(1, PaymentType.Card, 100d);
            pizzaService.addPayment(2, PaymentType.Card, 50d);
            assert pizzaService.getTotalAmount(PaymentType.Cash) == 0d;
            assert pizzaService.getTotalAmount(PaymentType.Card) == 150d;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


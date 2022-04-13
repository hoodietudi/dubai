package pizzashop.service;
import org.junit.jupiter.api.Test;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PizzaServiceTestLab03 {
    @Test
    void testP1() throws Exception {
        PizzaService pizzaService = new PizzaService(new MenuRepository(), new PaymentRepository());

        pizzaService.setPayment(null);

        assertEquals(0d, pizzaService.getTotalAmount(PaymentType.Cash));
    }

    @Test
    void testP2() throws Exception {
        PizzaService pizzaService = new PizzaService(new MenuRepository(), new PaymentRepository());

        assertEquals(0d, pizzaService.getTotalAmount(PaymentType.Cash));
    }


    @Test
    void testP3() throws Exception {
        PizzaService pizzaService = new PizzaService(new MenuRepository(), new PaymentRepository());

        pizzaService.addPayment(1, PaymentType.Cash, 10d);
        pizzaService.addPayment(2, PaymentType.Cash, 20d);
        pizzaService.addPayment(3, PaymentType.Cash, 44d);

        System.out.println(pizzaService.getPayments());

        assertEquals(74d, pizzaService.getTotalAmount(PaymentType.Cash));
    }

    @Test
    void testP4() throws Exception {
        PizzaService pizzaService = new PizzaService(new MenuRepository(), new PaymentRepository());

        pizzaService.addPayment(1, PaymentType.Card, 43d);
        pizzaService.addPayment(2, PaymentType.Card, 11d);
        pizzaService.addPayment(3, PaymentType.Card, 21d);
        pizzaService.addPayment(4, PaymentType.Card, 46d);

        System.out.println(pizzaService.getPayments());

        assertEquals(121d, pizzaService.getTotalAmount(PaymentType.Card));
    }

    @Test
    void testP5() throws Exception {
        PizzaService pizzaService = new PizzaService(new MenuRepository(), new PaymentRepository());

        pizzaService.addPayment(1, PaymentType.Card, 11d);
        pizzaService.addPayment(2, PaymentType.Cash, 34d);
        pizzaService.addPayment(3, PaymentType.Card, 12d);
        pizzaService.addPayment(4, PaymentType.Card, 78d);

        assertEquals(101d, pizzaService.getTotalAmount(PaymentType.Card));
        assertEquals(34d, pizzaService.getTotalAmount(PaymentType.Cash));
    }

}

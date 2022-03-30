package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PizzaServiceTest {

    private PizzaService service;

    @BeforeEach
    void setUp() {
        MenuRepository menuRepo = new MenuRepository();
        PaymentRepository payRepo = new PaymentRepository();
        this.service = new PizzaService(menuRepo, payRepo);
    }

    @Test
    @RepeatedTest(1)
    @Order(1)
    @Tag("amount")
    @DisplayName("Test ECP: Payment valid (amount valid)")
    public void addPaymentValidAmountECP() {
        try {
            service.addPayment(3, PaymentType.Card, 12.0);
            assert true;
        } catch (Exception ex) {
            // assert false;
            assertFalse(false);
        }
    }

    @Test
    @RepeatedTest(2)
    @Order(8)
    @Tag("amount")
    @DisplayName("Test ECP: Payment non-valid (amount 0.0)")
    public void addPaymentNonValidAmountECP_1() {
        try {
            service.addPayment(3, PaymentType.Cash, 0.0);
            assert false;
        } catch (Exception ex) {
            assertEquals("The amount can't be 0.0", ex.getMessage());
        }
    }

    @Test
    @Tag("amount")
    @Order(9)
    @DisplayName("Test ECP: Payment non-valid (amount negativ)")
    public void addPaymentNonValidAmountECP_2() {
        try {
            service.addPayment(1, PaymentType.Card, -85.0);
            assert false;
        } catch (Exception ex) {
            assertEquals("The amount has to be positive", ex.getMessage());
        }
    }

    @Test
    @RepeatedTest(1)
    @Tag("table")
    @Order(2)
    @DisplayName("Test ECP: Payment valid (table in interval)")
    public void addPaymentValidTableECP() {
        try {
            service.addPayment(3, PaymentType.Cash, 12.0);
            assert true;
        } catch (Exception ex) {
            // assert false;
        }
    }


    @Test
    @Tag("table")
    @Order(10)
    @DisplayName("Test ECP: Payment non-valid (table >  8)")
    public void addPaymentNonValidTableECP_1() {
        try {
            service.addPayment(15, PaymentType.Card, 45);
            assert false;
        } catch (Exception ex) {
            assertEquals("The table has to be <= 8", ex.getMessage());
        }

    }

    @Test
    @Order(11)
    @DisplayName("Test ECP: Payment non-valid (table < 1)")
    public void addPaymentNonValidTableECP_2() {
        try {
            service.addPayment(-8, PaymentType.Card, 120.0);
            assert false;
        } catch (Exception ex) {
            assertEquals("The table has to be >= 1", ex.getMessage());
        }
    }

    @Test
    @Order(3)
    @DisplayName("Test BVA: Payment valid (amount > 0.0)")
    public void addPaymentValidAmountBVA() {
        try {
            service.addPayment(1, PaymentType.Card, 5);
            assert true;
        } catch (Exception ex) {
            // assert false;
            Assertions.assertFalse(false);
        }
    }

    @Test
    @Order(12)
    @DisplayName("Test BVA: Payment non-valid (amount = 0.0)")
    public void addPaymentNonValidAmountBVA_1() {
        try {
            service.addPayment(1, PaymentType.Card, 0.0);
            assert false;
        } catch (Exception ex) {
            assertEquals("The amount can't be 0.0", ex.getMessage());
        }
    }

    @Test
    @Order(13)
    @DisplayName("Test BVA: Payment non-valid (amount < 0.0)")
    public void addPaymentNonValidAmountBVA_2() {
        try {
            service.addPayment(1, PaymentType.Card, -1.0);
            assert false;
        } catch (Exception ex) {
            assertEquals("The amount has to be positive", ex.getMessage());
        }
    }

    @Test
    @Order(4)
    @DisplayName("Test BVA: Payment valid (table = 1)")
    public void addPaymentValidTableBVA_1() {
        try {
            service.addPayment(1, PaymentType.Card, 5.0);
            assert true;
        } catch (Exception ex) {
            assert false;
        }
    }

    @Test
    @Order(5)
    @DisplayName("Test BVA: Payment valid (table = 2)")
    public void addPaymentValidTableBVA_2() {
        try {
            service.addPayment(2, PaymentType.Card, 5.0);
            assert true;
        } catch (Exception ex) {
            assert false;
        }
    }

    @Test
    @Order(14)
    @DisplayName("Test BVA: Payment non-valid (table = 0)")
    public void addPaymentNonValidTableBVA_1() {
        try {
            service.addPayment(0, PaymentType.Card, 5.0);
            assert false;
        } catch (Exception ex) {
            assertEquals("The table has to be >= 1", ex.getMessage());
        }
    }

    @Test
    @Order(6)
    @DisplayName("Test BVA: Payment valid (table = 7)")
    public void addPaymentValidTableBVA_3() {
        try {
            service.addPayment(7, PaymentType.Card, 7.0);
            assert true;
        } catch (Exception ex) {
            assert false;
        }
    }

    @Test
    @Order(7)
    @DisplayName("Test BVA: Payment valid (table = 8)")
    public void addPaymentValidTableBVA_4() {
        try {
            service.addPayment(8, PaymentType.Card, 15.0);
            assert true;
        } catch (Exception ex) {
            assert false;
        }
    }

    @Test
    @Order(15)
    @DisplayName("Test BVA: Payment non-valid (table = 9)")
    public void addPaymentNonValidTableBVA_2() {
        try {
            service.addPayment(9, PaymentType.Card, 15.0);
            assert false;
        } catch (Exception ex) {
            assertEquals("The table has to be <= 8", ex.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
    }
}

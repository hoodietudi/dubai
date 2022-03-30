package pizzashop.validator;

public class PaymentValidator {

    public void validateTable(int table) throws Exception {
        if (table < 1) {
            throw new Exception("The table has to be >= 1");
        }
        if (table > 8) {
            throw new Exception("The table has to be <= 8");
        }
    }

    public void validateAmount(double amount) throws Exception {
        if (amount == 0.0) {
            throw new Exception("The amount can't be 0.0");
        }
        if (amount < 0.0) {
            throw new Exception("The amount has to be positive");
        }
    }

}

package pizzashop.utils;

public interface PaymentOperation {
     void cardPayment();
     void cashPayment();
     void cancelPayment();
}
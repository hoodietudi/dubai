package pizzashop.handler;

public class Handler implements Thread.UncaughtExceptionHandler {

    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Unhandled exception caught!");
    }
}
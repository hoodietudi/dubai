package pizzashop.service;

        import pizzashop.model.MenuDataModel;
        import pizzashop.model.Payment;
        import pizzashop.model.PaymentType;
        import pizzashop.repository.MenuRepository;
        import pizzashop.repository.PaymentRepository;
        import pizzashop.validator.PaymentValidator;

        import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;
    private PaymentValidator validator;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
        validator = new PaymentValidator();
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){
        if (payRepo == null) return null;

        return payRepo.getAll();
    }

    public boolean addPayment(int table, PaymentType type, double amount) throws Exception {
        validator.validateTable(table);
        validator.validateAmount(amount);
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
        return true; //initial aveam public void, pentru lab02 am schimbat in boolean
    }

    public void setPayment(PaymentRepository payRepo) {
        this.payRepo = payRepo;
    }

    public double getTotalAmount(PaymentType type){
        double total=0.0f;                  //1
        List<Payment> l=getPayments();      //2
        if (l==null)                        //3
            return total;                   //4
        if(l.size()==0)                     //5
            return total;                   //6
        for (Payment p:l){                  //7
            if (p.getType().equals(type))   //8
                total+=p.getAmount();       //9
        }                                   //10
        return total;                       //11
    }                                       //12

}

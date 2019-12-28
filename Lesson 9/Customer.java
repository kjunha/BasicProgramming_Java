package BuildingBlocks;

public class Customer extends Person implements Security, Online {
    @Override
    public void shopOnline() {
        System.out.println("Online Shopping from Customer");
    }

    @Override
    void sayThankyou() {
        System.out.println("Thank you from Customer");
    }

    @Override
    public void safe() {
        System.out.println("Customer is safe");
    }
}

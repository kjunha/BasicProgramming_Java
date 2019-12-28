package BuildingBlocks;

public class Employee extends Person {
    int employee_id;

    @Override
    void sayThankyou() {
        System.out.println("Thank you from Employee");
    }
}

package BuildingBlocks;

public abstract class Person {
    int age;
    Gender gender = Gender.FEMALE;

    void sayHello() {
        System.out.println("Hello from Person");
    }

    abstract void sayThankyou();
}

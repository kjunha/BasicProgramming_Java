package OOPLesson;

public class Customer extends Person {
    int budget;

    public void buyStuff(String stuff) {
        System.out.println("I buy this: " + stuff);
    }

    @Override
    public void sayHello() {
        super.sayHello();
        System.out.println("I am a customer.");
    }
}

package OOPLesson;

public class LessonSeven {
    public static void main(String[] args) {
        Person p = new Person();
        Customer c1 = new Customer();
        Person c2 = new Customer();

        c1.name = "John";
        c1.budget = 1350;
        c2.name = "Frank";

        c1.sayHello();
        c1.buyStuff("Macbook Pro 2017");
        c2.sayHello();
    }
}

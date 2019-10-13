public class Test {
    public static void main(String[] args) {
    //Literal in Java: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
        //int
        System.out.println("Int: " + 1);
        //long
        System.out.println("Long: " + 0xffff0302L);
        //double
        System.out.println("Double: " + 2.756598123509112937);
        //float
        System.out.println("Float: " + 3.141592f);
        //byte
        System.out.println("Byte: " + 0b0100_1011);
        //char
        System.out.println("Character: " + 'a');
        //string
        System.out.println("String: " + "Hello World!");
        //boolean
        System.out.println("Boolean: " + false);

    //Expressions in Java: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html
    //Arithmetic Expressions
        //Addition: Answer is number
        int add = 3+5;
        System.out.println("Addition: " + add);
        //Subtraction
        int sub = 3-5;
        System.out.println("Subtraction: " + sub);
        //Multiplication
        int mul = 3*5;
        System.out.println("Multiplication: " + mul);
        //Division: CAUTION! Int div omits decimal points
        int div_int = 13/3;
        System.out.println("Division (int): " + div_int);
        //Division with double value
        double div_dbl = 13.0/3;
        System.out.println("Division (double): " + div_dbl);
        //Modulus: Returns remainders
        int mod = 13%3;
        System.out.println("Modulus" + mod);
        //Increment, Decrement
        int i = 1;
        System.out.println("Increment: " + (i++));
        System.out.println("Decrement: " + (i--));

    //Relational Expressions: Answer is Boolean
        System.out.println("13 > 12: " + (13 > 12));
        System.out.println("'a' == 'a': " + ('a' == 'a'));
        //Operators: <, >, ==, <=, >=
    //Logical Expressions: Answer is Boolean
        //Change A to false or any other Relational Expressions
        //Ex. boolean A = 12 < 7
        boolean A = true;
        //Change B to false or any other Relational Expressions
        boolean B = true;
        System.out.println("if A is True and B is true: " +  (A && B));
        //&& and &, & checks both side, && returns false when first element is false.
        System.out.println("if A is True or B is True: " + (A||B));
        //|| and |, | checks both side, || returns true when first element is true.

    }
}

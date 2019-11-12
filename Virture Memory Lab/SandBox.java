
public class SandBox {
    public static void main(String[] args) {
        PageTableEntry[] test = new PageTableEntry[10];
        test[0] = new PageTableEntry();
        if(test[0] == null) {
            System.out.println("It is null");
        } else {
            System.out.println("It is not null");
        }
    }
}

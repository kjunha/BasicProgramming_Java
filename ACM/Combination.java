public class Combination {
    static int global_c = 0;

    public static void recTest(int[] src, int[] selected, int srcid, int selid, int count) {
        if (srcid < src.length & selid < count) {
            selected[selid] = src[srcid];
            if (selid == count - 1) {
                int sum = 0;
                for (int i = 0; i < selected.length; i++) {
                    System.out.printf("[%d], ", selected[i]);
                }
                System.out.println(" ");
            }
            recTest(src, selected, srcid + 1, selid + 1, count);
            recTest(src, selected, srcid + 1, selid, count);
        }
    }
    public static void main(String[] args) {
        int[] src = {1,2,3,4,5,6,7,8,9,10};

        recTest(src, new int[5], 0,0,5);

    }
}

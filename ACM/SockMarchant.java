import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SockMarchant {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        ArrayList<int[]> pairs = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < ar.length; i++) {
            boolean added = false;
            for(int[] j : pairs) {
                if(j[0] == ar[i]){
                    if(j[1] == 0){
                        j[1] = ar[i];
                        added = true;
                        break;
                    }
                }
            }
            if(!added) {
                int[] newpair = new int[2];
                newpair[0] = ar[i];
                pairs.add(newpair);
            }
        }
        for(int[] p : pairs){
            if(p[0] == p[1]){
                count++;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
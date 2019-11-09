import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MovieDay {

    // Complete the beautifulDays function below.
    static int beautifulDays(int i, int j, int k) {
        int daycount = 0;
        for(int a = i; a <= j; a++) {
            int a_rev = Integer.parseInt(new StringBuilder(Integer.toString(a)).reverse().toString());
            if(Math.abs(a_rev - a)%k == 0) {
                daycount++;
            }
        }
        return daycount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] ijk = scanner.nextLine().split(" ");

        int i = Integer.parseInt(ijk[0]);

        int j = Integer.parseInt(ijk[1]);

        int k = Integer.parseInt(ijk[2]);

        int result = beautifulDays(i, j, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
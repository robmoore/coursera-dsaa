import java.util.*;
import java.io.*;

public class MaxPairwiseProduct2 {
    static long getMaxPairwiseProduct(int[] numbers) {
        Arrays.sort(numbers);

//        long result = 0;
//        int n = numbers.length;
//        for (int i = 0; i < n; ++i) {
//            for (int j = i + 1; j < n; ++j) {
//                if (((long) numbers[i]) * numbers[j] > result) {
//                    result = ((long) numbers[i]) * numbers[j];
//                }
//            }
//        }
//        return result;

        int n = numbers.length;

        return (long) numbers[n - 2] * numbers[n - 1];
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        if (n != numbers.length)
            throw new RuntimeException(String.format("Invalid number of numbers specified: %s", n));
        if (n < 2)
            throw new RuntimeException(String.format("List of numbers must be at least two but was %s", n));

        System.out.println(getMaxPairwiseProduct(numbers));
//        System.out.println(getMaxPairwiseProduct(new int[100000]));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
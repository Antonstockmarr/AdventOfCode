import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws Exception {
        int test = countIncreases("./Data/Day1_test.txt");
        if (test != 4) {
            throw new Exception("test failed. Got "+ test);
        }

        System.out.println(countIncreases("./Data/Day1.txt"));

        test = countSlidingWindowIncreases("./Data/Day1_test.txt");
        if (test != 1) {
            throw new Exception("test failed. Got "+ test);
        }

        System.out.println(countSlidingWindowIncreases("./Data/Day1.txt"));
    }

    private static int countIncreases(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        Scanner scanner = new Scanner(fileReader);
        int oldValue, newValue, count = 0;

        if (scanner.hasNextInt())
            oldValue = scanner.nextInt();
        else {
            fileReader.close();
            scanner.close();
            return 0;
        }

        while (scanner.hasNextInt()) {
                newValue = scanner.nextInt();
                if (newValue > oldValue){
                count++;
                }
            oldValue = newValue;
        }
        fileReader.close();
        scanner.close();
        return count;
    }

    private static int countSlidingWindowIncreases(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        Scanner scanner = new Scanner(fileReader);
        int oldValue, newValue, sum = 0, count = 0;

        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<3; i++) {
            if (!scanner.hasNextInt()) {
                fileReader.close();
                scanner.close();
                return 0;
            }
            newValue = scanner.nextInt();
            sum += newValue;
            queue.add(newValue);
        }

        while (scanner.hasNextInt()) {
            newValue = scanner.nextInt();
            oldValue = queue.poll();
            queue.add(newValue);

            if (sum + newValue - oldValue > sum){
                count++;
            }
            sum = sum + newValue - oldValue;
        }
        fileReader.close();
        scanner.close();
        return count;
    }
}

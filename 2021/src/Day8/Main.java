package Day8;


import java.util.LinkedList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FileReader fileReader = new FileReader("./Data/Day8.txt");
        Scanner scanner = new Scanner(fileReader);

        int sum = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");
            String[] patternStrings = parts[0].split(" ");
            Pattern[] patterns = new Pattern[10];
            for (int i=0; i< patternStrings.length; i++) {
                patterns[i] = new Pattern(patternStrings[i]);
            }
            String[] numbers = parts[1].trim().split(" ");

            assignNumbers(patterns);

            int[] result = translateNumbers(patterns, numbers);

            sum += result[0]*1000 + result[1]*100 + result[2]*10 + result[3];
        }
        System.out.println(sum);
    }

    private static int[] translateNumbers(Pattern[] patterns, String[] numbers) {
        int[] result = new int[4];
        for (int i=0; i<numbers.length; i++) {
            Pattern targetPattern = new Pattern(numbers[i]);
            for (Pattern pattern : patterns) {
                if (pattern.signals.containsAll(targetPattern.signals) &&
                        targetPattern.signals.containsAll(pattern.signals)) {
                    result[i] = pattern.number;
                }
            }
        }
        return result;
    }

    private static void assignNumbers(Pattern[] patterns) {
        List<Character> one = new LinkedList<>();
        List<Character> six = new LinkedList<>();
        List<Character> four = new LinkedList<>();
        for (Pattern pattern : patterns) {
            if (pattern.signals.size() == 2) {
                pattern.number = 1;
                one = pattern.signals;
            }
            if (pattern.signals.size() == 3) {
                pattern.number = 7;
            }
            if (pattern.signals.size() == 4) {
                pattern.number = 4;
                four = pattern.signals;
            }
            if (pattern.signals.size() == 7) {
                pattern.number = 8;
            }
        }
        for (Pattern pattern : patterns) {
            if (pattern.signals.size() == 5 && pattern.signals.containsAll(one)) {
                pattern.number = 3;
            } else if (pattern.signals.size() == 6 && !pattern.signals.containsAll(one)) {
                pattern.number = 6;
                six = pattern.signals;
            } else if (pattern.signals.size() == 6 && pattern.signals.containsAll(four)) {
                pattern.number = 9;
            } else if (pattern.signals.size() == 6) {
                pattern.number = 0;
            }
        }
        for (Pattern pattern : patterns) {
            if (pattern.number != null) continue;
            if (six.containsAll(pattern.signals)) {
                pattern.number = 5;
            } else {
                pattern.number = 2;
            }
        }
    }
}
package Day9;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = "./Data/Day9.txt";
        int lines = countLines(filename) + 1;
        int[][] numbers = new int[lines][];
        FileReader fileReader = new FileReader(filename);
        Scanner scanner = new Scanner(fileReader);
        for (int j=0; j<lines; j++) {
            String line = scanner.nextLine();
            char[] characters = line.toCharArray();
            int[] numberLine = new int[characters.length];
            for (int i=0; i<characters.length; i++) {
                numberLine[i] = Integer.parseInt(String.valueOf(characters[i]));
            }
            numbers[j] = numberLine;
        }

        Cave cave = new Cave(numbers);
//        int riskSum = cave.getValleys().values().stream().map(v -> v + 1).mapToInt(Integer::intValue).sum();
//        System.out.println(riskSum);
//        for (List<Pos> list : cave.getBasins().values()) {
//            System.out.println(list.get(0) + ": "+list.size() + " - " + list);
//        }
        cave.getBasins().values().stream().map(List::size).sorted().forEach(System.out::println);
    }



    public static int countLines(String filename) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i = 0; i < 1024; ) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        }
    }}

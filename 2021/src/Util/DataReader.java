package Util;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataReader {

    private Scanner scanner;
    private String filename;

    public DataReader(String filename) {
        this.filename = filename;
    }

    public List<String> readUntilEOF() throws FileNotFoundException {
        if (scanner == null) {
            scanner = new Scanner(new FileReader(filename));
        }
        List<String> lines = new LinkedList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    public List<String> readUntilEmptyLine() throws FileNotFoundException {
        if (scanner == null) {
            scanner = new Scanner(new FileReader(filename));
        }
        List<String> lines = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) break;
            lines.add(line);
        }
        return lines;
    }

    public int[][] readNumberMatrix() throws IOException {
        int lines = countLines();
        int[][] numbers = new int[lines][];
        FileReader fileReader = new FileReader(filename);
        Scanner localScanner = new Scanner(fileReader);
        for (int j=0; j<lines; j++) {
            String line = localScanner.nextLine();
            char[] characters = line.toCharArray();
            int[] numberLine = new int[characters.length];
            for (int i=0; i<characters.length; i++) {
                numberLine[i] = Integer.parseInt(String.valueOf(characters[i]));
            }
            numbers[j] = numberLine;
        }
        fileReader.close();
        localScanner.close();
        return numbers;
    }


    public int countLines() throws IOException {
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
    }
}
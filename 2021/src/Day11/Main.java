package Day11;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = "./Data/Day11.txt";
        int lines = countLines(filename);
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

        Flasher flasher = new Flasher();
        int maxRow = numbers.length;
        int maxCol = numbers[0].length;

        Map<Pos, Octopus> octopuses = new HashMap<>();
        for (int row=0; row < maxRow; row++) {
            for (int col=0; col < maxCol; col++) {
                Pos pos = new Pos(row, col);
                octopuses.put(pos, new Octopus(numbers[row][col], flasher));
            }
        }

        for (Pos pos : octopuses.keySet()) {
            Octopus octopus = octopuses.get(pos);
            List<Octopus> neighbours = pos.neighbours(maxRow,maxCol).stream().map(octopuses::get).collect(Collectors.toList());
            octopus.setNeighbours(neighbours);
        }
        int count = 0;
        while (notSynchronized(octopuses)) {
            count++;
            octopuses.values().forEach(o -> o.energy++);
            for (Pos pos : octopuses.keySet()) {
                Octopus octopus = octopuses.get(pos);
                if (octopus.energy > 9) {
                    octopus.flash();
                    pos.neighbours(maxRow, maxCol).stream().map(octopuses::get).forEach(Octopus::increaseEnergy);
                }
            }
        }

        printOctopuses(maxRow,maxCol,octopuses);
        System.out.println(flasher.flashes);
        System.out.println(count);
    }

    private static boolean notSynchronized(Map<Pos, Octopus> octopuses) {
        return octopuses.values().stream().map(o -> o.energy).distinct().count() > 1;
    }


    private static void printOctopuses(int maxRows, int maxCols, Map<Pos,Octopus> map) {
        System.out.println();
        for (int row = 0; row < maxRows; row++) {
            for (int col=0; col<maxCols; col++) {
                System.out.print(map.get(new Pos(row,col)).energy);
            }
            System.out.println();
        }
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

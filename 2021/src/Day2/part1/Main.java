package Day2.part1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FileReader fileReader = new FileReader("./Data/Day2.txt");
        Scanner scanner = new Scanner(fileReader);
        String line;
        String[] array;
        Instruction instruction;
        Coordinate sum = new Coordinate(0, 0);
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            array = line.split(" ");
            instruction = new Instruction(Direction.valueOf(array[0]), Integer.parseInt(array[1]));
            sum.add(instruction.asCoordinate());
        }

        System.out.println(sum.toString());
    }
}

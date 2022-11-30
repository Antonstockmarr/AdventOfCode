package Day5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        FileReader fileReader = new FileReader("./Data/Day5.txt");
        Scanner scanner = new Scanner(fileReader);

        SeaFloor seaFloor = new SeaFloor();

        while (scanner.hasNextLine()) {
            String textLine = scanner.nextLine();
            String[] positions = textLine.split("->");
            String[] position = positions[0].trim().split(",");
            Pos pos1 = new Pos(Integer.parseInt(position[0]),Integer.parseInt(position[1]));
            position = positions[1].trim().split(",");
            Pos pos2 = new Pos(Integer.parseInt(position[0]),Integer.parseInt(position[1]));
            seaFloor.addLine(new Line(pos1, pos2));
        }
        System.out.println(seaFloor.posWithTwoOrMoreLines());
    }
}

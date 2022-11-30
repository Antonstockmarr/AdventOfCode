package Day13;

import Util.DataReader;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        var dataReader = new DataReader("./Data/Day13.txt");
        List<String> points = dataReader.readUntilEmptyLine();
        List<String> foldStrings = dataReader.readUntilEOF();
        List<Pos> positions = points.stream().map(Main::stringToPos).collect(Collectors.toList());
        Paper paper = new Paper(positions);
        List<Fold> folds = foldStrings.stream().map(Main::stringToFold).collect(Collectors.toList());
        List<Fold> firstFold = new LinkedList<>();
        firstFold.add(folds.get(0));
        firstFold.add(folds.get(1));
        firstFold.add(folds.get(2));
        firstFold.add(folds.get(3));
        firstFold.add(folds.get(4));
        firstFold.add(folds.get(5));
        firstFold.add(folds.get(6));
        firstFold.add(folds.get(7));
        firstFold.add(folds.get(8));
        firstFold.add(folds.get(9));
        firstFold.add(folds.get(10));
        firstFold.add(folds.get(11));



        paper.fold(firstFold);
        System.out.println(paper.getDots());
        System.out.println(paper);
    }

    private static Pos stringToPos(String string) {
        String[] dim = string.split(",");
        int x = Integer.parseInt(dim[0]);
        int y = Integer.parseInt(dim[1]);
        return new Pos(x,y);
    }

    private static Fold stringToFold(String string) {
        Axis axis;
        if (string.contains("x")) {
            axis = Axis.X;
        }
        else {
            axis = Axis.Y;
        }
        int index = Integer.parseInt(string.split("=")[1]);
        return new Fold(axis, index);
    }
}

package Day12;

import Util.DataReader;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        DataReader dataReader = new DataReader("./Data/Day12.txt");
        Graph graph = new Graph(dataReader.readUntilEOF());
//        graph.getAllPaths().forEach(System.out::println);
        System.out.println(graph.getAllPaths().size());
    }
}

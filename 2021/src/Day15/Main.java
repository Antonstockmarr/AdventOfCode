package Day15;

import Util.DataReader;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        DataReader dataReader = new DataReader("./Data/Day15.txt");
        int[][] costs = dataReader.readNumberMatrix();

        int[][] newCosts = new int[costs.length*5][costs[0].length*5];
        int maxRow = costs.length;
        int maxCol = costs[0].length;
        for (int row=0; row<costs.length; row++) {
            for (int col=0; col<costs[0].length; col++) {
                for (int i=0; i<5; i++) {
                    for (int j=0; j<5; j++) {
                        int value = costs[row][col]+(j+i);
                        newCosts[row + maxRow * i][col + maxCol * j] = value < 10 ? value : value - 9;
                    }
                }
            }
        }
//        for (int row = 0; row < newCosts.length; row++) {
//            for (int col = 0; col < newCosts[row].length; col++) {
//                System.out.print(newCosts[row][col]);
//            }
//                System.out.println();
//        }


        int cost = computeShortestPathDistance(new Pos(0,0), newCosts.length, newCosts[0].length, newCosts);

        System.out.println(cost);

    }

    private static int computeShortestPathDistance(Pos startPos, int maxRows, int maxCols, int[][] costs) {
        Integer[][] distances = new Integer[maxRows][maxCols];
        if (startPos.row < 0 || startPos.row >= maxRows || startPos.col < 0 || startPos.col >= maxCols) {
            System.err.format("Tried to precompute distance matrix with wrong input");
            return 0;
        }
        PriorityQueue<Pos> cells = new PriorityQueue<>(65536, Comparator.comparingInt(cell -> distances[cell.row][cell.col]));
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                distances[row][col] = maxRows * maxCols;
                cells.add(new Pos(row, col));
            }
        }
        distances[startPos.row][startPos.col] = 0;//costs[startPos.row][startPos.col];
        Pos u;
        int oldDist, newDist;
        while (!cells.isEmpty()) {
            u = cells.poll();
            for (Pos v : u.neighbours(maxRows, maxCols)) {
                oldDist = distances[v.row][v.col];
                newDist = distances[u.row][u.col] + costs[v.row][v.col];
                if (oldDist > newDist) {
                    distances[v.row][v.col] = newDist;
                    cells.add(v);
                }
            }
        }
        for (int row = 0; row < distances.length; row++) {
            for (int col = 0; col < distances[row].length; col++) {
                //System.out.print(distances[row][col] + " ");
            }
        //    System.out.println();
        }
        return distances[maxRows - 1][maxCols - 1];
    }

}

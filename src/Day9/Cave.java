package Day9;

import java.util.*;
import java.util.stream.Collectors;

public class Cave {
    private Map<Pos, Integer> heights;
    private int rows;
    private int cols;


    public Cave(int[][] heightArray) {
        this.rows = heightArray.length;
        this.cols = heightArray[0].length;
        this.heights = new HashMap<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                heights.put(new Pos(row, col), heightArray[row][col]);
            }
        }
    }

    public Map<Pos, Integer> getValleys() {
        Map<Pos, Integer> valleys = new HashMap<>();
        for (Pos pos : heights.keySet()) {
            Integer height = heights.get(pos);
            if (pos.neighbours(rows, cols).stream().noneMatch(p -> heights.get(p) <= height)) {
                valleys.put(pos, height);
            }
        }
        return valleys;
    }

    public Map<Integer, List<Pos>> getBasins() {
        Map<Integer, List<Pos>> basins = new HashMap<>();
        Map<Pos, Integer> valleys = getValleys();
        int basinId = 0;
        for (Pos valley : valleys.keySet()) {
            LinkedList<Pos> newList = new LinkedList<>();
            newList.add(valley);
            basins.put(basinId, newList);
            Queue<Pos> queue = valley.neighbours(rows, cols).stream()
                    .filter(p -> heights.get(p) != 9)
                    .collect(Collectors.toCollection(LinkedList::new));
            while (!queue.isEmpty()) {
                Pos pos = queue.poll();
                if (basins.get(basinId).contains(pos)) continue;
                basins.get(basinId).add(pos);
                int finalBasinId = basinId;
                queue.addAll(pos.neighbours(rows, cols).stream()
                        .filter(p -> heights.get(p) != 9 && heights.get(p) >= heights.get(pos) && !basins.get(finalBasinId).contains(p))
                        .collect(Collectors.toList()));
            }
            basinId++;
        }
        return basins;
    }
}

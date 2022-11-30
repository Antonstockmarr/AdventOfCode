package Day5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Line {

    private Pos start;
    private Pos end;

    public Line(Pos start, Pos end) {
        this.start = start;
        this.end = end;
    }

    public List<Pos> getOverlappingPositions() {
        int max, min;
        List<Pos> positions = new LinkedList<>();
        if (start.col == end.col) {
            if (start.row < end.row) {
                max = end.row;
                min = start.row;
            }
            else {
                max = start.row;
                min = end.row;
            }
            for (int i=min; i<=max; i++) {
                positions.add(new Pos(i, start.col));
            }
            return positions;
        }
        if (start.row == end.row) {
            if (start.col < end.col) {
                max = end.col;
                min = start.col;
            }
            else {
                max = start.col;
                min = end.col;
            }
            for (int i=min; i<=max; i++) {
                positions.add(new Pos(start.row, i));
            }
            return positions;
        }
        else {
            int rowdiff = end.row - start.row;
            int coldiff = end.col - start.col;
            Pos currentPos = new Pos(start.row, start.col);
            Pos stepPos = new Pos(rowdiff > 0 ? 1 : -1, coldiff > 0 ? 1 : -1);
            int count = 0;
            while (!currentPos.equals(end)) {
//                System.out.println(currentPos + " + " + stepPos + "  (" + start + " -> "+ end + ")");
                positions.add(currentPos);
                currentPos = new Pos(currentPos.row + stepPos.row, currentPos.col + stepPos.col);
                count++;
            }
            positions.add(end);
        }
        return positions;
    }
}

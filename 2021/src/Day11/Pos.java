package Day11;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Pos {
    public int row;
    public int col;
    public Integer basinId;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public List<Pos> neighbours(int maxRows, int maxCols) {
        List<Pos> neighbours = new LinkedList<>();
        if (row > 0)
            neighbours.add(new Pos(row-1,col));
        if (row < maxRows-1 )
            neighbours.add(new Pos(row+1,col));
        if (col > 0)
            neighbours.add(new Pos(row,col-1));
        if (col < maxCols-1)
            neighbours.add(new Pos(row,col+1));
        if (row > 0 && col > 0) {
            neighbours.add(new Pos(row-1,col-1));
        }
        if (row < maxRows-1 && col > 0) {
            neighbours.add(new Pos(row+1,col-1));
        }
        if (row > 0 && col < maxCols - 1) {
            neighbours.add(new Pos(row-1,col+1));
        }
        if (row < maxRows-1 && col < maxCols-1) {
            neighbours.add(new Pos(row+1,col+1));
        }
        return neighbours;
    }


    @Override
    public String toString() {
        return "(" + row +
                "," + col +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return row == pos.row &&
                col == pos.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

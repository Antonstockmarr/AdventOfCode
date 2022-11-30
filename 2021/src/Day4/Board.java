package Day4;

public class Board {

    private int columns;
    private int rows;
    private Cell[][] cells;

    public Board(int[][] numbers, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        cells = new Cell[rows][];
        for (int i=0; i<rows; i++) {
            cells[i] = new Cell[columns];
            for (int j=0; j< columns; j++) {
                cells[i][j] = new Cell(numbers[i][j]);
            }
        }
    }

    public void mark(int number) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (cells[row][col].number == number) {
                    cells[row][col].marked = true;
                    return;
                }
            }
        }
    }

    public boolean hasWon() {
        boolean allMarked;
        for (int row = 0; row < rows; row++) {
            allMarked = true;
            for (int col = 0; col < columns; col++) {
                if (!cells[row][col].marked) {
                    allMarked = false;
                    break;
                }
            }
            if (allMarked) return true;
        }
        for (int col = 0; col < columns; col++) {
            allMarked = true;
            for (int row = 0; row < rows; row++) {
                if (!cells[row][col].marked) {
                    allMarked = false;
                    break;
                }
            }
            if (allMarked) return true;
        }
        return false;
    }

    public int score(int mostRecentlyCalled) {
        int sum = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (!cells[row][col].marked) {
                    sum += cells[row][col].number;
                }
            }
        }
        return sum * mostRecentlyCalled;
    }
}

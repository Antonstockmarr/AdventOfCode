package Day4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FileReader fileReader = new FileReader("./Data/Day4.txt");
        Scanner scanner = new Scanner(fileReader);

        List<Board> boards = new ArrayList<>();

        String line = scanner.nextLine();
        String[] numbers = line.split(",");

        int[][] boardNumbers;
        String[] cellRow;
        while (scanner.hasNextLine()) {
            boardNumbers = new int[5][];
            System.out.println(scanner.nextLine());
            for (int i=0; i< 5; i++) {
                cellRow = scanner.nextLine().trim().split("(\\s+)");
                System.out.println(cellRow.length);
                boardNumbers[i] = new int[5];
                for (int j = 0; j<5; j++) {
                    boardNumbers[i][j] = Integer.parseInt(cellRow[j]);
                }
            }
            boards.add(new Board(boardNumbers, 5, 5));
        }

//        findFirstWinner(boards, numbers);

        for (String number : numbers) {
            System.out.print(number+" ");
            int n = Integer.parseInt(number);
            boards.forEach(board -> board.mark(n));
            int boardsLeft = boards.size();
            if (boardsLeft == 1 && boards.get(0).hasWon()) {
                System.out.println(boards.get(0).score(n));
                return;
            }
            boards = boards.stream().filter(board -> !board.hasWon()).collect(Collectors.toList());
        }
    }

    private static void findFirstWinner(List<Board> boards, String[] numbers) {
        for (String number : numbers) {
            System.out.print(number+" ");
            int n = Integer.parseInt(number);
            boards.forEach(board -> board.mark(n));
            Optional<Board> winner = boards.stream().filter(Board::hasWon).findAny();
            if (winner.isPresent()) {
                System.out.println(winner.get().score(n));
                return;
            }
        }

    }
}

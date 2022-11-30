package Day6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {


        FileReader fileReader = new FileReader("./Data/Day6.txt");
        Scanner scanner = new Scanner(fileReader);
        String[] numbers = scanner.nextLine().split(",");

        Long[] fish = new Long[9];
        for (int i = 0; i<9; i++) {
            fish[i] = 0L;
        }
        for (String number : numbers) {
            fish[Integer.parseInt(number)] ++;
        }
        for (int i=0; i<256; i++) {
            long tmp = fish[0];
            System.arraycopy(fish, 1, fish, 0, 8);
            fish[6] += tmp;
            fish[8] = tmp;
        }

        Long sum = 0L;
        for (Long f : fish) {
            sum += f;
        }

        System.out.println(sum);

//        List<Integer> fish = new LinkedList<>();
//        for (String number : numbers) {
//            fish.add(Integer.parseInt(number));
//        }
//        List<Integer> nextGen = new LinkedList<>();
//
//        for (int i=0; i<256; i++) {
//            System.out.println(i);
//            fish = fish.stream().map(f -> --f).collect(Collectors.toList());
//            fish.forEach(f -> {
//                if (f == -1) nextGen.add(8);
//            });
//            fish = fish.stream().map(f -> f == -1 ? 6 : f).collect(Collectors.toList());
//            fish.addAll(nextGen);
//            nextGen.clear();
//        }
//
//        System.out.println(fish.size());
//
    }
}

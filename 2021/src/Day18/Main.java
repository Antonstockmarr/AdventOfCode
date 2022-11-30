package Day18;

import Util.DataReader;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        DataReader dataReader = new DataReader("./Data/Day18_test.txt");
        List<String> snailFishNumbers = dataReader.readUntilEOF();
        System.out.println(snailFishNumbers.size());
        BinaryTree base = new BinaryTree(snailFishNumbers.get(0));
        List<BinaryTree> numbers = snailFishNumbers.stream().map(BinaryTree::new).collect(Collectors.toList());

        System.out.println(sum(numbers));
    }

    public static Long sum(List<BinaryTree> trees) {
        Long max = 0L;
        for (BinaryTree tree : trees) {
            for (BinaryTree tree1 : trees) {
                System.out.println(tree);
                System.out.println(tree1);
                System.out.println(tree.copy().add(tree1).magnitude());
                System.out.println(tree.copy().add(tree1));
                if (!tree.equals(tree1)) {
                    Long currentSum = tree.copy().add(tree1).magnitude();
                    if (currentSum > max) {
                        max = currentSum;
                    }
                }
            }
        }
        return max;
    }
}

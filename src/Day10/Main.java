package Day10;


import Util.DataReader;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> lines = new DataReader("./Data/Day10.txt").readUntilEOF();
//        int sum = 0;
//        for (String line : lines) {
//            int penalty = analyzeLine(line);
//            sum += penalty;
//        }
//        System.out.println(sum);
        lines = lines.stream().filter(l -> {
            try {
                return analyzeLine(l) == 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());

        List<BigInteger> scores = new LinkedList<>();
        for (String line : lines) {
            Character[] completion = complete(line);
            scores.add(score(completion));
        }
        scores.sort(BigInteger::compareTo);
        scores.forEach(System.out::println);
        System.out.println(scores.get(scores.size()/2));
    }

    private static BigInteger score(Character[] completion) throws Exception {
        BigInteger score = BigInteger.valueOf(0);
        for (int i=0; i<completion.length; i++) {
            score = score.multiply(BigInteger.valueOf(5));
            score = score.add(BigInteger.valueOf(score(completion[i])));
        }
        return score;
    }

    private static int score(Character character) throws Exception {
        return switch (character) {
            case ')' -> 1;
            case ']' -> 2;
            case '}' -> 3;
            case '>' -> 4;
            default -> throw new Exception(String.valueOf(character));
        };
    }

    private static Character[] complete(String l) throws Exception {
        int index = 0;
        Stack<Character> stack = new Stack<>();
        while (index < l.length()) {
            Character bracket = l.charAt(index);
            if (isOpenBracket(bracket)) {
                stack.push(bracket);
            }
            else {
                if (stack.isEmpty()) throw new Exception("error in line");
                else {
                    Character top = stack.pop();
                    if (!matchingBrackets(top, bracket)) {
                        throw new Exception("error in line");
                    }
                }
            }
            index++;
        }
        Character[] completion = new Character[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            completion[i] = counterBracket(stack.pop());
            i++;
        }
        return completion;
    }

    private static Character counterBracket(Character bracket) throws Exception {
        return switch (bracket) {
            case '(' -> ')';
            case '[' -> ']';
            case '{' -> '}';
            case '<' -> '>';
            default -> throw new Exception(String.valueOf(bracket));
        };
    }

    private static int analyzeLine(String l) throws Exception {
        int index = 0;
        Stack<Character> stack = new Stack<>();

        while (index < l.length()) {
            Character bracket = l.charAt(index);
            if (isOpenBracket(bracket)) {
                stack.push(bracket);
            }
            else {
                if (stack.isEmpty()) return penalty(bracket);
                else {
                    Character top = stack.pop();
                    if (!matchingBrackets(top, bracket)) {
                        return penalty(bracket);
                    }
                }
            }
            index++;
        }
        return 0;
    }

    private static boolean isOpenBracket(Character bracket) {
        return bracket == '(' || bracket == '{' || bracket == '[' || bracket == '<';
    }

    private static boolean matchingBrackets(Character open, Character close) {
        return (open == '{' && close == '}')
                || (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '<' && close == '>');
    }

    private static int penalty(Character bracket) throws Exception {
        return switch (bracket) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> throw new Exception(String.valueOf(bracket));
        };
    }
}

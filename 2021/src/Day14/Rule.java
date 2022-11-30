package Day14;

public class Rule {
    public String input;
    public String output;

    public Rule(String rule) {
        String[] sides = rule.split("->");
        input = sides[0].trim();
        sides[1] = sides[1].trim();
        output = input.substring(0,1) + sides[1].charAt(0) + input.substring(1,2);
    }


    @Override
    public String toString() {
        return input + " -> " + output;
    }
}

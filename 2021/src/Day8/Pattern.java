package Day8;

import java.util.LinkedList;
import java.util.List;

public class Pattern {

    public List<Character> signals;
    public Integer number = null;

    public Pattern(String pattern) {
        signals = new LinkedList<>();
        for (char signal : pattern.toCharArray()) {
            signals.add(signal);
        }
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "signals=" + signals +
                ", number=" + number +
                '}';
    }
}

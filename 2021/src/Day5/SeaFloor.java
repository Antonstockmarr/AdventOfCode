package Day5;

import java.util.HashMap;
import java.util.Map;

public class SeaFloor {

    private Map<Pos,Integer> vents;

    public SeaFloor() {
        vents = new HashMap<>();
    }

    public void addLine(Line line) {
        for (Pos pos : line.getOverlappingPositions()) {
            Integer current = vents.putIfAbsent(pos, 1);
            if (current != null) {
                vents.replace(pos, current + 1);
            }
//            vents.compute(pos, (k, v) -> (v == null) ? 1 : v + 1);
        }
    }

    public long posWithTwoOrMoreLines() {
        return vents.values().stream().filter(i -> i >= 2).count();
    }
}

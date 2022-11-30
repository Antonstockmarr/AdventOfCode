package Day2.part1;

public class Coordinate {
    public int depth;
    public int displacement;


    public Coordinate(int depth, int displacement) {
        this.depth = depth;
        this.displacement = displacement;
    }

    public void add(Coordinate c) {
        this.depth += c.depth;
        this.displacement += c.displacement;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "depth=" + depth +
                ", displacement=" + displacement +
                '}';
    }
}

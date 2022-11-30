package Day2.part2;

public class State {
    public int depth;
    public int displacement;
    public int aim;


    public State(int depth, int displacement, int aim) {
        this.depth = depth;
        this.displacement = displacement;
        this.aim = aim;
    }

    public void execute(Instruction i) {
        if (i.deltaAim() != 0) {
            this.aim += i.deltaAim();
        }
        else {
            this.displacement += i.speed;
            this.depth += this.aim*i.speed;
        }

    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "depth=" + depth +
                ", displacement=" + displacement +
                '}';
    }
}

package Day2.part2;

public class Instruction {

    private Direction direction;
    public int speed;

    public Instruction(Direction direction, int speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public int deltaAim() {
        if (this.direction == Direction.down) {
            return speed;
        }
        if (this.direction == Direction.up) {
            return -speed;
        } else {
            return 0;
        }
    }
}

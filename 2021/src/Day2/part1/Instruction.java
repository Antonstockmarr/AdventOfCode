package Day2.part1;

public class Instruction {

    private Direction direction;
    private int speed;

    public Instruction(Direction direction, int speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public Coordinate asCoordinate() {
        if (this.direction == Direction.forward) {
            return new Coordinate(0, this.speed);
        }
        if (this.direction == Direction.down) {
            return new Coordinate(this.speed, 0);
        }
        else {
            return new Coordinate(-this.speed, 0);
        }
    }


}

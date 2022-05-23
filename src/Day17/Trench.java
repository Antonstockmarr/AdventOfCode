package Day17;

public class Trench {
    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;

    public Trench(int xMax, int xMin, int yMax, int yMin) {
        this.xMax = xMax;
        this.xMin = xMin;
        this.yMax = yMax;
        this.yMin = yMin;
    }

    public boolean canHit(int x, int y) {
        int height = 0;
        int distance = 0;
        while (y >= 0 || height > yMin) {
            height += y;
            distance += x;
            y--;

            if (x<0) {
                x++;
            }
            if (x> 0) {
                x--;
            }
            if (isInTargetRegion(distance,height)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInTargetRegion(int distance, int height) {
        return distance <= xMax && distance >= xMin && height <= yMax && height >= yMin;
    }

    public int maxHeight(int x, int y) {
        int height = 0;
        while (y > 0) {
            height += y;
            y--;
        }
        return height;
    }
}

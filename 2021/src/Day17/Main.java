package Day17;

public class Main {

    public static void main(String[] args) {
        Trench trench = new Trench(232, 211, -69, -124);
//        Trench trench = new Trench(30, 20, -5, -10);
        int maxHeight = 0;
        int count = 0;
        for (int x=0; x<250; x++) {
            for (int y = -150; y<1000; y++) {
                if (trench.canHit(x,y)) {
                    count++;
                    if (trench.maxHeight(x,y) > maxHeight) {
                        maxHeight = trench.maxHeight(x,y);
                    }
                }
            }
        }
        System.out.println(maxHeight);
        System.out.println(count);
    }
}

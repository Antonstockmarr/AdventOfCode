package Day13;

import java.util.*;

public class Paper {

    private Map<Pos, Integer> dots;
    private int maxX;
    private int maxY;

    public Paper(List<Pos> positions) {
        dots = new HashMap<>();
        for (Pos position : positions) {
            dots.put(position, 1);
        }
        maxX = positions.stream().map(p -> p.x).max(Integer::compareTo).get() + 1;
        maxY = positions.stream().map(p -> p.y).max(Integer::compareTo).get() + 1;
    }

    public void fold(List<Fold> folds) {
        Pos foldedPos;
        List<Pos> tmpadd = new LinkedList<>();
        List<Pos> tmprm = new LinkedList<>();
        Optional<Integer> tmpmaxx = folds.stream().filter(fold -> fold.axis == Axis.X).map(fold -> fold.index).min(Integer::compareTo);
        Optional<Integer> tmpmaxy = folds.stream().filter(fold -> fold.axis == Axis.Y).map(fold -> fold.index).min(Integer::compareTo);
        tmpmaxx.ifPresent(integer -> maxX = integer);
        tmpmaxy.ifPresent(integer -> maxY = integer);
        for (Fold fold : folds) {
            for (Pos pos : dots.keySet()) {
                if (fold.axis == Axis.X && pos.x > fold.index) {
                    foldedPos = new Pos(fold.index - (pos.x - fold.index), pos.y);
                    tmprm.add(pos);
                    if (foldedPos.x >= 0 && foldedPos.y >= 0) {
                        tmpadd.add(foldedPos);
                    }
                }
                if (fold.axis == Axis.Y && pos.y > fold.index) {
                    tmprm.add(pos);
                    foldedPos = new Pos(pos.x, fold.index - (pos.y - fold.index));
                    if (foldedPos.x >= 0 && foldedPos.y >= 0) {
                        tmpadd.add(foldedPos);
                    }
                }
            }
            tmprm.forEach(p -> dots.remove(p));
            tmpadd.forEach(p -> dots.putIfAbsent(p,1));
        }

    }

    public int getDots() {
        System.out.println("maxX: "+maxX);
        System.out.println("maxY: "+maxY);
        return (int) dots.keySet().stream().filter(pos -> pos.x < maxX && pos.y < maxY).count();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (dots.containsKey(new Pos(x,y))) {
                    stringBuilder.append("#");
                }
                else {
                    stringBuilder.append(".");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}


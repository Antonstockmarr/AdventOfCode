package Day12;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Node {

    private String name;
    public List<Node> neighbours;

    public Node(String name) {
        this.name = name;
        this.neighbours = new LinkedList<>();
    }

    public boolean isBigCave() {
        return name.equals(name.toUpperCase());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package Day12;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Path {

    public List<String> nodes;

    public Path(Stack<Node> stack) {
        Node[] nodeArray = new Node[stack.size()];
        stack.copyInto(nodeArray);
        nodes = Arrays.asList(nodeArray).stream().map(Node::toString).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.join(",",nodes);
    }
}

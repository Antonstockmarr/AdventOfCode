package Day12;

import java.util.*;

public class Graph {

    private Node start;
    private Node end;
    Map<String,Node> nodes;

    public Graph(List<String> lines) {
        nodes = new HashMap<>();
        start = new Node("start");
        end = new Node("end");
        nodes.put("start",start);
        nodes.put("end",end);
        for (String line : lines) {
            String[] endpoints = line.split("-");
            Node n1, n2;
            if (!nodes.containsKey(endpoints[0])) {
                n1 = new Node(endpoints[0]);
                nodes.put(n1.toString(), n1);
            }
            else {
                n1 = nodes.get(endpoints[0]);
            }
            if (!nodes.containsKey(endpoints[1])) {
                n2 = new Node(endpoints[1]);
                nodes.put(n2.toString(), n2);
            }
            else {
                n2 = nodes.get(endpoints[1]);
            }
            n1.neighbours.add(n2);
            n2.neighbours.add(n1);
        }
    }

    public List<Path> getAllPaths() {
        Stack<Node> nodeStack = new Stack<>();
        List<Path> paths = new LinkedList<>();
        visit(start, nodeStack, paths);
        return paths;
    }

    private void visit(Node node, Stack<Node> nodeStack, List<Path> paths) {
        if (canNodeBeAppended(node, nodeStack)) {
            nodeStack.push(node);
            if (node.equals(end)) {
                paths.add(new Path(nodeStack));
            }
            else {
                for (Node neighbour : node.neighbours) {
                    visit(neighbour, nodeStack, paths);
                }
            }
            nodeStack.pop();
        }
    }

    private boolean canNodeBeAppended(Node node, Stack<Node> nodeStack) {
        if (node.isBigCave()){
            return true;
        }
        if (node.equals(start) || node.equals(end)) {
            return !nodeStack.contains(node);
        }
        else {
            Set<Node> items = new HashSet<>();
            // Set.add() returns false if the element was already in the set.
            boolean hasDuplicate = nodeStack.stream().filter(n -> !n.isBigCave()).anyMatch(n -> !items.add(n));
            return (!hasDuplicate || !nodeStack.contains(node));
        }
    }
}

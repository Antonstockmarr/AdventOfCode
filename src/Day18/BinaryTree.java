package Day18;

import java.util.Objects;

public class BinaryTree {

    public Node root;

    public BinaryTree(String snailFishString) {
        root = new Node();
        Node currentNode = root;
        Node lastLeaf = null, newNode;
        for (int index = 0; index < snailFishString.length(); index++) {
            if (snailFishString.charAt(index) == '[') {
                newNode = new Node();
                newNode.parent = currentNode;
                currentNode.leftChild = newNode;
                currentNode = newNode;
            }
            else if (snailFishString.charAt(index) == ',') {
                if (currentNode.isLeaf()) {
                    currentNode.leftNeighbour = lastLeaf;
                    if (lastLeaf != null) {
                        lastLeaf.rightNeighbour = currentNode;
                    }
                    lastLeaf = currentNode;
                }
                newNode = new Node();
                currentNode = currentNode.parent;
                newNode.parent = currentNode;
                currentNode.rightChild = newNode;
                currentNode = newNode;
            }
            else if (snailFishString.charAt(index) == ']') {
                if (currentNode.isLeaf()) {
                    currentNode.leftNeighbour = lastLeaf;
                    if (lastLeaf != null) {
                        lastLeaf.rightNeighbour = currentNode;
                    }
                    lastLeaf = currentNode;
                }
                currentNode = currentNode.parent;
            }
            else {
                currentNode.value = Integer.parseInt(String.valueOf(snailFishString.charAt(index)));
            }
        }
    }

    public BinaryTree copy() {
        return new BinaryTree(this.toString());
    }

    public BinaryTree add(BinaryTree tree) {
        rightMostNode().rightNeighbour = tree.leftMostNode();
        tree.leftMostNode().leftNeighbour = rightMostNode();
        Node newRoot = new Node();
        newRoot.leftChild = this.root;
        newRoot.rightChild = tree.root;
        root.rightNeighbour = tree.root;
        tree.root.leftNeighbour = root;
        root.parent = newRoot;
        tree.root.parent = newRoot;
        root = newRoot;

        reduce();
        return this;
    }

    public void reduce() {
        while (true) {
            if (root.tryExplode(0)) {
                continue;
            }
            if (root.trySplit()) {
                continue;
            }
            return;
        }
    }

    public Node rightMostNode() {
        Node currentNode = root;
        while (currentNode.rightChild != null) {
            currentNode = currentNode.rightChild;
        }
        return currentNode;
    }

    public Node leftMostNode() {
        Node currentNode = root;
        while (currentNode.leftChild != null) {
            currentNode = currentNode.leftChild;
        }
        return currentNode;
    }

    public Long magnitude() {
        return root.magnitude();
    }

    @Override
    public String toString() {
        return root.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree that = (BinaryTree) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }
}

package Day18;

public class Node {


    public Node leftChild = null;
    public Node rightChild = null;
    public Node leftNeighbour = null;
    public Node rightNeighbour = null;
    public Node parent = null;
    public int value;


    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public boolean tryExplode(int nested) {
        if (nested < 4) {
            boolean leftExploded = false, rightExploded = false;
            if (leftChild != null) {
                leftExploded = leftChild.tryExplode(nested+1);
            }
            if (rightChild != null && !leftExploded) {
                rightExploded = rightChild.tryExplode(nested+1);
            }
            return rightExploded || leftExploded;
        }
        else {
            if (this.isLeaf()) {
                return false;
            }
            explode();
            return true;
        }
    }

    private void explode() {
//        System.out.println("exploded "+this);
        Node newLeftNeighbour = leftChild.leftNeighbour;
        Node newRightNeighbour = rightChild.rightNeighbour;
        if (newLeftNeighbour != null) {
            newLeftNeighbour.value += leftChild.value;
            newLeftNeighbour.rightNeighbour = this;
        }
        if (newRightNeighbour != null) {
            newRightNeighbour.value += rightChild.value;
            newRightNeighbour.leftNeighbour = this;
        }
        this.value = 0;
        this.rightNeighbour = newRightNeighbour;
        this.leftNeighbour = newLeftNeighbour;
        this.leftChild = null;
        this.rightChild = null;
    }

    public boolean trySplit() {
        if (!this.isLeaf()) {
            boolean leftSplit = false, rightSplit = false;
            if (leftChild != null) {
                leftSplit = leftChild.trySplit();
            }
            if (rightChild != null && !leftSplit) {
                rightSplit = rightChild.trySplit();
            }
            return rightSplit || leftSplit;
        }
        else {
            if (value > 9) {
                split();
                return true;
            }
            else {
                return false;
            }
        }
    }

    private void split() {
//        System.out.println("split "+this);
        Node leftNode = new Node();
        leftChild = leftNode;
        leftNode.leftNeighbour = leftNeighbour;
        if (leftNeighbour != null) {
            leftNeighbour.rightNeighbour = leftNode;
        }
        leftNode.parent = this;
        leftNode.value = this.value/2;

        Node rightNode = new Node();
        rightChild = rightNode;
        rightNode.rightNeighbour = rightNeighbour;
        if (rightNeighbour != null) {
            rightNeighbour.leftNeighbour = rightNode;
        }
        rightNode.parent = this;
        rightNode.value = this.value/2 + (this.value % 2);
        rightNode.leftNeighbour = leftNode;
        leftNode.rightNeighbour = rightNode;
    }

    public Long magnitude() {
        if (isLeaf()) {
            return (long) value;
        }
        else {
            return leftChild.magnitude()*3 + rightChild.magnitude()*2;
        }
    }

    @Override
    public String toString() {
        if (this.isLeaf()) {
            return String.valueOf(value);
        }
        else {
            return "[" + leftChild +","+ rightChild + "]";
        }
    }
}

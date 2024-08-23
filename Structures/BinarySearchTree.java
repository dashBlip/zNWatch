package StockManagementSystem.Structures;

import StockManagementSystem.StockFunctionality.Stock;

import java.text.DecimalFormat;

public class BinarySearchTree {

    private Node root;
    public static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public BinarySearchTree() {
        root = null;
    }

    public void insert(Stock stock) {
        Node newNode = new Node(stock);
        if (root == null) {
            root = newNode;
        } else {
            insertRec(root, newNode);
        }
    }

    private void insertRec(Node current, Node newNode) {
        if (newNode.purchasedWithPrice < current.purchasedWithPrice) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                insertRec(current.left, newNode);
            }
        } else {
            if (current.right == null) {
                current.right = newNode;
            } else {
                insertRec(current.right, newNode);
            }
        }
    }

    public Node search(double key) {
        return searchRec(root, key);
    }

    private Node searchRec(Node current, double key) {
        if (current == null || current.purchasedWithPrice == key) {
            return current;
        }
        if (key < current.purchasedWithPrice) {
            return searchRec(current.left, key);
        } else {
            return searchRec(current.right, key);
        }
    }

    double sum = 0;

    public void inOrderTraversal() {
        inOrderRec(root);
    }

    public double giveTotalProfit(){
        return sum;
    }

    private void inOrderRec(Node current) {
        if (current != null) {
            inOrderRec(current.left);
            current.displayNode();
            sum += current.iData;
            inOrderRec(current.right);
        }
    }
}

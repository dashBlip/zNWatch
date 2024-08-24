package StockManagementSystem.Structures;

import StockManagementSystem.StockFunctionality.Stock;

import java.text.DecimalFormat;

/**
 * The {@code BinarySearchTree} class represents a binary search tree (BST)
 * used for storing and manipulating {@link Stock} objects based on their purchase price.
 */
public class BinarySearchTree {

    /** The root node of the binary search tree. */
    private Node root;

    /** Decimal format for displaying numerical values. */
    public static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    /**
     * Constructs an empty {@code BinarySearchTree}.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts a {@link Stock} into the binary search tree.
     * The stock is inserted based on its purchase price.
     *
     * @param stock the {@link Stock} to be inserted into the tree
     */
    public void insert(Stock stock) {
        Node newNode = new Node(stock);
        if (root == null) {
            root = newNode;
        } else {
            insertRec(root, newNode);
        }
    }

    /**
     * Recursively inserts a {@link Node} into the tree.
     *
     * @param current the current node in the recursion
     * @param newNode the new {@link Node} to be inserted
     */
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

    /**
     * Searches for a {@link Node} with a specific purchase price in the tree.
     *
     * @param key the purchase price to search for
     * @return the {@link Node} with the specified purchase price, or {@code null} if not found
     */
    public Node search(double key) {
        return searchRec(root, key);
    }

    /**
     * Recursively searches for a {@link Node} with a specific purchase price.
     *
     * @param current the current node in the recursion
     * @param key the purchase price to search for
     * @return the {@link Node} with the specified purchase price, or {@code null} if not found
     */
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

    /** Accumulates the total profit while traversing the tree. */
    double sum = 0;

    /**
     * Performs an in-order traversal of the tree and displays each {@link Node}.
     * The total profit is calculated during the traversal.
     */
    public void inOrderTraversal() {
        inOrderRec(root);
    }

    /**
     * Returns the total profit accumulated during the in-order traversal.
     *
     * @return the total profit
     */
    public double giveTotalProfit() {
        return sum;
    }

    /**
     * Recursively traverses the tree in-order and displays each {@link Node}.
     * Updates the total profit during the traversal.
     *
     * @param current the current node in the recursion
     */
    private void inOrderRec(Node current) {
        if (current != null) {
            inOrderRec(current.left);
            current.displayNode();
            sum += current.iData;
            inOrderRec(current.right);
        }
    }
}

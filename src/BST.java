import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Niam Iyer
 * @version: 12/9/24
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        // TODO: Complete the search function
        // Checks if the value is the root
        if (val == root.getVal()) {
            return true;
        }
        // Checks the right subtree if val is bigger
        if (val > root.getVal()) {
            if (search(val, root.getRight())) {
                return true;
            }
        }
        // Checks the left subtree if val is smaller
        if (val < root.getVal()) {
            if(search(val, root.getLeft())) {
                return true;
            }
        }
        return false;
    }

    public boolean search(int val,  BSTNode side){
        // Overloaded search function
        // Base case for if the node equals the val
        if (side.getVal() == val) {
            return true;
        }
        // Base case for if the node is a leaf
        if (side.getLeft() == null && side.getRight() == null) {
            return false;
        }
        // Base cases for if there is no child on the side the val is supposed to be on
        if ((side.getLeft() == null) && val < side.getVal()) {
            return false;
        }
        if ((side.getRight() == null) && val > side.getVal()) {
            return false;
        }
        // Checks recursively on the right/left subtree depending on the val
        if (val > side.getVal()) {
            if (search(val, side.getRight())) {
                return true;
            }
        }
        if (val < side.getVal()) {
            if(search(val, side.getLeft())) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        // TODO: Complete inorder traversal
        ArrayList<BSTNode> arr = new ArrayList<BSTNode>();
        getInorder(root, arr);
        return arr;
    }

    public void getInorder(BSTNode side, ArrayList<BSTNode> nodes){
        //  Base case
        if (side == null) {
            return;
        }
        // Runs recursively until the leftmost node
        if (side.getLeft() != null) {
            getInorder(side.getLeft(), nodes);
        }
        // Adds left node to list
        nodes.add(side);
        // Adds right node to list
        getInorder(side.getRight(), nodes);
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() {
        // TODO: Complete preorder traversal
        ArrayList<BSTNode> arr = new ArrayList<BSTNode>();
        getPreorder(root, arr);
        return arr;
    }

    public void getPreorder(BSTNode side, ArrayList<BSTNode> nodes) {
        // Base case
        if (side == null) {
            return;
        }
        // Adds root of subtree, then recurses to add left
        nodes.add(side);
        getPreorder(side.getLeft(), nodes);
        // Recurses to add right
        getPreorder(side.getRight(), nodes);

    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        ArrayList<BSTNode> arr = new ArrayList<BSTNode>();
        getPostorder(root, arr);
        return arr;
    }

    public void getPostorder(BSTNode side, ArrayList<BSTNode> nodes) {
        // Base case
        if (side == null) {
            return;
        }
        // Adds right side first
        if (side.getRight() != null) {
            getPostorder(side.getRight(), nodes);
        }
        // Adds root of subtree next
        nodes.add(side);
        // Adds left side last
        getPostorder(side.getLeft(), nodes);
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        // TODO: Complete insert
        // Sets root to be the root of the new tree
        if (root == null) {
            root = new BSTNode(val);
        }
        else {
            insert(val, root);
        }
    }

    public BSTNode insert(int val, BSTNode side) {
        // Finds where the side is null and creates a new node
        if (side == null) {
            side = new BSTNode(val);
        }
        // If the side is not null, looks at right or left subtree depending on value of val
        if (val > side.getVal()) {
            side.setRight(insert(val, side.getRight()));
        }
        if (val < side.getVal()) {
            side.setLeft(insert(val, side.getLeft()));
        }
        return side;
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // TODO: Optional Challenge!
        return false;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}

/* Whitney Humecky
 * Class constructs a splay tree, performs
 * insertion, deletion, and search functions
 */

import java.util.*;

public class Splay {
    Node root;

    Splay() {
        root = null;
    }

    Splay(int k) {
        root = new Node(k);
        travel(root);
    }

    // Method locates appropriate location via binary search for new node
    // then calls bottomUp(node) to push new node to be new root
    // and travel(node) to print the new splay tree

    void insert(int k) {
        Node K = new Node(k);
        Stack<Node> stack1 = new Stack<Node>(); // To store parents of K

        System.out.print("\n\nInserting: " + k);

        if (root == null) { // set root
            root = K;
        } else {
            Node current = root;

            // locate appropriate parent to insert K in BST

            while ((k < current.value && current.left != null) || (k >= current.value && current.right != null)) {
                stack1.push(current);

                if (current.value <= k) { // K is right child or of right subtree
                    current = current.right;
                } else { // K is left child or of left subtree
                    current = current.left;
                }
            } // current is now set to desired parent of K

            stack1.push(current);

            if (k < current.value) { // make K left child
                current.left = new Node(k);
                current.left = K;
            } else { // OR make K right child
                current.right = new Node(k);
                current.right = K;
            }
        }

        bottomUp(K, stack1); // Push K to root
        travel(root); // Print tree

    }

    // Method calls searchFunction() to identify node and move it to root
    // then performs replacement of root and prints new tree via travel()

    void delete(int k) {
        Stack<Node> stack2 = new Stack<Node>(); // Store parents

        System.out.print("\n\nDeleting: " + k);

        // Search for K

        if (searchFunction(k)) {

            // If K was found, it is now root

            if (root.right == null) {
                // no right subtree, top of left subtree is now root

                root = root.left;
            } else if (root.left == null) {
                // no left subtree, top of right subtree is now root

                root = root.right;
            } else {

                Node current = root.left;

                // identify largest value of left subtree

                while (current.right != null) {
                    stack2.push(current);
                    current = current.right;
                }

                if (!stack2.empty()) {

                    // If largest value is not the top of left subtree, set parent to point to null

                    Node parent = stack2.pop();
                    parent.right = null;
                }

                // make value root, thus removing K

                current.right = root.right;
                root.left = current.left;
                root = current;
            }

            travel(root);
        }
    }

    // Method identifies if tree contains value k
    // calls bottomUp() to perform rotations to move node
    // for use by deletion and search methods
    // returns true if value is found in tree

    boolean searchFunction(int k) {
        Node current = root;
        Stack<Node> stack3 = new Stack<Node>();

        while ((k < current.value && current.left != null) || (k > current.value && current.right != null)) {
            stack3.push(current);
            if (current.value < k) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        bottomUp(current, stack3);

        if (current.value == k) {
            return true;
        } else { // k is not found in tree
            return false;
        }
    }

    // Method calls searchFunction() to perform search 
    // and travel() to print new tree, returns true if value is in tree

    boolean search(int k) {

        System.out.print("\n\nSearching for: " + k);

        if (searchFunction(k)) {
            travel(root);
            return true;
        } else { // k is not found in tree
            travel(root);
            return false;
        }
    }

    // Method performs splay rotations to move node K to root

    void bottomUp(Node K, Stack<Node> stack) {
        Node grand = null, parent = null;

        if (root == K) {
            return;
        } else { // K is not root, has at least one parent
            parent = stack.pop();

            if (!stack.empty()) { // K has grand parent
                grand = stack.pop();

                // current is Left Left Grand child "zig-zig"

                if (K.value < parent.value && parent.value < grand.value) {

                    // Right rotate about grandparent, grand becomes right child of parent

                    grand.left = parent.right;
                    parent.right = grand;

                    // Right rotate about parent, parent becomes right child of K

                    parent.left = K.right;
                    K.right = parent;

                    if (!stack.empty()) { // update new parent of K to point to K
                        if (stack.peek().value > K.value)
                            stack.peek().left = K;
                        else
                            stack.peek().right = K;
                    } else { // There is no next parent of K, K is root
                        root = K;
                    }

                }

                // Right Right Grandchild "zig-zig"

                else if (K.value >= parent.value && parent.value >= grand.value) {

                    // Left rotate about grandparent, grand becomes left child of parent

                    grand.right = parent.left;
                    parent.left = grand;

                    // Left rotate about parent, parent becomes left child of K

                    parent.right = K.left;
                    K.left = parent;

                    if (!stack.empty()) { // update new parent of K to point to K
                        if (stack.peek().value > K.value)
                            stack.peek().left = K;
                        else
                            stack.peek().right = K;
                    } else { // There is no next parent of K, K is root
                        root = K;
                    }
                }

                // Left Right Grandchild "zig-zig"

                else if (K.value < parent.value && parent.value >= grand.value) {

                    // Right rotate about parent, parent becomes right child of K

                    parent.left = K.right;
                    grand.right = K;
                    K.right = parent;

                    // Left rotate about grandparent, grand becomes left child of K

                    grand.right = K.left;
                    K.left = grand;

                    if (!stack.empty()) { // update new parent of K to point to K
                        if (stack.peek().value > K.value)
                            stack.peek().left = K;
                        else
                            stack.peek().right = K;
                    } else { // There is no next parent of K, K is root
                        root = K;
                    }

                }

                // Right Left Grandchild "zig-zag"

                else {

                    // Left rotate about parent, parent becomes left child of K

                    parent.right = K.left;
                    grand.left = K;
                    K.left = parent;

                    // Right rotate about grandparent, grand becomes right child of K

                    grand.left = K.right;
                    K.right = grand;

                    if (!stack.empty()) { // update new parent of K to point to K
                        if (stack.peek().value > K.value)
                            stack.peek().left = K;
                        else
                            stack.peek().right = K;
                    } else { // There is no next parent of K, K is root
                        root = K;
                    }
                }
            } else { // K is direct child of root
                Node temp = root;

                // K is Left child

                if (K.value < root.value) {
                    temp.left = K.right;

                    // K becomes root

                    temp.left = K.right;
                    root = K;
                    root.right = temp;
                }

                // K is Right Child
                else {
                    temp = root;

                    // K becomes root

                    temp.right = K.left;
                    root = K;
                    root.left = temp;
                }
            }
            if (root != K)
                bottomUp(K, stack); // recurr until K is root
        }
    }

    // Print tree via preorder traversal

    void travel(Node current) {

        if (root == current) {
            System.out.print("\n" + current.value + "RT");
        }

        if (current.left != null) { // print left child
            System.out.print(", " + current.left.value + "L");
            travel(current.left); // print left child until bottom layer is reached
        }

        if (current.right != null) {
            System.out.print(", " + current.right.value + "R");
            travel(current.right);
            return;
        }
    }
}

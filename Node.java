/*Whitney Humecky
 * Project 3
 * CS 3345.0U2
 * Dr. Zhao
 * Class creates and stores nodes and relevant data
 */

public class Node {
    Node root, left, right;
    int value;

    Node(){}
    public Node(int k){
    	value = k;
        left = null;
        right = null;
    }
}
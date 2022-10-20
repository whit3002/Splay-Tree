/*Whitney Humecky
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

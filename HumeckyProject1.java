/* Whitney Humecky 
 * Project 1
 * CS 3345.0U2
 * Dr. Zhao
*/

// Program utiizes Splay class functions to contruct a splay tree

public class HumeckyProject1 {

    // Driver class
    public static void main(String[] args) {

        Splay tree = new Splay();

        // Expected values to print

        tree.insert(1); // 1RT
        tree.insert(5); // 5RT, 1L
        tree.insert(3); // 3RT, 1L, 5R
        tree.insert(2); // 2RT, 1L, 3R, 5R
        tree.insert(10); // 10RT, 2L, 1L, 5R, 3L

        System.out.print("\n" + tree.search(3)); // 3RT, 2L, 1L, 10R, 5L & true

        tree.delete(3); // 2RT, 1L, 10R, 5L
        tree.delete(10); // 2RT, 1L, 5R

        System.out.print("\n" + tree.search(3)); // 5RT, 2L, 1L & false

        tree.insert(100); // 100RT, 5L, 2L, 1L
        tree.insert(52); // 52RT, 5L, 2L, 1L, 100R
        tree.insert(43); // 43RT, 5L, 2L, 1L, 52R, 100R
        tree.insert(65); // 65RT, 43L, 5L, 2L, 1L, 52R, 100R
        
        tree.delete(100); // 65RT, 43L, 5L, 2L, 1L, 52R
        tree.delete(1); // 43RT, 2L, 5R, 65R, 52L
    }
}

package Assignment_1._1_5_14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WeightedQuickUnion extends UF {

    // MARK: INITIALIZE

    // Keep track of tree depth for all id's
    private List<Integer> depth = new ArrayList<Integer>();

    // Construct superclass
    public WeightedQuickUnion(int N) {
        super(N); // UF initialization
        this.depth.addAll(Collections.nCopies(N, 0)); // All node depths trivially 0 at the start
    }

    // MARK: DEBUG METHODS

    // Get the list containing depths (for debug purposes)
    public List<Integer> listDepth() { return this.depth; };

    // Get individual depth of node (returns depth of root for accuracy)
    public int getDepth(int p) {
        int root = this.find(p);
        return this.depth.get(root);
    }

    // Track down the root of p and display path (for debug purposes)
    public void displayPath(int p) {
        String display = "";
        while (p != this.id[p]) {
            display +=  String.valueOf(p) + " -> ";
            p = this.id[p];
        }
        display += String.valueOf(p);
        System.out.println(display);
    }

    // Display the direct connection (for debug purposes)
    public void displayDirect(int p) {
        System.out.println(this.id[p]);
    }

    // MARK: D.S. IMPLEMENTATION

    /*
    - Override superclass 'find' for a more efficient UF implementation
    - In this new WeightedQuickUnion structure, where p represents id, id[p] represents the node 
      DIRECTLY CONNECTED to p
    - We track down the nodes until we find the root node, where root_id = id[root_id].
    */
    @Override
    public int find(int p) {
        while (p != this.id[p]) {
            p = this.id[p];
        }
        return p;
    }

    /*
    - 'union' for WeightedQuickUnion
    - Different from UF, we get the depth of p and q and assign the root of smaller tree to the larger root.
    - Smaller tree depth encourages more efficient computations on the d.s.
    - The depth of the new tree will either be equal to the larger tree, or (if both depths are the same) 
      smaller tree depth + 1. This is why we assign new depth as the maximum of 'big root' or 'small root + 1'
    */
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        // If directly connected, return from method
        if (pRoot == qRoot) return;
        // Else, give p and q the same root (id[qRoot] = pRoot or vice-versa)
        int pDepth = this.getDepth(p);
        int qDepth = this.getDepth(q);
        // Add shorter tree to the taller one
        if (pDepth > qDepth) {
            this.id[qRoot] = pRoot;
            this.depth.set(pRoot, Math.max(pDepth, qDepth + 1));
        } else {
            this.id[pRoot] = qRoot;
            this.depth.set(qRoot, Math.max(qDepth, pDepth + 1));
        }
    }

    // MARK: MAIN
    public static void main(String[] args) {
        WeightedQuickUnion uf = new WeightedQuickUnion(20);
        // Tree1
        uf.union(3,5);
        uf.union(3,2);
        uf.union(6,2);
        // Tree2
        uf.union(11,12);
        uf.union(13,14);
        uf.union(11,14);
        // Combine two trees
        uf.union(3, 13);
        // Manually testing (depth) accuracy
        uf.displayPath(6);
        uf.displayPath(13);
        uf.displayPath(12);
        uf.displayPath(11);
        // Display the full depth list (only the root values matter)
        List<Integer> depth_list = uf.listDepth();
        System.out.println(depth_list);
        // These are all supposed to return the max depth of the connected tree (root 5 in our case)
        // They're all going to return the same value
        System.out.println("Depth of node #6: "+ uf.getDepth(6));
        System.out.println("Depth of node #13: "+ uf.getDepth(13));
        System.out.println("Depth of node #12: "+ uf.getDepth(12)); 
        System.out.println("Depth of node #11: "+ uf.getDepth(11));
    }
}

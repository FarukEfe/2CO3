package Assignment_1._1_5_14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// We're going to debug the depth calculation by making sure that we assign the depth of subtree to 
public class WeightedQuickUnion extends UF {

    // We're going to keep track of depth at each id for optimizing the tree to shortest depth
    private List<Integer> depth = new ArrayList<Integer>();

    // Construct superclass
    public WeightedQuickUnion(int N) {
        super(N);
        this.depth.addAll(Collections.nCopies(N, 0));
    }

    // Override find method for QuickUnion implementation
    // "depth" variable helps us cold-update the depth at index p, which is helpful in the union operation
    @Override
    public int find(int p) {
        while (p != this.id[p]) {
            p = this.id[p];
        }
        return p;
    }

    public List<Integer> listDepth() { return this.depth; };

    public int getDepth(int p) {
        int root = this.find(p);
        return this.depth.get(root);
    }

    public void displayPath(int p) {
        String display = "";
        while (p != this.id[p]) {
            display +=  String.valueOf(p) + " -> ";
            p = this.id[p];
        }
        display += String.valueOf(p);
        System.out.println(display);
    }

    public void displayDirect(int p) {
        System.out.println(this.id[p]);
    }

    // Override union method for QuickUnion implementation
    @Override
    public void union(int p, int q) {
        int pRoot = find(p); // Get the root of p
        int qRoot = find(q); // Get the root of q
        // If directly connected, return from method
        if (pRoot == qRoot) return;
        // Else, give p and q the same root
        // The reason why we give p and q the same root is because this way, the tree depth will grow much smaller.
        // If the tree depth is smaller, some methods and computations will become faster to run.
        int pDepth = this.getDepth(p);
        int qDepth = this.getDepth(q);
        // Add shorter tree to the taller one
        // We don't need to update the depth values here, since depth @ indexes p and q are cold-updated inside 'find'
        if (pDepth > qDepth) {
            this.id[qRoot] = pRoot;
            this.depth.set(pRoot, Math.max(pDepth, qDepth + 1));
        } else {
            this.id[pRoot] = qRoot;
            this.depth.set(qRoot, Math.max(qDepth, pDepth + 1));
        }
    }

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

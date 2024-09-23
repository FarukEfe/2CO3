package Assignment_1.UF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightedQuickUnion extends UF {
    // We're going to keep track of depth at each id for optimizing the tree to shortest depth
    private List<Integer> depth = new ArrayList<Integer>();
    public List<Integer> listDepth() { return this.depth; };
    public int getDepth(int p) {
        int count = 0;
        while (p != this.id[p]) {
            count++;
            p = this.id[p];
        }
        return count;
    }

    // Construct superclass
    public WeightedQuickUnion(int N) {
        super(N);
        this.depth.addAll(Collections.nCopies(N, 0));
    }

    // Override find method for QuickUnion implementation
    // "depth" variable helps us cold-update the depth at index p, which is helpful in the union operation
    @Override
    public int find(int p) {
        //int depth = 0;
        int original = p; // Keeps original index for depth calculation
        while (p != this.id[p]) {
            p = this.id[p];
            //depth++;
        }
        //this.depth.add(original, depth);
        return p;
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
        } else {
            this.id[pRoot] = qRoot;
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnion uf = new WeightedQuickUnion(20);
        // Print statements b4
        int p_depth = uf.getDepth(8);
        boolean connected = uf.connected(3, 4);
        System.out.println("Before:");
        System.out.println(p_depth);
        System.out.println(connected);
        // Make some unions
        uf.union(3,5);
        uf.union(3,2);
        uf.union(6,2);
        // Manually testing algorithm accuracy
        uf.displayPath(3);
        uf.displayPath(2);
        uf.displayPath(6);
        int depth = uf.getDepth(6);
        int root = uf.find(6);
        System.out.println("Depth of node 6 is: " + depth + " with root " + root);
    }
}

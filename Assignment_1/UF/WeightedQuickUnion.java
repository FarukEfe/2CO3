package Assignment_1.UF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightedQuickUnion extends UF {
    // We're going to keep track of depth at each id for optimizing the tree to shortest depth
    private List<Integer> depth = new ArrayList<Integer>();
    public int getDepth(int p) { return this.depth.get(p); };

    // Construct superclass
    public WeightedQuickUnion(int N) {
        super(N);
        this.depth.addAll(Collections.nCopies(N, 0));
    }

    // Override find method for QuickUnion implementation
    // "depth" variable helps us cold-update the depth at index p, which is helpful in the union operation
    @Override
    public int find(int p) {
        int depth = 0;
        while (p != this.id[p]) {
            p = this.id[p];
            depth++;
        }
        this.depth.add(p, depth);
        return p;
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
        // Making sure the depth methods work
        int p_depth = uf.getDepth(4);
        System.out.println(p_depth);
        // Make some unions
        uf.union(3,4);
        uf.union(5,3);
        uf.union(6,8);
        uf.union(6, 11);
        uf.union(6,4);
        p_depth = uf.getDepth(6);
        System.out.println(p_depth);
    }
}

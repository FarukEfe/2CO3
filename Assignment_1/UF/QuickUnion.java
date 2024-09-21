package Assignment_1.UF;

import java.util.*;
// In the QuickUnion approach to UF data structure and its operations. We interpret the data a little differently.
// In this structure, each site p (represents id) holds value id[p] that refers to its connected site q (represents id).
// The base site of id "p" will contain the id of itself (p == id[p]), in which case we have found the common value of all connected sites.
// This reduces the "union" operation to constant time, since all we do is assign id "q" to value @ id[p] storage.
// When it comes to the "find" method, we jump from one element to another until the current site is the root (e.g., p == id[p]).
public final class QuickUnion extends UF {

    // We're going to keep track of depth at each id for optimizing the tree to shortest depth
    private List<Integer> depth = new ArrayList<Integer>();
    public int getDepth(int p) { return this.depth.get(p); };

    // Construct superclass
    public QuickUnion(int N) {
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
        QuickUnion union = new QuickUnion(20);
        // Making sure the depth methods work
        /*
        int p_depth = union.getDepth(4);
        System.out.println(p_depth);
        */
    }
}

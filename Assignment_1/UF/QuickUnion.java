package Assignment_1.UF;


// In the QuickUnion approach to UF data structure and its operations. We interpret the data a little differently.
// In this structure, each site p (represents id) holds value id[p] that refers to its connected site q (represents id).
// The base site of id "p" will contain the id of itself (p == id[p]), in which case we have found the common value of all connected sites.
// This reduces the "union" operation to constant time, since all we do is assign id "q" to value @ id[p] storage.
// When it comes to the "find" method, we jump from one element to another until the current site is the root (e.g., p == id[p]).
public class QuickUnion extends UF {

    // Construct superclass
    public QuickUnion(int N) {
        super(N);
    }

    // Override find method for QuickUnion implementation
    @Override
    public int find(int p) {
        while (p != this.id[p]) p = this.id[p];
        return p;
    }

    // Override union method for QuickUnion implementation
    @Override
    public void union(int p, int q) {
        int pRoot = this.id[p];
        int qRoot = this.id[q];
        // If directly connected, return from method
        if (pRoot == qRoot) return;
        // Else, give p and q the same root
        // The reason why we give p and q the same root is because this way, the tree depth will grow much smaller.
        // If the tree depth is smaller, some methods and computations will become faster to run.
        this.id[pRoot] = qRoot;
    }
}

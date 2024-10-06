package A1._1_5_14;


// 'UF' is a very fundamental representation of Union Find Tree Structure.
public class UF {
    protected int[] id; // For an arbitrary id 'p', id[p] represents its root
    protected int count; // Number of separate clusters/components

    // Initializer
    public UF(int N) {
        this.count = N;
        this.id = new int[N];
        for (int i=0;i<N;i++) {
            id[i] = i;
        }
    }

    public int countNodes() {
        return this.count;
    }
    // Time complexity O(1) (constant)

    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }
    // Assuming 'find' is constant time complexity, 'connected' has O(1) (constant) time complexity

    /* 
    - Get the root value @ p
    - If two id's p and q have the same value, then they're a part of the same tree.
    */
    public int find(int p) {
        return this.id[p];
    }
    // Time complexity O(1) (constant)

    /*
    - Basic algorithm for establishing union between two nodes.
    - As a result, these nodes and their connected nodes should all be connected to one another,
      i.e. connected(p, q) for any p and q sites in the d.s.
    */
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        // If already connected, then ignore & return
        if (pID == qID) { return; }
        // Else, go through all of the id's and replace value of any node connected to p to qID (connect all pID nodes to q)
        // Change root values of each node in pID to qID
        for (int i=0;i<this.id.length;i++) {
            if (this.id[i] == pID) this.id[i] = qID;
        }
        this.count--; // Reduce total number of clusters in the d.s.
    }

    // Making sure the code properly works
    public static void main(String[] args) {
        int N = 10;
        UF uf = new UF(N);
        // Make following connections
        uf.union(1,5);
        uf.union(2,3);
        uf.union(8,1);
    }
}

/*
 * This application of UF data structure surely has a fast 'find' method. However, when we're dealing with bigger trees, union method slows down on a linear scale.
 * 
*/
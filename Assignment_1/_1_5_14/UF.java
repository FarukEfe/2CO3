package Assignment_1._1_5_14;

public class UF {
    protected int[] id; // List of all nodes @ id's
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

    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    public int find(int p) {
        return this.id[p];
    }


    // For the assignment purposes, you need to replace the minority cluster value with the majority. This is called "Weighted quick-union by height"
    // Re-read the prompt to ensure you've properly understood the assignment question(s) (1.5.14, 1.5.15)
    public void union(int p, int q) {
        // Get value of id's p and q
        // Common values represent connections; two id's with same value in storage are connected to one another
        int pID = find(p);
        int qID = find(q);
        // If already connected, then ignore & return
        if (pID == qID) { return; }
        // Else, go through all of the id's and replace value of any node connected to p to qID (connect all pID nodes to q)
        for (int i=0;i<this.id.length;i++) {
            if (this.id[i] == pID) this.id[i] = qID;
        }
        this.count--; // Reduce total number of clusters by 1 (new merge)
    }

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
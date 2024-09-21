package Assignment_1;

public class UF {
    private int[] id; // List of all nodes @ id's
    private int count; // Number of separate clusters/components

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
        while (p != this.id[p]) p = id[p];
        return p;
    }

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
        this.count--;
    }

    public static void main(String[] args) {
        int N = 10;
        UF uf = new UF(N);
        // Connect 1 to 5
        uf.union(1,5);
        uf.union(2,3);
        uf.union(8,1);
    }
}

package Assignment_1._1_5_15;

public class BinomialTree {

    private int factorial(int k) {

        if (k == 0) return 1;

        int factorial = 1;
        for (int i=1;i<=k;i++) {
            factorial = factorial * i;
        }

        return factorial;
    }
    
    public double computeAvgDepth(int n) {
        double total_nodes = Math.pow(2,n);
        double total_depth = 0;

        int n_factorial = this.factorial(n); // No need to re-compute a fixed value, so stays outside the loop
        for (int i=0;i<=n;i++) {
            int binom_nodes = n_factorial / (this.factorial(n - i) * this.factorial(i)); // Pascal's Identity
            int cum_depth_i = i * binom_nodes;
            total_depth += cum_depth_i;
        }
        System.out.println(total_depth + " " + total_nodes);
        return total_depth/total_nodes;
    }

    public static void main(String[] args) {
        int max_depth = 3;
        BinomialTree tree = new BinomialTree();
        double avg_node_depth = tree.computeAvgDepth(max_depth);
        System.out.println("Average node depth is: " + avg_node_depth + ".");
    }
}

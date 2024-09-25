package Assignment_1._1_3_45;

public class StackGenerability {

    // Returns 0 if stack underflows, 1 otherwise
    // N represents total number of directives for push and pop, each.
    public static int checkGenerability(int N) {
        // Initialized N push and N pop directives & total number of directives is 2 * N
        int n_push = N; int n_pop = N; int length = 2 * N;
        // Variable to emulate stack length
        int stack_len = 0;
        for (int i=0;i<length;i++) {
            // Randomly choose to push or pop (push = 1 and pop = 0)
            int rand = (Math.random() < 0.5) ? 0 : 1;
            // Update stack_length based on the push or pop directive
            stack_len = (rand == 1) ? stack_len + 1 : stack_len - 1;
            // Update remaining push and pop directives
            n_push = (rand == 1) ? n_push - 1 : n_push;
            n_pop = (rand == 0) ? n_pop - 1 : n_pop;

            if (stack_len < 0) {
                System.out.println("push performed is " + (N - n_push) + " and pop performed is " + (N-n_pop));
                return 0;
            }
        }
        System.out.println("push performed is " + (N - n_push) + " and pop performed is " + (N-n_pop));
        return 1;
    }
    // A basic iteration loop, giving it a time complexity of O(N)

    public static void main(String[] args) {
        int N = 20;
        int generatable = checkGenerability(N);
        System.out.println("The generability status of this stack sequence is: " + generatable);
    }
}

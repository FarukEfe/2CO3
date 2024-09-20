package Assignment_1;

import java.util.*;
import java.math.*;

public class StackGenerability {
    
    // 0 represents pop, 1 represents push
    public static List<Integer> makeSequence(int N) {
        int n_push = N; int n_pop = N; int length = 2 * N;

        List<Integer> sequence = new ArrayList<Integer>();
        for (int i=0;i<length;i++) {
            
            int rand = (Math.random() < 0.5) ? 0 : 1;
            if (n_push == 0) {
                rand = 0;
            } else if (n_pop == 0) {
                rand = 1;
            }

            sequence.add(rand);
            if (rand == 0) {
                n_pop--;
            } else {
                n_push--;
            }
        }
        return sequence;
    }

    // Sequence represents push/pull directives as 1/0
    // Returns 0 if stack underflows, 1 otherwise
    public static int checkGenerability(List<Integer> sequence) {

        int stack_len = 0;
        for (int i=0;i<sequence.size();i++) {
            int item = sequence.get(i);

            stack_len = (item == 1) ? stack_len + 1 : stack_len - 1;

            if (stack_len < 0) {
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        int N = 2;
        List<Integer> random_sequence = makeSequence(N);
        int generatable = checkGenerability(random_sequence);
        System.out.println("For sequence of lenght " + N + " being generated as:\n" + random_sequence + "\n");
        System.out.println("The generability status of this stack sequence is: " + generatable);
    }
}

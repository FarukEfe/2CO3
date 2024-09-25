package Assignment_1._1_4_34;

import java.util.*;

final class Solution {
    int steps;
    boolean solved;

    public Solution(int steps, boolean solved) {
        this.steps = steps; this.solved = solved;
    }
}

final class TestMeasures {
    List<Long> durations = new ArrayList<Long>();
    List<Integer> steps = new ArrayList<Integer>();
}

// Provides Algorithmic Solution and Test Cases for 1.4.34 in the textbook
public class GuessTheNumber {

    // MARK: SOLUTION
    
    /*
    - Initial guess is 1 and the next guess is N
    - If next guess is closer to answer than the previous (a.k.a Hotter), pull previous guess up/down to half the
      guess range.
    - Repeat the same process until a solution is found, or max > min (which means no solution).
    */
    public static Solution solve(int N, int num, boolean message) {

        int step_count = 0; boolean solved = false;
        int max = N; int min = 1;
        int guess = min;
        do {
            step_count++;
            int min_diff = Math.abs(num-min);
            int max_diff = Math.abs(num-max);

            if (min_diff == 0 || max_diff == 0) {
                System.out.println("Found!!!");
                solved = true;
                break;
            }

            if (min_diff > max_diff) {
                if (message) System.out.println((guess == max) ? "Hotter" : "Colder");
                min = Math.round((max+min) / 2) + 1;
                guess = min;
            } else {
                if (message) System.out.println((guess == min) ? "Hotter" : "Colder");
                max = Math.round((max+min) / 2);
                guess = max;
            }
        } while (max > min);

        Solution prf = new Solution(step_count, solved);
        return prf;
    }

    // MARK: TESTING

    public static TestMeasures test(int up_to) {
        List<Long> time_list = new ArrayList<Long>();
        List<Integer> steps_list = new ArrayList<Integer>();
        for (int N=1;N<up_to + 1;N++) {
            // Measure execution time & get duration
            final long start = System.currentTimeMillis();
            Solution result = solve(N, N+1, false);
            final long end = System.currentTimeMillis();
            final long duration = end - start;
            // Print statement
            System.out.println("Total worst-case steps for N = " + N + ": " + result.steps);
            // Record data
            time_list.add(duration);
            steps_list.add(result.steps);
        }
        TestMeasures measures = new TestMeasures();
        measures.durations = time_list;
        measures.steps = steps_list;
        return measures;
    }

    public static void customTest(int N, int num) {
        Solution res = solve(N, num, true);
        String statement = (res.solved) ? "Guess #" + num + " found in " + res.steps + " steps." : "No solution found.";
        System.out.println(statement);
    }

    // MARK: MAIN
    
    public static void main(String[] args) {

        Solution result = solve(100, 49, true);
        System.out.println((result.solved) ? "Solved in " + result.steps + " steps" : "Couldn't find a solution.");
        TestMeasures __ = test(100000);
    }   
}

package Assignment_1;

import java.util.*;
import java.math.*;

final class Metrics {
    int steps = 0;
    int solved = 0;
}

final class TestMeasures {
    List<Long> durations = new ArrayList<Long>();
    List<Integer> steps = new ArrayList<Integer>();
}

// Provides Algorithmic Solution and Test Cases for 1.4.34 in the textbook
public class GuessTheNumber {
    
    public static Metrics solve(int N, int num) {

        int step_count = 0; int solved = 0;
        int max = N; int min = 1;

        do {
            step_count++;
            int min_diff = Math.abs(num-min);
            int max_diff = Math.abs(num-max);

            if (min_diff == 0 || max_diff == 0) {
                solved = 1;
                break;
            }

            if (min_diff > max_diff) {
                min = Math.round((max+min) / 2) + 1;
            } else {
                max = Math.round((max+min) / 2);
            }
        } while (max > min);

        Metrics prf = new Metrics();
        prf.steps = step_count;
        prf.solved = solved;
        return prf;
    }

    public static TestMeasures test(int up_to) {
        List<Long> time_list = new ArrayList<Long>();
        List<Integer> steps_list = new ArrayList<Integer>();
        for (int N=1;N<up_to;N++) {
            // Measure execution time & get duration
            final long start = System.currentTimeMillis();
            Metrics result = solve(N, N+1);
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
        Metrics res = solve(N, num);
        String statement = (res.solved == 1) ? "Guess #" + num + " found in " + res.steps + " steps." : "No solution found.";
        System.out.println(statement);
    }
    
    public static void main(String[] args) {

        //customTest(50, 28);
        TestMeasures tests_result = test(100000);
    }   
}

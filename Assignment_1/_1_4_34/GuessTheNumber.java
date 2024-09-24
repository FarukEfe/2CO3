package Assignment_1._1_4_34;

import java.util.*;

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
        int guess = min;
        do {
            step_count++;
            int min_diff = Math.abs(num-min);
            int max_diff = Math.abs(num-max);

            if (min_diff == 0 || max_diff == 0) {
                System.out.println("Found!!!");
                solved = 1;
                break;
            }

            if (min_diff > max_diff) {
                System.out.println((guess == max) ? "Hotter" : "Colder");
                min = Math.round((max+min) / 2) + 1;
                guess = min;
            } else {
                System.out.println((guess == min) ? "Hotter" : "Colder");
                max = Math.round((max+min) / 2);
                guess = max;
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

        Metrics result = solve(80, 58);
        System.out.println((result.solved == 1) ? "Solved in " + result.steps + " steps" : "Couldn't find a solution.");
        //TestMeasures tests_result = test(100000);
    }   
}

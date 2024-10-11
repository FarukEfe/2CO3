package A2._2_5_20;

import java.util.*;
/*
Assuming we have a list of tuples of jobs (start time, end time), we're trying to find largest and smallest
intervals of idle time and working time
*/
public class IdleTime {

    public void printTupleList(List<Tuple<Integer,Integer>> list) {
        for (int i=0;i<list.size();i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println("");
    }
    // Create tuple-list from start and end times (assumption is that both parameter lists are always the same length)
    public List<Tuple<Integer,Integer>> makeTupleList(int[] starts, int[] ends) {
        List<Tuple<Integer,Integer>> jobs = new ArrayList<Tuple<Integer,Integer>>();
        for (int i=0;i<starts.length;i++) {
            Tuple<Integer,Integer> item = new Tuple<Integer,Integer>(starts[i], ends[i]);
            jobs.add(item);
        }
        return jobs;
    }

    // Bubble sort the jobs
    public void sort(List<Tuple<Integer,Integer>> list) {
        for (int i=0;i<list.size();i++) {
            for (int j=0;j<list.size() - 1;j++) {
                Tuple<Integer,Integer> item = list.get(j);
                Tuple<Integer,Integer> next = list.get(j+1);
                if (item.getFirst() > next.getFirst()) {
                    list.set(j, next); list.set(j+1, item);
                }
            }
        }
    }

    public Tuple<Integer,Integer> solve(List<Tuple<Integer,Integer>> jobs) {
        // Sort jobs by starting time
        this.sort(jobs);
        // Merge overlapping jobs
        int pivot_idx = 0;
        int i = 1;
        while (i<jobs.size()) {
            Tuple<Integer,Integer> pivot_job = jobs.get(pivot_idx);
            Tuple<Integer,Integer> item = jobs.get(i);
            // If item job starts during the execution of pivot_job merge
            if (pivot_job.getSecond() >= item.getFirst()) {
                // Assign the bigger ending time to the 2nd element of pivot
                pivot_job.setSecond((item.getSecond() > pivot_job.getSecond()) ? item.getSecond() : pivot_job.getSecond());
                // Remove next job since we've made sure that jobs list is sorted (item.start > pivot.end in this case)
                jobs.remove(i);
            } else {
                // Set new pivot point
                pivot_idx = i;
                i++;
            }
        }
        this.printTupleList(jobs);
        // Get longest busy & idle intervals
        int longest_busy = 0;
        int longest_idle = 0;
        i = 0;
        while (i<jobs.size()) {
            int time_busy = jobs.get(i).getSecond() - jobs.get(i).getFirst();
            int time_idle = (i >= jobs.size() - 1) ? 0 : jobs.get(i+1).getFirst() - jobs.get(i).getSecond();
            if (time_busy > longest_busy) {
                longest_busy = time_busy;
            }
            if (time_idle > longest_idle) {
                longest_idle = time_idle;
            }
            i++;
        }
        return new Tuple<Integer,Integer>(longest_busy, longest_idle);
    }
    public static void main(String[] args) {
        IdleTime solver = new IdleTime();
        int[] starts = {5,12,16,13,9,20};
        int[] ends = {8,14,19,14,22,21};
        List<Tuple<Integer,Integer>> jobs = solver.makeTupleList(starts, ends);
        solver.printTupleList(jobs);
        solver.sort(jobs);
        solver.printTupleList(jobs);
        Tuple<Integer,Integer> solution = solver.solve(jobs);
        System.out.println(solution);
    }
}

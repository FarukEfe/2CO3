package Assignment_1._1_4_17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmallestLargest {
    
    public static void smallestLargest(List<Integer> list) {
        int smallest = list.get(0);
        int largest = list.get(0);;
        for (int i=0;i<list.size();i++) {
            int item = list.get(i);
            smallest = (item  < smallest) ? item : smallest;
            largest = (item > largest) ? item : largest;
        }
        System.out.println("Smallest in list: " + smallest + ", Largest in list: " + largest);
    }

    public static void main(String[] args) {
        List<Integer> sorted_1 = new ArrayList<Integer>(Arrays.asList(1,1,2,-6,5,8,99,9,18,15,17));
        smallestLargest(sorted_1);
    }
}

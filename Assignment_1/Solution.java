package Assignment_1;

import java.util.*;
import java.math.*;

public class Solution {

    // This algorithm provides solution to the problem on 1.4.12 on the Algorithms textbook.
    public static void commonElements(List<Integer> sorted_1, List<Integer> sorted_2) {

        int l1_size = sorted_1.size();
        int l2_size = sorted_2.size();
        int i_1 = 0;
        int i_2 = 0;
        while (i_1 < l1_size && i_2 < l2_size) {
            int item_1 = sorted_1.get(i_1);
            int item_2 = sorted_2.get(i_2);
            if (item_1 > item_2) {
                i_2++;
            } else if (item_1 < item_2) {
                i_1++;
            } else {
                System.out.println("Common item @ i_1=" + i_1 + " and i_2=" + i_2 + ": " + item_1);
                i_1++;
                i_2++;
            }
        }
    }

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
        List<Integer> sorted_1 = new ArrayList<Integer>(Arrays.asList(1,1,2,3,5,8,8,9,11,15,18));
        List<Integer> sorted_2 = new ArrayList<Integer>(Arrays.asList(1,3,8,8,9,12,19));
        commonElements(sorted_1, sorted_2);
        smallestLargest(sorted_1);
    }
}

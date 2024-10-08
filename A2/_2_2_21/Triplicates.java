package A2._2_2_21;

import java.util.*;

import A2._2_2_22.MSThreeWay;

public class Triplicates {
    

    public static MSThreeWay ms = new MSThreeWay();
    /*
     * Return the lexicographically smallest common string encounter. Return "" if there is none.
     * Our assumption is that l1, l2, and l3 are all sorted lists at this point.
     */
    public String encounter(String[] l1, String[] l2, String[] l3) {
        
        int i_1 = 0;
        int i_2 = 0;
        int i_3 = 0;
        while (i_1<l1.length && i_2<l2.length && i_3<l3.length) {
            String item_1 = l1[i_1];
            String item_2 = l2[i_2];
            String item_3 = l3[i_3];
            // If strings are equal, return
            if (item_1 == item_2 && item_2 == item_3) {
                return item_1;
            }
            // Increase index of smaller entries
            String greatest = Triplicates.ms.greatest(item_1, item_2, item_3);
            i_1 = (item_1 == greatest) ? i_1 : i_1 + 1;
            i_2 = (item_2 == greatest) ? i_2 : i_2 + 1;
            i_3 = (item_3 == greatest) ? i_3 : i_3 + 1;
        }

        return "";
    }

    public String solve(String[] l1, String[] l2, String[] l3) {
        MSThreeWay ms = Triplicates.ms;
        l1 = ms.mergesort(l1);
        l2 = ms.mergesort(l2);
        l3 = ms.mergesort(l3);
        System.out.println(l1); System.out.println(l2); System.out.println(l3);
        return this.encounter(l1, l2, l3);
    }

    public static void main(String[] args) {
        Triplicates solution = new Triplicates();

        String[] example1 = {"Haha","lol","not","adhige", "stpd"};
        String[] example2 = {"adhige","Haa","not","adhe", "stpd"};
        String[] example3 = {"Haha","ll","not","adhige"};

        String result = solution.solve(example1, example2, example3);
        System.out.println(result);
    }
}

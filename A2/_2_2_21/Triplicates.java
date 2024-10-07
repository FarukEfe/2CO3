package A2._2_2_21;

import java.util.*;

public class Triplicates {
    
    /*
     * Return if str1 and str2 are equals.
     */
    public boolean equals(String str1, String str2) {
        return (str1.compareTo(str2) == 0) ? true : false;
    }

    /*
     * Return if str1 is greater than str2.
     */
    public boolean greater(String str1, String str2) {
        return (str1.compareTo(str2) == 1) ? true : false;
    }

    /*
     * Get the greates out of str1, str2, and str3.
     */
    public String greatest(String str1, String str2, String str3) {
        String result = (this.greater(str1, str2)) ? ((this.greater(str1, str3)) ? str1 : str3) : ((this.greater(str2, str3)) ? str2 : str3);
        return result;
    }

    public String smallest(String str1, String str2, String str3) {
        String result = (this.greater(str1, str2)) ? ((this.greater(str2, str3)) ? str3 : str2) : ((this.greater(str1, str3)) ? str3 : str1);
        return result;
    }

    /*
     * Return the lexicographically smallest common string encounter. Return "" if there is none.
     * Our assumption is that l1, l2, and l3 are all sorted lists at this point.
     */
    public String encounter(List<String> l1, List<String> l2, List<String> l3) {
        
        int i_1 = 0;
        int i_2 = 0;
        int i_3 = 0;
        while (i_1<l1.size() && i_2<l2.size() && i_3<l3.size()) {
            String item_1 = l1.get(i_1);
            String item_2 = l2.get(i_2);
            String item_3 = l3.get(i_3);
            // If strings are equal, return
            if (item_1 == item_2 && item_2 == item_3) {
                return item_1;
            }
            // Increase index of smaller entries
            String greatest = this.greatest(item_1, item_2, item_3);
            i_1 = (item_1 == greatest) ? i_1 : i_1 + 1;
            i_2 = (item_2 == greatest) ? i_2 : i_2 + 1;
            i_3 = (item_3 == greatest) ? i_3 : i_3 + 1;
        }

        return "";
    }

    public List<String> merge(List<String> l1, List<String> l2, List<String> l3) {

        List<String> merged = new ArrayList<String>();
        while (l1.size() > 0 && l2.size() > 0) {
            String item_1 = l1.get(0);
            String item_2 = l2.get(0);
            if (greater(item_1,item_2)) {
                merged.addLast(item_2);
                l2.remove(0);
            } else {
                merged.addLast(item_1);
                l1.remove(0);
            }
        }
        // One of these lists is surely empty, so this won't break the sort order
        merged.addAll(l1);
        merged.addAll(l2);
        // Add elements from l3
        int add_idx = 0; // To track insertion idx in merged
        while(l3.size() > 0 && add_idx < merged.size()) {
            String item = merged.get(add_idx);
            String item_3 = l3.get(0);
            if (greater(item,item_3)) {
                merged.add(add_idx, item_3);
                l3.remove(0);
            } else {
                add_idx++;
            }
        }
        // Add remaning l3
        merged.addAll(l3);
        return merged;
    }

    public List<String> mergesort(List<String> list) {
        int length = list.size();
        if (length <= 1) {
            return list;
        }
        
        List<String> l1 = list.subList(0, (int)(length / 3));
        List<String> l2 = list.subList((int)(length / 3), (int)(2 * length / 3));
        List<String> l3 = list.subList((int)(2 * length / 3), length);

        l1 = mergesort(l1);
        l2 = mergesort(l2);
        l3 = mergesort(l3);

        return merge(l1, l2, l3);
    }

    public String solve(List<String> l1, List<String> l2, List<String> l3) {
        l1 = this.mergesort(l1);
        l2 = this.mergesort(l2);
        l3 = this.mergesort(l3);

        return this.encounter(l1, l2, l3);
    }

    public static void main(String[] args) {
        Triplicates solution = new Triplicates();

        List<String> example1 = new ArrayList<String>(Arrays.asList("Haha","lol","not","adhige", "stpd"));
        List<String> example2 = new ArrayList<String>(Arrays.asList("adhige","Haa","not","adhe", "stpd"));
        List<String> example3 = new ArrayList<String>(Arrays.asList("Haha","ll","not","adhige"));

        String result = solution.solve(example1, example2, example3);
        System.out.println(result);
    }
}

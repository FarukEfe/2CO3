package A1._1_4_12;

// Provides solution to the problem on 1.4.12 on the Algorithms textbook.
public class CommonElements {

    // Finds common elements between
    public static void findCommons(int[] list_1, int[] list_2) {
        // As for the parameters, we're assuming that list_1 and list_2 will always be sorted.
        // List lengths
        int l1_size = list_1.length;
        int l2_size = list_2.length;
        // Starting indexes
        int i_1 = 0;
        int i_2 = 0;
        // Run loop until reaching the end of either one of the lists
        while (i_1 < l1_size && i_2 < l2_size) {
            int item_1 = list_1[i_1];
            int item_2 = list_2[i_2];
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
    /*
    - We know the list is sorted, so after a match at indexes i, j for first and second lists (respectively), we
      can deduce that the next match of values can only be in the next indexes.
    - This is why we can implement a linear approach as a solution. In the worst case, the program runs through
      the full length of list_1 and list_2, which gives us a linear time complexity of O(M + N), where M represents
      list_1.length and N represents list_2.length.
    */


    public static void main(String[] args) {
        int sorted_1[] = {1,1,2,3,5,8,8,9,11,15,18};
        int sorted_2[] = {1,3,8,8,9,12,19};
        findCommons(sorted_1, sorted_2);
    }

}

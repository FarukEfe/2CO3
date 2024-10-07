package A2._2_2_21;

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

    /*
     * Return the lexicographically smallest common string encounter. Return "" if there is none.
     * Our assumption is that l1, l2, and l3 are all sorted lists at this point.
     */
    public String merge_encounter(String[] l1, String[] l2, String[] l3) {
        
        int i_1 = 0;
        int i_2 = 0;
        int i_3 = 0;
        while (i_1<l1.length && i_2<l2.length && i_3<l3.length) {
            String item_1 = l1[i_1];
            String item_2 = l2[i_2];
            String item_3 = l3[i_3];

        }
        return "";
    }

    public void merge(String[] l1, String[] l2, String[] l3) {

    }

    public void mergesort(String[] list) {

    }
}

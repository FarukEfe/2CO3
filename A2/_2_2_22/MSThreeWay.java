package A2._2_2_22;

public class MSThreeWay {
    
    public boolean greater(String l1, String l2) {
        return (l1.compareTo(l2) == 1) ? true : false;
    }

    public String greatest(String str1, String str2, String str3) {
        String result = (this.greater(str1, str2)) ? ((this.greater(str1, str3)) ? str1 : str3) : ((this.greater(str2, str3)) ? str2 : str3);
        return result;
    }

    public void merge(String[] list, int s1, int e1, int s2, int e2) {
        int i_1 = s1; int i_2 = s2; int i_m = 0;
        // Merge 2 lists
        while(i_1 < e1 && i_2 < e2) {
            String item1 = list[i_1];
            String item2 = list[i_2];
            String replace = list[i_m];
            if (greater(item1, item2)) {
                list[i_m] = item2; 
                list[i_2] = replace;
                i_2++;
            } else {
                list[i_m] = item1;
                list[i_1] = replace;
                i_1++;
            }
            i_m++;
        }

        while (i_2 < e2) {
            String item2 = list[i_2];
            String replace = list[i_m];
            list[i_m] = item2; 
            list[i_2] = replace;
            i_2++; i_m++;
        }

        while (i_1 < e1) {
            String item1 = list[i_1];
            String replace = list[i_m];
            list[i_m] = item1; 
            list[i_1] = replace;
            i_1++; i_m++;
        }
    }

    public void mergesort(String[] list, int start, int end) {
        int range = end-start;
        if (range <= 2) {
            if (range == 2 && greater(list[start],list[start+1])) {
                String replace = list[start+1];
                list[start+1] = list[start];
                list[start] = replace;
            }
            return;
        }
        int partition = range / 3;
        int i1 = start+partition; int i2 = start+2*partition;
        System.out.println(partition + " " + i1 + " " + i2);
        // Merge Sort Recursive Step
        mergesort(list,start,i1);
        mergesort(list,i1,i2);
        mergesort(list,i2,end);
        // 3-Way Merge
        merge(list,start,i1,i1,i2); // Merge first two subarrays
        merge(list,start,i2,i2,end); // Merge the result of 1st merge and the remaining part
    }

    public static void main(String[] args) {
        MSThreeWay ms = new MSThreeWay();
        String[] example = {"Haha","lol","not","adhige", "stpd","stpd","stpd","stpd","stpd","stpd"};
        ms.mergesort(example,0,example.length);
        for (int i=0;i<example.length;i++) {
            System.out.println(example[i]);
        }
    }
}

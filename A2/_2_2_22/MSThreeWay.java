package A2._2_2_22;

public class MSThreeWay {
    
    public boolean greater(String l1, String l2) {
        return (l1.compareTo(l2) == 1) ? true : false;
    }

    public String greatest(String str1, String str2, String str3) {
        String result = (this.greater(str1, str2)) ? ((this.greater(str1, str3)) ? str1 : str3) : ((this.greater(str2, str3)) ? str2 : str3);
        return result;
    }

    public String[] merge(String[] l1, String[] l2) {
        String[] m = {};
        int i_1 = 0; int i_2 = 0; int i_m = 0;
        // Merge 2 lists
        while(i_1 < l1.length || i_2 < l2.length) {
            String item1 = l1[i_1];
            String item2 = l2[i_2];
            // Add remaining element of the remaining list
            if (i_1 == l1.length) {
                m[i_m] = item2; i_2++; i_m++;
                continue;
            } else if (i_2 == l2.length) {
                m[i_m] = item1; i_1++; i_m++;
                continue;
            }

            if (greater(item1, item2)) {
                m[i_m] = item2; i_2++;
            } else {
                m[i_m] = item1; i_1++;
            }
            i_m++;
        }
        return m;
    }

    public String[] mergesort(String[] list) {
        if (list.length <= 1) {
            return list;
        }

        int partition = list.length / 3;
        String[] s1 = {}; String[] s2 = {}; String[] s3 = {};

        for (int i=0;i<list.length;i++) {
            if (i < partition) {
                s1[i] = list[i];
            } else if (i < 2*partition) {
                s2[i-partition] = list[i];
            } else {
                s3[i-2*partition] = list[i];
            }
        }
        // Merge Sort Recursive Step
        s1 = mergesort(s1);
        s2 = mergesort(s2);
        s3 = mergesort(s3);
        // 3-Way Merge
        return this.merge(this.merge(s1,s2),s3);
    }

    public static void main(String[] args) {
        MSThreeWay ms = new MSThreeWay();
        String[] example = {"Haha","lol","not","adhige", "stpd"};
        System.out.println(example);
        example = ms.mergesort(example);
        System.out.println(example);
    }
}

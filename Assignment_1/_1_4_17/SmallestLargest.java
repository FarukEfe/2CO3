package Assignment_1._1_4_17;

public class SmallestLargest {
    /*
    . Print the pair of numbers with the biggest absolute value difference
    . Absolute value means that it doesn't matter which number gets subtracted, as long as the distance 
      between is the largest out of all potential pairs.
    . Intuitively, that would be the difference between the smallest and largest numbers in the list.
    */
    public static void smallestLargest(double[] list) {
        double smallest = list[0];
        double largest = list[0];
        for (int i=0;i<list.length;i++) {
            double item = list[i];
            smallest = (item  < smallest) ? item : smallest;
            largest = (item > largest) ? item : largest;
        }
        System.out.println("Smallest in list: " + smallest + ", Largest in list: " + largest);
        System.out.println("The farthest pair found in the list is: (" + smallest + ", " + largest + ").");
    }

    public static void main(String[] args) {
        double[] list = {1,1,2,-6,5,8,99,9,18,15,17};
        smallestLargest(list);
    }
}

package A2._2_3_15;

public class NutsAndBolts {

    public boolean isMatch(int nut, int bolt) {
        return nut == bolt;
    }

    public boolean isSmaller(int nut, int bolt) {
        return nut < bolt;
    }

    public void solve(int[] nuts, int[] bolts) {
        int bolt = bolts[0]; int match = -1; // Our matching pairs (we're searching for 'match')

        int[] smaller_nuts = {}; int i_s = 0;
        int[] larger_nuts = {}; int i_l = 0;
        int[] smaller_bolts = {}; int[] larger_bolts = {};
        for (int i=0;i<nuts.length;i++) { // Classify nuts and find match
            int nut = nuts[i];
            if (isMatch(nut, bolt)) { match = nut; continue; }
            if (isSmaller(nut, bolt)) { smaller_nuts[i_s++] = nut; }
            else { larger_nuts[i_l++] = nut; }
        }

        i_l = 0; i_s = 0; // Reset indexes
        for (int i=0;i<bolts.length;i++) { // Classify bolts
            int compare_bolt = bolts[i];
            if (isMatch(match, compare_bolt)) continue;
            if (isSmaller(match, compare_bolt)) { larger_bolts[i_l++] = compare_bolt; } 
            else { smaller_bolts[i_s++] = compare_bolt; }
        }

        solve(smaller_nuts,smaller_bolts);
        solve(larger_nuts,larger_bolts);
    }

    public static void main(String[] args) {
        
    }
}

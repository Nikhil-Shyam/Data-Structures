import java.util.TreeSet;

class DoubleLinear {
    
    public static int dblLinear (int n) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);

        for (int i = 0; i < n; i++) {
            int curr = set.pollFirst();

            set.add(2 * curr + 1);
            set.add(3 * curr + 1);
        }
      return set.first();
    }
}

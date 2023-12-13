import java.util.TreeSet;
import java.util.Iterator;

public class WhichAreIn { 
	
	public static String[] inArray(String[] array1, String[] array2) {
		TreeSet<String> set = new TreeSet<>(); 
    for (String i: array1){
      for (String j: array2){
        if (j.indexOf(i) >= 0)
          set.add(i);
      }
    }

    String[] ans = new String[set.size()];
    Iterator<String> it = set.iterator();
    for (int i = 0; i < set.size(); i++)
      ans[i] = it.next();

    return ans;
	}
}

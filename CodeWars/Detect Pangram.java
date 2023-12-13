import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.HashSet;

public class PangramChecker {
  public boolean check(String sentence){
    sentence = sentence.toLowerCase();

    StringTokenizer st = new StringTokenizer(sentence, "0123456789!?,. ");
    String check = "";
    while (st.hasMoreTokens())
      check += st.nextToken();

    HashSet<String> set = new HashSet<>();
    for (int i = 0; i < check.length(); i++)
      set.add(check.substring(i, i+1));

    if (set.size() == 26)
      return true;
    else
      return false;
  }
}

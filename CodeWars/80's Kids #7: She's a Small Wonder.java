import java.util.HashSet;
import java.util.StringTokenizer;

public class Robot {
  private HashSet<String> set;
  
  
  public Robot(){
    set = new HashSet<>();
  }
  
  public String learnWord(String word){
    String output = word;
    word = word.toLowerCase();
    
    StringTokenizer st = new StringTokenizer(word, "abcdefghijklmnopqrtstuvwxyz");
    if (st.	countTokens() > 0 || word.length() == 0)
      return "I do not understand the input";
    
    String[] knownWords = {"thank", "you", "for", "teaching", "me", "i", "already", "know", "the", "word", "do", "not", "understand", "input"};
    for (int i = 0; i < knownWords.length; i++){
      if (word.equals(knownWords[i]))
        return "I already know the word " + output;
    }
    
    if (set.contains(word))
      return "I already know the word " + output;
    else
      set.add(word);
    return "Thank you for teaching me " + output;
  }
  
}

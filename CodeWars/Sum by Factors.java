import java.util.TreeSet;

public class SumOfDivided {
  public static String sumOfDivided(int[] l) {
    TreeSet<Integer> set = new TreeSet<>();
        
    for (int i = 0; i < l.length; i++){
      int factor = 2;
      int temp = Math.abs(l[i]);
      while (temp > 1){
        if (temp % factor == 0){
          set.add(factor);
          temp /= factor;
        }else
          factor++;
      }
    }

    String ans = "";
    for(Integer num : set){
      int sum = 0;
      ans+="(";
      for(int i = 0; i < l.length; i++){
        if(l[i]%num==0)
          sum+=l[i];
      }
      ans+=num+" "+sum+")";
    }
    return ans;
  }
}

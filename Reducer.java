import java.util.*;
import java.io.*;
import java.lang.*;

public class Template {
    public Template(){
        try{
            File file = new File("Reducer.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));

            String st;
            while ((st = input.readLine()) != null){
                // 48/92
                String[] pieces = st.split("/");
                int num = Integer.parseInt(pieces[0]);
                int den = Integer.parseInt(pieces[1]);
                // loop through to find the gcd and reduce on the way
                // display your answer

                // special case where the numerator is 0
                if (num == 0)
                    System.out.print(0);

                // special case where the denominator is 0
                if (den == 0)
                    System.out.print("undef");

                // case where numerator is bigger than denominator
                // i then print out whatever num/den is because it'll be an integer
                // then do num % den to get the remainder which makes it the case where numerator is smaller than denominator
                if (num > den && den != 0){
                    System.out.print((num/den) + " ");
                    num %= den;
                }

                // greatest common divisor
                int gcd = 2;

                // running this loop as long as my gcd is less than my numerator
                while(gcd <= num && num != 0 && den != 0){

                    // if my numerator and denominator are divisible by the gcd i go ahead and divide both
                    if (num % gcd == 0 && den % gcd == 0){
                        num /= gcd;
                        den /= gcd;
                    }

                    // then i move to the next gcd
                    gcd++;
                }

                // printing
                if (num == den)
                    System.out.println(1);
                else if (num == 0 || den == 0)
                    System.out.println();
                else
                    System.out.println(num + "/" + den);
            }
        }
        catch(IOException e){

        }
    }

    public static void main(String[] args){
        Template app = new Template();
    }
}

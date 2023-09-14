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
                if (num == 0)
                    System.out.print(0);
                if (den == 0)
                    System.out.print("undef");
                if (num > den && den != 0){
                    System.out.print((num/den) + " ");
                    num %= den;
                }
                int gcd = 2;
                while(gcd <= num && num != 0 && den != 0){
                    if (num % gcd == 0 && den % gcd == 0){
                        num /= gcd;
                        den /= gcd;
                    }
                    gcd++;
                }
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

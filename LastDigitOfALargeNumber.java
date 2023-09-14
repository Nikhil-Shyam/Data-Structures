import java.io.*;
import java.math.BigInteger;

public class LastDigitOfALargeNumber {
    public LastDigitOfALargeNumber(){
        try{
            File file = new File("LargeNumberPracticeSet.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));

            String st;

            while ((st = input.readLine()) != null){
                String[] pieces = st.split(" ");
                int base = Integer.parseInt(pieces[0].substring(pieces[0].length()-1));
                BigInteger pow = new BigInteger(pieces[1]);
                int mod;
                if (pow.equals(0))
                    System.out.println(1);
                else{
                    switch(base){
                        case 0:
                            System.out.println(0);
                            break;
                        case 1:
                            System.out.println(1);
                            break;
                        case 2:
                            mod = (pow.remainder(BigInteger.valueOf(4))).intValue();
                            if (mod == 1)
                                System.out.println(2);
                            else if (mod == 2)
                                System.out.println(4);
                            else if (mod == 3)
                                System.out.println(8);
                            else
                                System.out.println(6);
                            break;
                        case 3:
                            mod = (pow.remainder(BigInteger.valueOf(4))).intValue();
                            if (mod == 1)
                                System.out.println(3);
                            else if (mod == 2)
                                System.out.println(9);
                            else if (mod == 3)
                                System.out.println(7);
                            else
                                System.out.println(1);
                            break;
                        case 4:
                            mod = (pow.remainder(BigInteger.valueOf(2))).intValue();
                            if (mod == 1)
                                System.out.println(4);
                            else
                                System.out.println(6);
                            break;
                        case 5:
                            System.out.println(5);
                            break;
                        case 6:
                            System.out.println(6);
                            break;
                        case 7:
                            mod = (pow.remainder(BigInteger.valueOf(4))).intValue();
                            if (mod == 1)
                                System.out.println(7);
                            else if (mod == 2)
                                System.out.println(9);
                            else if (mod == 3)
                                System.out.println(3);
                            else
                                System.out.println(1);
                            break;
                        case 8:
                            mod = (pow.remainder(BigInteger.valueOf(4))).intValue();
                            if (mod == 1)
                                System.out.println(8);
                            else if (mod == 2)
                                System.out.println(4);
                            else if (mod == 3)
                                System.out.println(2);
                            else
                                System.out.println(6);
                            break;
                        case 9:
                            mod = (pow.remainder(BigInteger.valueOf(2))).intValue();
                            if (mod == 1)
                                System.out.println(9);
                            else
                                System.out.println(1);
                            break;
                        default:
                            break;
                    }
                }
            }

        }        
        catch(IOException e){

        }
    }
    public static void main(String[] args){
        LastDigitOfALargeNumber app = new LastDigitOfALargeNumber();
    }
}

import java.io.*;
import java.math.BigInteger;

public class LastDigitOfALargeNumber {
    public LastDigitOfALargeNumber(){
        try{
            File file = new File("LargeNumberPracticeSet.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));

            String st;

            while ((st = input.readLine()) != null){
                // splitting base and exponent
                String[] pieces = st.split(" ");

                // only taking the last number of the base (only thing that matters)
                int base = Integer.parseInt(pieces[0].substring(pieces[0].length()-1));

                // taking the entire exponent, using BigInteger because they're really long
                BigInteger pow = new BigInteger(pieces[1]);
                
                // any time i used this variable im doing the power divided by the number of possible last digits and taking the remainder
                // i just have to use a lot of methods from the BigInteger class to do this, so dw about that too much
                int mod;

                // n^0 always equals 0, so pretty standard
                if (pow.equals(0))
                    System.out.println(1);
                else{
                    switch(base){
                        // if the last number of the base is 0, whatever it is raised to will have a last digit of 0
                        // ex: 10^3 = 1000
                        case 0:
                            System.out.println(0);
                            break;

                        // same thing as above
                        // ex: 11^3 = 1331
                        case 1:
                            System.out.println(1);
                            break;

                        // this will have 4 possible outcomes
                        // ex: 2^1 = 2 | 2^2 = 4 | 2^3 = 8 | 2^4 = 16 | 2^5 == 32 (goes back to 2 and repeats)
                        case 2:
                            mod = (pow.remainder(BigInteger.valueOf(4))).intValue();
                            // hopefully u can understand all of these if statements
                            if (mod == 1)
                                System.out.println(2);
                            else if (mod == 2)
                                System.out.println(4);
                            else if (mod == 3)
                                System.out.println(8);
                            else
                                System.out.println(6);
                            break;

                        // same deal with 2
                        // ex: 3^1 = 3 | 3^2 = 9 | 3^3 = 27 | 3^4 = 81 | 3^5 = 3 (repeats)
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

                        // same thing as 2 but this time there are 2 possible outcomes
                        // ex: 4^1 = 4 | 4^2 = 16 | 4^3 = 24 (repeats)
                        case 4:
                            mod = (pow.remainder(BigInteger.valueOf(2))).intValue();
                            if (mod == 1)
                                System.out.println(4);
                            else
                                System.out.println(6);
                            break;
                        
                        // easy
                        case 5:
                            System.out.println(5);
                            break;
                        
                        //easy
                        case 6:
                            System.out.println(6);
                            break;
                        
                        // same thing as 2
                        // ex: 7^1 = 7 | 7^2 = 49 | 7^3 = 343 | 7^4 = 2401 | 7^5 = 16807 (repeats)
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
                        
                        // same thing as 2
                        // ex: 8^1 = 8 | 8^2 = 64 | 8^3 = 512 | 8^4 = 4096 | 8^5 = 32768 (repeats)
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
                        
                        // same thing as 4
                        // ex: 9^1 = 9 | 9^2 = 81 | 9^3 = 729 (repeats)
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

import java.util.*;
import java.io.*;
import java.lang.*;

public class GleamingTheCube {
    public GleamingTheCube(){
        try{
            File file = new File("CubeInput.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));

            // initializing variables
            String st;
            int tempDown;
            int tempUp;
            int tempSouth;
            int tempNorth;
            int tempEast;
            int tempWest;

            while ((st = input.readLine()) != null){
                // array that represents my cube, inside the while loops because i want a new dice for each line
                // up, south, down, north, west, east
                int[] arr = {1, 5, 6, 2, 3, 4};
                
                // for loop to go through the line, one letter at a time
                for (int i = 0; i < st.length(); i++){
                    // making temp variables that represent each side of the cube so i can use it later
                    // inside the for loop because every time i roll the dice i want it to say just for the line
                    tempUp = arr[0];
                    tempDown = arr[2];
                    tempSouth = arr[1];
                    tempNorth = arr[3];
                    tempEast = arr[5];
                    tempWest = arr[4];

                    // checking what individual letter says and "rotate" the cube based on that
                    if (st.substring(i, i+1).equals("N")){
                        arr[0] = tempSouth;
                        arr[1] = tempDown;
                        arr[2] = tempNorth;
                        arr[3] = tempUp;
                    }else if (st.substring(i, i+1).equals("S")){
                        arr[0] = tempNorth;
                        arr[1] = tempUp;
                        arr[2] = tempSouth;
                        arr[3] = tempDown;
                    }else if (st.substring(i, i+1).equals("E")){
                        arr[0] = tempWest;
                        arr[5] = tempUp;
                        arr[4] = tempDown;
                        arr[2] = tempEast;
                    }else{
                        arr[0] = tempEast;
                        arr[5] = tempDown;
                        arr[2] = tempWest;
                        arr[4] = tempUp;
                    }
                }

                // print out the top part of the dice
                System.out.println(arr[0]);
            }

        }        
        catch(IOException e){

        }
    }
    public static void main(String[] args){
        GleamingTheCube app = new GleamingTheCube();
    }
}

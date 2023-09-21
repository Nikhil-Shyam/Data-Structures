import java.io.*;

public class GuitarHero {
    public GuitarHero(){
        try{
            File file = new File("GuitarSong.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));

            String st;
            int[][] chords = {  {29, 24, 19, 14, 10, 5},
                                {28, 23, 18, 13, 9, 4},
                                {27, 22, 17, 12, 8, 3},
                                {26, 21, 16, 11, 7, 2},
                                {25, 20, 15, 10, 6, 1}  };
            

            String[][] whole = new String[5][];
            int index = 0;
            int measureCount = -1;
            while ((st = input.readLine()) != null){
                String[] line = st.split(",");
                measureCount = line.length;
                whole[index] = line;
                index++;
            }
            int[][] output = new int[29][measureCount];
            String[][] measures = new String[whole[0].length][whole.length];
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 7; j++){
                    measures[j][i] = whole[i][j];
                }
            }


            for (String[] s: measures){
                for (String j: s)
                    System.out.print(j + " ");
                System.out.println();
            }

            for (int[] i: chords){
                for (int j: i)
                    System.out.print(j + " ");
                System.out.println();
            }
        }        
        catch(IOException e){
            System.out.println("wtf");
        }
    }
    public static void main(String[] args){
        GuitarHero app = new GuitarHero();
    }
}
import java.io.*;

// fuck this code it works but it's only like 98% perfect
// i couldn't figure out how to deal with the double 'B' thing and holding a guitar string from the top
// i can't be bothered to explain this so good luck

public class GuitarHero {
    public GuitarHero(){
        try{
            File file = new File("GuitarSong.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));

            String st;
            String[] notes = {"G#", "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "B", "A#", "A", "G#", "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A", "G#", "G", "F#", "F", "E"};
            

            String[][] whole = new String[5][];
            int index = 0;
            int measureCount = -1;
            while ((st = input.readLine()) != null){
                String[] line = st.split(",");
                measureCount = line.length;
                whole[index] = line;
                index++;
            }

            String[][] measures = new String[whole[0].length][whole.length];
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 7; j++){
                    measures[j][i] = whole[i][j];
                }
            }         

            String[] ans = new String[measures.length];
            for(int i = 0; i < measureCount; i++){
                int count = 5;
                String ult = "";
                for (int j = 0; j < 6; j++){
                    String str = "";
                    for (int k = 4; k >= 0; k--){
                        str += measures[i][k].substring(count, count+1);
                    }
                    ult += str;
                    if (count != 0)
                        count--;
                }
                ans[i] = ult;
            }

            System.out.print("Measure\t");
            for (int i = 1; i <= measureCount; i++){
                System.out.print(i + "\t");
            }
            System.out.println();
            
            String[][] output = new String[30][measureCount];
            for (int i = 0; i < ans.length; i++){
                for (int j = 0; j < ans[i].length(); j++){
                    if (ans[i].substring(j, j+1).equals("o") || ans[i].substring(j, j+1).equals("*"))    
                        output[j][i] = "O";
                    else
                        output[j][i] = " ";
                }
            }
            
            for (int i = 0; i < output.length; i++){
                System.out.print(notes[i] + "\t");
                for (int j = 0; j < output[i].length; j++){
                    System.out.print(output[i][j] + "\t");
                }
                System.out.println();
            }
        }
        catch(IOException e){
        }
    }
    public static void main(String[] args){
        GuitarHero app = new GuitarHero();
    }
}

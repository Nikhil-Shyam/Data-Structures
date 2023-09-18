import java.io.*;

public class Template {
    public Template(){
        try{
            File file = new File("FileName.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));

            String st;
            while ((st = input.readLine()) != null){
                
            }

        }        
        catch(IOException e){

        }
    }
    public static void main(String[] args){
        Template app = new Template();
    }
}

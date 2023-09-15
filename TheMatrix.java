import java.io.*;

public class TheMatrix {
    public TheMatrix(){
        try{
            File file = new File("MatrixFile.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));

            String st;
            while ((st = input.readLine()) != null){
                String[] matrices = st.split("\t");
                String sM1 = matrices[0].substring(2, matrices[0].length()-2);
                String sM2 = matrices[1].substring(2, matrices[1].length()-2);
                String[] aM1 = sM1.split("},\\{");
                String[] aM2 = sM2.split("},\\{");
                String[][] s2M1 = new String[aM1.length][];
                String[][] s2M2 = new String[aM2.length][];
                for (int i = 0; i < aM1.length; i++){
                    s2M1[i] = aM1[i].split(",");
                }
                for (int i = 0; i < aM1.length; i++){
                    s2M2[i] = aM2[i].split(",");
                }
                int[][] m1 = new int[s2M1.length][s2M1[0].length];
                int[][] m2 = new int[s2M2.length][s2M2[0].length];
                for (int i = 0; i < m1.length; i++){
                    for (int j = 0; j < m1[0].length; j++)
                        m1[i][j] = Integer.parseInt(s2M1[i][j]);
                }
                for (int i = 0; i < m2.length; i++){
                    for (int j = 0; j < m2[0].length; j++)
                        m2[i][j] = Integer.parseInt(s2M2[i][j]);
                }
                
                System.out.println("Matrix 1:");
                for(int[] i: m1){
                    for (int j: i)
                        System.out.print(j + "\t");
                    System.out.println();
                }
                System.out.println("Matrix 2:");
                for(int[] i: m2){
                    for (int j: i)
                        System.out.print(j + "\t");
                    System.out.println();
                }

                if (m1.length != m2.length || m1[0].length != m2[0].length){
                    System.out.println("Sum:");
                    System.out.println("Sum is not possible.");
                    System.out.println("Difference:");
                    System.out.println("Difference is not possible.");
                }else{
                    System.out.println("Sum:");
                    for (int i = 0; i < m1.length; i++){
                        for (int j = 0; j < m1.length; j++)
                            System.out.print(m1[i][j] + m2[i][j] + "\t");
                        System.out.println();
                    }
                    System.out.println("Difference:");
                    for (int i = 0; i < m1.length; i++){
                        for (int j = 0; j < m1.length; j++)
                            System.out.print(m1[i][j] - m2[i][j] + "\t");
                        System.out.println();
                    }
                }

                System.out.println("Product:");
                if (m1[0].length != m2.length)
                    System.out.println("Product is not possible.");
                else{
                    for (int i = 0; i < m1.length; i++){
                        for (int j = 0; j < m2[0].length; j++){
                            int sum = 0;
                            for (int k = 0; k < m1[0].length; k++)
                                sum += m1[i][k] * m2[k][j];
                            System.out.print(sum + "\t");
                        }
                        System.out.println();
                    }
                }
            }

        }        
        catch(IOException e){

        }
    }
    public static void main(String[] args){
        TheMatrix app = new TheMatrix();
    }
}
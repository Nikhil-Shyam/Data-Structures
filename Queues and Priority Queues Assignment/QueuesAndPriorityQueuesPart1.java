import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class QueuesAndPriorityQueuesPart1 {
    public QueuesAndPriorityQueuesPart1(){
        try{
            File file = new File("/Users/nikhilshyam/Desktop/Data Structures/Template/QueuesAndPriorityQueues.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));

            String st;
            while ((st = input.readLine()) != null){
                Queue<Word> q = new LinkedList<>();
                PriorityQueue<Word> pq = new PriorityQueue<>();
                StringTokenizer st1 = new StringTokenizer(st, ",.!?\":; ");
                while(st1.hasMoreTokens()){
                    String token = st1.nextToken();
                    q.add(new Word(token));
                    pq.add(new Word(token));
                }

                while(!q.isEmpty()){
                    System.out.println(String.format("%-25s %-20s", q.poll().getWord(), pq.poll().getWord()));
                }

            }
        }catch(IOException e){ System.out.println("Can't find file"); }
    }

    public static void main(String[] args){
        new QueuesAndPriorityQueuesPart1();
    }

    public class Word implements Comparable<Word>{
        private String word;

        public Word(String w){
            word = w;
        }

        public String getWord(){
            return word;
        }

        public int compareTo(Word otherWord){
            String a = word.toLowerCase();
            String b = otherWord.word.toLowerCase();
            
            // ascending order
            return a.compareTo(b);

            // descending order
            // return -a.compareTo(b);
        }
    }

}

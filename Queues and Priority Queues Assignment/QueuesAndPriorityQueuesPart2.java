import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Stack;

public class QueuesAndPriorityQueuesPart2 {
    String[][] car = new String[398][];
    Stack<Car> s = new Stack<>();
    Queue<Car> q = new LinkedList<>();
    PriorityQueue<Car> pq = new PriorityQueue<>();

    public QueuesAndPriorityQueuesPart2(){
        try{
            File file = new File("/Users/nikhilshyam/Desktop/Data Structures/Template/CarData.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));

            String st = input.readLine();
            int next = 0;
            while ((st = input.readLine()) != null){
                car[next] = st.split("\t");
                next++;
            }

            for(int i = 0; i < car.length; i++){
                int carID = Integer.parseInt(car[i][0]);
                int MPG = Integer.parseInt(car[i][1]);
                int engineSize = Integer.parseInt(car[i][2]);
                int HP = Integer.parseInt(car[i][3]);
                int weight = Integer.parseInt(car[i][4]);
                int acceleration = Integer.parseInt(car[i][5]);
                int countryOfOrigin = Integer.parseInt(car[i][6]);
                int numberOfCylinders = Integer.parseInt(car[i][7]);

                s.push(new Car(carID, MPG, engineSize, HP, weight, acceleration, countryOfOrigin, numberOfCylinders));
                q.add(new Car(carID, MPG, engineSize, HP, weight, acceleration, countryOfOrigin, numberOfCylinders));
                pq.add(new Car(carID, MPG, engineSize, HP, weight, acceleration, countryOfOrigin, numberOfCylinders));
            }

            System.out.println("Priority Queue");
            System.out.println("CarID\tMPG\tSize\tHP\tWeight\tAccel\tOrigin\tCylinders");
            while(!pq.isEmpty()){
                System.out.println(pq.poll());
            }
            
            System.out.println("\nQueue");
            System.out.println("CarID\tMPG\tSize\tHP\tWeight\tAccel\tOrigin\tCylinders");
            while(!q.isEmpty()){
                System.out.println(q.poll());
            }    

            System.out.println("\nStack");
            System.out.println("CarID\tMPG\tSize\tHP\tWeight\tAccel\tOrigin\tCylinders");
            while(!s.isEmpty()){
                System.out.println(s.pop());
            }    

        }catch(IOException e){ System.out.println("Can't find file"); }
    }

    public static void main(String[] args){
        new QueuesAndPriorityQueuesPart2();
    }

    public class Car implements Comparable<Car>{
        private int carID;
        private int MPG;
        private int engineSize;
        private int HP;
        private int weight;
        private int acceleration;
        private int countryOfOrigin;
        private int numberOfCylinders;

        public Car(int cID, int mpg, int eS, int hp, int w, int a, int cOO, int nOC){
            carID = cID;
            MPG = mpg;
            engineSize = eS;
            HP = hp;
            weight = w;
            acceleration = a;
            countryOfOrigin = cOO;
            numberOfCylinders = nOC;
        }

        public int compareTo(Car otherCar){
            int a1 = acceleration;
            int a2 = otherCar.acceleration;
            int mpg1 = MPG;
            int mpg2 = otherCar.MPG;
            int hp1 = HP;
            int hp2 = otherCar.HP;
            int es1 = engineSize;
            int es2 = otherCar.engineSize;
            int w1 = weight;
            int w2 = otherCar.weight;
            int c1 = numberOfCylinders;
            int c2 = otherCar.numberOfCylinders;
            int cid1 = carID;
            int cid2 = otherCar.carID;
            int o1 = countryOfOrigin;
            int o2 = otherCar.countryOfOrigin;

            if(a1 > a2)
                return 1;
            if (a1 < a2)
                return -1;

            if(mpg1 > mpg2)
                return 1;
            if (mpg1 < mpg2)
                return -1;

            if(hp1 > hp2)
                return 1;
            if (hp1 < hp2)
                return -1;

            if(es1 > es2)
                return 1;
            if (es1 < es2)
                return -1;

            if(w1 > w2)
                return 1;
            if (w1 < w2)
                return -1; 

            if(c1 > c2)
                return 1;
            if (c1 < c2)
                return -1;

            if(cid1 > cid2)
                return 1;
            if (cid1 < cid2)
                return -1;
                
            if(o1 > o2)
                return 1;
            if (o1 < o2)
                return -1;
            
            return 0;
        }

        public String toString(){
            return carID + "\t" + MPG + "\t" + engineSize + "\t" + HP + "\t" + weight + "\t" + acceleration + "\t" + countryOfOrigin + "\t" + numberOfCylinders;
        }

        public int getCarID(){
            return carID;
        }

        public int getMPG(){
            return MPG;
        }

        public int getEngineSize(){
            return engineSize;
        }

        public int getHP(){
            return HP;
        }

        public int getWeight(){
            return weight;
        }

        public int getAcceleration(){
            return acceleration;
        }

        public int getCountryOfOrigin(){
            return countryOfOrigin;
        }

        public int getNumberOfCylinders(){
            return numberOfCylinders;
        }
    }
}

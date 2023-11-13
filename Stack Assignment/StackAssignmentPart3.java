// change path name

import java.util.Stack;
import java.io.*;

public class StackAssignmentPart3{
	String[][] person = new String[88][];
	Stack<Character> maleCharacters = new Stack<>();
	Stack<Character> femaleCharacters = new Stack<>();
	Stack<Character> droids = new Stack<>();
	Stack<Character> validBirthYear = new Stack<>();

	public StackAssignmentPart3(){
		try{
			File file = new File("C:\\Users\\nikhi\\OneDrive\\Desktop\\TextPad\\StarWarsCharacters.csv");
			BufferedReader input = new BufferedReader(new FileReader(file));

			String st;
			int next = 0;
			while ((st = input.readLine()) != null){
				person[next] = st.split(",");
				next++;
			}

			addToStacks();

			printMaleCharacters();

			printFemaleCharacters();

			printDroids();

			printValidAges();
		}catch(IOException e){ System.out.println("Can't find file"); }
	}

	public static void main(String[] args){
		new StackAssignmentPart3();
	}

	public void addToStacks(){
		for (int i = 1; i < person.length; i++){
			String species = person[i][8];
			String name = person[i][0];
			String birthYear = person[i][5];
			String gender = person[i][6];
			String homeWorld = person[i][7];

			try{
				birthYear = birthYear.substring(0, birthYear.indexOf("BBY"));
			}catch(StringIndexOutOfBoundsException e){}

			if (species.equals("Human")){
				if (gender.equals("male"))
					maleCharacters.push(new Character(name, birthYear, gender, homeWorld));
				if (gender.equals("female"))
					femaleCharacters.push(new Character(name, birthYear, gender, homeWorld));
			}
			if (species.equals("Droid"))
				droids.push(new Character(name, birthYear, gender, homeWorld));
			if (!birthYear.equals("NA"))
				validBirthYear.push(new Character(name, birthYear, gender, homeWorld));
		}
	}

	public void printMaleCharacters(){
		System.out.println("Male Characters");
		System.out.println(String.format("%-25s %-25s", "Name", "Homeworld"));
		while(!maleCharacters.empty()){
			Character temp = maleCharacters.pop();
			String name = temp.getName();
			String homeWorld = "";
			if (temp.getHomeWorld().equals("NA"))
				homeWorld = "Unknown";
			else
				homeWorld = temp.getHomeWorld();
			System.out.println(String.format("%-25s %-25s", name, homeWorld));
		}
		System.out.println();
	}

	public void printFemaleCharacters(){
		System.out.println("Female Characters");
		System.out.println(String.format("%-25s %-25s", "Name", "Homeworld"));
		while(!femaleCharacters.empty()){
			Character temp = femaleCharacters.pop();
			String name = temp.getName();
			String homeWorld = "";
			if (temp.getHomeWorld().equals("NA"))
				homeWorld = "Unknown";
			else
				homeWorld = temp.getHomeWorld();
			System.out.println(String.format("%-25s %-25s", name, homeWorld));
		}
		System.out.println();
	}

	public void printDroids(){
		System.out.println("Droids");
		System.out.println(String.format("%-25s %-25s", "Name", "Homeworld"));
		while(!droids.empty()){
			Character temp = droids.pop();
			String name = temp.getName();
			String homeWorld = "";
			if (temp.getHomeWorld().equals("NA"))
				homeWorld = "Unknown";
			else
				homeWorld = temp.getHomeWorld();
			System.out.println(String.format("%-25s %-25s", name, homeWorld));
		}
		System.out.println();
	}

	public void printValidAges(){
		System.out.println("Ages");
		System.out.println(String.format("%-25s %-20s %-25s", "Name", "Homeworld", "Birth Year (BBY)"));
		while(!validBirthYear.empty()){
			Character temp = validBirthYear.pop();
			String name = temp.getName();
			String homeWorld = "";
			String birthYear = "";
			if (temp.getHomeWorld().equals("NA"))
				homeWorld = "Unknown";
			else
				homeWorld = temp.getHomeWorld();
			if (temp.getBirthYear().equals("NA"))
				birthYear = "Unknown";
			else if (!temp.getBirthYear().contains("."))
				birthYear = temp.getBirthYear() + ".0";
			else
				birthYear = temp.getBirthYear();
			System.out.println(String.format("%-25s %-20s %-25s", name, homeWorld, birthYear));
		}
	}

	public class Character{
		private String name;
		private String birthYear;
		private String gender;
		private String homeWorld;

		public Character(String n, String bY, String g, String hW){
			name = n;
			birthYear = bY;
			gender = g;
			homeWorld = hW;
		}

		public String getName(){
			return name;
		}

		public String getBirthYear(){
			return birthYear;
		}

		public String getGender(){
			return gender;
		}

		public String getHomeWorld(){
			return homeWorld;
		}
	}
}
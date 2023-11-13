import java.util.Stack;
import java.util.Scanner;

public class StackAssignmentPart2{
	Stack<String> stack = new Stack<>();
	Scanner reader = new Scanner(System.in);

	public StackAssignmentPart2(){
		System.out.print("Enter a string: ");
		String inp = reader.nextLine();

		for (int i = 0; i < inp.length(); i++){
			stack.push(String.valueOf(inp.charAt(i)));
		}

		String output = "";
		while(!stack.empty()){
			output += stack.pop();
		}

		System.out.println(output);

	}

	public static void main(String[] args){
		new StackAssignmentPart2();
	}
}
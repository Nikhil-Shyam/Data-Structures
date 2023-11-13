import java.util.Stack;
import java.util.Scanner;

public class StackAssignmentPart1{
	Stack<Integer> stack = new Stack<Integer>();
	Scanner reader = new Scanner(System.in);

	public StackAssignmentPart1(){
		System.out.print("Enter a number: ");
		int inp = reader.nextInt();

		while(inp/2 != 0){
			stack.push(inp%2);
			inp /= 2;
		}

		String output = "1";
		while(!stack.empty()){
			output += Integer.toString(stack.pop());
		}

		System.out.println(output);
	}

	public static void main(String[] args){
		new StackAssignmentPart1();
	}
}
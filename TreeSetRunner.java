import java.util.*;

public class TreeSetRunner{
	TreeSet<Integer> integerSet;
	TreeSet<Integer> integerCopySet;
	TreeSet<String> stringSet;

	ArrayList<Integer> integerList;

	public TreeSetRunner(){
		integerSet = new TreeSet<>();
		stringSet = new TreeSet<>();
		integerList = new ArrayList<>();

		// 1
		while (integerSet.size() < 30){
			int temp = (int)(Math.random()*100)+1;
			if (integerSet.add(temp))
				System.out.print(temp + ", ");
		}

		// 2
		System.out.println();
		System.out.println(integerSet.preOrder());
		System.out.println();

		// 3
		System.out.println("Size: " + integerSet.size());

		// 4
		integerCopySet = new TreeSet<>();
		StringTokenizer preOrder = new StringTokenizer(integerSet.preOrder(), "[, ]");
		while (preOrder.hasMoreTokens())
			integerCopySet.add(Integer.valueOf(preOrder.nextToken()));
		System.out.println();

		// 5
		System.out.println(integerCopySet.preOrder());
		System.out.println(integerCopySet.inOrder());
		System.out.println(integerCopySet.postOrder());

		// 6
		integerCopySet = new TreeSet<>();
		StringTokenizer inOrder = new StringTokenizer(integerSet.inOrder(), "[, ]");
		while (inOrder.hasMoreTokens())
			integerCopySet.add(Integer.valueOf(inOrder.nextToken()));
		System.out.println();

		// 7
		System.out.println(integerCopySet.preOrder());
		System.out.println(integerCopySet.inOrder());
		System.out.println(integerCopySet.postOrder());
		System.out.println("preOrder and inOrder have the same output while postOrder is the reverse");

		// 8
		integerCopySet = new TreeSet<>();
		StringTokenizer postOrder = new StringTokenizer(integerSet.postOrder(), "[, ]");
		while (postOrder.hasMoreTokens())
			integerCopySet.add(Integer.valueOf(postOrder.nextToken()));
		System.out.println();

		// 9
		System.out.println(integerCopySet.preOrder());
		System.out.println(integerCopySet.inOrder());
		System.out.println(integerCopySet.postOrder());
		System.out.println("fill this out\n");

		// 10
		while (stringSet.size() < 20){
			String temp = Character.toString((char)((int)(Math.random()*26)+65));
			if (stringSet.add(temp))
				System.out.print(temp + ", ");
		}
		System.out.println();
		System.out.println(stringSet.preOrder());
		System.out.println();

		// 11
		System.out.println(stringSet.preOrder());
		System.out.println(stringSet.inOrder());
		System.out.println(stringSet.postOrder());
		System.out.println();

		// 12
		for (int i = 1; i <= 3; i++){
			System.out.println("Right Rotation #" + i);
			stringSet.rotateRight();
			System.out.println(stringSet.preOrder());
			System.out.println(stringSet.inOrder());
			System.out.println(stringSet.postOrder());
		}
		System.out.println();

		// 13
		for (int i = 1; i <= 3; i++){
			System.out.println("Left Rotation #" + i);
			stringSet.rotateLeft();
			System.out.println(stringSet.preOrder());
			System.out.println(stringSet.inOrder());
			System.out.println(stringSet.postOrder());
		}
		System.out.println();

		// 14
		integerSet = new TreeSet<>();
		while (integerSet.size() < 30){
			int temp = (int)(Math.random()*100)+1;
			integerSet.add(temp);
			integerList.add(temp);
		}
		System.out.println(integerSet.preOrder());
		System.out.println(integerList);
		System.out.println();

		// 15 and 16
		int i = 1;
		while (integerSet.size() > 0){
			int ran = (int)(Math.random()*integerList.size());
			int temp = integerList.remove(ran);
			integerSet.remove(temp);
			System.out.println("Removal #" + i + " || " + temp);
			System.out.println(integerSet.preOrder());
			System.out.println(integerSet.inOrder());
			System.out.println(integerSet.postOrder());
			i++;
		}
	}

	public static void main(String[] args){
		new TreeSetRunner();
	}
}

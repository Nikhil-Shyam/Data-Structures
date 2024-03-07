public class TreeSetRunner{
	TreeSet<Integer> set;
	TreeSet<Integer> copySet;

	public TreeSetRunner(){
		set = new TreeSet<>();

		// 1
		while (set.size() < 30){
			int ran = (int)(Math.random()*100)+1;
			set.add(ran);
		}

		// 2
		System.out.println(set.preOrder());
		System.out.println();

		// 3
		System.out.println("Size: " + set.size());

		// 4
		copySet = new TreeSet<>();
		String preOrder = set.preOrder();
		preOrder = preOrder.substring(1, preOrder.length()-1);
		for (int i = 0; i < set.size(); i++){
			copySet.add(Integer.valueOf(preOrder.substring(0, preOrder.indexOf(","))));
			if (i != 28)
				preOrder = preOrder.substring(preOrder.indexOf(" ")+1);
		}
		copySet.add(Integer.valueOf(preOrder));
		System.out.println();

		// 5
		System.out.println(copySet.preOrder());
		System.out.println(copySet.inOrder());
		System.out.println(copySet.postOrder());

		// 6
		copySet = new TreeSet<>();
		String inOrder = set.inOrder();
		inOrder = inOrder.substring(1, inOrder.length()-1);
		for (int i = 0; i < set.size(); i++){
			copySet.add(Integer.valueOf(inOrder.substring(0, inOrder.indexOf(","))));
			if (i != 28)
				inOrder = inOrder.substring(inOrder.indexOf(" ")+1);
		}
		copySet.add(Integer.valueOf(inOrder));
		System.out.println();

		// 7
		System.out.println(copySet.preOrder());
		System.out.println(copySet.inOrder());
		System.out.println(copySet.postOrder());
		System.out.println("preOrder and inOrder have the same output while postOrder is the reverse");

		// 8
		copySet = new TreeSet<>();
		String postOrder = set.postOrder();
		postOrder = postOrder.substring(1, postOrder.length()-1);
		for (int i = 0; i < set.size(); i++){
			copySet.add(Integer.valueOf(postOrder.substring(0, postOrder.indexOf(","))));
			if (i != 28)
				postOrder = postOrder.substring(postOrder.indexOf(" ")+1);
		}
		copySet.add(Integer.valueOf(postOrder));
		System.out.println();

		// 9
		System.out.println(copySet.preOrder());
		System.out.println(copySet.inOrder());
		System.out.println(copySet.postOrder());
	}

	public static void main(String[] args){
		new TreeSetRunner();
	}
}
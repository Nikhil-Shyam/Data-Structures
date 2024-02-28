public class TreeSet<E extends Comparable<E>>{
	private E root;
	private int size;
	private String string;

	public void add(E value){
		if (root == null){
			root = new TreeNode<E>(value);
			size++;
		}else
			add(root, value);
	}

	public void add(TreeNode<E> node, E value){
		if (node.getValue() == value)
			return;
		if (node.getValue() < value){
			if (node.getLeft() == null){
				node.setLeft(new TreeNode<E>(value));
				size++;
				return;
			}else{
				add(node.getLeft(), value);
			}
		}else{
			if (node.getRight() == null){
				node.setRight(new TreeNode<E>(value));
				size++;
				return;
			}else{
				add(node.getRight(), value);
			}
		}
	}

	public class TreeNode<E extends Comparable<E>>{
		private TreeNode<E> left;
		private TreeNode<E> right;
		private E value;

		public TreeNode<E>(E value){
			this.value = value;
			right = null;
			left = null;
		}

		public TreeNode<E> getRight(){
			return right;
		}

		public TreeNode<E> getLeft(){
			return left;
		}

		public void setRight(TreeNode<E> right){
			this.right = right;
		}

		public void setLeft(TreeNode<E> left){
			this.left = left;
		}

		public E getValue(){
			return value;
		}

		public String toString(){
			return value + "";
		}
	}
}
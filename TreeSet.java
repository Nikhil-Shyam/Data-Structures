public class TreeSet<E extends Comparable<E>>{
	private TreeNode<E> root;
	private int size;
	private String string;

    public TreeSet(){
        root = null;
        size = 0;
        string = "";
    }

	public void add(E value){
		if (root == null){
			root = new TreeNode<E>(value);
			size++;
		}else
			add(root, value);
	}

	private void add(TreeNode<E> node, E value){
		if (node.getValue() == value)
			return;
		if (node.getValue().compareTo(value) > 0){
			if (node.getLeft() == null){
				node.setLeft(new TreeNode<E>(value));
				size++;
				return;
			}else
				add(node.getLeft(), value);
		}else{
			if (node.getRight() == null){
				node.setRight(new TreeNode<E>(value));
				size++;
				return;
			}else
				add(node.getRight(), value);
		}
	}

    public String preOrder(){
        if (size == 0)
            return "[]";

		string = "";
        string += "[";
        preOrder(root);
        string = string.substring(0, string.length()-2) + "]";

        return string;
    }

    private void preOrder(TreeNode<E> node){
        if (node != null){
            string += node.getValue() + ", ";
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public String inOrder(){
        if (size == 0)
            return "[]";
        if (string.length() != 0)
            string = "";

        string += "[";
        inOrder(root);
        string = string.substring(0, string.length()-2) + "]";

        return string;
    }

    private void inOrder(TreeNode<E> node){
        if (node != null){
            inOrder(node.getLeft());
            string += node.getValue() + ", ";
            inOrder(node.getRight());
        }
    }

    public String postOrder(){
        if (size == 0)
            return "[]";
        if (string.length() != 0)
            string = "";

        string += "[";
        postOrder(root);
        string = string.substring(0, string.length()-2) + "]";

        return string;
	}

    private void postOrder(TreeNode<E> node){
        if (node != null){
            postOrder(node.getLeft());
            postOrder(node.getRight());
            string += node.getValue() + ", ";
        }
    }

    public boolean contains(E value){
		return contains(root, value);
	}

    private boolean contains(TreeNode<E> node, E value){
        if (node == null)
            return false;
        if (node.getValue().equals(value))
            return true;
        if (node.getValue().compareTo(value) > 0)
            return contains(node.getLeft(), value);
        else
            return contains(node.getRight(), value);
    }

    public E minValue(){
		return minValue(root);
	}

    private E minValue(TreeNode<E> node){
        if (node.getLeft() == null)
			return node.getValue();

		return minValue(node.getLeft());
    }

    public void remove(E value){
        if (root == null)
            return;
        if (contains(root, value)){
            if (root.getLeft() == null && root.getRight() == null){
                root = null;
                size = 0;
                return;
            }else{
                size--;
                root = remove(root, value);
            }
        }
    }

    private TreeNode<E> remove(TreeNode<E> node, E value){
        if (node == null)
            return null;
        if (node.getValue().compareTo(value) > 0)
            node.setLeft(remove(node.getLeft(), value));
        else if (node.getValue().compareTo(value) < 0)
            node.setRight(remove(node.getRight(), value));
        else{
            if (node.getLeft() == null && node.getRight() == null)
                node = null;
            else if (node.getLeft() == null)
                return node.getRight();
            else if (node.getRight() == null)
                return node.getLeft();
            else{
                E temp = minValue(node.getRight());
                node.setValue(temp);
                node.setRight(remove(node.getRight(), temp));
            }
        }
        return node;
    }

    public int size(){
        return size;
    }

    public E getRoot(){
		return root.getValue();
	}

	/***********************************************/
	public void rotateLeft(){
		if (root.getLeft() == null)
			return;

		rotateLeft(root);
	}

	private void rotateLeft(TreeNode<E> node){
		TreeNode<E> temp = node;

		temp.setLeft(temp.getLeft().getRight());
		root = node.getLeft();
		root.setRight(temp);
	}
	/***********************************************/

	private class TreeNode<E extends Comparable<E>>{
		private TreeNode<E> left;
		private TreeNode<E> right;
		private E value;

		public TreeNode(E value){
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

		public void setValue(E value){
			this.value = value;
		}

		public String toString(){
			return value + "";
		}
	}
}

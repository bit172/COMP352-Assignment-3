
public class TreeNode 
{
	TreeNode parent;
	TreeNode left;
	TreeNode right;
	
	char character;
	int frequency;
	int occurence;
	
	int value;

	public TreeNode(TreeNode p)
	{
		parent = p;
		left = null;
		right = null;

	}
	
	public TreeNode(int o, char c)
	{
		frequency = 1;
		occurence = o;
		character = c;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	
}

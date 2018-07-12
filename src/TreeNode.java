
public class TreeNode 
{
	TreeNode left;
	TreeNode right;
	
	char character;
	int frequency;
	int occurence;
	String encoding;
	int value;
	
	
	public TreeNode() {}


	public TreeNode(int o, char c)
	{
		frequency = 1;
		occurence = o;
		character = c;
	}

	
}

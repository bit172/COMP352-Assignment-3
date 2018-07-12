
public class SplayNode {
	SplayNode parent;
	SplayNode left;
	SplayNode right;
	int value;
	
	public SplayNode() {}
	
	public String toString()
	{
		return String.format("%-13s  %-13s %-13s %-13s%n",
				"Parent : " + (parent == null ? "null" : parent.value ),
				"Left : " + (left == null ? "null" : left.value ),
				"Right : " + (right == null ? "null" : right.value ),
				"Value: " +  value);

	}
	
	public SplayNode(int v)
	{
		value = v;
	}

}

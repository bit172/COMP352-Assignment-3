import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
public class SplayTree 
{	
	static int comparisons = 0;
	static int zigzig = 0;
	static int zigzag = 0;
	
	public static void main(String[] args)
	{	
		Tree T = new Tree();
		Scanner sc = null;

		try
		{
			sc = new Scanner(new FileInputStream(args[0]));
			String s = null;
			int number = 0;
			while(sc.hasNextLine())
			{
				s = sc.nextLine();
				number++;
				char operation = s.charAt(0);
				number = Integer.parseInt(s.substring(1, s.length()));
				
				if(operation == 'a')
				{
					add(T,number);
				}
				else if(operation == 'f')
				{
					find(T,number);
				}
				else if(operation == 'r')
				{
					remove(T,number);
				}
				
				if(number == Integer.parseInt(args[1]))
				{
					System.out.println("Traversal at " + number + Postorder(T.root));
				}
			}
			
			System.out.println(comparisons + " Comparisons");
			System.out.println(zigzig + " Zig-Zigs");
			System.out.println(zigzag + " Zig-Zags");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
			System.exit(0);
		}
	}
	
	private static class Tree
	{
		SplayNode root;
		
		public Tree() {}
	}
	
	
	public static void splay(Tree T, SplayNode N)
	{	
		
		if(isRoot(N) || isRoot(N.parent))
			return;
		
		// ZigZig left left
		else if( N.parent.value < N.parent.parent.value && N.value < N.parent.value)
			zigzig(N,false);	
		
		// ZigZig right right
		else if(N.parent.value > N.parent.parent.value && N.value > N.parent.value)
			zigzig(N,true);
		
		// ZigZag left right
		else if(N.parent.value < N.parent.parent.value && N.value > N.parent.value)
			zigzag(N,false);
		
		//ZigZag right left
		else if(N.parent.value > N.parent.parent.value && N.value < N.parent.value)
			zigzag(N,true);
		
		splay(T,N);
		
		SplayNode SP = N;
		while(true)
		{
			if(SP.parent == null)
			{
				T.root = SP;
				break;
			}
			
			SP = SP.parent;
		}
			
			
	}
	
	public static void add(Tree T,int value)
	{	
		SplayNode N = new SplayNode(value);
		
		if(T.root == null)
			{
				T.root = N;
				N.parent = null;
				return;
			}
		
		SplayNode current = T.root;
		SplayNode parent = null;
		boolean left = false;
		
		while(current != null)
		{	
			left = false;
			
			if(value < current.value)
			{	
				comparisons++;
				parent = current;
				
				if(current.left == null)
				{
					left = true;
					break;
				}
				else
					current = current.left;
				
			}
				
			else
			{
				comparisons++;
				parent = current;
				
				if(current.right == null)
				{
					left = false;
					break;
				}
				else
					current = current.right;
			}
				
		}
		
		if(left)
		{
			current.left = N;
			current.left.parent = parent;
		}
		
		else
		{
			current.right = N;
			current.right.parent = parent;
		}
		
		// Recursively splay the node up
		splay(T,N);
		
		
	}
	
	public static String Postorder(SplayNode N)
	{	
		String S = null;
        if (N == null)
            return "";
 
        // first recur on left subtree
        Postorder(N.left);
 
        // then recur on right subtree
        Postorder(N.right);
        
        return S + " " + N.value;
    }
	
	public static void find(Tree T, int value)
	{	
		if(T.root == null)
			return;
		if(T.root.value == value)
			return;
		
		SplayNode current = T.root;
		
		while(current != null)
		{	
			comparisons++;
			if(value < current.value)
				current = current.left;
			
			else if(value > current.value)
				current = current.right;
			
			else if(current != null && value == current.value)
				break;
		}
		
		if(current == null)
			System.out.println("Could not find " + value);
		else
		{
			splay(T,current);
		}
	}
	
	public static void remove(Tree T, int value)
	{	
		boolean valueAtRoot = false;
		
		if(T.root == null)
		{	
			System.out.println("Could not find " + value);
			return;
		}
			
		
		if(T.root.value == value)
		{
			valueAtRoot = true;
		}
		
		SplayNode current = T.root;
		while(!valueAtRoot && current != null)
		{	 
			comparisons++;
			if(value < current.value)
				current = current.left;
			
			else if(value > current.value)
				current = current.right;
			
			if(current != null && value == current.value)
				break;
		}
		
		if(current == null)
		{
			System.out.println("Could not find " + value);
			return;
		}
		else
		{
			splay(T,current);
		}
		
		if(isRoot(current))
		{
			SplayNode rightside = current.right;
			
			SplayNode newRoot = null;
			
			// If the tree has only a root
			if(current.left == null && current.right == null)
			{
				T.root = null;
			}
			
			//First element of left sub-tree is largest
			else if(current.right == null)
			{	
				current = current.left;
				
				if(current.right == null)
				{
					newRoot = current;
					newRoot.right = rightside;
					rightside.parent = newRoot;
					newRoot.parent = T.root.left = T.root.right = null;
					T.root = newRoot;
				}
				else
				{	
					// find largest element from left subtree and put it as the root
					while(current.right != null)
						current = current.right;
					
					newRoot = current;
					
					if(newRoot.left != null)
						newRoot.left.parent = newRoot.parent;
					
					newRoot.parent.right = newRoot.left;
					
					
					newRoot.left = T.root.left;
					newRoot.right = rightside;
					
					T.root.left.parent = newRoot;
					
					newRoot.parent = T.root.left = T.root.right = null;
					T.root = newRoot;
					
				}
				
			}
			else if(current.left == null)
			{	
				current = current.right;
				
				// smallest element is first element of first sub tree
				if(current.left == null)
				{
					newRoot = current;
					newRoot.left = T.root.left;
					T.root.left.parent = newRoot;
					newRoot.parent = T.root.left = T.root.right = null;
					T.root = newRoot;
				}
				else
				{
					
					while(current.left != null)
						current = current.left;
					
					newRoot = current;
					
					if(newRoot.right != null)
						newRoot.right.parent = newRoot.parent;
					
					newRoot.parent.left = newRoot.right;
					
					newRoot.left = T.root.left;
					newRoot.right = rightside;
					
					T.root.right.parent = newRoot;
					
					newRoot.parent = T.root.left = T.root.right = null;
					T.root = newRoot;
				}
			}
		}
		else
		{
			// If the sub-tree has only a root
			if(current.left == null && current.right == null)
			{
				if(current.value < current.parent.value)
					current.parent.left = null;
				else
					current.parent.right = null;
					
			}
			
			else if(current.left == null)
			{	
				//Get smallest value from right tree and replace the node to remove
				SplayNode toRemove = current;
				current = current.right;
				
				if(current.left == null)
				{
					toRemove = current;
					toRemove.left = T.root.left;
					T.root.left.parent = toRemove;
					toRemove.parent = T.root.left = T.root.right = null;
					T.root = toRemove;
				}
				else
				{
					while(current.left != null)
						current = current.left;
					
					// Take right side of node and connect it to the parent of the smallest
					if(current.right != null)
						current.right.parent = current.parent;
					
					current.parent.left = current.right;
					
					current.left = toRemove.left;
					current.right = toRemove.right;
					current.parent = toRemove.parent;
					
					if(current.value < current.parent.value)
						current.parent.left = current;
					else
						current.parent.right = current;
				}
			}
			
			else if(current.right == null)
			{	
				// largest from left subtree
				SplayNode toRemove = current;
				current = current.left;
				
				if(current.right == null)
				{
					toRemove = current;
					toRemove.right = T.root.right;
					T.root.right.parent = toRemove;
					toRemove.parent = T.root.left = T.root.right = null;
					T.root = toRemove;
				}
				
				while(current.right != null)
					current = current.right;
				if(current.left != null)
					current.left.parent = current.parent;
				
				current.parent.right = current.left;
				
				current.left = toRemove.left;
				current.right = toRemove.right;
				current.parent = toRemove.parent;
				
				if(current.value < current.parent.value)
					current.parent.left = current;
				else
					current.parent.right = current;
				
			}
			
			else
			{
				//Get smallest value from right tree and replace the node to remove
				SplayNode toRemove = current;
				current = current.right;
				
				if(current.left == null)
				{
					toRemove = current;
					toRemove.left = T.root.left;
					T.root.left.parent = toRemove;
					toRemove.parent = T.root.left = T.root.right = null;
					T.root = toRemove;
				}
				else
				{
					while(current.left != null)
						current = current.left;
					
					// Take right side of node and connect it to the parent of the smallest
					if(current.right != null)
						current.right.parent = current.parent;
					
					current.parent.left = current.right;
					
					current.left = toRemove.left;
					current.right = toRemove.right;
					current.parent = toRemove.parent;
					
					if(current.value < current.parent.value)
						current.parent.left = current;
					else
						current.parent.right = current;
				}
			}
		}
	}
	
	public static boolean isRoot(SplayNode N)
	{
		return N.parent == null;
	}
	
	public static void zigzig(SplayNode N, boolean direction)
	{	
		SplayNode middle = N.parent;
		SplayNode top = N.parent.parent;
		zigzig++;
		// left left
		if(!direction)
		{	
			// first rotation
			top.left = middle.right;
			middle.right = top;
			
			// adjusting parents
			middle.parent = top.parent;
			top.parent = middle;
			
			// second rotation
			middle.left = N.right;
			N.right = middle;
			
			// adjusting parents
			N.parent = middle.parent;
			middle.parent = N;
			
			
		}
		// right right
		else
		{	
			// first rotation
			top.right = middle.left;
			middle.left = top;
			
			// adjusting parents
			middle.parent = top.parent;
			top.parent = middle;
			
			// second rotation
			middle.right = N.left;
			N.left = middle;
			
			//adjusting parents
			N.parent = middle.parent;
			middle.parent = N;
				
		}
		
		if(N.parent != null)
		{
			if(N.value > N.parent.value)
				N.parent.right = N;
			else
				N.parent.left = N;
		}
		
	}
	
	public static void zigzag(SplayNode N, boolean direction)
	{	
		SplayNode middle = N.parent;
		SplayNode top = N.parent.parent;
		zigzag++;
		
		// left right
		if(!direction)
		{	
			// first rotation
			middle.right = N.left;
			top.left = N;
			N.left = middle;
			
			// adjusting parents
			N.parent = top;
			middle.parent = N;
			
			// second rotation
			top.left = N.right;
			N.right = top;
			
			// adjusting parents
			N.parent = top.parent;
			top.parent = N;
			
		}
		
		// right left
		else
		{	
			// first rotation
			middle.left = N.right;
			top.right = N;
			N.right = middle;
			
			//adjusting parents
			N.parent = top;
			middle.parent = N;
			
			// second rotation
			top.right = N.left;
			N.left = top;
			
			//adjusting parents
			N.parent = top.parent;
			top.parent = N;
			
		}
		
		if(N.parent != null)
		{
			if(N.value > N.parent.value)
				N.parent.right = N;
			else
				N.parent.left = N;
		}
		
	}
	
	
}

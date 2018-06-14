
public class PriorityQueue 
{	
	TreeNode[] PQ;
	int size;
	final int max_size;
	int last;
	
	public PriorityQueue(TreeNode[] input)
	{
		PQ = input;
		size = input.length;
		max_size = size;
		last = size;
		Heapify(input,size);
	}
	
	private void heapify_section(TreeNode[] array, int size, int parent_index)
	{
		int smallest = parent_index;  // Assume parent is "lightest" and compare later
		int leftchild = 2 * parent_index + 1;
		int rchild = 2 * parent_index + 2;

			
		
 		 
		 
 		// left child is smaller
		if (leftchild < size && array[leftchild].frequency <= array[smallest].frequency)
			{	
				if(array[leftchild].frequency == array[smallest].frequency)
				{
					if(array[leftchild].occurence > array[smallest].occurence)
					{
						smallest = leftchild;
					}
				}
				else
					smallest = leftchild;
			}
		 
		// right child is smallest
		if (rchild < size && array[rchild].frequency <= array[smallest].frequency)
			{
				if(array[rchild].frequency == array[smallest].frequency)
				{
					if(array[rchild].occurence > array[smallest].occurence)
					{
						smallest = rchild;
					}
				}
				else
					smallest = rchild;
			}
			
		 
		        
		if (smallest != parent_index)
		{
			swap(array,parent_index,smallest);
		            
			// Recursively heapify the sub-trees affected by the swappings 
			heapify_section(array, size, smallest);
		}
		
	}
	
	private void Heapify(TreeNode[] array,int size)
	{		
		for(int i = size/2 - 1; i >= 0; i--)
			heapify_section(array,size,i);
			
	}
	
	private static void swap(TreeNode[] array,int a, int b)
	{	
		TreeNode temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public void printPQ()
	{
		for(int i = 0 ; i < size; i++)
		{
			System.out.println("Character: " + PQ[i].character + " Frequency: " + PQ[i].frequency + " Occurence: " + PQ[i].occurence);
		}
	}
	
	public TreeNode dequeue()
	{	
		TreeNode out;
		
		if(size == 0)
		{
			System.out.println("Queue is empty.");
			return null;
		}
		
		if(size != 1)
		{
			out = PQ[0];
			PQ[0] = null;
			swap(PQ,0,size-1);
			last--;
			size--;
			Heapify(PQ,size);
			return out;
		}
		else
		{
			out = PQ[0];
			PQ[0] = null;
			last--;
			size--;
			return out;
		}
			
	}
	
	public void enqueue(TreeNode N)
	{	
		if(size == max_size)
		{
			System.out.println("Queue is full");
			return;
		}
		else
		{
			PQ[last] = N;
			size++;
			last++;
			Heapify(PQ,size);
		}
		
	}
}

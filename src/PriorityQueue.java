
public class PriorityQueue 
{	
	TreeNode[] PQ;
	int size;
	int first = 0;
	
	public PriorityQueue(TreeNode[] input)
	{
		PQ = input;
		size = input.length;
		Heapify(input,size,first);
	}
	
	private static void heapify_section(TreeNode[] array, int size, int parent_index)
	{
		int smallest = parent_index;  // Assume parent is largest and compare later
		int leftchild = 2*parent_index + 1;  // Index starts at 0 so 2i + 1
 		int rchild = 2*parent_index + 2;  // Index starts at 0 so 2i + 2
		 
 		// left child is largest
		if (leftchild < size && array[leftchild].frequency <= array[smallest].frequency)
			{	
				if(array[leftchild].frequency == array[smallest].frequency)
				{
					if(array[leftchild].occurence < array[smallest].occurence)
					{
						smallest = leftchild;
					}
				}
				else
					smallest = leftchild;
			}
		 
		// right child is larger
		if (rchild < size && array[rchild].frequency <= array[smallest].frequency)
			{
				if(array[rchild].frequency == array[smallest].frequency)
				{
					if(array[rchild].occurence < array[smallest].occurence)
					{
						smallest = rchild;
					}
				}
			}
		 
		        
		if (smallest != parent_index)
		{
			swap(array,parent_index,smallest);
		            
			// Recursively heapify the sub-trees affected by the swappings 
			heapify_section(array, size, smallest);
		}
		
	}
	
	private static void Heapify(TreeNode[] array,int size, int first)
	{
		for(int i = size/2 - 1; i >= first; i--)
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
		for(int i = first, j = 1 ; j <= size; i++,j++)
		{
			System.out.println("Character: " + PQ[i].character + " Frequency: " + PQ[i].frequency + " Occurence: " + PQ[i].occurence);
		}
	}
	
	public TreeNode dequeue()
	{
			if(size != 1)
			{	
				if(size == 0)
					return null;
				first++;
				size--;
				Heapify(PQ,size,first);
				
				return PQ[first-1];
			}
			else
			{	
				size--;
				return PQ[first];
			}
			
	}
}

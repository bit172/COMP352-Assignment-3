
public class Huffman {
	
	public static void main(String[] args)
	{	
		
	}
	private class Tree
	{
		TreeNode root;
	}
	
	private class FrequencyTable
	{
		FrequencyNode head;
		int size = 0;
		
		public FrequencyTable(FrequencyNode H)
		{
			head = H;
			size++;
		}
		
		public void add(char C,int O)
		{
			FrequencyNode T = head;
			
			while(T.next != null)
			{
				if(T.character == C)
				{
					T.frequency++;
					return;
				}
				
				T = T.next;
			}
			
			FrequencyNode N = new FrequencyNode(C,O);
			
			T.next = N;
		}
		
	}
	
	private class FrequencyNode
	{
		int frequency;
		int occurence;
		char character;
		FrequencyNode next;
		public FrequencyNode(char character,int occurence) {
			this.frequency = 1;
			this.occurence = occurence;
			this.character = character;
			this.next = null;
		}
		
		
	}

}

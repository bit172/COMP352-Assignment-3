import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Huffman {
	
	public static void main(String[] args)
	{	
		BufferedReader BR = null;
		int occurence = 0;
		FrequencyTable FT = new FrequencyTable();
		
		try 
		{
			 BR = new BufferedReader(new FileReader(args[0]));
			 
			 int current = BR.read();
			 
			 while(current != -1)
			 {
				FT.add(current, occurence);
				occurence++;
				current = BR.read();
			 }
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
			System.exit(0);
		} 
		catch (IOException e) 
		{
			System.out.println("IO Error");
			System.exit(0);
		}
		
		TreeNode[] farray = FT.getFrequencyArray();
		
		int input_size = 0;
		
		for(int i = 0; i < 255;i++)
		{
			if(farray[i] != null)
			{
				input_size++;
			}
		}
		TreeNode[] input = new TreeNode[input_size];
		
		for(int i = 0, j=0; i < 255;i++)
		{
			if(farray[i] != null)
			{
				input[j] = farray[i];
				j++;
			}
		}
		
		PriorityQueue PQ = new PriorityQueue(input);
		PQ.printPQ();
		
		
	}
	private class Tree
	{
		TreeNode root;
	}
	
	private static class FrequencyTable
	{	// 256 Total ASCII Characters
		private TreeNode[] FrequencyArray = new TreeNode[256];
		
		public FrequencyTable()
		{
			
		}
		
		public void add(int I,int O)
		{
			char character = (char) I;
			
			if(FrequencyArray[I] == null)
			{
				TreeNode N = new TreeNode(O,character);
				FrequencyArray[I] = N;
			}
			else
				FrequencyArray[I].frequency++;
		}
		
		public TreeNode[] getFrequencyArray()
		{
			return FrequencyArray;
		}
			
	}

}

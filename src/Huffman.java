import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Huffman {
	
	public static void main(String[] args)
	{	
		BufferedReader BR = null;
		FrequencyTable FT = new FrequencyTable();
		
		try 
		{	// Reading the file from the input of the first command-line argument
			 BR = new BufferedReader(new FileReader(args[0]));
			 
			 // Reading all of the characters of the file and putting them in the frequency table
			 int current = BR.read();
			 
			 while(current != -1)
			 {
				FT.add(current);
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
		
		// Transferring the data from the frequency table to the heap based priority queue
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
		
		// Building Huffman Tree
		
		// Set the root to null initially
		Tree HuffmanTree = new Tree(null);
		
		
		while(PQ.size > 1)
		{
			TreeNode x = PQ.dequeue();
			TreeNode y = PQ.dequeue();
			
			TreeNode z = new TreeNode();
			
			z.frequency = x.frequency + y.frequency;
			z.occurence = FrequencyTable.occurrence++;
			z.character = (char) 0;
			
			z.left = x;
			z.right = y;
			
			HuffmanTree.root = z;
			
			PQ.enqueue(z);
		}
		
		String S = "";
		System.out.println("Encoding Table");
		System.out.println("===========================================================================");
		
		// Printing encoding table
		printCode(HuffmanTree.root, S,farray);
		
		Scanner kb = new Scanner(System.in);
		
		
		System.out.print("\nPlease enter a string to encode: ");
		String to_encode = kb.nextLine();
		
		// Get String from user to encode
		getEncoding(to_encode, farray);
		
		kb.close();
		
		
	}
	
	private static class Tree
	{
		TreeNode root;
		
		public Tree(TreeNode r)
		{
			root = r;
		}
		
	}
	
	private static class FrequencyTable
	{	
		// 256 Total ASCII Characters
		private TreeNode[] FrequencyArray = new TreeNode[256];
		
		// This will be used by the HuffmanTree when adding nodes to get a new occurrence
		public static int occurrence = 0;
		
		public FrequencyTable(){}
		
		public void add(int I)
		{	
			char character = (char) I;
			
			if(FrequencyArray[I] == null)
			{
				TreeNode N = new TreeNode(occurrence,character);
				occurrence++;
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
	
	public static void printCode(TreeNode root, String s,TreeNode[] Freq)
    {
 
        // Reaching a leaf node
        if (root.left == null && root.right == null) 
        {
        	
        	String Char_print = "Character: " + Freq[(int)root.character].character;
        	String Freq_print = " Frequency: " + Freq[(int)root.character].frequency;
        	String Occ_print = " Occurence: " + Freq[(int)root.character].occurence;
        	Freq[(int) root.character].encoding = s;
        	String Enc_print = " Encoding:" + Freq[(int) root.character].encoding;
        	System.out.printf("%-15s  %-15s %-15s %-15s%n",Char_print,Freq_print,Occ_print,Enc_print); 
            return;
        }
 
        // left add 0
        // right add 1
 
        //Higher order recursion on the left and right
        printCode(root.left, s + "0",Freq);
        printCode(root.right, s + "1",Freq);
    }
	
	public static void getEncoding(String s, TreeNode[] Freq)
	{
		int len = s.length();
		
		System.out.println();
		
		for(int i = 0; i < len ; i++)
		{
			System.out.print(Freq[(int)s.charAt(i)].encoding);
		}
		
		System.out.println();
		
	}

}

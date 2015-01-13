import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 Sort using radix sort algorithm
 @author Mark Santiago
 */
public class FileModifiedRadixSort 
{
	private static String fileName; //the name of the file
	
	public static void main (String args[]) throws FileNotFoundException
	{
		long startTime = System.currentTimeMillis(); //the time the program starts
		
		fileName = args[0];
		
		//Reads the file
		Scanner input = new Scanner(new File(fileName));
		List<String> lines = new ArrayList<String>();
		while (input.hasNextLine()) 
		{
		  lines.add(input.nextLine());
		}
		
		//Find the longest string
		String longest = lines.get(0);
		for (int i = 0; i < lines.size(); i++)
		{
			String current = lines.get(i);
			if (current.length() > longest.length())
			{
				longest = current;
			}
		}
		
		radixSort(lines, longest.length());
		
		long endTime = System.currentTimeMillis(); //the time the program ends
		
		System.out.println((endTime - startTime) + " milliseconds");
	}
	
	/**
	 Performs radix sort.
	 @param data the strings to be sorted
	 @param stringLength the max length of the string
	 */
	public static void radixSort(List<String> data, int stringLength)
	{
		int index = 0;
		ArrayList<ArrayList<String>> arrays = new ArrayList<ArrayList<String>>(); //a new array created for each different string length
		String[] sorted = new String[data.size()]; //the sorted strings
		
		//Initialize all of the arraylists
		for (int i = 0; i < stringLength; i++)
		{
			arrays.add(new ArrayList<String>());
		}
		
		//Go through list, adding strings of same length to same array list
		for (int i = 0; i < data.size(); i++)
		{
			for (int j = 0; j < stringLength; j++)
			{
				if (data.get(i).length() == j)
				{
					arrays.get(j).add(data.get(i));
				}
			}
		}	
		
		//Add all strings into a single sorted array
		for (int k = 0; k < arrays.size(); k++)
		{
			for (int l = 0; l < arrays.get(k).size(); l++)
			{
				sorted[index] = arrays.get(k).get(l);
				index++;		
			}
		}
			
		//Print the results
		for (int i = 0; i < sorted.length - 1; i++)
		{
			System.out.println(sorted[i]);
		}
	}
}

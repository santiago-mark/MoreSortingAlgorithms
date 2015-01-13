import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 Produces a quick sort on files of random words.
 The line numbers in which pivot_pos are used are: 49, 50, 63.
 @author Mark Santiago
 */
public class FileQuickSort 
{
	private static String fileName; //the file of the strings being sorted
	private static int pivot_pos; //the desired pivot position
	public static void main (String args[]) throws FileNotFoundException
	{
		long startTime = System.currentTimeMillis(); //the time the program starts
		
		fileName = args[0];
		pivot_pos = Integer.parseInt(args[1]);
		
		//Reads the file
		Scanner input = new Scanner(new File(fileName));
		List<String> lines = new ArrayList<String>();
		while (input.hasNextLine()) 
		{
		  lines.add(input.nextLine());
		}
		
		quickSort(lines, 0, lines.size() - 1);
		
		for (int i = 0; i < lines.size(); i++)
		{
			System.out.println(lines.get(i));
		}
		
		long endTime = System.currentTimeMillis(); //the time the program ends
		
		System.out.println((endTime - startTime) + " milliseconds");
	}
	
	/**
	 Recursively sorts an ArrayList by the Quick Sort algorithm
	 @param data the list of strings to be sorted
	 @param from the beginning index
	 @param to the ending index
	 */
	public static void quickSort(List<String> data, int from, int to)
	{
		if (from < to)
		{
			int p = partition(data, from, to);
			
			quickSort(data, from + pivot_pos, p - 1);
			quickSort(data, p + 1 + pivot_pos, to);
		}	
	}
	
	/**
	 Partitions the array list around a pivot point
	 @param data the list of strings to be sorted
	 @param from the beginning index
	 @param to the ending index
	 @return the index with the pivot point
	 */
	private static int partition(List<String> data, int from, int to)
	{
		String pivot = data.get(from + pivot_pos); //the pivot point
		int i = from - 1;
		
		for (int j = from; j < to; j++)
		{
			if (data.get(j).compareTo(pivot) <= 0)
			{
				i++;
				Collections.swap(data, i, j);
			}
		}
		
		Collections.swap(data, i + 1, from + pivot_pos);
		return i + 1;
	}
}

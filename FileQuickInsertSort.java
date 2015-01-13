import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
Produces a randomized-quick sort and insertion sort on files of random words.
The program switches from the randomized-quick sort method to the insertion sort method in the
sort() method, which is lines 46 - 56.
The random pivot used for randomized-quick sort is decided on line 87.
@author Mark Santiago
*/
public class FileQuickInsertSort 
{
	private static String fileName; //the file of the strings being sorted
	/*
	 the minimum size needed for the array before switching to the insertion sort method
	 */
	private static int insert_threshold;
	
	public static void main (String args[]) throws FileNotFoundException
	{
		long startTime = System.currentTimeMillis(); //the time the program starts
		
		fileName = args[0];
		insert_threshold = Integer.parseInt(args[1]);
		
		//Reads the file
		Scanner input = new Scanner(new File(fileName));
		List<String> lines = new ArrayList<String>();
		while (input.hasNextLine()) 
		{
		  lines.add(input.nextLine());
		}
		
		sort(lines, 0, lines.size() - 1);
		
		for (int i = 0; i < lines.size(); i++)
		{
			System.out.println(lines.get(i));
		}
		
		long endTime = System.currentTimeMillis(); //the time the program ends
		
		System.out.println((endTime - startTime) + " milliseconds");
	}
	
	/**
	 Decide which sort method to use.
	 @param data the strings to be sorted
	 @param from the beginning index
	 @param to the ending index
	 */
	public static void sort(List<String> data, int from, int to)
	{
		if (data.size() < insert_threshold)
		{
			insertionSort(data, from, to);
		}
		else
		{
			randomizedQuickSort(data, from, to);
		}
	}
	
	/**
	 Sort according to the insertion sort algorithm
	 @param data the strings to be sorted
	 @param from the beginning index
	 @param to the ending index
	 */
	public static void insertionSort(List<String> data, int from, int to)
	{
		for (int j = 1; j < data.size(); j++)
		{
			String word = data.get(j);
			int i = j - 1;
			while (i > 0 && data.get(i).compareTo(word) <= 0)
			{
				data.set(i + 1, data.get(i));
				i--;
			}
			data.set(i + 1, word);
		}
	}
	
	/**
	 Recursively sort with a randomized-quick sort algorithm
	 @param data the strings to be sorted
	 @param from the beginning index
	 @param to the ending index
	 */
	public static void randomizedQuickSort(List<String> data, int from, int to)
	{
		if (from < to)
		{
			int p = randomizedPartition(data, from, to);
			sort(data, from, p - 1);
			sort(data, p + 1, to);
		}
	}
	
	
	/**
	 Pick a random pivot point to partition the array around
	 @param data the strings to be sorted
	 @param from the beginning index
	 @param to the ending index
	 @return the partitions of the data
	 */
	private static int randomizedPartition(List<String> data, int from, int to)
	{
		Random random = new Random();
		int randomPivot = Math.abs(random.nextInt(data.size())); //pick random pivot
		Collections.swap(data, to, randomPivot);
		return partition(data, from, to);
	}
	
	/**
	 Partitions the array list around a pivot point
	 @param data the strings to be sorted
	 @param from the beginning index
	 @param to the ending index
	 @return the index with the pivot point
	 */
	private static int partition(List<String> data, int from, int to)
	{
		String pivot = data.get(to); //the pivot point
		int i = from - 1;
		
		for (int j = from; j < to; j++)
		{
			if (data.get(j).compareTo(pivot) <= 0)
			{
				i++;
				Collections.swap(data, i, j);
			}
		}
		
		Collections.swap(data, i + 1, from);
		return i + 1;
	}
}

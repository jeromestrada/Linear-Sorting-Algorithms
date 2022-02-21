package linearsort;

import java.util.ArrayList;

/**
 * Demonstrates how Bucket Sort sorts using Insertion Sort to sort each bucket.
 * @author Jerom Estrada
 *
 */
public class BucketSort {
	
	public static void main(String [] args)
	{
		System.out.println("Initial state of A array:");
		int[] array = {71,72,21,81,41,22,31,50,42,82,32,61,43,62,23};
		printArray(array);

		System.out.println("\nCalling bucketSort(A)...\n");
		bucketSort(array);
		System.out.println("\nResult after using bucket sort:");
		printArray(array);
		
	}
	
	/**
	 * Bucket Sort that uses an array of ArrayList<Double> for buckets.
	 * @param array to be sorted using Bucket Sort.
	 */
	public static void bucketSort(int[] array)
	{
		int arrayLength = array.length;
		
		ArrayList<Double> normalized = new ArrayList<>(); // Empty array list to contain normalized numbers.
		
		for(int i = 0; i < arrayLength; i++)
		{	// Since the numbers have their subscripts in the ones place, diving by 100 is similar to dividing with 10
			// without the subscripts. This is consistent with the way we should normalize the numbers.
			normalized.add((double)array[i]/100); // Dividing with 100 to normalize that numbers to be [0,1).
		}
		
		// Make empty buckets. Each bucket is an ArrayList of Doubles.
		ArrayList<Double>[] buckets = new ArrayList[arrayLength];
		for(int i = 0; i < arrayLength; i++)
		{
			buckets[i] = new ArrayList<Double>(); // Create an empty ArrayList<Double>
		}
		System.out.println("There are " + buckets.length + " buckets!");
		for(int i = 0; i < arrayLength; i++)
		{
			// Follows the psuedocode: bucket[ array[floor(n * array[i])] = array[i]
			System.out.println("Adding " + normalized.get(i) + " into bucket " + (int)Math.floor(arrayLength*normalized.get(i)));
			buckets[(int)Math.floor(arrayLength*normalized.get(i))].add(normalized.get(i));
		}
		/*
		 * Sorts each bucket using Insertion Sort.
		 */
		for(int i = 0; i < arrayLength; i++)
		{
			System.out.println("Calling insertion sort on bucket " + i);
			insertionSort(buckets[i]);
		}
		
		// Concatenate the results of each sorted bucket.
		System.out.println("\nConcatenating the buckets into the output array...");
		int arrayIndex = 0;
		for(int i = 0; i < arrayLength; i++)
		{
			while(buckets[i].size() > 0)
			{
				double item = buckets[i].remove(0) * 100;
				array[arrayIndex] = (int) item;
				arrayIndex++;
				System.out.println("Inserted " + item + ", ");
			}
		}	
	}
	
	/**
	 * Insertion Sort that is used in Bucket Sort to sort elements in an ArrayList
	 * @param array to be sorted using Insertion Sort.
	 */
	public static void insertionSort(ArrayList<Double> array)
	{
		int i = 0;
		for(int j = 1; j < array.size(); j++)
		{
			double key = array.get(j);
			i = j-1;
			while(i >= 0 && array.get(i) > key)
			{
				array.set(i+1, array.get(i));
				i--;
			}
			array.set(i+1, key);
		}
		printArrayL(array);
	}
	
	/**
	 * Prints the int elements in an array.
	 * @param array to be printed.
	 */
	public static void printArray(int[] array)
	{
		if(array.length == 0)
		{
			return;
		}
		System.out.print("( " + array[0]);
		for(int i = 1; i < array.length; i++)
		{
			System.out.print(", " + array[i]);
		}
		System.out.println(" )");
	}

	/**
	 * Prints the elements in an ArrayList.
	 * @param array to be printed.
	 */
	public static void printArrayL(ArrayList<Double> array)
	{
		if(array.size() == 0)
		{
			return;
		}
		System.out.print("( " + array.get(0));
		for(int i = 1; i < array.size(); i++)
		{
			System.out.print(", " + array.get(i));
		}
		System.out.println(" )");
	}

}

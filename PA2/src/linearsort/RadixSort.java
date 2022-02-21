package linearsort;

import java.util.Random;

/**
 * Demonstrates how Radix Sort works on an array list of 30 5-digit hexadecimal numbers.
 * @author Jerom Estrada
 *
 */
public class RadixSort {
	
	public static void main(String [] args)
	{
		/*
		 * Toggles the printArrayS to lined or not. Lined - prints the elements in a new line each to see the sort per digit clearly.
		 */
		boolean lined = true;
		
		Random rand = new Random();
		System.out.println("Initial state of A array:");
		String[] array = new String[30];
		
		for(int i = 0; i < 30; i++)
		{
			array[i] = Integer.toHexString(rand.nextInt(983040) + 65536); // generates [65536, 1048576)
		}
		printArrayS(array, lined);
		
		System.out.println("Calling radixSort(A, 5)");
		radixSort(array, 5);
		System.out.println("\n Final Result using Radix Sort on 5 digit hexadecimal numbers");
		printArrayS(array, lined);
		
	}
	
	/**
	 * Radix Sort that employs a Modified Counting Sort to sort the array with a specified number of digits.
	 * @param array that will be sorted using Radix Sort.
	 * @param digits of the numbers in the array to be sorted by Radix Sort.
	 */
	public static void radixSort(String[] array, int digits)
	{
		String[] outArray = new String[array.length];
		
		for(int i = 1; i <= digits; i++)
		{
			System.out.println("\nSorting on digit " + i + ": ");
			countingSortOnDigit(array, outArray, 16, i);
			// Deep copy outArray into array, to save the state of this call for the next one.
			for(int j = 0; j < array.length; j++)
			{
				array[j] = outArray[j];
				outArray[j] = ""; // reinitialize outArray to null.
			}
		}
		
	}
	/**
	 * Modified Counting Sort that has an additional parameter, digit. Digit signifies the position of the digits the will be sorted.
	 * @param inArray is the input array to the Counting Sort On Digit
	 * @param outArray is the output array from the Counting Sort On Digit.
	 * @param range is the range of the numbers to be sorted.
	 * @param digit is the position of the digits, 1 being the least significant digit, to be sorted in the input array.
	 */
	public static void countingSortOnDigit(String[] inArray, String[] outArray, int range, int digit)
	{
		// Signifies the start of the outputs in the Counting Sort Algorithm.
		System.out.println("--------------------- Modified Counting Sort ---------------------\n");
		
		/*
		 * Toggles the printArrayS to lined or not. Lined - prints the elements in a new line each to see the sort per digit clearly.
		 */
		boolean lined = true;
		
		System.out.println("State of the array before sorting on digit " + digit + ":");
		printArrayS(inArray, lined);
		
		/*
		 *  Create an empty auxillary array.
		 */
		System.out.println("Creating C array...");
		int[] auxArray = new int[range+1];
		for(int i = 0; i <= range; i++)
		{
			auxArray[i] = 0;
		}
		System.out.println("Printing empty C array:");
		printArray(auxArray);
		
		/*
		 * Fill the auxillary array with the instances of the elements accordingly.
		 */
		System.out.println("\nRecording occurences in C...");
		for(int i = 0; i < inArray.length; i++)
		{	// This index is calculated based on the decimal value of the hex digit in the ith position in the String.
			int stringLength = inArray[i].length();
			int indexOfDigit = Integer.parseInt(inArray[i].substring(stringLength-digit, stringLength-digit+1), range);
			auxArray[indexOfDigit]++;
		}
		System.out.println("State of C array after recording occurences:");
		printArray(auxArray);
		
		/*
		 * Compute the numberof elements that is <= than i, this will serve
		 * as the indexing of the elements in the outArray later on.
		 */
		System.out.println("Computing the initial indices of each value...");
		for(int i = 1; i <= range; i++) // we start i at 1 because we add i-1 to every index.
		{
			auxArray[i] += auxArray[i-1];
		}
		System.out.println("State of C array after computing initial indices:");
		printArray(auxArray);
		
		/*
		 * Now we start putting the elements in their right spots starting with the 
		 * indices computed from the previous for loop.
		 */
		int step = 1;
		System.out.println("\nPutting each element to the right spots...");
		System.out.println("Printing state of B array for each element placement...");
		for(int i = inArray.length-1; i >= 0; i--)
		{
			int stringLength = inArray[i].length();
			int indexOfDigit = Integer.parseInt(inArray[i].substring(stringLength-digit, stringLength-digit+1), range);
			outArray[auxArray[indexOfDigit] - 1] = inArray[i];
			auxArray[indexOfDigit]--; // decrement by one to put next element of the same value down by one position.
			System.out.print("Step " + step++ + ": ");
			printArrayS(outArray, lined);
		}
		
		System.out.println("State of A array after sorting: ");
		printArrayS(outArray, lined);
		// Formatting Output to signify end of the outputs of Counting Sort Algorithm.
		System.out.println("--------------------- Modified Counting Sort ---------------------\n");
	}
	
	/**
	 * Prints the array of Strings.
	 * @param array that contains Strings.
	 */
	public static void printArrayS(String[] array, boolean lined)
	{
		if(array.length == 0)
		{
			return;
		}
		System.out.print("( " + array[0]);
		if(lined)
		{
			System.out.println();
		}
		for(int i = 1; i < array.length; i++)
		{
			System.out.print(", " + array[i]);
			if(lined)
			{
				System.out.println();
			}
		}
		System.out.println(" )");
	}
	/**
	 * Prints the array of ints.
	 * @param array that contains ints.
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

}

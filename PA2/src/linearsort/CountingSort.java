package linearsort;

/**
 * Demonstrates the stability of Counting Sort.
 * @author Jerom Estrada
 *
 */
public class CountingSort {
	
	public static void main(String [] args)
	{
		/*
		 * I have decided that the subscripts can be represented using one more place value.
		 * This is not going to affect the algorithm since there will be no comparisons done.
		 * Thus, the stability of Counting Sort can still be observed using the given set.
		 */
		System.out.println("Initial state of A array:");
		int[] array = {71,72,21,81,41,22,31,51,42,82,32,61,43,62,23};
		printArray(array);
		
		System.out.println("\nInitial state of B array:");
		int[] outputArray = new int[15];
		printArray(outputArray);

		System.out.println("\nCalling countingSort(A, B, 9)...\n");
		countingSort(array, outputArray, 9);
		System.out.println("\nResult after using counting sort:");
		printArray(outputArray);
	}
	
	public static void countingSort(int[] inArray, int[] outArray, int range)
	{
		System.out.println("--------------------- Counting Sort ---------------------\n");
		
		/* 
		 * Create empty auxillary array to store the number of elements
		 * that is <= i.
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
		{	// We are dividing by 10 to remove the subscript, which is actually the number in the ones place.
			// Doing so will strip the ones and use the 'real' number as the value for the indexing.
			auxArray[inArray[i]/10]++; // add one instance of this element.
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
			outArray[auxArray[inArray[i]/10] - 1] = inArray[i];
			auxArray[inArray[i]/10]--; // decrement by one to put next element of the same value down by one position.
			System.out.print("Step " + step++ + ": ");
			printArray(outArray);
		}
		
		System.out.println("--------------------- Counting Sort ---------------------\n");
	}
	
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

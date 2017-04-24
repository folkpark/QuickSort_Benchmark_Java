/*
 * 
  * Author: Parker Folkman 
 * CSCI 361 CS Architecture
 * Program Description: This code is part of a project where 
 * performance of QuickSort written in Java is compared to 
 * bubbleSort written in Assembly. This code sorts an array
 * of specified length and times the sorting. The results are
 * written out to a txt file named JavaResults.txt in the project
 * folder. 
 * 
*/



package QuickSort;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		PrintWriter writer = new PrintWriter("JavaResults.txt", "UTF-8");
		boolean again = true;
		do{
			System.out.print("Enter the size of the array (-1 to quit)-> ");
			int arrSize = sc.nextInt();
			//writer.println("Array Size = " + arrSize);
			
			if(arrSize != -1){
				System.out.print("Enter the number of trials -> ");
				int trials = sc.nextInt();
				
				int[] intArray = new int[arrSize]; //Create new array object
				for(int j=0;j<intArray.length;j++) //Initialize with random numbers
					intArray[j] = rand.nextInt(arrSize);
				
				printArray(intArray);
				
				for(int i=0;i<trials;i++){
					long duration = findSortTime(intArray);
					writer.println(duration);
				}
				writer.println();
			}else{
				again = false;
			}
		}while(again);
		
		writer.close();
		sc.close();
	}//End Main Function
	
	public static synchronized void quickSort(int[] arr, int low, int high){
		if(arr == null || arr.length == 0 || low >= high){ //Base Case
			return;
		}
		
		//Find the pivot
		int middle = low + (high - low) /2;
		int pivot = arr[middle];
		
		//make left < [ivot and right > pivot
		int i = low;
		int j = high;
		while(i <= j){
			while(arr[i] < pivot){
				i++;
			}
			
			while(arr[j] > pivot){
				j--;
			}
			if(i <= j){
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
		if(low < j){
			quickSort(arr,low,j);
		}
		if(high > i){
			quickSort(arr,i,high);
		}
	}
	
	public static void printArray(int[] intArray) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writeInput = new PrintWriter("SortingInputs.txt", "UTF-8");
		for(int i=0;i<intArray.length;i++){
			writeInput.println(intArray[i]);
		}
		writeInput.close();
	}
	
	public static synchronized long findSortTime(int[] intArray){
		long duration = 0;
		long start = System.nanoTime();
		quickSort(intArray,0,intArray.length-1);
		long end = System.nanoTime();
		duration = end - start; 
		return duration;
	}

}//End Main Class



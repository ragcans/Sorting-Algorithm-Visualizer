package sorting;

import java.util.Arrays;
import java.util.Random;

public class Algorithms { 
    private int[] arr;
    private int arrSize = 10;
    private int generation = 0;

    public Algorithms() {
        arr = new int[arrSize];
        randomize(arr, arrSize);
    }

    /*
     * Fisher-Yates Shuffle Algorithm from geeksforgeeks.org
     * You can learn more about the algorithm here:
     * https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/ 
     */
    public void randomize(int arr[], int n) {
        Random r = new Random();
        for (int i = n-1; i > 0; i--) {
            int j = r.nextInt(i+1);     // Pick a random index from 0 to i
            
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }



    public void SelectionSort() {
        int smPos = generation;

        // finds the smallest element of the list 
        for (int i = generation + 1; i < arrSize; i++){
            if (arr[i] < arr[smPos])
                smPos = i;
        }

        // swaps smallest element with the element at position generation
        int temp = arr[generation];
        arr[generation] = arr[smPos];
        arr[smPos] = temp;

        generation++;   // updates generation
    }



    public void InsertionSort() {
        int x = generation + 1;

        if (x < arrSize) {
            int num = arr[x]; 

            // Compares element num to its predecessor and swaps the two if num is smaller
            // Swaps continue until num is in its correct place 
            while(x > 0 && arr[x-1] > num ){
                arr[x] = arr[x-1]; 
                x--;
            }
            arr[x] = num;
            generation++;
        }
    }


    // TODO: MAKE NON RECURSIVE
    public int[] MergeSort(int[] list) {
        // Base case: A list of size 1 is already sorted.
        if(arrSize <= 1){
            return arr;
        }
        
        // Copy each half of the array
        int[] leftHalf = Arrays.copyOfRange(arr, 0, arrSize/2);
        int[] rightHalf = Arrays.copyOfRange(arr, arrSize/2, arrSize);

        // Recursively sort each half
        leftHalf = MergeSort(leftHalf);
        rightHalf = MergeSort(rightHalf);

        // Merge the two sorted lists together
        arr = mergeLists(leftHalf, rightHalf);

        // Print out the merged list
        
        return arr;
    }

    public int[] mergeLists(int[] listA, int[] listB){
        // Create a new array with the combined length of of listA and listB
        int[] merged = new int[listA.length + listB.length];

        int indexA = 0;
        int indexB = 0;
        int i = 0; // going through the merged array

        // while each list still has number, pick the smallest and add to the merged list.
        while(indexA < listA.length && indexB < listB.length){
            if(listA[indexA] < listB[indexB]){
                merged[i++] = listA[indexA++];
            } 
            else {
                merged[i++] = listB[indexB++];
            }
        }

        // when one list runs out, use the remainder of whichever is left to fill;
        while(indexA < listA.length){
            merged[i++] = listA[indexA++];
        }
        while(indexB < listB.length){
            merged[i++] = listB[indexB++];
        }

        return merged;
    }



    public void BogoSort() {
        boolean sorted = true; 

        // checks if the array is sorted
        for (int i = 0; i < arrSize-1; i++) {
            if (arr[i] > arr[i+1]) {
                sorted = false;
                break;
            }
        }   

        // if the array isnt sorted, shuffle the array
        if (sorted == false) {
            randomize(arr, arrSize); 
        }
    }


}
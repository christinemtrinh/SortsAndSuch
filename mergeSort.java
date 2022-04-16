/*
This program implements mergeSort
Note that to test the *function*, "mergeSort" was actually spelled "mergSort"
*/
public class mergeSort {
    public static void main(String args[]) {
        //set up array for testing
        int[] myList = new int[]{8, 2, 3, 1, 9, 23, 21, 17, 31, 99, 3};

        //print list before sorting 
        for (int i=0; i<myList.length; i++)
            System.out.printf("%d ", myList[i]);

        //sort and keep time
        long startT = System.nanoTime();
        mergSort(myList); 
        long endT = System.nanoTime(); 

        //print list after sorting 
        System.out.printf("\n");
        for (int i=0; i<myList.length; i++)
            System.out.printf("%d ", myList[i]);

        //print time
        System.out.printf("\n\tTime taken: %d ns", endT-startT);
        return;
    }

    //driver function for merge sort
    public static void mergSort(int[] arr) {
        int[] tmpAr = new int[arr.length];
        mergSort(arr, tmpAr, 0, arr.length-1);
    }

    //actual function for merge sort
    private static void mergSort(int[] arr, int[] tmpAr, int left, int right) {
        //recurse the subarrays
        if (left < right) {
            int center = (left+right) / 2;
            mergSort(arr, tmpAr, left, center);
            mergSort(arr, tmpAr, center+1, right);
            merge(arr, tmpAr, left, center+1, right);
        }
    }

    //helper function to merge two sublists into one in non-decreasing order
    private static void merge(int[] arr, int[] tmpAr, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int tmpP = left;
        int numElements = rightEnd - left + 1;
        
        //compare both subarrays and pull from appropriate one 
        while(left <= leftEnd && right <= rightEnd) {
            if (arr[left] <= arr[right])
                tmpAr[tmpP++] = arr[left++];
            else
                tmpAr[tmpP++] = arr[right++];
        }

        //right subarray is depleted, merge remaining left
        while (left <= leftEnd) {
                tmpAr[tmpP++] = arr[left++];
        }

        //left subarray is depleted, merge remaining right
        while (right <= rightEnd) {
                tmpAr[tmpP++] = arr[right++];
        }

        //copy back into the original array 
        for(int i=0; i<numElements; i++, rightEnd--)
            arr[rightEnd] = tmpAr[rightEnd];
    }
}

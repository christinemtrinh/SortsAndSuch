/*
This program implements mergeSort
Note that to test the *function*, "mergeSort" was actually spelled "mergSort"
*/
public class mergeSort {
    public static void main(String args[]) {
        //set up array for testing
        int[] myList = new int[]{1, 3, 9, 10, 5,
            4, 4, 5, 6, 6, 
            7, 7, 8, 14, 14, 
            15, 16, 17, 17, 17,
            19, 20, 20, 21, 25,
            26, 8, 11, 14, 14,
            26, 26,	28, 28, 30, 
            31, 33,  34, 23, 25,
            34, 34, 35, 37, 33,
            37, 37, 38, 39, 39,
            43, 43, 44, 45, 46,
            51, 47, 47, 47, 50,
            57, 58, 52, 54, 55,
            60, 61, 45, 57, 57,
            61, 61, 66, 67, 67, 
            68, 68,	69, 65, 69,
            69, 76, 69, 69, 45,
            71, 71, 71, 72, 72, 
            72, 73, 74, 67, 68,
            75, 78, 80, 81, 81,
            83, 83, 83, 83, 83,
            83, 88, 86, 86, 76,
            88, 90, 91, 95, 96,
            97, 97, 91, 92, 95,
            98, 100, 100, 100, 96};

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

/* 
This program implements quickSort (for lists 15 elements or more)
Otherwise, it may just be more efficient to do insertionSort 
Note that to test the *function*, "quickSort" was actually spelled "quikSort"
*/

public class quickSort {

    //cutoff size for quick sort vs insertion sort 
    static int CUTOFF = 15;     
    public static void main(String args[]) {
        //set up array for testing
        int[] myList = new int[]{8, 2, 3, 1, 9, 23, 21, 17, 31, 99, 3};

        //print list before sorting 
        for (int i=0; i<myList.length; i++)
            System.out.printf("%d ", myList[i]);

        //sort and keep time
        long startT = System.nanoTime();
        quikSort(myList); 
        long endT = System.nanoTime(); 
        
        //print list after sorting 
        System.out.printf("\n");
        for (int i=0; i<myList.length; i++)
            System.out.printf("%d ", myList[i]);

        //print time
        System.out.printf("\n\tTime taken: %d ns", endT-startT);
        return;
    }
    //driver for quick sort
    public static void quikSort(int[] arr) {
        quikSort(arr, 0, arr.length-1);
    }

    //actual quick sort method
    private static void quikSort(int[] arr, int left, int right) {
        //check list size before doing quickSort
        if (left + CUTOFF <= right) {
            int pivot = median3(arr, left, right);

            //partition
            int i = left, j = right - 1;
            while (true) {
                while (arr[++i] < pivot) {}
                while (arr[--j] > pivot) {}
                if (i < j)
                    swap(arr, i, j);
                else
                    break; 
            }

            //restore pivot
            swap(arr, i, right - 1);
        
            //sort subarrays
            quikSort(arr, left, i-1);
            quikSort(arr, i+1, right);
        }
        //list has less than 15 elements
        else
            insertionSort(arr);     
    }

    //function to swap references in an array
    //this step is needed because java passes by values,
    //so reassigneing parameters takes more work
    private static void swap(int[] arr, int a, int b) {
        int x = arr[a];
        arr[a] = arr[b];
        arr[b] = x;
    }

    //helper method for median of 3 for picking a pivot
    private static int median3(int[] arr, int left, int right) {

        int center = (left + right) / 2;
        if(arr[center] < arr[left])
            swap(arr, left, center);
        if(arr[right]< arr[left])
            swap(arr, left, right);
        if(arr[right] < arr[center])
            swap(arr, center,right);

        //move pivot to end of list
        swap(arr, center, right-1);
        return arr[right -1]; 
    }

    //alternative sorting method for smaller lists
    public static void insertionSort(int[] arr) {
        int j; 
        //traverse through list L -> R
        for (int p=1; p<arr.length; p++) {
            int temp = arr[p];
            //move current element backwards to right place
            for (j=p; j>0 && temp < arr[j-1]; j--)
                arr[j] = arr[j-1];
            arr[j] = temp;
        }
    }
}
public class SortingAlgorithms {

    // Stores the number of comparisons made
    private static long comparisons;

    // Stores the number of swaps/movements made
    private static long swaps;


    // Reset counters before sorting a new partition
    public static void resetCounters() {
        comparisons = 0;
        swaps = 0;
    }


    // Return number of comparisons
    public static long getComparisons() {
        return comparisons;
    }


    // Return number of swaps
    public static long getSwaps() {
        return swaps;
    }


    // ---------------- BUBBLE SORT ----------------

    public static void bubbleSort(int[] array) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {

                comparisons++;

                if (array[j] > array[j + 1]) {

                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    swaps++;
                    swapped = true;
                }
            }

            // Array is already sorted
            if (!swapped) {
                break;
            }
        }
    }


    // ---------------- INSERTION SORT ----------------

    public static void insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= 0) {

                comparisons++;

                if (array[j] > key) {

                    array[j + 1] = array[j];
                    swaps++;

                    j--;

                } else {
                    break;
                }
            }

            array[j + 1] = key;
        }
    }


    // ---------------- MERGE SORT ----------------

    public static void mergeSort(int[] array) {

        if (array.length <= 1) {
            return;
        }

        mergeSort(array, 0, array.length - 1);
    }


    private static void mergeSort(int[] array, int left, int right) {

        if (left < right) {

            int middle = (left + right) / 2;

            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }


    private static void merge(
            int[] array,
            int left,
            int middle,
            int right) {

        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
        }

        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftSize && j < rightSize) {

            comparisons++;

            if (leftArray[i] <= rightArray[j]) {

                array[k] = leftArray[i];
                i++;

            } else {

                array[k] = rightArray[j];
                j++;
            }

            // Count the movement into the merged array
            swaps++;

            k++;
        }


        // Copy remaining elements from left partition
        while (i < leftSize) {

            array[k] = leftArray[i];

            swaps++;

            i++;
            k++;
        }


        // Copy remaining elements from right partition
        while (j < rightSize) {

            array[k] = rightArray[j];

            swaps++;

            j++;
            k++;
        }
    }
}
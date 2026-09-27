
import java.util.Random;
import java.util.Scanner;

public class HybridSorting {

    private static final int ARRAY_SIZE = 30;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int[] originalArray = new int[ARRAY_SIZE];

        // Generate 30 random integers from 0 to 100
        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = random.nextInt(101);
        }

        System.out.println("=================================");
        System.out.println("     HYBRID SORTING ALGORITHM");
        System.out.println("=================================");

        System.out.println("\nOriginal Array:");
        displayArray(originalArray);

        // Let the user choose three sorting algorithms
        int algorithm1 = chooseAlgorithm(scanner, 1);
        int algorithm2 = chooseAlgorithm(scanner, 2);
        int algorithm3 = chooseAlgorithm(scanner, 3);

        System.out.println("\nSelected Algorithms:");
        System.out.println("Algorithm 1: " + getAlgorithmName(algorithm1));
        System.out.println("Algorithm 2: " + getAlgorithmName(algorithm2));
        System.out.println("Algorithm 3: " + getAlgorithmName(algorithm3));

        // Divide the 30-element array into three equal partitions
        int[] partition1 = new int[10];
        int[] partition2 = new int[10];
        int[] partition3 = new int[10];

        for (int i = 0; i < 10; i++) {
            partition1[i] = originalArray[i];
            partition2[i] = originalArray[i + 10];
            partition3[i] = originalArray[i + 20];
        }

        System.out.println("\nPartitions Created:");

        System.out.print("Partition 1: ");
        displayArray(partition1);

        System.out.print("Partition 2: ");
        displayArray(partition2);

        System.out.print("Partition 3: ");
        displayArray(partition3);

        // Statistics for each algorithm
        long[] comparisons = new long[3];
        long[] swaps = new long[3];

        // Sort partition 1
        SortingAlgorithms.resetCounters();
        sortPartition(partition1, algorithm1);

        comparisons[0] = SortingAlgorithms.getComparisons();
        swaps[0] = SortingAlgorithms.getSwaps();

        System.out.println("\nAfter Algorithm 1 - "
                + getAlgorithmName(algorithm1) + ":");
        displayArray(partition1);

        // Sort partition 2
        SortingAlgorithms.resetCounters();
        sortPartition(partition2, algorithm2);

        comparisons[1] = SortingAlgorithms.getComparisons();
        swaps[1] = SortingAlgorithms.getSwaps();

        System.out.println("\nAfter Algorithm 2 - "
                + getAlgorithmName(algorithm2) + ":");
        displayArray(partition2);

        // Sort partition 3
        SortingAlgorithms.resetCounters();
        sortPartition(partition3, algorithm3);

        comparisons[2] = SortingAlgorithms.getComparisons();
        swaps[2] = SortingAlgorithms.getSwaps();

        System.out.println("\nAfter Algorithm 3 - "
                + getAlgorithmName(algorithm3) + ":");
        displayArray(partition3);

        // Merge the three sorted partitions
        int[] mergedArray = new int[ARRAY_SIZE];

        for (int i = 0; i < 10; i++) {
            mergedArray[i] = partition1[i];
            mergedArray[i + 10] = partition2[i];
            mergedArray[i + 20] = partition3[i];
        }

        System.out.println("\nMerged Array Before Final Refinement:");
        displayArray(mergedArray);

        /*
         * Final refinement.
         * The assignment requires one selected algorithm
         * to be used again on the merged array.
         * Here Algorithm 1 is used.
         */
        SortingAlgorithms.resetCounters();
        sortPartition(mergedArray, algorithm1);

        long finalComparisons = SortingAlgorithms.getComparisons();
        long finalSwaps = SortingAlgorithms.getSwaps();

        System.out.println("\nFinal Refinement Algorithm: "
                + getAlgorithmName(algorithm1));

        System.out.println("\nFinal Sorted Array:");
        displayArray(mergedArray);

        // Calculate totals
        long totalComparisons =
                comparisons[0]
                + comparisons[1]
                + comparisons[2]
                + finalComparisons;

        long totalSwaps =
                swaps[0]
                + swaps[1]
                + swaps[2]
                + finalSwaps;

        // Display statistics
        System.out.println("\n=================================");
        System.out.println("        SORTING STATISTICS");
        System.out.println("=================================");

        for (int i = 0; i < 3; i++) {

            System.out.println("\nAlgorithm " + (i + 1)
                    + ": " + getAlgorithmName(
                    i == 0 ? algorithm1 :
                    i == 1 ? algorithm2 : algorithm3));

            System.out.println("Comparisons: " + comparisons[i]);
            System.out.println("Swaps/Movements: " + swaps[i]);
        }

        System.out.println("\nFinal Refinement Comparisons: "
                + finalComparisons);

        System.out.println("Final Refinement Swaps/Movements: "
                + finalSwaps);

        System.out.println("\nTotal Comparisons: "
                + totalComparisons);

        System.out.println("Total Swaps/Movements: "
                + totalSwaps);

        // Display asymptotic complexities
        System.out.println("\n=================================");
        System.out.println("      ASYMPTOTIC COMPLEXITY");
        System.out.println("=================================");

        displayComplexity("Algorithm 1", algorithm1);
        displayComplexity("Algorithm 2", algorithm2);
        displayComplexity("Algorithm 3", algorithm3);

        scanner.close();
    }


    // Display menu and get a valid algorithm choice
    public static int chooseAlgorithm(
            Scanner scanner, int algorithmNumber) {

        int choice;

        while (true) {

            System.out.println("\nChoose Algorithm "
                    + algorithmNumber + ":");

            System.out.println("1. Bubble Sort");
            System.out.println("2. Insertion Sort");
            System.out.println("3. Merge Sort");

            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {

                choice = scanner.nextInt();

                if (choice >= 1 && choice <= 3) {
                    return choice;
                }
            } else {
                scanner.next();
            }

            System.out.println(
                    "Invalid choice. Please enter 1, 2 or 3.");
        }
    }


    // Run the sorting algorithm selected by the user
    public static void sortPartition(
            int[] array, int choice) {

        switch (choice) {

            case 1:
                SortingAlgorithms.bubbleSort(array);
                break;

            case 2:
                SortingAlgorithms.insertionSort(array);
                break;

            case 3:
                SortingAlgorithms.mergeSort(array);
                break;

            default:
                System.out.println(
                        "Invalid sorting algorithm.");
        }
    }


    // Return the name of the selected sorting algorithm
    public static String getAlgorithmName(int choice) {

        switch (choice) {

            case 1:
                return "Bubble Sort";

            case 2:
                return "Insertion Sort";

            case 3:
                return "Merge Sort";

            default:
                return "Unknown";
        }
    }


    // Display an array
    public static void displayArray(int[] array) {

        for (int value : array) {
            System.out.print(value + " ");
        }

        System.out.println();
    }


    // Display complexity information
    public static void displayComplexity(
            String label, int choice) {

        System.out.println("\n" + label
                + " - " + getAlgorithmName(choice));

        switch (choice) {

            case 1:
                System.out.println("Best Case: O(n)");
                System.out.println("Average Case: O(n^2)");
                System.out.println("Worst Case: O(n^2)");
                break;

            case 2:
                System.out.println("Best Case: O(n)");
                System.out.println("Average Case: O(n^2)");
                System.out.println("Worst Case: O(n^2)");
                break;

            case 3:
                System.out.println("Best Case: O(n log n)");
                System.out.println("Average Case: O(n log n)");
                System.out.println("Worst Case: O(n log n)");
                break;

            default:
                System.out.println("Complexity unavailable.");
        }
    }
}
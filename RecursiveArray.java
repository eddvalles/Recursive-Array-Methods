import java.util.Scanner;

public class RecursiveArray {
    public static void main(String[] args) {
         Scanner scan = new Scanner(System.in);

         System.out.print("Enter the number of items to read: ");
         int count_numbers = scan.nextInt();
         int[] intArray = new int[count_numbers];

         System.out.print("Enter the number: ");
         for (int i = 0; i < count_numbers; i++) {
             intArray[i] = scan.nextInt();
         }

        int midpoint = intArray.length / 2;

        int sumOne = addArrayElements(intArray, 0, midpoint - 1);
        int sumTwo = addArrayElements(intArray, midpoint, intArray.length - 1);

        if (sumOne == sumTwo) { // Checks if the sums of both halves are the same
            System.out.printf("Each half of the array sums to %d.\n", sumOne);
            // The below if checks if the two halves contain the same numbers, if true, will then check if they're mirrored
            if (sameNumbers(intArray, midpoint, 0, intArray.length - 1)) {
                System.out.println("The two halves contain the same numbers");

                /*
                 * This checks if two halves of an array are mirrors of each other, as in {1, 4, 2, 5, 6, 9, 9, 6, 5, 2, 4, 1},
                 * Where the numbers 1, 4, 2, 5, 6, 9 are mirrored across
                 */
                if (checkMirror(intArray, midpoint, 0, intArray.length - 1)) {
                    System.out.println("The two halves are mirror images of each other.");
                }
                else {
                    System.out.println("The two halves are not mirror images of each other.");
                }
            }
            else {
                System.out.println("The two halves do not contain the same numbers.");
            }
        }
        else { // The sum of both halves are not the same
            System.out.println("The two halves have different sums");
        }
    }

    // A method that checks if the two halves have the same numbers
    public static boolean sameNumbers(int[] A, int midpoint, int lower, int upper) {
        if (lower == midpoint) { // What is my true condition that will confirm that I have all the same numbers ?
            return true;
        }
        if (!(searchArray(A, midpoint, midpoint - 1, upper, A[lower]))) {
            return false;
        }
        return sameNumbers(A, midpoint, lower + 1, upper);
    }

    // A method that is called within sameNumbers to verify that the number is somewhere in the second half of array
    public static boolean searchArray(int[] A, int midpoint, int lower, int upper, int find) {
        if (lower > upper) {
            return false;
        }
        if (A[lower] == find) {
            return true;
        }
        return searchArray(A, midpoint, lower + 1, upper, find);
    }

    // A method that checks to see if two halves of an array are mirrored across
    public static boolean checkMirror(int[] A, int midpoint, int lower, int upper) {
        // I may also have to include a filter which checks if I have two even halves
        if (upper < midpoint) {
            return true;
        }
        if (!(A[lower] == A[upper])) {
            return false;
        }
        return checkMirror(A, midpoint, lower + 1, upper - 1);
    }

    /*
     * A recursive method used to sum the elements in the array. Takes in the array,
     * and an upper, and lower parameter for each half.
     */
    public static int addArrayElements(int[] A, int lower, int upper) {
        if (lower == upper) {
            return A[upper];
        }
        return A[lower] + addArrayElements(A, lower + 1, upper);
    }
}

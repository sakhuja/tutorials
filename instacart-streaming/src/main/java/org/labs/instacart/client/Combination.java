package org.labs.instacart.client;

public class Combination {

    static void combinationUtil(String arr[], String data[], int start, int end, int index, int r) {

        // Current combination is ready to be printed, print it
        String[] temp = new String[3];
        if (index == r) {
            // System.out.print("[");
            for (int j = 0; j < r; j++) {
                temp[j] = data[j];
                // System.out.print("\"" + data[j] + "\"");
                if (j < r - 1) {
                    // System.out.print(",");
                }
            }
            // System.out.print("]");
            checkPosse(temp);
            System.out.println("");
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i + 1, end, index + 1, r);
        }
    }

    private static void checkPosse(String[] posse) {
        boolean sl = sameLength(posse);
        boolean dl = diffLength(posse);

        boolean sc = sameChar(posse);
        boolean dc = diffChar(posse);

        boolean sp = samePrefix(posse);
        boolean dp = diffPrefix(posse);

        boolean sCase = sameCase(posse);
        boolean dCase = diffCase(posse);

        System.out.println("sl " + sl);
        System.out.println("dl " + dl);

        System.out.println("sc " + sc);
        System.out.println("dc " + dc);

        System.out.println("sp " + sp);
        System.out.println("dp " + dp);

        System.out.println("sCase " + sCase);
        System.out.println("dCase " + dCase);
        System.out.println("------------");
        if((sl && sc && sCase && sp) || (dl && sc && dCase && dp)) {
//            sl && dc && sCase && sp ||
//                    sl && sc && dCase && sp ||
//                    sl && sc && sCase && dp
            for(String p : posse) {
                System.out.print(p + ", ");
            }

        }
    }

    private static boolean samePrefix(String[] posse) {
     if(posse[0].charAt(0) == posse[1].charAt(0) &&
        posse[1].charAt(0) == posse[2].charAt(0) &&
        posse[0].charAt(0) == posse[2].charAt(0)) {
         return true;
     }

     return false;
    }

    private static boolean diffPrefix(String[] posse) {
        if(posse[0].charAt(0) != posse[1].charAt(0) &&
           posse[1].charAt(0) != posse[2].charAt(0) &&
           posse[0].charAt(0) != posse[2].charAt(0)) {
            return true;
        }
        return false;
    }

    private static boolean diffCase(String[] posse) {
        return true;
    }

    private static boolean sameCase(String[] posse) {
        return true;
    }

    private static boolean diffChar(String[] posse) {
        return false;
    }

    private static boolean sameChar(String[] posse) {
        return false;
    }

    private static boolean diffLength(String[] posses) {
        if (posses[0].length() != posses[1].length() &&
                posses[1].length() != posses[2].length() &&
                posses[0].length() != posses[2].length()) {
            return true;
        }
        return false;
    }

    private static boolean sameLength(String[] posses) {
      if (posses[0].length() == posses[1].length() &&
              posses[0].length() == posses[2].length()) {
          return true;
      }
      return false;
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static void printCombination(String arr[], int n, int r) {
        // A temporary array to store all combination one by one
        String data[] = new String[r];

        // Print all combination using temporary array 'data[]'
        combinationUtil(arr, data, 0, n - 1, 0, r);
    }

    public static void main(String[] args) {
        String arr[] = {
                "-ÜÜÜ",
                "+aa",
                "-ÖÖÖ",
                "=aaa",
                "+aaa",
                "-oo",
                "-UU",
                "+OO",
                "+UUU",
                "=u",
                "-o",
                "=o"};
        int r = 3;
        int n = arr.length;
        printCombination(arr, n, r);
    }
}

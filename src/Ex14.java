/**
 * This class answers MMN14 questions.
 *
 * Author: Daniel Litvak
 * Date: 29.5.2018
 */

public class Ex14
{
    // Q1 - 1
    // The right answers are: 3, 5, 6dis

    // Q1 - 2.1

    /**
     * Returns true if the value is in the matrix, the matrix must be sorted
     * and "what" method will return true if it gets the matrix as a parameter
     *
     * if n is the number of rows then the time complexity is O(n) and the space complexity is O(1)
     *
     * @param m   the matrix
     * @param val the value to search
     * @return true if the value is found; false otherwise
     */
    public static boolean findValWhat(int[][] m, int val)
    {
        int n = m.length;

        // Starting point is from the max row and starting column
        int Row = n - 1;
        int Col = 0;
        boolean found = false;
        while (!found)
        {
            if (m[Row][Col] == val)
                found = true;

                // If the value is smaller than the current target decrease the row
            else if (m[Row][Col] > val)
            {
                Row--;
            }
            // If the value is bigger than the current target increase the column
            else
            {
                Col++;
            }
            // If the the target is out of bounds break the loop
            if (Col == n || Row == n || Col < 0 || Row < 0)
            {
                break;
            }
        }
        return found;
    }
    // Q1 - 2.2

    /**
     * Returns true if the value is in the matrix, each value in the matrix must
     * be smaller than all the values in the the next row so "test" method will return true if it gets the matrix
     * as a parameter.
     *
     * if n is the number of rows then the time complexity is O(n) and the space complexity is O(1)
     *
     * @param m   the matrix
     * @param val the value to search
     * @return true if the value is found; false otherwise
     */
    public static boolean findValTest(int[][] m, int val)
    {
        int numberOfRows = m.length;
        int row = 0;
        // search for the row that has the value
        while (val > m[row][0])
        {
            // move to the next row to search for bigger numbers
            row++;

            // don't let the row go out of bounds
            if (row == numberOfRows)
                break;
        }
        // after the search for the row the value will be in this row or the one before

        //check if the row is out of bounds
        if (row == numberOfRows)
        {
            // search the value in the previous row
            for (int i = 0; i < m[row - 1].length; i++)
            {
                if (m[row - 1][i] == val)
                    return true;
            }
            // value not found
            return false;
        }
        //check if the row is the first row
        if (row == 0)
        {
            // search for only this row
            for (int i = 0; i < m[row].length; i++)
            {
                if (m[row][i] == val)
                    return true;
            }
            // value not found
            return false;
        }
        // generic case, not last row nor first row

        // search this row
        for (int i = 0; i < m[row].length; i++)
        {
            if (m[row][i] == val)
                return true;
        }

        // search the previous row
        for (int i = 0; i < m[row - 1].length; i++)
        {
            if (m[row - 1][i] == val)
                return true;
        }
        return false;
    }

    // Q2 - 1

    /**
     * Returns how many substrings there are in the given string that start with the character,
     * end with the same character and have that character in the middle only once.
     *
     * if n is the length of the string then the time complexity is O(n) and the space complexity is O(1)
     *
     * @param s the string to search in
     * @param c the character to search for
     * @return the number of substrings found
     */
    public static int subStrC(String s, char c)
    {
        // Counter for the characters c in the string s
        int count = 0;
        int n = s.length();
        for (int i = 0; i < n; i++)
        {
            // If found character c add up the counter
            if (s.charAt(i) == c)
                count++;
        }

        // the answer is the number of c characters minus 2;
        if (count > 2)
            return count - 2;
        else
            return 0;
    }

    // Q2 - 2

    /**
     * Returns how many substring there are in the given string that start with the character,
     * end with the same character and have that character in the middle from 0 up to k times.
     *
     * if n is the length of the string then the time complexity is O(n) and the space complexity is O(1)
     *
     * @param s the string to search in
     * @param c the character to search for
     * @param k the maximum number of the c character in the middle of s for a fitting substring
     * @return the number of substrings in the string s
     */
    public static int subStrMaxC(String s, char c, int k)
    {
        int n = s.length();
        int counter = 0;
        int result = 0;
        for (int i = 0; i < n; i++)
        {
            // If found character c add up the counter
            if (s.charAt(i) == c)
                counter++;
        }

        // to calculate the number of substring the first value to add is counter -1
        int addingValue = counter - 1;

        // the result is the sum of an arithmetic sequence that its initial term is the number of the times
        // the character appears minus 1, the difference is -1, and the number of terms is k+1
        for (int i = 0; i < k + 1 && addingValue > 0; i++)
        {
            result += addingValue;
            addingValue--;
        }
        return result;

    }

    // Q3 - 1

    /**
     * Returns the number of ways spiderman can get to the n-th floor.
     *
     * @param n the floor to get to
     * @return the number of ways to get to the floor n, if n = 0 the method returns 0;
     */
    public static int spiderman(int n)
    {
        //base conditions
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else if (n == 2)
            return 2;
        else
        {
            // The number of ways to n is the number of ways to get to n-1 plus n-2 because,
            // from both you can jump right to n
            return spiderman(n - 1) + spiderman(n - 2);
        }
    }

    // Q3 - 2

    /**
     * Return the number of ways spiderman can get to the n-th floor but when he reaches the 20th floor
     * he must take the elevator.
     *
     * @param n the floor to get to
     * @return the number of ways to get to the floor
     */
    public static int spidermanPhoneBooth20(int n)
    {
        if (n < 20 || n > 29)
            return 0;
        // Until n = 21 the calculation stays the same
        if (n < 22)
            return spiderman(n);
        else
        {
            // The number of ways to floor n > 21 is the number of ways to the lift (n = 20),
            // plus the number of ways without using the elevator and passing in level 20.
            // the number of ways to floor n > 21 without using the elevator is the number of ways to 19, from there
            // spiderman must jump to floor 21 and climb again sos the result would be the number of ways to 19 times
            // the number of ways to n > 21 from floor 21
            return spiderman(20) + spiderman(19) * spiderman(n - 21);
        }
    }

    // Q4

    /**
     * Returns the number of valid paths in the matrix that start from position 0,0 and end in the last column
     * and the last row.
     *
     * @param mat the matrix
     * @return the number of valid paths in the matrix
     */
    public static int countPaths(int[][] mat)
    {
        // Start the function from the location 0,0
        return countPaths(mat, 0, 0);
    }

    // Returns the number of paths in the matrix from position: mat[r][c]
    private static int countPaths(int[][] mat, int r, int c)
    {
        // If the target is the final destination return 1 for a success
        if (r == (mat.length - 1) && c == (mat[mat.length - 1].length - 1))
            return 1;
            // If landed on a tile that is out of bounds return 0
        else if (r > (mat.length - 1) || c > (mat[mat.length - 1].length - 1))
            return 0;
            // If landed on zero the path can't move, so return zero
        else if (mat[r][c] == 0)
            return 0;

            // If the target is just a regular tile
        else
        {
            // Take the first and second digits as optional positions to move
            int x = mat[r][c] % 10;
            int y = mat[r][c] / 10;
            // if the x and y values are the same calculate only for one path because they will get to the same place
            if (x == y)
                return countPaths(mat, r + x, c + y);
            else

                // Return the sum of the results from the next 2 possible locations
                return countPaths(mat, r + x, c + y) + countPaths(mat, r + y, c + x);
        }

    }
}

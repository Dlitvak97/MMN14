public class Ex14
{
    // Q1 - 1
    // Answers are: 3, 5, 6

    // Q1 - 2.1

    /**
     * @param m
     * @param val
     * @return
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
    // cant do it

    // Q2 - 1
    public static int subStrC(String s, char c)
    {
        // Counter for the characters c in s
        int count = 0;
        int n = s.length();
        for (int i = 0; i < n; i++)
        {
            // If found character c add up the counter
            if (s.charAt(i) == c)
                count++;
        }

        if (count > 2)
            return count - 2;
        else
            return 0;
    }

    // Q2 - 2
    public static int subStrMaxC(String s, char c, int k)
    {
        int n = s.length();
        int counter = 0;
        for (int i = 0; i < n; i++)
        {
            // Search for the c character to start searching for substrings
            if (s.charAt(i) == c)
            {
                // Continue searching for substrings from the next index
                int secondIndex = i + 1;
                // Start counting the middle occurrences from -1 because after 3 occurrences of c it will become 1
                // (-1 + 3 = 1)
                int middleOccurrences = -1;
                // Search until the index has reached the last character or the middle occurrences
                // surpassed the maximum(k)
                while (secondIndex < n && middleOccurrences < k)
                {
                    if (s.charAt(secondIndex) == c)
                    {
                        middleOccurrences++;
                        counter++;
                    }
                    secondIndex++;
                }
            }
        }
        return counter;
    }

    // Q3 - 1
    public static int spiderman(int n)
    {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else if (n == 2)
            return 2;
        else
        {
            // The number of ways is the number of ways to get to n-1 plus n-2 because,
            // from both you can jump right to n
            return spiderman(n - 1) + spiderman(n - 2);
        }
    }

    // Q3 - 2
    public static int spidermanPhoneBooth20(int n)
    {
        // Until n = 21 the calculation stays the same
        if (n < 22)
            return spiderman(n);
        else
        {
            // The number of ways to n > 21 is the number of ways to the lift (n = 20),
            // plus the number of ways to one level before the lift (n = 19) and from there,
            // there can be only one way and it is to jump to 21
            // so the counting begins from the start like 21 is level 0
            return spiderman(20) + spiderman(19) + spiderman(n - 21);
        }
    }

    // Q4
    public static int countPaths(int[][] mat)
    {
        // Start the function from the location 0,0
        return countPaths(mat, 0, 0);
    }

    public static int countPaths(int[][] mat, int r, int c)
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
            // Return the sum of the results from the next 2 possible locations
            return countPaths(mat, r + x, c + y) + countPaths(mat, r + y, c + x);
        }

    }
}

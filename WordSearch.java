import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;

public class WordSearch {

    public static void main(String[] args) {

        char[][] letters = FileScan();

        Scanner s = new Scanner(System.in);
        String word;

        do {
            //scan the word from the user to check if it exists in the lettersMatrix
            System.out.println("Enter word to find: ");
            word = s.next();

            System.out.println(word + " => " + checkWordExists(letters, word));

        } while (!word.equals("QUIT"));
    }

    public static char[][] FileScan() {

        int rowCount = 0;
        int colCount = 0;
        char c;

        File file = new File("C:/Users/m.assi/Desktop/letterMatrix.txt");
        char[][] letters;

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> data = new ArrayList<String>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            data.add(line);
        }

        fileScanner.close();
        rowCount = data.size();
        colCount = data.get(0).trim().length();

        // Instantiate a two dimensional array of characters
        letters = new char[rowCount][colCount];

        // Populate the array with the letters in Letters.txt
        for (int i = 0; i < rowCount; i++) {
            String line = data.get(i);
            int pos = 0;
            for (int j = 0; j < colCount; j++) {
                letters[i][j] = line.charAt(pos);
                pos++;
            }
        }
        // return the array
        return letters;
    }

    public static boolean checkWordExists(char[][] letterMatrix, String word) {
        int m = letterMatrix.length;
        int n = letterMatrix[0].length;

        boolean result = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(letterMatrix, word, i, j, 0)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public static boolean dfs(char[][] letterMatrix, String word, int i, int j, int k) {
        int m = letterMatrix.length;
        int n = letterMatrix[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n) {
            return false;
        }

        if (letterMatrix[i][j] == word.charAt(k)) {
            char temp = letterMatrix[i][j];
            letterMatrix[i][j] = '#';
            if (k == word.length() - 1) {
                return true;
            } else if (dfs(letterMatrix, word, i - 1, j, k + 1) || dfs(letterMatrix, word, i + 1, j, k + 1) ||
                       dfs(letterMatrix, word, i, j - 1, k + 1) || dfs(letterMatrix, word, i, j + 1, k + 1)) {
                return true;
            }
            letterMatrix[i][j] = temp;
        }
        return false;
    }
}

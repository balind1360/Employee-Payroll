import java.util.ArrayList;
import java.util.Arrays;

/**
 * Name: ArrayListParser                        Last Updated: 11/18/24
 * 
 * Creator: D. Balin
 * 
 * Purpose: Helper class to turn Strings to ArrayLists
 */


public class ArrayListParser {
    
    // returns an ArrayList<String> from a string in the format of an ArrayList toString
    public static ArrayList<String> parseArrayListString(String str) {
        // Remove the square brackets
        str = str.substring(1, str.length() - 1);
        
        // Split the string by commas
        String[] elements = str.split(", ");
        
        // Convert the array to an ArrayList
        return new ArrayList<>(Arrays.asList(elements));
    }

}

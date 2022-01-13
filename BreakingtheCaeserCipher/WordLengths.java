
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;
public class WordLengths {
    
      public void countWordsLengths (FileResource resource, int[] counts) {
        //
        for(String word : resource.words()) {
        
            int length = word.length();
            String realLetter = null;
            StringBuilder sb = new StringBuilder();
        for (int k = 0; k < length ; k++) {
            char ch = word.charAt(k);
            boolean cb = Character.isLetter(ch);
            if (k == 0 && cb == true) {
                sb.append(ch);
                //word = word.substring(1);
                //System.out.println(word);
            }
            if (k>0 && k< (length-1)) {
                sb.append(ch);
            }
            if (k == (length-1) && cb == true) {
                sb.append(ch);
              //word = word.substring(k, length-1);
              //System.out.println(word);

                }
        } realLetter = sb.toString();
       int fullLength = realLetter.length();
       //System.out.println(realLetter);
       counts [fullLength] +=1;
        
    
    }
}

public int  indexOfMax( int [] values) {
    int count = 0;
    int result = 0;
    for (int k = 0; k < values.length; k ++) {
    
        if (values [k] > count) {
            count = values [k];
            result = k;
        }
    }
    return result;
}
    
    public void testCountWordLengths() {
    
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordsLengths (fr, counts);
        int max = indexOfMax(counts);
        for (int k=0; k < counts.length; k++) { 
        System.out.println("Words with " + k + " amount of letters" + "\t" + counts[k] );
        
    }
    System.out.println("The Index of the maximum is " + max);
}
    }


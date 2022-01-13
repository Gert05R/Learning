
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        //for (int k=0; k<myText.length; k++) {
        //System.out.println(k + " " +myText[k]);}
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        //System.out.println("this is the key " + key);
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("Arraylist is " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String [] words, String target, int start) {
       
        for (int k = start; k<words.length; k++) {
           
            if(words[k].equals(target)) {
                return k;
            }
           }
           return -1;
       }
       
    private void testindexOf() {
        String text = "this is this just a test yes this is a simple test";
        String [] input = text.split("\\s+");
        indexOf(input, "this", 0);
        indexOf(input, "this", 3);
        indexOf(input, "frog", 0);
        indexOf(input, "this", 5);
        indexOf(input, "simple", 2);
        indexOf(input, "test", 25);
    }
    
    /*private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        for (int k = 0; k< myText.length; k++) {
        
            int index = indexOf(myText, key, k);
            int next = index+1;
            String Next = myText[next];
            follows.add(Next);
        }
        return follows;
    }*/
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>(); 
        int pos = 0;
        while (pos< myText.length) {
        
            int start = indexOf(myText, key, pos);
            
            if (start == -1) {
            break;}
            if (start + 1 >= myText.length) {
            break;
            }
            String next = myText[start+1];
            follows.add(next);
            //System.out.println("test is " + key.length() + " " + pos + " " + myText.length + " " + start);
            pos = start+ 1;
        }
        
        return follows;
    }

}

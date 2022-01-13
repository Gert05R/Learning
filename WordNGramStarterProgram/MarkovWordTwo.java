
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;

public class MarkovWordTwo implements IMarkovModel{

    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2);  // random word to start with, minus 2 because otherwise we might get an index that has no follow up
        String key1 = myText[index];
        sb.append(key1);
        sb.append(" ");
        String key2 = myText[index+1];
        sb.append(key2);
        sb.append(" ");
        //System.out.println("this is the key " + key);
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);//gets the arraylist of the following words to key1 and key2
            //System.out.println("Arraylist is " + follows);
            if (follows.size() == 0) {
                break;
            }
            
            index = myRandom.nextInt(follows.size());// randomly picks one of these following words in this arraylist
            String next = follows.get(index);//gets the string itself
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String [] words, String target1, String target2, int start) {
       
        for (int k = start; k<words.length; k++) {
           
            if(words[k].equals(target1) && words[k+1].equals(target2)) {
                return k;
            }
           }
           return -1;
       }
       

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>(); 
        int pos = 0;
        while (pos< myText.length) {
        
            int start = indexOf(myText, key1, key2, pos); //gets the position of the first key
            
            if (start == -1) {
            break;}
            if (start + 2 >= myText.length) {
            break;
            }
            String next = myText[start+2];
            follows.add(next);
            //System.out.println("test is " +  " " + pos + " " + myText.length + " " + start +" Next: " + next + " Key1 " + key1 + " Key2 " + key2);
            pos = start+ 2;
        }
        
        return follows;
    }
    
     /*   private void testindexOf() {
        String text = "this is this just a test yes this is a simple test";
        String [] input = text.split("\\s+");
        indexOf(input, "this", 0);
        indexOf(input, "this", 3);
        indexOf(input, "frog", 0);
        indexOf(input, "this", 5);
        indexOf(input, "simple", 2);
        indexOf(input, "test", 25);
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        for (int k = 0; k< myText.length; k++) {
        
            int index = indexOf(myText, key, k);
            int next = index+1;
            String Next = myText[next];
            follows.add(Next);
        }
        return follows;
    }*/
}

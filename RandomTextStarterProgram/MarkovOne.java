
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class MarkovOne {
    
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);// by using the seed, you allways get the same output, this helps in debugging
    }
    
    public void setTraining(String s){
        myText = s.trim();//creates a training text and trim: The trim() method removes whitespace from both ends of a string.
    }
    
    public String getRandomText(int numChars){//his method generates and returns random text that is numChars long.
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);// -1 want .length houdt geenr ekening met positie 0
        String key = myText.substring(index, index+1);
        sb.append(key);
        
        for(int k=0; k < numChars-1; k++){// -1 want anders wordt de output langer dan numchars aangezien al een letter is teogevoegd aan de stringbuilder
            ArrayList <String> follows = getFollows(key);
            //int index = myRandom.nextInt(myText.length());//creates a random integer within the training text
            if (follows.size() == 0) 
            {break;}
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+ next;
            
            //sb.append(myText.charAt(index));// finds a random character within the training text because of the random integer
        }
        
        return sb.toString();
    }
    
    public ArrayList <String> getFollows(String key) {
    
        ArrayList <String> follows = new ArrayList <String>();
        int pos = 0;
        while ( pos < myText.length()) {
            
            int index = myText.indexOf(key, pos);
            if (index == -1) {
                break;
            }
            if (index+key.length() > myText.length()-1) {
            
                break;
            }
            String value = myText.substring(index+key.length(), index+key.length()+1);
            //System.out.println(value);
            follows.add(value);
            pos = index+key.length();
        }
        return follows;
    }
}

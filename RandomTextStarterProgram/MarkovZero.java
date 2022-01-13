
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;

public class MarkovZero {
    private String myText;
    private Random myRandom;
    
    public MarkovZero() {
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
        for(int k=0; k < numChars; k++){
            
            int index = myRandom.nextInt(myText.length());//creates a random integer within the training text
            sb.append(myText.charAt(index));// finds a random character within the training text because of the random integer
        }
        
        return sb.toString();
    }
}

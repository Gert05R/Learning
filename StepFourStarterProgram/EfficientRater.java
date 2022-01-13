
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class EfficientRater implements Rater {
    private String myID; //a unique String ID for this rater
    private HashMap<String, Rating> myRatings; //an ArrayList of Ratings

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }
    //A new Rating is created and added to myRatings.
    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }
    

    public boolean hasRating(String id) {        
        return myRatings.containsKey(id);
    }

    public String getID() {
        return myID;
    }
    //A method getRating that has one parameter item. This method returns the double rating of this item if it is in myRatings. Otherwise this method returns -1.
    public double getRating(String id) {
        return myRatings.get(id).getValue();
    }
    //A method numRatings that returns the number of ratings this rater has.
    public int numRatings() {
        return myRatings.size();
    }
    //A method getItemsRated that has no parameters. This method returns an ArrayList of Strings representing a list of all the items that have been rated. 
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String item : myRatings.keySet()){
            list.add(myRatings.get(item).getItem()); //Purpose of having both item and getitem???
        }
        
        return list;
    }

}

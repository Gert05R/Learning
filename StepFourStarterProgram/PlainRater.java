
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
//keeps track of one rater and all their ratings. 
public class PlainRater implements Rater {
    private String myID; //a unique String ID for this rater
    private ArrayList<Rating> myRatings; //an ArrayList of Ratings

    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }
    //A new Rating is created and added to myRatings.
    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }
        
        return false;
    }

    public String getID() {
        return myID;
    }
    //A method getRating that has one parameter item. This method returns the double rating of this item if it is in myRatings. Otherwise this method returns -1.
    public double getRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }
        
        return -1;
    }
    //A method numRatings that returns the number of ratings this rater has.
    public int numRatings() {
        return myRatings.size();
    }
    //A method getItemsRated that has no parameters. This method returns an ArrayList of Strings representing a list of all the items that have been rated. 
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }
        
        return list;
    }
}

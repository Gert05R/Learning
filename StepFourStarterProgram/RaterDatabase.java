
/**
 * Write a description of RaterDatabase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;//A HashMap named ourRaters that maps a rater ID String to a Rater object that includes all the movie ratings made by this rater. 
    
    //A private initialize method with no parameters that initializes the HashMap ourRaters if it does not exist. 
    private static void initialize() {
        // this method is only called from addRatings 
        if (ourRaters == null) {
            ourRaters = new HashMap<String,Rater>();
        }
    }
    //A public static initialize method with one String parameter named filename. You can call this method with the name of the file used to initialize the rater database.

    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters= new HashMap<String,Rater>();
            addRatings("data/" + filename);
        }
    }   
    //A public static void addRatings method that has one String parameter named filename. You could alternatively call this method to add rater ratings to the database from a file. 
    public static void addRatings(String filename) {
        initialize(); 
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for(CSVRecord rec : csvp) {
                String id = rec.get("rater_id");
                String item = rec.get("movie_id");
                String rating = rec.get("rating");
                addRaterRating(id,item,Double.parseDouble(rating));
        } 
    }
    //A public static void addRaterRating method that has three parameters, a String named raterID representing a rater ID, a String named movieID that represents a movie ID, 
    //and a double named rating that is the rating the rater raterID has given to the movie movieID. 
    //This function can be used to add one rater and their movie rating to the database. Notice that the method addRatings calls this method. 
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize(); 
        Rater rater =  null;
                if (ourRaters.containsKey(raterID)) {
                    rater = ourRaters.get(raterID); 
                } 
                else { 
                    rater = new EfficientRater(raterID);

                    ourRaters.put(raterID,rater);
                 }
                 rater.addRating(movieID,rating);
    } 
    //A method getRater has one String parameter named id. This method returns a Rater that has this ID.          
    public static Rater getRater(String id) {
        initialize();
        
        return ourRaters.get(id);
    }
    //A method getRaters that has no parameters. This method returns an ArrayList of Raters from the database. 
    public static ArrayList<Rater> getRaters() {
        initialize();
        ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
        
        return list;
    }
    //A method size that has no parameters. This method returns the number of raters in the database. 
    public static int size() {
        return ourRaters.size();
    }
    
    
     
}

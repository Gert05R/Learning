
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
    
        FirstRatings FR = new FirstRatings();
        myMovies = FR.loadMovies(moviefile);
        myRaters = FR.loadRaters(ratingsfile);
        
    }
    
    public int getMovieSize() {  
        return myMovies.size();
    }
    
    public int getRaterSize() { 
        ArrayList <EfficientRater> raters = myRaters;
        
        HashMap <String, Integer> raterID = new HashMap <String, Integer>();
        for (int k = 0; k< raters.size(); k++) {
            if (! raterID.containsKey(raters.get(k).getID())) {
            
                raterID.put(raters.get(k).getID(), 1);
            }
            else {
                raterID.put(raters.get(k).getID(), raterID.get(raters.get(k).getID())+1);
            }
        //System.out.println("Rater#" + "\t" + raters.get(k).getID() + "\t" + raters.get(k).numRatings());
        //System.out.println("Rater#" + "\t" + raters.get(k).getItemsRated());
        }
        
        return raterID.size();// Print the total number of raters. 
        
    }
    
    public double getAverageByID(String id, int minimalRaters ) {
    double sum = 0.0;
    int count = 0;
    ArrayList <EfficientRater> myRatersIDAverage = new ArrayList <EfficientRater>();     //Create an arraylist of Rater objects (Rater ID + arraylist with item + rating) of myRater where the movie item = id 
      for(int k=0; k < myRaters.size(); k++){
            EfficientRater rt = myRaters.get(k);
            ArrayList <String> current = rt.getItemsRated();
            for (int i=0; i<current.size(); i++) {
                if (current.get(i).equals(id)) {
                myRatersIDAverage.add(rt);
                }
            }
      }
      // check if the total size of the myRatersIDAverage has enough ratings and add these ratings to the sum
      if (myRatersIDAverage.size() >= minimalRaters) {
        for (int k=0; k<myRatersIDAverage.size(); k++) {
            EfficientRater rt = myRatersIDAverage.get(k);
            sum += rt.getRating(id);
            count += 1;
        }
        double average = sum/count;
        return average;
        
      }
      else {return 0.0;}
    
    }
    
    public ArrayList <Rating> getAverageRatings(int minimalRaters) {
    
        ArrayList <Rating> getAverageRatings = new ArrayList <Rating>();
        
        for (int k = 0; k<myRaters.size(); k++) {
            EfficientRater rt = myRaters.get(k);
            ArrayList <String> items = rt.getItemsRated();
            for (int i =0; i<items.size(); i++) {
            String movieID = items.get(i);
            //System.out.println("Test1 " + movieID);
            Double average = getAverageByID(movieID, minimalRaters);
            //System.out.println("Test2 " + average);
           
            
            if (average  > 0.0) {
                Rating current = new Rating (movieID, average);
                getAverageRatings.add(current);
            }
                
            }
        }
        
        ArrayList <Rating> AverageRatings = new ArrayList <Rating>();
        ArrayList <String> items = new ArrayList <String>();
        for (int i=0; i<getAverageRatings.size(); i++) {
            Rating current = getAverageRatings.get(i);
            String item = current.getItem();
            
            if (! items.contains(item)) {
            
                AverageRatings.add(getAverageRatings.get(i));
            }
            items.add(item);
        }
        return AverageRatings;
    } 
    
    public String getTitle ( String id) {
        for (Movie mv : myMovies) {
        
            if (mv.getID().equals(id)) {
                return mv.getTitle();
        }
        }
        return "The id: " + id + " Was not found";
    }
    
    public String getID(String title) {
    
         for (Movie mv : myMovies) {
        
            if (mv.getTitle().equals(title)) {
                return mv.getID();
        }
        }
        return "NO SUCH TITLE.";
        
    }
    }  




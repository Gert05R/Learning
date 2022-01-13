
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;

public class FourthRatings {
    
    //You should not copy, nor should you have any instance variables in FourthRatings—you'll use RaterDatabase and MovieDatabase static methods in place of instance variables—so 
    //where you have code with myRaters, you need to replace the code with calls to methods in the RaterDatabase class.
    
    public static double getAverageByID(String id, int minimalRaters ) {
    double sum = 0.0;
    int count = 0;
    ArrayList <Rater> myRatersIDAverage = new ArrayList <Rater>();     //Create an arraylist of Rater objects (Rater ID + arraylist with item + rating) of myRater where the movie item = id 
      for(int k=0; k < RaterDatabase.size(); k++){

            ArrayList <Rater> current = RaterDatabase.getRaters();
            for (Rater rt : current) {
                if (rt.getID().equals(id)) {
                myRatersIDAverage.add(rt);
                }
            }
      }
      // check if the total size of the myRatersIDAverage has enough ratings and add these ratings to the sum
      if (myRatersIDAverage.size() >= minimalRaters) {
        for (int k=0; k<myRatersIDAverage.size(); k++) {
            Rater rt = myRatersIDAverage.get(k);
            sum += rt.getRating(id);
            count += 1;
        }
        double average = sum/count;
        return average;
        
      }
      else {return 0.0;}
    
    }
    
    public static ArrayList <Rating> getAverageRatings(int minimalRaters) {
    
        ArrayList <Rating> getAverageRatings = new ArrayList <Rating>();
        
        
            ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
            for (String id: movies) {
            Double average = getAverageByID(id, minimalRaters);
            if (average  > 0.0) {
                Rating current = new Rating (id, average);
                getAverageRatings.add(current);
            }
                
            }
        
        return getAverageRatings;
    } 
    
    public static ArrayList <Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
    
        ArrayList <Rating> getAverageRatings = new ArrayList <Rating>();
        
        
            ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
            for (String id: movies) {
            Double average = getAverageByID(id, minimalRaters);
            if (average  > 0.0) {
                Rating current = new Rating (id, average);
                getAverageRatings.add(current);
            }
                
            }
        
        return getAverageRatings;
    }
    
    private Rating dotProduct (Rater me, Rater r) {
        //ArrayList <Double> dotproduct = new ArrayList <Double>();
        //String id = me.getID();
        Double dotproduct = 0.0;// the product of simillarity between two persons(raters me and r)
        ArrayList <String> moviesMe = me.getItemsRated();
        //double Me = me.getRating(id) - 5;
        
        //String idr = r.getID();
        //double R = r.getRating(idr) - 5;
        ArrayList <String> moviesR = r.getItemsRated();
        
          for (String Movie : moviesMe) {
            if (moviesR.contains(Movie)) {
            
                double ME = me.getRating(Movie)-5;
                double RE = r.getRating(Movie)-5;
                double avg = ME*RE;
                
                dotproduct += avg;
            
            
            }
          
        }
        
        return new Rating (r.getID(), dotproduct);
    }
    
     
    
    //Note that in each Rating object the item field is a rater’s ID, and the value field is the dot product 
    //comparison between that rater and the rater whose ID is the parameter to getSimilarities. 
    private ArrayList <Rating> getSimilarities (String id) {
    
        ArrayList <Rating> similarRaters = new ArrayList <Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater rt: RaterDatabase.getRaters()) {
            if (! rt.equals(me)) {
            Rating current = dotProduct (me, rt);
            if (current.getValue() > 0) {
            similarRaters.add(current);}}
        } 
        Collections.sort(similarRaters, Collections.reverseOrder());
        
        return similarRaters;
    }
    
    private double avgMovieRating(String movieID, ArrayList <Rating> simmilarRaters, int minimalRaters) {
    
            int count = 0;
            double WeightedSum = 0.0;
            double Average = 0.0;
            for (Rating rater : simmilarRaters) {
                
                Rater rat = RaterDatabase.getRater(rater.getItem());
                if (rat.hasRating(movieID)) {
                double value = rat.getRating(movieID); // 
                double Simillarity = rater.getValue();
                double weighted = value*Simillarity;
                WeightedSum += weighted;
                count ++;
                
            }
            
        }
        if (count >= minimalRaters) {
                
                return WeightedSum/count;
                }
       return 0.0;
    }
    
    
    
    public ArrayList <Rating> getSimilarRatings( String id/*rater ID*/, int numSimilarRaters, int minimalRaters) {
        ArrayList <Rating> list = getSimilarities(id);//List of rater ID's + dotproducts (of the parameter id, which is the person on which tthe dotprodcut is calculated)
        ArrayList <Rating> rec = new ArrayList <Rating>();
        ArrayList<Rating> similarRaters =  new ArrayList<Rating>();
        for (int k=0; k<numSimilarRaters; k++) {
        
            similarRaters.add(list.get(k));//creates list of all Top simillar raters
            
        }
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movies) {
            
            double Average = avgMovieRating(movieID, similarRaters, minimalRaters);
            if (Average>0) {
            Rating Current = new Rating(movieID,Average);
            rec.add(Current);
            }
        }
        Collections.sort(rec, Collections.reverseOrder());
        return rec;
    }
    
    public ArrayList <Rating> getSimilarRatingsByFilter( String id/*rater ID*/, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList <Rating> list = getSimilarities(id);//List of rater ID's + dotproducts (of the parameter id, which is the person on which tthe dotprodcut is calculated)
        ArrayList <Rating> rec = new ArrayList <Rating>();
        ArrayList<Rating> similarRaters =  new ArrayList<Rating>();
        for (int k=0; k<numSimilarRaters; k++) {
        
            similarRaters.add(list.get(k));//creates list of all Top simillar raters
            
        }
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movies) {
            
            double Average = avgMovieRating(movieID, similarRaters, minimalRaters);
            if (Average>0) {
            Rating Current = new Rating(movieID,Average);
            rec.add(Current);
            }
        }
        Collections.sort(rec, Collections.reverseOrder());
        return rec;
    }
    
    

}

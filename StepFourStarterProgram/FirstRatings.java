
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.util.ArrayList;
import java.util.Collections;

public class FirstRatings {
    
    //get a parser to read all the files
    public ArrayList <Movie> loadMovies (String filename) {
    
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList <Movie> Movies = new ArrayList <Movie>();
        for (CSVRecord record : parser) {
            Movie mv = new Movie (record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"),
            record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
            Movies.add(mv);
        }
        return Movies;
    }
    
    public void testLoadMovies() {
    
        ArrayList <Movie> input = loadMovies ("data/ratedmoviesfull.csv");
        System.out.println("Number of Movies " + input.size());
      //  for (int k = 0; k< input.size(); k++) {
        //System.out.println("Movie#" + k + "\t" + input.get(k).getTitle());
    //  }
        int countGenre = 0;
        int countMinutes = 0;
       for (int k = 0; k< input.size(); k++) {
         if (input.get(k).getGenres().contains("Comedy")) {
            countGenre+=1;
            }
         if (input.get(k).getMinutes()> 150) {
            countMinutes+=1;
            }
       
        }
       System.out.println("#Movies with genre Comedy:" + "\t" + countGenre);
       System.out.println("#Movies with length over 150" + "\t" + countMinutes);
       
       ArrayList <String> directors = new ArrayList <String>();
       for (int k = 0; k< input.size(); k++) {
        String director = input.get(k).getDirector();
        if (director.contains(",")) {
        String   [] splitDirectors = director.split(",");
        for (int i = 0; i<splitDirectors.length; i++) {
            String test = splitDirectors [i];
            if (!directors.contains(test)) {
            
                directors.add(test);
            }
        }}
        
        else {directors.add(director);}
        
        }
        
        HashMap <String, Integer> map = new HashMap <String, Integer>();
        for (int i=0; i<directors.size(); i++) {
        if (!map.containsKey(directors.get(i))) {
            map.put(directors.get(i), 1);
        }
        else {
            map.put(directors.get(i), map.get(directors.get(i))+1);
        }
        }
        
        /*for (String direct : map.keySet()) {
                
                System.out.println(direct + map.get(direct));
            }*/
        
        int count =0;
        
        for (String direct : map.keySet()) {
                
                int current =map.get(direct);
                if (current > count) {
                
                    count = current;
                }
            }
        ArrayList <String> maxDirectors = new ArrayList <String>();
        for (String direct : map.keySet()) {
                
                
                if (map.get(direct) == count) {
                
                    maxDirectors.add(direct);
                }
            }
        System.out.println("#Max directors " + count + " by the director " + maxDirectors);
            
        
        
       System.out.println("All directors " + directors);
       
      
    }
    
    public ArrayList <EfficientRater> loadRaters(String filename) {
    
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList <EfficientRater> Raters = new ArrayList <EfficientRater>();
        for (CSVRecord record : parser) {
            EfficientRater rt = new EfficientRater (record.get("rater_id"));
            String item = record.get("movie_id");
            rt.addRating(item, Double.parseDouble(record.get("rating")));
            Raters.add(rt);
        }
        return Raters;
    
    }
    
 
    
    
    public void testLoadRaters() {
    
        ArrayList <EfficientRater> raters = loadRaters("data/ratings.csv");
        
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
        
        System.out.println("the total number of raters: " + raterID.size());// Print the total number of raters. 
        
        
        //For each rater, print the rater’s ID and the number of ratings they did on one line, followed by each rating (both the movie ID and the rating given) on a separate line.
        for (String key : raterID.keySet()) {
            
            System.out.println("Rater ID: "+ key + " Has this amount of ratings "+ raterID.get(key));
            for (int k=0; k<raters.size(); k++) {
            if (raters.get(k).getID().contains(key)) {
                
                ArrayList <String> items = raters.get(k).getItemsRated();
                for (int i=0; i<items.size(); i++) {
            
                String item = items.get(i);
               
                System.out.println("Rater#" + "\t" + items + "\t" + raters.get(k).getRating(item));
            
            }
            }
            }
        }
        
        //Add code to find the number of ratings for a particular rater you specify in your code. For example, if you run this code on the rater whose rater_id is 2 for the file ratings_short.csv, you will see they have three ratings. 
        for (String key : raterID.keySet()) {
            String target = "193";
        if (key.equals(target)) {
        
            System.out.println("the number of ratings for rater_id" + target + " = " + raterID.get(key));
            
        }
        }
        
        //Add code to find the maximum number of ratings by any rater. Determine how many raters have this maximum number of ratings and who those raters are.
        int count = 0;
        for (String key : raterID.keySet()) {
        
        if (raterID.get(key)> count) {
            count = raterID.get(key);
        }
        }
        System.out.println("the Max. number of ratings is: " + count);
        for (String key : raterID.keySet()) {
        if (raterID.get(key) == count) {
            System.out.println("the ID's with the max number of ratings : rater_ID"  + key);
        }
        }
        
        //Add code to find the number of ratings a particular movie has.
        String Target = "1798709";
        int countRatings = 0;
        for (int k=0; k<raters.size(); k++) {
        ArrayList <String> items = raters.get(k).getItemsRated();
          for (int i=0; i<items.size(); i++) {
            
                String item = items.get(i);
                if (item.equals(Target)) {
                
                    countRatings +=1;
                    
                }
               
                
            
            }
        }
        System.out.println("The movie id " + Target+ " has #ratings = " /*+ raters.get(k).getRating(item)*/ + countRatings);
        
        //Add code to determine how many different movies have been rated by all these raters.
        
        HashMap <String, Integer> Movies = new HashMap <String, Integer>();
        for (int k=0; k<raters.size(); k++) {
        ArrayList <String> items = raters.get(k).getItemsRated();
          for (int i=0; i<items.size(); i++) {
                
                String item = items.get(i);
                if (! Movies.containsKey(item)) {
                    Movies.put(item, 1);
                }
                else {
                
                    Movies.put(item, Movies.get(item)+1);
                }

            }
        }
        System.out.println("#Movies rated = " + Movies.size());
}

/*void test() {
        FileResource fr = new FileResource("data/ratedmovies_short.csv");
        CSVParser parser = fr.getCSVParser();
        ArrayList <Movie> Movies = new ArrayList <Movie>();
        for (CSVRecord record : parser) {
            Movie mv = new Movie (record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"),
            record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
            Movies.add(mv);
            System.out.println("#1" + mv);
            System.out.println("#2" +toString());
            System.out.println("#3" +mv.toString());
        }
    }*/
}


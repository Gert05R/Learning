
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings() {
    
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        int Movies = sr.getMovieSize();
        int Raters = sr.getRaterSize();
        System.out.println("Movies " + Movies + " Raters " + Raters);
        //add code to print a list of movies and their average ratings, for all those movies that have at least a specified number of ratings, sorted by averages. Specifically, this method should print the list of movies, one movie per line (print its rating first, followed by its title) in sorted order by ratings, lowest rating to highest rating. 
        ArrayList <Rating> test = sr.getAverageRatings(12);
        Collections.sort(test);
        for (int i=0; i< test.size(); i++) {

            System.out.println(test.get(i).getValue () + "\t" + sr.getTitle(test.get(i).getItem ()) );
        }
    }
    
    public void getAverageRatingOneMovie() {
        String title= "Vacation";
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String id =sr.getID(title);
        System.out.println(title +"\t"+ sr.getAverageByID(id, 0));
    
    }
    
    public void testgetAverageByID() {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        System.out.println(sr.getAverageByID("1798709", 2));
        
    }
    
    public void testgetAverageRatings() {
    
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        ArrayList <Rating> test = sr.getAverageRatings(20);
        for (int i=0; i< test.size(); i++) {
            
            System.out.println("This is the item " + test.get(i).getItem ()+ " with average rating " + test.get(i).getValue ());
        }
    }
}

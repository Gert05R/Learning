
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings() {
    

        RaterDatabase.initialize("ratings.csv");
        int Raters = RaterDatabase.size();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");
        ArrayList <Rating> test = FourthRatings.getAverageRatings(35);
        System.out.println("Found: " + test.size() + " Movies");
        Collections.sort(test);
        for (int i=0; i< test.size(); i++) {
            System.out.println(test.get(i).getValue () + "\t" + MovieDatabase.getTitle(test.get(i).getItem ()) );
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
    

        RaterDatabase.initialize("ratings.csv");
        int Raters = RaterDatabase.size();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");       
        AllFilters AF = new AllFilters();
        Filter f = new YearAfterFilter(1990); 
        AF.addFilter(f);
        Filter f2 = new GenreFilter("Drama");
        AF.addFilter(f2);
        ArrayList <Rating> test = FourthRatings.getAverageRatingsByFilter(8, AF);      
        System.out.println("Found: " + test.size() + " Movies");
        Collections.sort(test);
        for (int i=0; i< test.size(); i++) {
            System.out.println(test.get(i).getValue () +"\t" + MovieDatabase.getYear(test.get(i).getItem ()) +  "\t" + MovieDatabase.getTitle(test.get(i).getItem ()) );
            System.out.println("\t" + MovieDatabase.getGenres(test.get(i).getItem ()));
            
        }
    
    }
    
    public void printSimilarRatings() {
    
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        int Raters = RaterDatabase.size();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");
        ArrayList <Rating> test = fr.getSimilarRatings("71", 20, 5);
        for (int i=0; i< test.size(); i++) {
        System.out.println(MovieDatabase.getTitle(test.get(i).getItem()) + "\t" + test.get(i).getValue() );
        }
    }
    
    public void printSimilarRatingsByGenre() {
    
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        int Raters = RaterDatabase.size();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");
        Filter f = new GenreFilter("Mystery");
        ArrayList <Rating> test = fr.getSimilarRatingsByFilter("964", 20, 5, f);
        for (int i=0; i< test.size(); i++) {
        System.out.println(MovieDatabase.getTitle(test.get(i).getItem()) + "\t" + test.get(i).getValue() );
        System.out.println("\t" + MovieDatabase.getGenres(test.get(i).getItem()));
        }
        
    }
    
        public void printSimilarRatingsByDirector() {
    
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        int Raters = RaterDatabase.size();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList <Rating> test = fr.getSimilarRatingsByFilter("120", 10, 2, f);
        for (int i=0; i< test.size(); i++) {
        System.out.println(MovieDatabase.getTitle(test.get(i).getItem()) + "\t" + test.get(i).getValue() );
        System.out.println("\t" + MovieDatabase.getDirector(test.get(i).getItem()));
        }
        
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
    
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        int Raters = RaterDatabase.size();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");
        AllFilters AF = new AllFilters();
        Filter f = new MinutesFilter(80, 160); 
        AF.addFilter(f);
        Filter f2 = new GenreFilter("Drama");
        AF.addFilter(f2);
        ArrayList <Rating> test = fr.getSimilarRatingsByFilter("168", 10, 3, AF);
        for (int i=0; i< test.size(); i++) {
        System.out.println(MovieDatabase.getTitle(test.get(i).getItem()) + "\t" + test.get(i).getValue() );
        System.out.println("\t" + MovieDatabase.getGenres(test.get(i).getItem()));
        }
        
    }
    
        public void printSimilarRatingsByYearAfterAndMinutes() {
    
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        int Raters = RaterDatabase.size();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");
        AllFilters AF = new AllFilters();
        Filter f = new MinutesFilter(70, 200); 
        AF.addFilter(f);
        Filter f2 = new YearAfterFilter(1975);
        AF.addFilter(f2);
        ArrayList <Rating> test = fr.getSimilarRatingsByFilter("314", 10, 5, AF);
        for (int i=0; i< test.size(); i++) {
        System.out.println(MovieDatabase.getTitle(test.get(i).getItem()) + "\t" + test.get(i).getValue() );
        System.out.println("\t" + MovieDatabase.getGenres(test.get(i).getItem()));
        }
        
    }
}

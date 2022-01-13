
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    
    public void printAverageRatings() {
    
        ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
        int Raters = tr.getRaterSize();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");
        //add code to print a list of movies and their average ratings, for all those movies that have at least a specified number of ratings, sorted by averages. Specifically, this method should print the list of movies, one movie per line (print its rating first, followed by its title) in sorted order by ratings, lowest rating to highest rating. 
        ArrayList <Rating> test = tr.getAverageRatings(35);
        System.out.println("Found: " + test.size() + " Movies");
        Collections.sort(test);
        for (int i=0; i< test.size(); i++) {
            System.out.println(test.get(i).getValue () + "\t" + MovieDatabase.getTitle(test.get(i).getItem ()) );
        }
    }
    
    public void printAverageRatingsByYear() {
    
        ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
        int Raters = tr.getRaterSize();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");
        //add code to print a list of movies and their average ratings, for all those movies that have at least a specified number of ratings, sorted by averages. Specifically, this method should print the list of movies, one movie per line (print its rating first, followed by its title) in sorted order by ratings, lowest rating to highest rating. 
        Filter f = new YearAfterFilter(2000);
        ArrayList <Rating> test = tr.getAverageRatingsByFilter(20, f);
        System.out.println("Found: " + test.size() + " Movies");
        Collections.sort(test);
        for (int i=0; i< test.size(); i++) {
            System.out.println(test.get(i).getValue () + "\t" + MovieDatabase.getYear(test.get(i).getItem ()) + "\t" + MovieDatabase.getTitle(test.get(i).getItem ()) );
        }
    }
    
    public void printAverageRatingsByGenre() {
    
        ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
        int Raters = tr.getRaterSize();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");       
        Filter f = new GenreFilter("Comedy");      
        ArrayList <Rating> test = tr.getAverageRatingsByFilter(20, f);      
        System.out.println("Found: " + test.size() + " Movies");
        Collections.sort(test);
        for (int i=0; i< test.size(); i++) {
            System.out.println(test.get(i).getValue () +  "\t" + MovieDatabase.getTitle(test.get(i).getItem ()) );
            System.out.println("\t" + MovieDatabase.getGenres(test.get(i).getItem ()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
    
        ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
        int Raters = tr.getRaterSize();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");       
        Filter f = new MinutesFilter(105, 135);      
        ArrayList <Rating> test = tr.getAverageRatingsByFilter(5, f);      
        System.out.println("Found: " + test.size() + " Movies");
        Collections.sort(test);
        for (int i=0; i< test.size(); i++) {
            System.out.println(test.get(i).getValue () + "\t"+  "Time: "+  MovieDatabase.getMinutes(test.get(i).getItem()) +  "\t" + MovieDatabase.getTitle(test.get(i).getItem ()) );
            
        }
    }
    
    public void printAverageRatingsByDirectors() {
    
        ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
        int Raters = tr.getRaterSize();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");       
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");      
        ArrayList <Rating> test = tr.getAverageRatingsByFilter(4, f);      
        System.out.println("Found: " + test.size() + " Movies");
        Collections.sort(test);
        for (int i=0; i< test.size(); i++) {
            System.out.println(test.get(i).getValue () +  "\t" + MovieDatabase.getTitle(test.get(i).getItem ()) );
            System.out.println("\t" + MovieDatabase.getDirector(test.get(i).getItem ()));
            
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
    
        ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
        int Raters = tr.getRaterSize();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");       
        AllFilters AF = new AllFilters();
        Filter f = new YearAfterFilter(1990); 
        AF.addFilter(f);
        Filter f2 = new GenreFilter("Drama");
        AF.addFilter(f2);
        ArrayList <Rating> test = tr.getAverageRatingsByFilter(8, AF);      
        System.out.println("Found: " + test.size() + " Movies");
        Collections.sort(test);
        for (int i=0; i< test.size(); i++) {
            System.out.println(test.get(i).getValue () +"\t" + MovieDatabase.getYear(test.get(i).getItem ()) +  "\t" + MovieDatabase.getTitle(test.get(i).getItem ()) );
            System.out.println("\t" + MovieDatabase.getGenres(test.get(i).getItem ()));
            
        }
    
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
    
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        int Raters = tr.getRaterSize();
        System.out.println("read data for " + Raters + " Raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int Movies = MovieDatabase.size();
        System.out.println("read data for " + Movies + " Movies");       
        AllFilters AF = new AllFilters();
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"); 
        AF.addFilter(f);
        Filter f2 = new MinutesFilter(90, 180);
        AF.addFilter(f2);
        ArrayList <Rating> test = tr.getAverageRatingsByFilter(3, AF);      
        System.out.println("Found: " + test.size() + " Movies");
        Collections.sort(test);
        for (int i=0; i< test.size(); i++) {
            System.out.println(test.get(i).getValue () + "\t"+  "Time: "+  MovieDatabase.getMinutes(test.get(i).getItem()) +  "\t" + MovieDatabase.getTitle(test.get(i).getItem ()) );
            System.out.println("\t" + MovieDatabase.getDirector(test.get(i).getItem ()));
            
        }
    
        
    
    }
}

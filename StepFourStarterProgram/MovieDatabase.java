import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;
//The class MovieDatabase—This class is an efficient way to get information about movies. It stores movie information in a HashMap for fast lookup of movie information given a movie ID.
// The class also allows filtering movies based on queries. All methods and fields in the class are static.
// This means you'll be able to access methods in MovieDatabase without using new to create objects, but by calling methods like MovieDatabase.getMovie("0120915"). 
//This class has the following parts:

public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies;//A HashMap named ourMovies that maps a movie ID String to a Movie object with all the information about that movie.
    
    //A public initialize method with one String parameter named moviefile. You can call this method with the name of the file used to initialize the movie database.
    public static void initialize(String moviefile) {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies("data/" + moviefile);
        }
    }
    //- A private initialize method with no parameters that will load the movie file ratedmoviesfull.csv if no file has been loaded. 
    //This method is called as a safety check with any of the other public methods to make sure there is movie data in the database. 
    private static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies("data/ratedmoviesfull.csv");
        }
    }   

    // A private loadMovies method to build the HashMap. 
    private static void loadMovies(String filename) {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);
        for (Movie m : list) {
            ourMovies.put(m.getID(), m);
        }
    }
    //- A containsID method with one String parameter named id. This method returns true if the id is a movie in the database, and false otherwise.
    public static boolean containsID(String id) {
        initialize();
        return ourMovies.containsKey(id);
    }
    // - Several getter methods including getYear, getTitle, getMovie, getPoster, getMinutes, getCountry, getGenres, and getDirector. 
    //Each of these takes a movie ID as a parameter and returns information about that movie
    public static int getYear(String id) {
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id) {
        initialize();
        return ourMovies.get(id).getGenres();
    }

    public static String getTitle(String id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(String id) {
        initialize();
        return ourMovies.get(id);
    }

    public static String getPoster(String id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }
    // A size method that returns the number of movies in the database. 
    public static int size() {
        return ourMovies.size();
    }
    // A filterBy method that has one Filter parameter named f. This method returns an ArrayList of type String of movie IDs that match the filtering criteria.
    public static ArrayList<String> filterBy(Filter f) {
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        
        return list;
    }

}


/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;
public class GenreFilter implements Filter {
    
    private String Genre; 
    
    public GenreFilter(String myGenre) {
    
        Genre = myGenre;
    }
    
    public boolean satisfies(String id) {
        String [] Genres = MovieDatabase.getGenres(id).split(",");
        
        for (int i =0; i< Genres.length; i++) {
            
            if (Genre.equals(Genres[i].trim())) {
                
                return true;
            }
        }
        
        
        return false;
    }
    



}

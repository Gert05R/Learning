
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    
    private String [] Directors;
    
    public DirectorsFilter (String myDirector) {
    
        Directors = myDirector.split(",");
    }
    
    
    public boolean satisfies(String id) {
        
        for (int i=0; i<Directors.length; i++) {
           if (MovieDatabase.getDirector(id).trim().equals(Directors[i].trim())) {
        
            return true;
           }
        }
        
        return false;
    }
}

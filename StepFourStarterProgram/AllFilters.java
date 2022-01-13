import java.util.ArrayList;
//The class AllFilters combines several filters. This class has the following:
//- A private variable named filters that is an ArrayList of type Filter.
//- An addFilter method that has one parameter named f of type Filter. This method allows one to add a Filter to the ArrayList filters. 
//- A satisfies method that has one parameter named id representing a movie ID. This method returns true if the movie satisfies the criteria of all the filters in the filters ArrayList. 
//Otherwise this method returns false.
public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        }
        
        return true;
    }

}

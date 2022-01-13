
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;

public class RecommendationRunner implements Recommender {
    
    /*public ArrayList<String> getItemsToRate () {
        int length = 20;
        Filter f = new YearAfterFilter(2000);
        Random rand = new Random();
        ArrayList <String> list = MovieDatabase.filterBy(f);
        ArrayList <String> output = new ArrayList <String>();
        for (int i =0; i<length; i++) {
        
            output.add(MovieDatabase.getTitle(list.get(rand.nextInt(list.size()))));
        }
        return output;
    }*/
    

    
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> toRet = new ArrayList<String>();
        for(int i = 0; i < 10; i ++) {
            toRet.add(movies.get(i));
        }
        return toRet;
    }
    

    
    /*public void printRecommendationsFor (String webRaterID) {
        
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList <Rating> recommendations = fr.getSimilarRatings( webRaterID, 20, 5);
    
        Collections.sort(recommendations);
        if(recommendations.size() == 0) {
            System.out.println("<p>Sorry, there are no recommendations for you.</p>");
            System.exit(1);
        }
        System.out.println("<html><table>");
        
        
        System.out.println("<tr><th>Rank</th>" + "<th>Title</th>" + "<th>Rating</th></tr>");
        
        for (int i = 0; i <20; i++) {
        
            System.out.println("<tr><td>"+ (i+1) + "</td>" + "<td>"   + MovieDatabase.getTitle(recommendations.get(i).getItem()) + "</td>"  +"<td>" + recommendations.get(i).getValue() + "</td></tr>");
        }
        System.out.println("</table></html>");
    }*/
    
     public void printRecommendationsFor(String webRaterID){
        FourthRatings fr = new FourthRatings();
        int numSimilarRaters = 4;
        int minimalRaters = 2 ;

        ArrayList<Rating> recList = fr.getSimilarRatings(webRaterID,numSimilarRaters,minimalRaters);

        if(recList.size()==0){
            printError();
        }

        else{
            printUpperPart();
            int i=0;
            for(Rating r: recList){
                i++;
                if((i+1)%2 == 0){
                    System.out.println("<tr class=\"even_rows\"><td>" + i + "</td>");
                }
                else{
                    System.out.println("<tr class=\"odd_rows\"><td>" + i + "</td>");
                }

                String URL = MovieDatabase.getPoster(r.getItem());
                String title = MovieDatabase.getTitle(r.getItem());
                String director = MovieDatabase.getDirector(r.getItem());
                String country = MovieDatabase.getCountry(r.getItem());
                int year = MovieDatabase.getYear(r.getItem());
                String genre = MovieDatabase.getGenres(r.getItem());
                int minutes = MovieDatabase.getMinutes(r.getItem());

                System.out.println("<td><table><tr><td class = \"pic\">");

                if(URL.length()>3){
                    System.out.println("<img src = \""+URL+"\" target=_blank></td>");
                }

                System.out.println("<td><h3>"+ title+"</h3>");
                System.out.println("<b>by "+ genre+"</b><br>");
                System.out.println(year+"<br>");
                System.out.println(country+"<br>");
                System.out.println(minutes+" minutes</td></tr></table></td></tr>");
                if(i>12) break;
            }
            printLowerPart();

        }
    }

    private void printError(){
        System.out.println("This is system error, please try again!");
    }

    private void printUpperPart(){
        System.out.println("<link href=\"https://fonts.googleapis.com/css?family=Syncopate\" rel=\"stylesheet\"><link href=\"https://fonts.googleapis.com/css?family=Roboto|Syncopate\" rel=\"stylesheet\"><div id=\"header\"><h2>Recommended Movies:</h2></div><table class=\"outside_table\"><tr  class=\"table-header\"><th>&nbsp</th><th class=\"movie_title\">Title</th></tr>");
    }

    private void printCSS(){
        System.out.println("<style>* {margin: 0;padding: 0;}img{height: 100px;margin-right:10px;}#header{background-color: #F49F58;margin-top: 0;height: 100px;}h2{padding-left: 15px;padding-top: 40px;color: #FFFFFF;}h3{}body{margin-top: 0;font-family: 'Arial'}th{text-align: left;font-family: 'Arial', sans-serif;padding-top:15px;padding-bottom: 7px;}td{padding-top: 10px;padding-right: 10px;padding-left: 10px;padding-bottom: 5px;}tr{padding-bottom: 10px;}.table-header{background-color: #FFB97F;}.odd_rows{background-color: #FFE4CC;}.even_rows{background-color: #FFFFFF;}.outside_table{width: 100%;border-collapse: collapse;}.movie_title{width = 40%;}</style>");
    }

    private void printLowerPart(){
        System.out.println("</table>");
    }
}

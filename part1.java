
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class part1 {
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int totalNames = 0;
        int totalGirlsNames = 0;
        int totalBoysNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String sex = rec.get(1);
            
            int numBorn = Integer.parseInt(rec.get(2));
            
            totalBirths += numBorn;
            if (sex.equals("F")) {
            
                totalGirls += numBorn;
                totalGirlsNames += 1;
            } else {
                totalBoys += numBorn;
                totalBoysNames += 1;
            }
        }
        System.out.println("totalbirths = " + totalBirths);
        System.out.println("total Girls = " + totalGirlsNames);
        System.out.println("total Boys = " + totalBoysNames);
        System.out.println("total names = " + totalNames);
    }
    
    public int getRank(int year, String name, String gender) {
        
        int count = 0;
        int rank = 0;
        
        //String file = ("documents/yob" + year + ".csv");
        //String path = file.getAbsolutePath();
        //File file = new File("documents/yob" + year + ".csv");
        //String path = file.getPath();
        
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        //System.out.println(fr);
        //FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
           String sex = rec.get(1);
           String selectName = rec.get(0); 
           
           if (sex.equals(gender)) {
               count = count + 1;
               //System.out.println(selectName);
               
               if (selectName.equals(name)) {
                
                   rank = count;
                   //System.out.println(selectName);
                   
                }
            } 
        
        }
        //System.out.println("rank is " + rank);
        if (rank == 0) {
        System.out.println("-1");}
        else {
        System.out.println("rank is " + rank);}
        
        return rank;
    }
    
    public String getName(int year, int rank, String gender) {
     int count = 0;
     String result = null;
     FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
     for (CSVRecord rec : fr.getCSVParser(false)) {
           String sex = rec.get(1);
           String selectName = rec.get(0); 
           if (sex.equals(gender)) {
            
               count +=1;
               if (count == rank) {
                   
                   result = selectName;
                   System.out.println(result);
                   break;
                }
                
            }
        
        }
        if (count> rank) {System.out.println("No Name");}
        return result;
         
    }
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
    
        int count1 = 0;
        int count2 = 0;
        int rank1 = 0;
        int rank2 = 0;
        String result = null;
        
        FileResource fr1 = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        FileResource fr2 = new FileResource("us_babynames/us_babynames_by_year/yob" + newYear + ".csv");
        for (CSVRecord rec : fr1.getCSVParser(false)) {
            String sex = rec.get(1);
            String selectName = rec.get(0);
            if (sex.equals(gender)) {
               count1 +=1;
               if (selectName.equals(name)) {
                   rank1 = count1;
                   
                   for (CSVRecord rec2 : fr2.getCSVParser(false)) {
                String sex2 = rec2.get(1);
                String selectName2 = rec2.get(0);
                
                if (sex2.equals(gender)) {
                    count2 +=1;
                    
                    if (count2 == rank1) {
                    result = selectName2;
                    
                    break;
                }    
                    
                }
                
            
            }
        }
    }
            
    
    }
    System.out.println(name + " born in " + year + " would be " + result + " if she was born in " + newYear );
    return result;
}

public int yearOfHighestRank (String name, String gender) {

    DirectoryResource dr = new DirectoryResource();
    int rank= 99999999;
    String Year = null;

    for (File f:dr.selectedFiles()) {
        int count = 0;
        
        FileResource fr = new FileResource(f);
        for (CSVRecord rec : fr.getCSVParser(false)) {
           String sex = rec.get(1);
           String selectName = rec.get(0); 
           
           if (sex.equals(gender)) {
               count = count + 1;
               //System.out.println(selectName);
               
               if (selectName.equals(name)) {
                   
                  
                    if (count < rank) {
                    rank = count;
                    Year = f.getPath();
                   break;}
                }             
                
            } 
        
        }
        //System.out.println("rank is " + rank);
        
    }
    int yob = (Year.indexOf("yob"))+3;
    String year = Year.substring(yob, yob+4);
    int result = Integer.parseInt(year);
    if (rank == 99999999) {
        System.out.println("-1");}
        else {
        System.out.println("YEAR IS " + result);}
    return result;
} 

public double getAverageRank (String name, String gender) {

    DirectoryResource dr = new DirectoryResource();
    double rank= 0.0;
    double CountFiles = 0.0;
    

    for (File f:dr.selectedFiles()) {
        int count = 0;
        
        FileResource fr = new FileResource(f);
        for (CSVRecord rec : fr.getCSVParser(false)) {
           String sex = rec.get(1);
           String selectName = rec.get(0); 
           
           if (sex.equals(gender)) {
               count = count + 1;
               //System.out.println(selectName);
               
               if (selectName.equals(name)) {
                   CountFiles += 1;
                   rank = count + rank;
                   
                   break;}
                }             
                
            } 
        
        }
      double averageRank = rank/CountFiles;
      System.out.println("rank is " + averageRank);
      return averageRank;  
    } 
    
public int getTotalBirthsRankedHigher (int year, String name, String gender) {
        int count = 0;
        int CountBirths = 0;
        int result = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
           String sex = rec.get(1);
           String selectName = rec.get(0); 
           int births = Integer.parseInt(rec.get(2));
           if (sex.equals(gender)) {
               CountBirths = CountBirths + births;

               
               if (selectName.equals(name)) {
                   
                   result = CountBirths - births;
                   
                   break;}
                }             
                
            } 
      
      System.out.println("Total births ranked Higher " + result);
      return result;  
        }
      
    


    public void testTotalBirths() {
    
        //FileResource fr = new FileResource();
        //getRank(1971, "Frank", "M");
        //getName(1982, 450, "M");
        //whatIsNameInYear("Owen", 1974, 2014, "M");
        //yearOfHighestRank("Mich", "M");
        //getAverageRank("Robert", "M");
        getTotalBirthsRankedHigher(1990, "Drew", "M");
        //totalBirths(fr);
    }
}


/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class Part1 {
    
    public void printData(CSVParser parser, String f) {
        FileResource fr = new FileResource(f);
        parser = fr.getCSVParser();
        System.out.println("All the Temperatures on the coldest day were: ");
        for(CSVRecord record : parser) {
        System.out.println( record.get("DateUTC") +" - " + record.get("TemperatureF") + "F");
    }
}

    public CSVRecord coldestHourInFile (CSVParser parser) {
    
        CSVRecord lowestSoFar = null;
        
        for (CSVRecord currentRow : parser) {
        
            if (lowestSoFar == null) {
            
                lowestSoFar = currentRow;
            }
            else {
                    String curTemp = currentRow.get("TemperatureF");
                    if (curTemp.equals("-9999")) {}
                    else {
                    double currentTemp = Double.parseDouble(curTemp);
                    double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                    
                    
                     
                    if (currentTemp < lowestTemp) {
                        
                        lowestSoFar = currentRow;
                    
                }}
            }
        }return lowestSoFar;
    }
    
    public String fileWithColdestTemperature() {
         
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        String lowestFile = null;
        for (File f : dr.selectedFiles()) {
        
            FileResource fr = new FileResource(f);
            
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            String currentFile = f.getAbsolutePath(); 
            if (lowestSoFar == null) {
            lowestSoFar = currentRow;
            lowestFile = currentFile;
            }
            else {
                    String curTemp = currentRow.get("TemperatureF");
                    double currentTemp = Double.parseDouble(curTemp);
                    double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                    
                    if (currentTemp < lowestTemp) {
                        
                        lowestSoFar = currentRow;
                        lowestFile = currentFile;
                
            }
        }
        
    }return lowestFile;
}

public CSVRecord lowestHumidityInFile(CSVParser parser) {

    //FileResource fr = new FileResource();
    //parser = fr.getCSVParser();
    CSVRecord lowestHumidity = null;
    
    for (CSVRecord currentRow : parser) {
    
        if (lowestHumidity == null) {
            
                lowestHumidity = currentRow;
            }
            else {
                    String curHum = currentRow.get("Humidity");
                    if (curHum.equals("N/A")) {
                    
                 
                        
                    }
                 else {double currentHum = Double.parseDouble(curHum);
                    double lowestHum = Double.parseDouble(lowestHumidity.get("Humidity"));
                    if (currentHum < lowestHum) {
                        
                        
                        lowestHumidity = currentRow;}
            }}
        }
        
        return lowestHumidity;
    }
    
public CSVRecord lowestHumidityInManyFiles() {

     DirectoryResource dr = new DirectoryResource();
     CSVRecord lowestSoFar = null;
     
        for (File f : dr.selectedFiles()) {
        
            FileResource fr = new FileResource(f);
            
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            
            if (lowestSoFar == null) {
            lowestSoFar = currentRow;
            
            }
            else {
                    double currentHum = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
                
                    if (currentHum < lowestHum) {
                        
                        lowestSoFar = currentRow;
                       
                
            }
        }
        
    }return lowestSoFar;
    
} 

public double averageTemperatureInFile (CSVParser parser) {
    CSVRecord averageTemp = null;
    double currentTotal = 0.0;
    double count = 0.0;
    double result = 0.0;
    for (CSVRecord currentRow : parser) {
      
            String curTemp = currentRow.get("TemperatureF");
            if (curTemp != "-9999") {
            double currentTemp = Double.parseDouble(curTemp);
            currentTotal = currentTotal + currentTemp;
            count = count +1;
            }

            }
        result = currentTotal/count;
        return result;
}

public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
CSVRecord averageTemp = null;
double currentTotal = 0.0;
double count = 0.0;
double result = 0.0;

for (CSVRecord currentRow : parser) {
      
            String currentHum = currentRow.get("Humidity");
            int curHum = Integer.parseInt(currentHum);
            if (curHum >= value) {
            String curTemp = currentRow.get("TemperatureF");
            if (curTemp != "-9999") {
            double currentTemp = Double.parseDouble(curTemp);
            currentTotal = currentTotal + currentTemp;
            count = count +1;
            }

            }
    
} result = currentTotal/count; 
return result;
}

public void testAverageTemperatureWithHighHumidityInFile() {

    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double result = averageTemperatureWithHighHumidityInFile(parser, 80);

    if (result>1)
    { //System.out.println("No temperatures with that humidity" );
    System.out.println("Average temperature in file is " + result);}
    else {
    //System.out.println("Average temperature in file is " + result)
    System.out.println("No temperatures with that humidity" );
}
}

public void testAverageTemperatureInFile() {

    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double result = averageTemperatureInFile(parser);
    System.out.println("Average temperature in file is " + result);
    
}

public void testLowestHumidityInManyFiles() {

    CSVRecord csv = lowestHumidityInManyFiles();
    System.out.println("Lowest Humidty was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    
}

public void testLowestHumidityInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord csv = lowestHumidityInFile(parser);
    System.out.println("The lowest Humidty was " + csv.get("Humidity") + " At Date " + csv.get("DateUTC")); 
}


public void testFileWithColdestTemperature() {
    String f = fileWithColdestTemperature();
    System.out.println("Coldest day was in file " + f);
    FileResource fr = new FileResource(f);
    CSVParser current = fr.getCSVParser();
    CSVRecord coldest = coldestHourInFile(current);
    System.out.println("Coldest temperature on that day was " +  coldest.get("TemperatureF"));
    printData(current, f);
}


    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Lowest temperature was " + lowest.get("TemperatureF") + " At " + lowest.get("DateUTC"));
       
}
}

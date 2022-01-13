
/**
 * Write a description of part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class part1 {

    public void countryInfo (CSVParser parser, String Country) {
    
        for (CSVRecord record : parser) {
        
            String country = record.get("Country");
            String Exports = record.get("Exports");
            String Value = record.get("Value (dollars)");
            
            if (country.contains(Country)) {
            
                if (Exports.equals("")) {
                    String result = "Not Found";
                    System.out.println(result);}
                else { 
                    String result = (country + ": " + Exports + ": " + Value);
                    System.out.println(result);
            }
        }
        }
    }
    
    public void listExportersTwoProducts ( CSVParser parser, String exportitem1, String exportitem2) {
    
        for (CSVRecord record : parser) { 
            
            String export = record.get("Exports");
            
            if (export.contains(exportitem1) && export.contains(exportitem2)) {
            
                String country = record.get("Country");
                System.out.println(country);
                
            }
        }
    
    }
    
    public void numberOfExporters (CSVParser parser,String exportitem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            
            if(exports.contains(exportitem)) {
            
                count = count+1;
                
            }
        }System.out.println("the number of countries is " + count);
        
    
    }
    
    public void bigExporters(CSVParser parser, String amount) {
    
        for (CSVRecord record : parser) {
        
            String value = record.get("Value (dollars)");
            //amount = "$999,999,999";
            
            if (value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
            
        }
        
    }
    
    public void tester () {
    
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //countryInfo(parser,"Macedonia");
        
        //parser = fr.getCSVParser();
        //listExportersTwoProducts (parser, "cotton", "flowers");
        
        //parser = fr.getCSVParser();
        //numberOfExporters(parser, "cocoa");
        
        //parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}

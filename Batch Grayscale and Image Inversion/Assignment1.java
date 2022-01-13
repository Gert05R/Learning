
/**
 * Write a description of Assignment1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;

public class Assignment1 {

    public ImageResource makeGray(ImageResource inImage) {
    
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel :outImage.pixels()) {
        
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            
            pixel.setRed(average);
            pixel.setBlue(average);
            pixel.setGreen(average);
            
        }
        return outImage;
    } 
    
    public void selectAndConvert() {
    
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
        
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            String fname = inImage.getFileName();
            String newName = "Gray-" + fname;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
    
    //public void doSave() {
    
        //DirectoryResource dr = new DirectoryResource();
        //for (File f : dr.selectedFiles()) {
        
           // ImageResource image = new ImageResource(f);
          //  String fname = image.getFileName();
          //  String newName = "Gray-" + fname;
          //  image.draw();
          //  image.save();
       // }
    
   // }
    
    public void testMakeGray() {
        
        ImageResource test = new ImageResource();
        ImageResource gray = makeGray(test);
        gray.draw();
    }
    
}

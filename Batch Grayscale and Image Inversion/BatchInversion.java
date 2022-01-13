
/**
 * Write a description of Assignment1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;

public class BatchInversion {

    public ImageResource makeInversion(ImageResource inImage) {
    
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel :outImage.pixels()) {
        
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int redPix = (255 - inPixel.getRed());
            int bluePix = (255 - inPixel.getBlue());
            int greenPix= (255 - inPixel.getGreen());
            pixel.setRed(redPix);
            pixel.setBlue(bluePix);
            pixel.setGreen(greenPix);
            
        }
        return outImage;
    } 
    
    public void selectAndConvert() {
    
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
        
            ImageResource inImage = new ImageResource(f);
            ImageResource inverse = makeInversion(inImage);
            String fname = inImage.getFileName();
            String newName = "Inverted-" + fname;
            inverse.setFileName(newName);
            inverse.draw();
            inverse.save();
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
        ImageResource inverse = makeInversion(test);
        inverse.draw();
    }
    
}
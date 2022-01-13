
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Tester {
    
    public void testCC() {
    
        CaesarCipher cc = new CaesarCipher(5);
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("this is the input " + input);
        String encrypted = cc.encrypt(input);
        System.out.println("this is the encrypted string " + encrypted);
        CaesarCracker crack = new CaesarCracker('a');
        int dkey = crack.getKey(encrypted);
        String decrypted = crack.decrypt(encrypted);
        System.out.println("this is the dkey " + dkey);
        System.out.println("this is the decrypted string " + decrypted);
    }
    
    public void testVigenere() {
    
        int [] rome = {17, 14, 12, 4};
        VigenereCipher vig = new VigenereCipher(rome);
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("this is the input " + input);
        String encrypted = vig.encrypt(input);
        System.out.println("this is the encrypted string " + encrypted);
        String decrypted = vig.decrypt(encrypted);
        System.out.println("this is the decrypted string " + decrypted);
        
    }
    
    
    public void testBreaker() {
    
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String input = fr.asString();
        //System.out.println("this is the input " + input);
        
        //String sliced = vb.sliceString(input, 4, 5);
        //System.out.println(sliced);
        char mostCommon = 'e';
        int klength = 4;
        
        int[] keys =  vb.tryKeyLength(input, klength, mostCommon);
        for (int k = 0; k<keys.length; k++) {
        System.out.println(keys [k]);}
    }
    
    
}

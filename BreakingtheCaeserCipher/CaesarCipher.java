
/**
 * Write a description of CaeserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;
public class CaesarCipher {
    
    public String encrypt (String input, int key) {
   
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //int Key =  key+1;
        String shiftedAlphabet= alphabet.substring(key) + alphabet.substring(0, key);
        for (int i=0; i< encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            
            //char lowerCurrChar= Character.isLowerCase(currChar);
            if (Character.isLowerCase(currChar)) {
            
                String alphabetLow =  alphabet.toLowerCase();
                int idx = alphabetLow.indexOf(currChar);
            if (idx != -1) {
                String alphabetShiftedLow =  shiftedAlphabet.toLowerCase();
                char newChar = alphabetShiftedLow.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
            }
            else{
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
            
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }}
            
        }
        String result = encrypted.toString();
        return result;   
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        
        StringBuilder encrypted = new StringBuilder(input);
        for (int i=0; i< encrypted.length(); i++) {
            if (i % 2 == 0) {
            String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String shiftedAlphabet= alphabet.substring(key1) + alphabet.substring(0, key1);
            char currChar = encrypted.charAt(i);
            
            //char lowerCurrChar= Character.isLowerCase(currChar);
            if (Character.isLowerCase(currChar)) {
            
                String alphabetLow =  alphabet.toLowerCase();
                int idx = alphabetLow.indexOf(currChar);
            if (idx != -1) {
                String alphabetShiftedLow =  shiftedAlphabet.toLowerCase();
                char newChar = alphabetShiftedLow.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
            }
            else{
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
            
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }}}
            else {
                
            String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String shiftedAlphabet= alphabet.substring(key2) + alphabet.substring(0, key2);
            char currChar = encrypted.charAt(i);
            
            //char lowerCurrChar= Character.isLowerCase(currChar);
            if (Character.isLowerCase(currChar)) {
            
                String alphabetLow =  alphabet.toLowerCase();
                int idx = alphabetLow.indexOf(currChar);
            if (idx != -1) {
                String alphabetShiftedLow =  shiftedAlphabet.toLowerCase();
                char newChar = alphabetShiftedLow.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
            }
            else{
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
            
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }}
            }
            
        }
        String result = encrypted.toString();
        return result;
       
        
    }
    
    public void testEncrypt() {
        //String result = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
        //String result = encrypt("Where is the Woefas At, eeeeeeeiihhhhh", 15);
        String result = encryptTwoKeys ("Top ncmy qkff vi vguv vbg ycpx",24, 6);
        System.out.println(result);
    }
    
    public void testCaeser() {
        //String message = "FIRST LEGION ATTACK EAST FLANK!";
        int key = 23;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encryptTwoKeys(message, 21, 8);
        System.out.println("key is " + key + "\n" + encrypted);
        
    }
}

/**
 * Write a description of DecryptionCeaserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;

public class CaesarBreaker {
    
    public int [] countLetters (String message) {
    
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int [26];
        for (int k=0; k < message.length(); k++) {
        
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
            
                counts[dex] += 1;
            }
        } return counts;
    }
    
    public int maxIndex (int [] vals) {
    
        int maxDex = 0;
        for (int k=0; k<vals.length; k++) {
        if (vals[k] > vals[maxDex]) {
        
            maxDex = k;
        }
        }return maxDex;
    }
    
    public String decrypt(String encrypted) {
    
        CaesarCipher cc = new CaesarCipher();
        int [] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
        
            dkey = 26- (4-maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);
    }
    
    public String halfOfString  (String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int k = start; k< message.length(); k +=2) {
            
            
            char ch= message.charAt(k);
            sb.append(ch);
                
            } String result = sb.toString(); 
            return result;
            }
        
    public int getKey (String s) {

        int [] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        return maxDex;
    }        
    
    public String decryptTwoKeys (String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String first = halfOfString (encrypted, 0);
        String second = halfOfString (encrypted, 1);
        
        int dkey1 = getKey(first);
        if (dkey1 < 4) {
        
            dkey1 = 26 - (4-dkey1);
        } 
        else 
        {dkey1 = dkey1 - 4;}
        
        int dkey2 = getKey(second);
        if (dkey2 < 4) {
        
            dkey2 = 26 - (4-dkey2);
        } 
        else 
        {dkey2 = dkey2 - 4;}
        
        System.out.println("key one is " + dkey1 + " Key two is " + dkey2);
        return cc.encryptTwoKeys(encrypted, 26-dkey1, 26-dkey2);
        
    }
    
    public void testDecrypt () {
        //String result = halfOfString("Qbkm Zgis", 1);
        //String result = decrypt("Lwtgt xh iwt Ldtuph Pi, tttttttxxwwwww");
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String result = decryptTwoKeys(message);
        System.out.println(result);
    }


}
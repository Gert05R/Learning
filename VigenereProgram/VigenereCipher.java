import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    CaesarCipher[] ciphers;
    
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];// sets the array of ciphers (that can decrypt ) to the same size of the array of keys
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);// creates a new ceasercipher array with each key 
        }
    }
    
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {//creates an array of the characters of the input
            int cipherIndex = i % ciphers.length;// returns 0 initially, so it sets the index in the array
            CaesarCipher thisCipher = ciphers[cipherIndex];// gets the current cipher
            answer.append(thisCipher.encryptLetter(c));//adds the given character to the end of the sequence, and then encrypts the current letter with the ceaercupher encrypt method
            i++;
        }
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
}

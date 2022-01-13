import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;
    
    public CaesarCipher(int key) {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                            alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }
    
    private char transformLetter(char c, String from, String to) {
        int idx = from.indexOf(c); // Accesses the corresponding character of the alphabet that equals char c
        //System.out.println("idx is " + idx);
        if (idx != -1) { // in case -1 the character is not in the alphabet or shifted, and the same character gets returned
            //System.out.println(" to charat is " + to.charAt(idx));
            return to.charAt(idx); // as the alphabet is shifted with a key, the smae index should be returned to shift the alphhabet however the input should be the shifted alphabet
        }
        return c;
    }
    
    public char encryptLetter(char c) {
        // this accesses the private class transfromletter and has its order of accessing the alphabet in such way so you encrypt the input
        return transformLetter(c, alphabet, shiftedAlphabet);
    }
    
    public char decryptLetter(char c) {
        // this accesses the private class transfromletter and has its order of accessing the alphabet in such way so you decrypt the input
        return transformLetter(c, shiftedAlphabet, alphabet);
    }
    
    private String transform(String input, String from, String to){
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i); //Accesses each character of the input at a time
            c = transformLetter(c, from, to); // replaces each letter of the input with its transformed letter depending on the encrypt/decrypt method
            sb.setCharAt(i, c);// replaces the character at the specified index in the sequence with the given character
        }
        return sb.toString();
    }
    
    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet);
    }
    
    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet);
    }
    
    public String toString() {
        return "" + theKey;
    }
    
}

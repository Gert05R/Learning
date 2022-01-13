import edu.duke.*;

public class CaesarCracker {
    char mostCommon;
    
    public CaesarCracker() {
        mostCommon = 'e';
    }
    
    public CaesarCracker(char c) {
        mostCommon = c;
    }
    
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz"; 
        int[] counts = new int[26]; //creates new array the size of the alphabet
        for(int k=0; k < message.length(); k++){ //LOOps over the input message
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));// gets the index within the alphabet of the corresponding letter of the message
            if (dex != -1){ // in case the input (kth character of the message) is not available wihtin the alphabet, nothin happens
                counts[dex] += 1; // counts the occurence of each letter within the message
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k < vals.length; k++){//loops over the array values
            if (vals[k] > vals[maxDex]){//gets the maximum index of letters within the array, this for finding the key in english which is done through the letter 'e'
                maxDex = k;
            }
        }
        return maxDex;
    }

    public int getKey(String encrypted){
        int[] freqs = countLetters(encrypted);// gets the array with the most letters
        int maxDex = maxIndex(freqs);// finds the maxIndex of the array
        int mostCommonPos = mostCommon - 'a';// finds the index of the mostcommonPosition, returns 4 for when mostcommon is e
        //System.out.println("this the mostcommonPOS" + mostCommonPos + "mostCommon" + mostCommon + " A = " + 'a');
        int dkey = maxDex - mostCommonPos;// finds the decryption key by finding the maxdex(the letter that occurs the most i.e. e) and comparing this with the natural position of the most common letter (i.e. e)
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos-maxDex);
        }
        return dkey;
    }
    
    public String decrypt(String encrypted){
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);
        
    }
   
}

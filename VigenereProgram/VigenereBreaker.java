import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int k = whichSlice; k<message.length(); k += totalSlices) {
        
            char c = message.charAt(k);
            sb.append(c);
            
            
        }
        String result = sb.toString();
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        CaesarCracker crack = new CaesarCracker(mostCommon);
        for (int k = 0; k<klength ; k++) {
        String sliced = sliceString(encrypted,k ,klength );
        int keyS = crack.getKey(sliced);
        key [k] = keyS;
        }
        return key;
        
    }

    public void breakVigenere (int klength) {
        FileResource fr = new FileResource();
        String input = fr.asString();

        
        
        int[] keys = tryKeyLength(input, klength, 'e');
        for (int k = 0; k< keys.length; k++) {
        System.out.println(keys[k]);}
        VigenereCipher vig = new VigenereCipher(keys);
        String decrypted = vig.decrypt(input);
        FileResource res = new FileResource();
        HashSet <String> hash =readDictionary (res);
        int test =  countWords(input, hash);
        System.out.println("this is the amount of valid words" + test);
        System.out.println(decrypted);
        
        
        
    }
    
    public HashSet <String> readDictionary (FileResource fr) {

        HashSet <String> hash = new HashSet <String>();
        for (String line : fr.lines()) {
            //System.out.print(line);
            String lower = line.toLowerCase();
            hash.add(lower);
            
            
        }
        //for (String s : hash) {
        //System.out.print(s);
        //}
        //System.out.print(hash.size());
        return hash;
    }
    
    //public int countWords(String message, HashSet <String> dictionary) {
        
      //  int counts = 0;
      //  for (String word: message.split("\\W+")) {
            
      //      if (dictionary.contains(word.toLowerCase())) {
      //          counts ++;
                
      //      }
      //  }
        
      //  return counts; // set breakpoint here
            
      //  }
    
     public int countWords(String message, HashSet<String> dictionary){
        String[] sa = message.split("\\W+");
        int i = 0;
        for (String s: sa){
            String slower = s.toLowerCase();
            if (dictionary.contains(slower)){
                i++;
            }
        }
        return i;
    }  
      
      
    public void testcountWords() {
        FileResource fr = new FileResource();
        int tel= 0;
            for (String word : fr.words()) {
           tel +=1;
           }
        System.out.println("tel is " + tel);
        String input = fr.asString();
        FileResource dic = new FileResource();
        HashSet <String> dictionary = readDictionary(dic);
        
        int counts = countWords(input, dictionary);
        System.out.println("counts is " + counts);
    }
    
    public String breakForLanguage (String encrypted, HashSet <String> dictionary) {
    
        //CaesarCracker crack = new CaesarCracker();
    int max = 0;
    char c = mostCommonCharln(dictionary);
    
    
    HashMap <String, Integer> results = new HashMap <String, Integer>(); //niet teruggevonden?
    
    for (int k = 1; k <= 100; k++) {
        
        int[] keys = tryKeyLength(encrypted, k, c);
        
        VigenereCipher vig = new VigenereCipher(keys);
        String decrypted = vig.decrypt(encrypted);
        
        int a = countWords(decrypted, dictionary);
        if (a> max) {
            max = a;
        }
    }
    for (int j = 1; j <= 100; j++){
            int[] key = tryKeyLength(encrypted, j, c);
            VigenereCipher vc = new VigenereCipher(key);
            String s = vc.decrypt(encrypted);
            int a = countWords(s, dictionary);
            
            if (a == max){
                System.out.println("length of key is " + j + " Amount of real words: " + a);
                //for (int k = 0; k< key.length; k++) {
                  //  System.out.println(key[k]);}
                return s;
                
            }
        }
        System.out.println ( "this is max" + max);
        return null;
    }
    
    public void breakVigenere2 () {
        FileResource fr = new FileResource();
        String input = fr.asString();
        

        FileResource dic = new FileResource();
        HashSet <String> dictionary = readDictionary(dic);
        //int test = countWords(input,dictionary);
        //System.out.println(test);
        
        String decrypted = breakForLanguage (input,dictionary);
        System.out.println(decrypted);
        
        //int[] keys = tryKeyLength(input, 4, 'e');
        //VigenereCipher vig = new VigenereCipher(keys);
        //String decrypted = vig.decrypt(input);
        //System.out.println(decrypted);

    }
    
    public char mostCommonCharln (HashSet<String> dictionary) {
        HashMap <Character, Integer> counts = new HashMap <Character, Integer>();
        int max = 0;
        
        for ( String S : dictionary) {
            for (int k = 0; k< S.length(); k++) {
            String s = S.toLowerCase();
            char current = s.charAt(k);
            if (! counts.containsKey(current)) {
                counts.put(current, 1);
            }
            
            else {
            
                counts.put(current, counts.get(current) +1);
            }
            
            }
        }
        
        for (char c : counts.keySet()) {
            int currInt = counts.get(c);
            if ( currInt > max) {
            
                max=currInt;
                
            }
        }
        for (char c : counts.keySet()) {
            int currInt = counts.get(c);
            if (currInt == max) {
                System.out.println("character is " + c + " with amount " + currInt);
                return c;
                
            }
        }
        return 'a';
    }
    
    public void breakForAllLangs(String encrypted, HashMap <String, HashSet<String>> languages) {
        int max = 0;
        for (String language : languages.keySet()) {
        
            HashSet <String> text = languages.get(language);
            String decrypted = breakForLanguage (encrypted, text);
            int counts =  countWords(decrypted, text);
            if (counts > max) {                
                max = counts;
            }
        }
        
        for (String language : languages.keySet()) {
        
            HashSet <String> text = languages.get(language);
            String decrypted = breakForLanguage (encrypted, text);
            int counts =  countWords(decrypted, text);
            if (counts == max) {
                System.out.println("the language is " + language + " Counts is " + counts + " Decrypted is " + decrypted);
            }
        }
    }

    public void breakVigenere3 () {
        HashMap <String, HashSet <String>> languages = new HashMap <String, HashSet <String>>();
        FileResource inp = new FileResource();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            languages.put(f.getName(), readDictionary(fr));
            System.out.println(f.getName() + " read.");
        }
        
        String input = inp.asString();
        System.out.println();
        breakForAllLangs(input, languages);
                        
    }
    
    }
    

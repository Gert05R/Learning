
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;

public class Tester {
    
    public void testGetFollows () {
    
        MarkovOne markovOne = new MarkovOne();
        String st = "this is a test yes this is a test.";
        st = st.replace('\n', ' ');
        markovOne.setTraining(st);
        ArrayList <String> test = markovOne.getFollows(".");
        System.out.println(test);
        //for (int i =0; i<test.size(); i++ ) {
        //    System.out.print(test.get(i));
        //}
        
    }
    
    public void testGetFollowsWithFile () {
    
        MarkovOne markovOne = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markovOne.setTraining(st);
        ArrayList <String> test = markovOne.getFollows("he");
        System.out.println(test);
        System.out.println(test.size());
    }

}

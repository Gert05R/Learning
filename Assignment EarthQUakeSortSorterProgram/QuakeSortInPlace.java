
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int getLargestDepth(ArrayList <QuakeEntry> quakeData, int from) {
        int minIndex = from; 
        for (int k = from+1; k < quakeData.size(); k++) {
        
            if (quakeData.get(k).getDepth() > quakeData.get(minIndex).getDepth()) {
                minIndex = k;
            }
        }
        return minIndex;
    }
    
    public void sortByLargestDepth (ArrayList<QuakeEntry> in) {
        for (int i=0; i< 50; i++) {
            int max = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(max);
            in.set(i,qmax);
            in.set(max,qi);
            System.out.println("This is number " + i + " " +in.get(i) );
        }
        
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void onePassBubbleSort(ArrayList <QuakeEntry> quakeData, int NumSorted) {
        
        for (int i = 0; i+1 < quakeData.size(); i++) {
         double first = quakeData.get(i).getMagnitude();
         //System.out.println(first);
         double second = quakeData.get(i+1).getMagnitude();
         //System.out.println(second);
         if (second < first) {
             QuakeEntry Second = quakeData.get(i+1);
             QuakeEntry First = quakeData.get(i);
             quakeData.set(i, Second);
             quakeData.set(i+1, First);
            }
        }
        NumSorted += 1;
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
    //EarthQuakeParser parser = new EarthQuakeParser();
    //String source = "earthquakeDataSampleSix2.atom";
    //ArrayList<QuakeEntry> list  = parser.read(source);
    //System.out.println("read data for "+list.size()+" quakes");
    for (QuakeEntry qe: in) { 
            System.out.println(qe);
        }  
        int N = in.size();
        for (int i = 0; i< N-1; i++) {
        onePassBubbleSort(in, N-1);
        System.out.println("Printing quakes after pass " + i);
        for (QuakeEntry qe: in) { 
            System.out.println(qe);
        }  

    }
        
    }
    
    public boolean checkInSortedOrder (ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i+1 < quakes.size(); i++) {
         double first = quakes.get(i).getMagnitude();
         //System.out.println(first);
         double second = quakes.get(i+1).getMagnitude();
         //System.out.println(second);
         if (second < first) {
             return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
    
        int N = in.size();
        for (int i = 0; i< N-1; i++) {
        onePassBubbleSort(in, N-1);
        //System.out.println("Printing quakes after pass " + i);
        //for (QuakeEntry qe: in) { 
        //    System.out.println(qe);
        //}  
        if (checkInSortedOrder(in)) {
            System.out.println("Amount of passes were required: " + (i+1));
            break;
        }
    }
    }
    
    public void sortByMagnitudeWithCheck (ArrayList<QuakeEntry> in) {
    
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if (checkInSortedOrder(in)) {
                System.out.println("Amount of passes were required: " + (i+1));
            break;
        }
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //System.out.println("EarthQuakes in sorted order ");
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) {
            
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}

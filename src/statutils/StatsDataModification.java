/*
Name: Danielius Zurlys
Student ID: 20130611
*/

package statutils;
import java.util.List;
import java.util.ArrayList;
 

//Class StatsDataModification
//Class used to modify provided data
//Available functions:
//      getNormalisedData() - normalises the provided data list an returns it
public class StatsDataModification{
    
    //constructor
    //Action: None
    public StatsDataModification(){
        
        
    }
    
    //Function getNormalisedData()
    // Provides a normlised list of data based on provided list
    // Arguments:
    //      newData - the input data list to normalise
    //Return: Normalised data list (List<Double>)
    public static List<Double> getNormalisedData(List<Double> newData, double maximumVal, double minimumVal){
        
        //define temporary list
        List<Double> normalisedData = new ArrayList<Double>();
        
        //get width of one data point
        double dataPointWidth = (maximumVal-minimumVal)/newData.size();
        
        //calculate the whole area of the data list
        double totalArea = 0;
         for (int i = 0; i < newData.size(); i++) {
             
             totalArea += newData.get(i)*dataPointWidth;
             
         }
        
        //add normalised values to the normlised list based
        //on totla area and input list
        for (int i = 0; i < newData.size(); i++) {
            
            normalisedData.add((double)newData.get(i)/totalArea);
            
        }
        
        //return normalised data
        return normalisedData;
    }
    
}

/*
Name: Danielius Zurlys
Student ID: 20130611
*/
package statutils;

import java.util.ArrayList;
import java.util.List;

/*
Class BinStatistics
Used to retrieve bin information of the stored data
Available functions:
        countSamplesInBins() - calculates and stores how mnay values each bin should have

*/
public class BinStatistics extends AbstractStats {
    
    //specification of bin values to use
    private int numberOfBins;
    
    //specification of width of each bin
    private double binWidth;
    
    //list specifying ending values of bins
    private List<Double> binEndingValues;
    
    //list specifying number of values ineach bin
    private List<Double> binValueFrequencies;   
    
    //Function setupLists()
    //Refreshes bin data lists
    //Arguments: None
    //Return: None
    private void refreshData(){
        
        binValueFrequencies.clear();
        binEndingValues.clear();
        
    }
    
    //Function setupLists()
    //Sets up initila values for the bin data
    //Arguments: 
    //      newData - new data to separate into bins
    //      newNumberOfBins - new number of bins to separate data into
    //Return: None
    public void setup(List<Double> newData, int newNumberOfBins){
        
        data = newData;
        numberOfBins = newNumberOfBins;
        setupLists();
    }
    
    //Function setupLists()
    //Sets up initila values for the bin data
    //Arguments: None
    //Return: None
    private void setupLists(){
        
        //refresh bin data lists
        refreshData();
        if(data.size()>0){
            
            //get maximum and minimum valeusof the list
            //(the list is sorted in abstract class when data is received)
            double maximumValue = super.data.get(super.data.size()-1);
            double minimumValue = super.data.get(0);
            
            //get width of a bin using specified number of bins
            binWidth = (maximumValue - minimumValue)/numberOfBins;
            
            //set initial values
            for(int i = 0;i<numberOfBins;i++){

                //initial number of values in bin is zero
                binValueFrequencies.add(0.0);

                //ending values of each bin specified
                //by taking steps equal to bin width
                binEndingValues.add(minimumValue+binWidth*(i+1));

            }
        }
    }
    
    //Default Constructor
    //Action:
    //      calls parent constructor
    //      sets maximum, minimum values of data
    public BinStatistics(){
        super();
        numberOfBins = 0;
        binWidth = 0;
        
        //initialize lists
        binValueFrequencies = new ArrayList<Double>();
        binEndingValues = new ArrayList<Double>();
        
    }
    
    //Constructor
    //Action:
    //      calls parent constructor
    //      sets maximum, minimum values of data
    public BinStatistics(List<Double> newData, int nOfBins){
        super(newData);
        numberOfBins = nOfBins;
        binWidth = 0;
        
        //initialize lists
        binValueFrequencies = new ArrayList<Double>();
        binEndingValues = new ArrayList<Double>();
        
        //setup initial bin lists
        setupLists();
        
    }
    
    //Function setNumberOfBins()
    //Sets number of bins the stored data should be put into
    //Arguments: newValue - new number of bins to use
    //Return: None
    public void setNumberOfBins(int newValue){
        
        numberOfBins = newValue;
        
    }
    
    //Function getNumberOfBins()
    //Get number of bins used for calcuatios
    //Arguments: None
    //Return: the number of bins (int)
    public int getNumberOfBins(){
        
        return numberOfBins;
        
    }
    
    //Function setBinWidth()
    //Manually set the width of the bin
    //Arguments: newValue - new bin width to use
    //Return: None
    public void setBinWidth(double newValue){
        
        binWidth = newValue;
        
    }
    
    //Function getBinWith()
    //Get width of one bin
    //Arguments: None
    //Return: the bin width (double)
    public double getBinWith(){
        
        return binWidth;
        
    }
    
    //Function countSamplesInBins()
    //Claculates number of values each bin should have and saves them in stored list
    //Arguments: None
    //Return: None
    public void calcSamplesInBins(){
        
        //running value of currently checked data point
        int latestDataPoint = 0;
        
        //running value of data points in each bin
        double dataCount = 0;
        
        //iterate through created list of bin endings
        for(int i = 0;i<binEndingValues.size();i++){
            
            //refresh data point count for each bin
            dataCount = 0;
            
            //iterate through the data points until ending of a bin is reached
            //(the list is sorted in abastract class thus this can be
            // a constant iteration from begginning to end
            while(super.data.get(latestDataPoint)<binEndingValues.get(i)){
                
                //increment nubmer of values that current bin has
                dataCount++;
                
                //increment the index of data point to check
                latestDataPoint++;

            }
            //assign number of data points to the bin
            binValueFrequencies.set(i, dataCount);
        }
        
    }
    
    //Function getBinEndingValues()
    //Get a list of values, specifying ending of each bin range
    //Arguments: None
    //Return: a list of ending values (List<Double>)
     public List<Double> getBinEndingValues(){
        
        return binEndingValues;
        
    }
    
    //Function getEndingOfBin()
    //Get a value, specifying ending of range of a chosen bin
    //Arguments:
    //      binIndex - index of bin in list from which to get ending value
    //Return: ending value of chosen bin (Double)
    public Double getEndingOfBin(int binIndex){
        if(binIndex<binEndingValues.size()){
            return binEndingValues.get(binIndex);
        }
        else{
            
            return 0.0;
            
        } 
        
    }
    
    //Function getBinData()
    //Get a list of values, specifying number of values stored in each bin
    //Arguments: None
    //Return: a list of values in bins (List<Double>)
    public List<Double> getBinData(){
        
        return binValueFrequencies;
        
    }
    
    //Function getSampleNumInBin()
    //Get a values, specifying number of data points in s chosen bin
    //Arguments:
    //      binIndex - index of bin in list from which to get ending value
    //Return: number of data points in a chosen bin (Double)
    public Double getSampleNumInBin(int binIndex){
        
        if(binIndex<binValueFrequencies.size()){
            return binValueFrequencies.get(binIndex);
        }
        else{
            
            return 0.0;
            
        } 
        
    }
    
    //Function prepareForNewData()
    //Refreshes the relevant bin data
    //Arguments: None
    //Return: None
    protected void prepareForNewData(){
        //setup lists again
        setupLists();
        
    }
    
}

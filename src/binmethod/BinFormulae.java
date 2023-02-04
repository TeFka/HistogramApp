/*
Name: Danielius Zurlys
Student ID: 20130611
*/

package binmethod;
import java.util.ArrayList;
import java.util.List;

/*
Abstract class BinFormulae
Class used as abstract reference for
its extended classes used for number of bins calculations
Available functionality:
        setData() - set data list to use
        setDataPoint() - sets a data point in the specified location of data list
        getData() - retrieves used data list
        getDataPoint() - retrieves data point at the specified location
        getDataSize() - retrieves size of used data
        getNumberOfBins() - retrieves calculated number of bins(calcuclated in extended classes)
*/
public abstract class BinFormulae {
    
    //stored Data list to use for calculations
    protected List<Double> storedData;
    
    //the calculated number of bins
    protected int numberOfBins;
    
    //Default Constructor
    //Action: sets initial member values
    public BinFormulae(){
        
       storedData = new ArrayList<Double>();
        
    }
    
    //constructor
    //Action: sets initial member values
    public BinFormulae(List<Double> data){
        
        storedData = data;
        
    }
    
    //Function getData()
    //Retrieves the stored list of data
    //Arguments: None
    //Return: The stored data list (List<Double>)
    public List<Double> getData(){
        
        return storedData;
        
    }
    
    //Function getDataPoint()
    //Sets a new data list
    //Arguments
    //      index - index of data point ot retrieve
    //Return: the data point value (double)
    public double getDataPoint(int index){
        return storedData.get(index);    
    }
    
    //Function setData()
    //Sets a new data list
    //Arguments
    //      newData - new list of data
    //Return: None
    public void setData(List<Double> newData){
        
        storedData = newData;
        
    }
    
    //Function setDataPoint()
    //Sets a new value at specified location
    //Arguments
    //      newValue - value to set
    //      index - position of new value
    //Return: None
    public void setDataPoint(Double newValue, int index){
        
        // sets the specified value at the specified index
        storedData.set(index, newValue);
        
    }
    
    //Function getDataSize()
    //Sets a new data list
    //Arguments: None
    //Return: the retrieved data size (int)
    public int getDataSize(){
        
        return storedData.size();
        
    }
    
    //Function getNumberOfBins()
    //Retrieves the stored number of bins
    //Arguments: None
    //Return: the stored number of bins (int)
    public int getNumberOfBins(){
        
        return numberOfBins;
    
    }
    
    //Abstract Function calculateNumberOfBins()
    //Calculates the nuber of bins based on active rule(class)
    //Arguments: None
    //Return: None
    public abstract void calculateNumberOfBins();
            
}

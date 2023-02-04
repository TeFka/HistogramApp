/*
Name: Danielius Zurlys
Student ID: 20130611
*/
package statutils;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
Abstract class BinFormulae
Class used as abstract reference for
its extended classes used for data statistics operations
Available functionality:
        setData() - set data list to use
        setDataPoint() - sets a data point in the specified location of data list
        getData() - retrieves used data list
        getDataPoint() - retrieves data point at the specified location
        getDataSize() - retrieves size of used data
*/
public abstract class AbstractStats {
    
    //the data to get statistics from
    protected List<Double> data;
    
    //Default constructor
    //Action: sets initial member values
    public AbstractStats(){
        
        data = new ArrayList<Double>();
        
    }
    
    //constructor
    //Action: sets initial member values
    public AbstractStats(List<Double> newData){
        
        data = newData;
        
        //sort input data
        Collections.sort(data);
        
    }
    
    //Function setData()
    //Sets up a new data list
    //Arguments
    //      newData - new list of data
    //Return: None
    public void setData(List<Double> newData){
        
        data = newData;
        
        //sort new data
        Collections.sort(data);
        
        //perform preparation for data (defined in extended classes)
        prepareForNewData();
        
    }
    
    //Function getData()
    //Retrieves the stored list of data
    //Arguments: None
    //Return: The stored data list (List<Double>)
    public List<Double> getData(){
        
        return data;
        
    }
    
    //Function getDataPoint()
    //Sets a new data list
    //Arguments
    //      newData - new list of data
    //Return: the retrieved data point (double)
    public double getDataPoint(int index){
        
        //check if index is not out of range
        if(index<data.size()){
        return data.get(index);
        }
        else{
            return 0.0;
        }
        
    }
    
    //Function getDataSize()
    //Sets a new data list
    //Arguments: None
    //Return: the retrieved data size (int)
    public int getDataSize(){
        
        return data.size();
        
    }
    
    //Abstract Function prepareForNewData()
    //Performs setup operations in case of the stored data being modified
    //Arguments: None
    //Return: None
    protected abstract void prepareForNewData();
    
}

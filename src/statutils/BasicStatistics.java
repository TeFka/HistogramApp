/*
Name: Danielius Zurlys
Student ID: 20130611
*/
package statutils;
import java.util.List;
import java.lang.Math;

import java.util.Collections;
/*
Class BasicStatistics
Used to retriev basic statistical
information from the provided data list
Available functionality:
        calcMean() - calculates mean of the data
        calcMax() - calculates highest value of the data
        calcMin() - calculates smallest value of the data
        calcvVariance() - calculates variance of the data
        calcMedian() - calculates median of the data
        calcStandartDeviation() - calculates standart deviation of the data
*/
public class BasicStatistics extends AbstractStats {
    
    //stored basic statistical values
    private double dataMean;
    private double dataVariance;
    private double dataMax;
    private double dataMin;
    private double dataMedian;
    private double dataStandartDeviation;
    
    //specification of statistic value availability
    //specifies if the values were calculated already
    private boolean meanAvailable;
    private boolean varianceAvailable;
    private boolean medianAvailable;
    private boolean standartDeviationAvailable;
    
    //DEfault constructor
    //Action:
    //      sets initial member values
    //      calls parent constructor
    public BasicStatistics(){
        super();
        
        //set default values
        dataMean = 0;
        dataVariance = 0;
        dataMax = 0;
        dataMin = 0;
        dataMedian = 0;
        dataStandartDeviation = 0;
        
        //specifiy that no more complex values are availble
        meanAvailable = false;
        varianceAvailable = false;
        medianAvailable = false;
        standartDeviationAvailable = false;
    
    }
    
    //constructor
    //Action:
    //      sets initial member values
    //      calls parent constructor
    public BasicStatistics(List<Double> newData){
        super(newData);
        
        //set default values
        dataMean = 0;
        dataVariance = 0;
        dataMax = 0;
        dataMin = 0;
        dataMedian = 0;
        dataStandartDeviation = 0;
        
        //specifiy that no more complex values are availble
        meanAvailable = false;
        varianceAvailable = false;
        medianAvailable = false;
        standartDeviationAvailable = false;
    
    }
    
    //Function calcVariance()
    //calculates variance of the data
    //Arguments: None
    //Return: None
    public void calcMean(){
        
        dataMean = 0;
        if(data.size()>0){
            //add all data values
            for (int i = 0; i <data.size(); i++) {
                dataMean += super.data.get(i);
            }

            //divide the overall result but the data size
            dataMean /= data.size();
        }
        meanAvailable = true;
    }
    
    //Function calcVariance()
    //calculates variance of the data
    //Arguments: None
    //Return: None
    public void calcVariance(){
        
        //check if size is acceptable for variance calculation
        if(data.size()>1){
            //retrieve mean if it was not stored yet
            if(!meanAvailable) calcMean();

            double squaredDeviationsSum = 0;

            //get total square deviation from all data points
            for (int i = 0; i <data.size(); i++) {

                squaredDeviationsSum += ((super.data.get(i)-dataMean)*(super.data.get(i)-dataMean));

            }

            //divide square deviation to get variance
            dataVariance = squaredDeviationsSum/(data.size()-1);
            
        }
        else{
            dataVariance = 0;
        }
        varianceAvailable = true;
    }
    
    //Funcrion calcMax()
    //calculates highest value of the data
    //Arguments: None
    //Return: None
    public void calcMax(){
        if(data.size()>0){
            //get the starting value
            dataMax = super.data.get(super.data.size()-1);
        }
        else{
            dataMax = 0;
        }
    }
    
    //Function calcMin()
    //calculates smallest value of the data
    //Arguments: None
    //Return: None
    public void calcMin(){
        if(data.size()>0){
            //get the starting value
            dataMin = super.data.get(0);
        }
        else{
            dataMin = 0;
        }
    }
    
    //Function calcMedian()
    //calculates median of the data
    //Arguments: None
    //Return: None
    public void calcMedian(){
        
        if(data.size()>0){
            //check if there is an even or odd number of dta points
            if(data.size()%2==0){
                //Even: get an average of two middle values
                dataMedian = (data.get((data.size()/2)-1)+data.get(data.size()/2))/2;
            }
            else{
                //Odd: get the middle value
                dataMedian = data.get(1+data.size()/2);
            }
        }
        else{
            dataMedian = 0;
        }
        medianAvailable = true;
    }
    
    //Function calcStandartDeviation()
    //calculates standart deviation of the data
    //Arguments: None
    //Return: None
    public void calcStandartDeviation(){
        
        //retrieve variance if it was not stored yet
        if(!varianceAvailable) calcVariance();
        
        //get square root of variance to get standart deviation
        dataStandartDeviation = Math.sqrt(dataVariance);
        
        standartDeviationAvailable = true;
    }
    
    //Function getMean()
    //Retrieves stored mean value
    //Arguments: None
    //Return: the mean value (double)
    public double getMean(){
        
        //retrieve mean
        return dataMean;
        
    }
    
    //Function getVariance()
    //Retrieves stored variance value
    //Arguments: None
    //Return: the variance value (double)
    public double getVariance(){
        
        //retrieve variance
        return dataVariance;
        
    }
    
    //Function getMax()
    //Retrieves stored maximumvalue
    //Arguments: None
    //Return: the maximum value (double)
    public double getMax(){
                
        //retrieves maximum value
        return dataMax;
        
    }
    
    //Function getMin()
    //Retrieves stored minimum value
    //Arguments: None
    //Return: the minimum value (double)
    public double getMin(){
        
        //retrieves minimum values
        return dataMin;
        
    }
    
    //Function getMedian()
    //Retrieves stored median value
    //Arguments: None
    //Return: the median value (double)
    public double getMedian(){
        
        //retrieve median
        return dataMedian;
        
    }
    
    //Function getStandartDeviation()
    //Retrieves stored standartDeviation value
    //Arguments: None
    //Return: the standartDeviation value (double)
    public double getStandartDeviation(){
        
        //retrieve standart deviation
        return dataStandartDeviation;
        
    }
    
    //Function prepareForNewData()
    //Refreshes the stored statistical data
    //Arguments: None
    //Return: None
    protected void prepareForNewData(){
        
        //refresh data statistic values
        dataMean = 0;
        dataVariance = 0;
        dataMax = 0;
        dataMin = 0;
        dataMedian = 0;
        dataStandartDeviation = 0;
        
        //refresh statistic value availability
        meanAvailable = false;
        varianceAvailable = false;
        medianAvailable = false;
        standartDeviationAvailable = false;
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HistogramApplication;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import java.io.FileWriter;

import binmethod.SturgesFormula;
import binmethod.SquareRootChoice;
import binmethod.RiceRule;
import java.io.IOException;
import statutils.BasicStatistics;
import statutils.BinStatistics;
import statutils.StatsDataModification;
import mathutils.DataFitter;


enum BinRule{
  SQUARE_ROOT,
  STURGES_FORMULA,
  RICE_RULE
}

//Class HistogramApplicationGUIModel
//A model class for operation of GUI
public class HistogramApplicationGUIModel {
    
    //lists of latest data to use
    private List<Double> latestData;
    private List<Double> latestBinEndings;
    private List<Double> latestBinFrequencies;
    private List<Double> latestNormalisedBinData;
    private List<Double> latestFittedData;
    
    //used bin rule for histogram
    private BinRule chosenBinRule;
    
    //specification if certain stage of data is available
    //each stage becomes viable as data is being modified
    private boolean dataAvailable;
    private boolean normalisationChosen;
    private boolean dataFitted;
    
    //Instance for basic statistical figures calculations
    private BasicStatistics basicStatsInstance;
    
    //Instance for bin calculations
    private BinStatistics binStatsInstance;
    
    //used number of bins for bin calculations
    private int dataNumberOfBins;
    
    //data fitting values
    private double normalisationCoefficientVal;
    private double PDFCentreVal;
    private double PDFWidthVal;
    
    //Other parameters
    
    //value used to scale numbers to make them 
    //have less values after decimal point
    private double scaleValue;
    
    //constructor
    //Action:
    //      Initiliazies objects
    //      Sets initila parameters
    public HistogramApplicationGUIModel(){
        
        //initialize all data lists
        latestData = new ArrayList<Double>();
        latestBinEndings = new ArrayList<Double>();
        latestBinFrequencies = new ArrayList<Double>();
        latestNormalisedBinData = new ArrayList<Double>();
        latestFittedData = new ArrayList<Double>();
        
        //initialize data statistic tools
        basicStatsInstance = new BasicStatistics();
        binStatsInstance = new BinStatistics();        
        
        //set initial parameters
        dataAvailable = false;
        normalisationChosen = false;
        dataFitted = false;
        chosenBinRule = BinRule.SQUARE_ROOT;
        scaleValue = 1;
    }
    
    //Function setLatestData()
    //Sets the input data used for histogram creation
    //Arguments: newData - data to set and use
    //Return: None
    public void setLatestData(List<Double> newData){
        
        latestData = newData;
        
    }
    
    //Function readDataFile()
    //Reads data from a file and stores it
    //Arguments: usedFile - file to retrive data from
    //Return: specification if file read was succefful(Boolean)
    public boolean readDataFile(File usedFile){
        
        //only read data if the file is a text(.txt) file
        if(usedFile.toString().endsWith(".txt")){
            try {
                //define list to read data into
                //this is done to not modify any existing data
                //in case a file reading fails
                List<Double> newData = new ArrayList<Double>();
                
                //set up file reader
                Scanner myReader = new Scanner(usedFile);
                
                //iterate through the file
                while (myReader.hasNextLine()) {
                  //retrieve each line and remove non-number
                  //elements that could potentially be on the line
                  //Exceptions are decemal separator '.' and
                  //'-' for negative number
                  String data = myReader.nextLine();
                  data = data.replaceAll("[^\\d.-]", "");
                  
                  //add data to temporary array
                  newData.add(Double.parseDouble(data));
                }
                myReader.close();
                
                //replace the latest data if the file rad was successful
                latestData = newData;
                dataAvailable = true;
            } catch (FileNotFoundException e) {
              //handle file read error
              System.out.println("An error occurred.");
              e.printStackTrace();
              dataAvailable = false;
            }
        
        }
        else{
                    
            dataAvailable = false;
             
        }
        
        return dataAvailable;
    }
    
    //Function writeData()
    //Writes latest data information to the specified text file
    //Arguments: usedFile - file to write data into
    //Return: specification if file write was succefful(Boolean)
    public boolean writeData(File usedFile){
        
        boolean success = true;
        try ( //retrieve the type of file opened
            FileWriter myWriter = new FileWriter(usedFile)) {
                
            if(dataFitted){//check if fitted data should be retrieved
                
                //iterate thorugh data and write all data points
                for(int i = 0;i<latestFittedData.size();i++){
                    
                    myWriter.write(String.valueOf(latestBinEndings.get(i))+"    "+String.valueOf(latestFittedData.get(i))+ System.lineSeparator());
                    
                }
            
            }
            else if(normalisationChosen){//check if normalised data should be retrieved
                
                //iterate thorugh data and write all data points
                for(int i = 0;i<latestNormalisedBinData.size();i++){
                    
                    myWriter.write(String.valueOf(latestBinEndings.get(i))+"    "+String.valueOf(latestNormalisedBinData.get(i))+ System.lineSeparator());
                    
                }

            }
            else{//check if bin data should be retrieved
                
                //iterate thorugh data and write all data points
                for(int i = 0;i<latestBinFrequencies.size();i++){
                    
                    myWriter.write(String.valueOf(latestBinEndings.get(i))+"    "+String.valueOf(latestBinFrequencies.get(i))+ System.lineSeparator());
                    
                }

            }
            //stop writting
            myWriter.close();
        }
        catch (IOException e){ // catch file exceptions
            
            success = false;
            
        }
        
        //return sepcification if writtig was sucessful
        return success;
    }
    
    //Function setBinRule()
    //Specifies which bin rule should be used
    //to calculate number of data bins
    //Arguments: newRule - the new rule to use
    //Return: None
    void setBinRule(BinRule newRule){
        
        //set rule
        chosenBinRule = newRule;
        
    }
    
    //Function choseNumberOfBins()
    //Use chosen bin rule to retrieve number of bins
    //Arguments: None
    //Return: None
    public void choseNumberOfBins(){
        switch(chosenBinRule) {
            //square root choice
            case SQUARE_ROOT:
              //create square root choice instance
              SquareRootChoice squareRootInstance = new SquareRootChoice(latestData);
              //calculate the number of bins
              squareRootInstance.calculateNumberOfBins();
              //retrieve number of bins
              dataNumberOfBins = squareRootInstance.getNumberOfBins();
              break;
            //Sturge's formula choice
            case STURGES_FORMULA:
               //create Sturge's formula instance
              SturgesFormula sturgesInstance = new SturgesFormula(latestData);
              //calculate the number of bins
              sturgesInstance.calculateNumberOfBins();
              //retrieve number of bins
              dataNumberOfBins = sturgesInstance.getNumberOfBins();
              break;
            //Rice Rule choice
            case RICE_RULE:
              //create rice rule instance
              RiceRule riceRuleInstance = new RiceRule(latestData);
              //calculate the number of bins
              riceRuleInstance.calculateNumberOfBins();
              //retrieve number of bins
              dataNumberOfBins = riceRuleInstance.getNumberOfBins();
              break;
        }

    }
    
    //Function calcScale()
    //Calculate the required scale to
    //remove amount of numbers after decimal point
    //Arguments: None
    //Return: None
    public void calcScale(){
        
        //retrieve mean value as a reference for scale
        double runningScaleValue = Math.abs(basicStatsInstance.getMean());
        scaleValue = 1;
        
        //increase scale value until reference value
        //becomes higher than one
        while(runningScaleValue<1){
            runningScaleValue*=10;
            scaleValue *= 10;    
        }
        
    }
    
    //Function handleLatestData()
    //Retrieves information from the latest input data
    //calculated relevant statisticaly figues
    //separates data into bins
    //Arguments: None
    //Return: None
    public void handleLatestData(){
        
        //clear
        clearDataModifications();
        
        //set latest data to basic statitics tool
        basicStatsInstance.setData(latestData);
        
        //calculcate all basic statistical figues
        basicStatsInstance.calcMean();
        basicStatsInstance.calcVariance();
        basicStatsInstance.calcMax();
        basicStatsInstance.calcMin();
        basicStatsInstance.calcMedian();
        basicStatsInstance.calcStandartDeviation();
        
        //retrieve required scale
        calcScale();
        
        //retrive number of required bins
        choseNumberOfBins();
        
        //set paremeters to bin statistics tool
        binStatsInstance.setup(latestData, dataNumberOfBins); 
        
        //calculate numbers of values in bins
        binStatsInstance.calcSamplesInBins();
        
        //set sammple count in each bin
        latestBinFrequencies = binStatsInstance.getBinData();  
        
        //get ending values of bin ranges
        latestBinEndings = binStatsInstance.getBinEndingValues();
        
        handleDataNormalisation();
    }
    
    //Function handleLatestData()
    //Handles normalisation of the latest
    //input data
    //Arguments: None
    //Return: None
    public void handleDataNormalisation(){     
        
        if(!normalisationChosen){
            //retrieve normalised data list
            latestNormalisedBinData = StatsDataModification.getNormalisedData(latestBinFrequencies, basicStatsInstance.getMax(), basicStatsInstance.getMin());

            //specify that normalised data is available
            normalisationChosen = true;
        }

    }
    
    //Function handleLatestData()
    //Handles data fitting of the normalised input data
    //input data
    //Arguments: None
    //Return: None
    public void handleDataFitting(){
        
        if(!dataFitted){
            //retrieve fitting parameters
            double[] params;

            //check which data is the latest and should bi fitted
            if(normalisationChosen){
                params = DataFitter.calcFittedData(latestBinEndings, latestNormalisedBinData);
            }
            else{
                params = DataFitter.calcFittedData(latestBinEndings, latestBinFrequencies);
            }

            //store data fitting parameters
            normalisationCoefficientVal = params[0];
            PDFCentreVal = params[1];
            PDFWidthVal = params[2];

            //retrieve a list of fitted data 
            latestFittedData = DataFitter.getPDF_YData(latestBinEndings,
                                            normalisationCoefficientVal,
                                            PDFCentreVal,
                                            PDFWidthVal);

            //specifiy that fitted data is available
            dataFitted = true;
        }

    }
    
    //Function setScaleValue()
    //Set scale value for values to 
    //remove amount of numbers after decimal point
    //Arguments: newValue - new scale value
    //Return: None
    public void setScaleValue(double newValue){
        
        scaleValue = newValue;
        
    }
    
    //Function getScaleValue()
    //Get scale value used to 
    //remove number of number after decimal point
    //Arguments: None
    //Return: the scale value (double)
    public double getScaleValue(){
        
        return scaleValue;
        
    }
    
    //Function getLatestData()
    //Retrieve the latest relevant data after all modifications
    //Arguments: None
    //Return: the lastest data list (List<Double>)
    public List<Double> getLatestData(){
        
        if(dataFitted){//check if fitted data should be retrieved
            return latestFittedData;
            
        }
        else if(normalisationChosen){//check if normalised data should be retrieved
            
            return latestNormalisedBinData;
            
        }
        else{//check if unmodified bin data should be retrieved
            
            return latestBinFrequencies;
            
        }
        
    }
    
    //Function getLatestBinFrequencies()
    //Retrieves the list, specifying amount of data values in bins
    //Arguments: None
    //Return: the list of bin data values (List<Double>)
    public List<Double> getLatestBinFrequencies(){
        
        return latestBinFrequencies;
        
    }
    
    //Function getLatestNormalisedBinData()
    //Retrieves the list, specifying latest normalised values
    //Arguments: None
    //Return: list of normalised values (List<Double>)
    public List<Double> getLatestNormalisedBinData(){
        
        return latestNormalisedBinData;
        
    }
    
    //Function getLatestFittedData()
    //Retrieves the list, specifying latest fitted values
    //Arguments: None
    //Return: list of fitted values (List<Double>)
    public List<Double> getLatestFittedData(){
        
        return latestFittedData;
        
    }
    
    //Function getBinEndings()
    //Retrieves the list, specifying endings of bin ranges
    //Arguments: None
    //Return: the list of bin ending values (List<Double>)
    public List<Double> getBinEndings(){
        
        return latestBinEndings;
        
    }
    
    //Function clearLatestData()
    //Clear latest data and all its parameters and modifications
    //Arguments: None
    //Return: None
    public void clearLatestData(){
        
        //refresh data and its availability
        latestData.clear();
        latestBinFrequencies.clear();
        dataAvailable = false;
        
        //clear data modifications
        clearDataModifications();
    }
    
    //Function clearDataModifications()
    //Clear data modifications
    //Arguments: None
    //Return: None
    public void clearDataModifications(){
        
        //refresh normalised data
        refreshDataNormalizationStage();
        
        //refresh fitted data
        refreshDataFittingStage();
        
        //*More modification could be added*
    }
    
    //Function refreshDataNormalizationStage()
    //Clear normalised data and its availability
    //Arguments: None
    //Return: None
    public void refreshDataNormalizationStage(){
        
        //clear normalised data
        latestNormalisedBinData.clear();
        
        //specify that normalised data is not available
        normalisationChosen = false;

    }
    
    //Function refreshDataFittingStage()
    //Clear fitting data and its availability
    //Arguments: None
    //Return: None
    public void refreshDataFittingStage(){
        
        //clear fitted data
        latestFittedData.clear();
        
        //specify that fitted data is not available
        dataFitted = false;
        
    }
    
    //Function chooseNormalisation()
    //Function to specifiy whether nomrlaziation values should be used
    //Arguments: newVal - specification of data usage
    //Return: None
    public void chooseNormalisation(boolean newVal){
        
        normalisationChosen = newVal;
        
    }
    //Function chooseFitting()
    //Function to specifiy whether fitted data values should be used
    //Arguments: newVal - specification of data usage
    //Return: None
    public void chooseFitting(boolean newVal){
        
        dataFitted = newVal;
        
    }
    
    //Function dataIsAvailable()
    //Function to find out if input data is available
    //Arguments: None
    //Return: specification if data is available (boolean)
    public boolean dataIsAvailable(){
        
        return dataAvailable;
        
    }
    
    //Function getMax()
    //Retrieves stored maximumvalue of data
    //Arguments: None
    //Return: the maximum value (double)
    public double getMax(){
        
        return basicStatsInstance.getMax();
        
    }
    
    //Function getMin()
    //Retrieves stored minimum value of data
    //Arguments: None
    //Return: the minimum value (double)
    public double getMin(){
        
        return basicStatsInstance.getMin();
        
    }
    
    //Function getVariance()
    //Retrieves stored variance value of data
    //Arguments: None
    //Return: the variance value (double)
    public double getDataVariance(){
        
        return basicStatsInstance.getVariance();
        
    }
    
    //Function getMean()
    //Retrieves stored mean value of data
    //Arguments: None
    //Return: the mean value (double)
    public double getDataMean(){
        
        return basicStatsInstance.getMean();
        
    }
    
    //Function getMedian()
    //Retrieves stored median value of data
    //Arguments: None
    //Return: the median value (double)
    public double getDataMedian(){
        
        return basicStatsInstance.getMedian();
        
    }
    
    //Function getStandartDeviation()
    //Retrieves stored standart deviation value of data
    //Arguments: None
    //Return: the standartDeviation value (double)
    public double getDataStandartDeviation(){
        
        return basicStatsInstance.getStandartDeviation();
        
    }
    
    //Function getNumberOfBins()
    //Get number of bins based on which stored data is divided
    //Arguments: None
    //Return: the number of bins (int)
    public int getNumberOfBins(){
        
        return dataNumberOfBins;
        
    }
    
    //Function getAmountOfData()
    //Retreives the size of latest data
    //Arguments: None
    //Return: the retrieved data size (int)
    public int getLatestAmountOfData(){
        
        return latestData.size();
        
    }
    
    //Function getNormalisationCoefficient()
    //Retrieves normalisation coefficient of data fitting
    //Arguments: None
    //Return: the normalisation coefficient (double)
    public double getNormalisationCoefficient(){
        
        return normalisationCoefficientVal;
        
    }
    
    //Function getFittedPDFCentre()
    //Retrieves PDF centre of data fitting
    //Arguments: None
    //Return: the PDF centre (double)
    public double getFittedPDFCentre(){
        
        return PDFCentreVal;
        
    }
    
    //Function getFittedPDFWidth()
    //Retrieves PDF width of data fitting
    //Arguments: None
    //Return: the PDF width (double)
    public double getFittedPDFWidth(){
        
        return PDFWidthVal;
        
    }
}

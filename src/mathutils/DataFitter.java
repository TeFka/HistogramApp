/*
Name: Danielius Zurlys
Student ID: 20130611
*/

//imported packages
package mathutils;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.fitting.*;

//class DataFitter
//Class used to perform data fitting of lists of data
//Availabe functions
//      Data fitting - retrieves data fitting parameters
//      PDF calculation - calculated the Y values of a PDF based on provided parameters
public class DataFitter {
    
    //constructor
    //Action: None
    public DataFitter(){
        
    }
    
    //Function getPDF_YDataPoint()
    //Retrieves a fitted Y value based on provided
    //X value and fitting parameters
    //Arguments:
    //      xDataPoint - an X value
    //      PDFnormalizatioFactor - fitting pdf normalization factor
    //      pdfMean - fitting pdf mean
    //      pdfWidth - fitting pdf width
    //Return: the calculated fitted Y value (double)
    public static double getPDF_YDataPoint(double xDataPoint, double PDFnormalizationFactor,                                    
                                           double pdfMean, double pdfWidth){
        
        //calculate the y data pont value and\
        //retrieve it
        double powerVal = (xDataPoint - pdfMean)/pdfWidth;
        return PDFnormalizationFactor*Math.exp(-0.5*(powerVal*powerVal));
    }
    
    //Function getPDF_YDataPoint()
    //Retrieves a list of fitted Y values based on provided
    //list of X values and fitting parameters
    //Arguments:
    //      xDataPoint - a list of X value
    //      PDFnormalizatioFactor - fitting pdf normalization factor
    //      pdfMean - fitting pdf mean
    //      pdfWidth - fitting pdf width
    //Return: a list of calculated fitted Y values (double)
    public static List<Double> getPDF_YData(List<Double> xData, double PDFnormalizationFactor,                                    
                                           double pdfMean, double pdfWidth){
        
        //create temporary list for y data
        List<Double> yData = new ArrayList<Double>();
        
        //iterate through x data
        for(int i = 0;i<xData.size();i++){
            
            //calculate the y data pont value and add it to the list
            double powerVal = (xData.get(i) - pdfMean)/pdfWidth;
            yData.add(PDFnormalizationFactor*Math.exp(-0.5*(powerVal*powerVal)));
        }
        
        //retrieve the list
        return yData;
    }
    
    //Function calcFittedData()
    //Performs data fitting based
    //on the provided x and Y data lists
    //Arguments:
    //      theXData - a list definning all X points
    //      theYData - a list definning all Y points
    public static double[] calcFittedData(List<Double> theXData, List<Double> theYData){
        
        //define weight observations class which is used for data fitting
        WeightedObservedPoints obs = new WeightedObservedPoints();
        //add data pairs of the provided lists to the weight observations
        for(int i = 0;i<theYData.size();i++){
            
            obs.add(theXData.get(i), theYData.get(i));
            
        }
        //retrieve the fitting paramters using the GaussianCurveFitter functionality
        double[] parameters = GaussianCurveFitter.create().fit(obs.toList());
        // Print out result on screen 
        
        return parameters;
    }
    
}

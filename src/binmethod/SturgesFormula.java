/*
Name: Danielius Zurlys
Student ID: 20130611
*/

package binmethod;

import java.util.List;
import java.lang.Math;
import binmethod.BinFormulae;

/*
Abstract class SturgesFormula
Class used to calculate number of bins
for the specified data using Sturge's Formula
Class is an extension of BinFormulae
*/
public class SturgesFormula extends BinFormulae{
    
    //Default Constructor
    //Action: calls parent default constructor
     public SturgesFormula(){ 
        //using parent constructor
        super();
    }
     
    //constructor
    //Action: calls parent constructor
    public SturgesFormula(List<Double> data){
        
        //using parent constructor
        super(data);
    }
    
    //Function calculateNumberOfBins()
    //Calculates the nuber of bins using Sturge's Formula
    //Arguments: None
    //Return: None
    @Override
    public void calculateNumberOfBins(){
        
        //calculation using Sturge's formula
        numberOfBins = (int)Math.round(3.3*Math.log10((double)storedData.size())+1);
        
    }
}

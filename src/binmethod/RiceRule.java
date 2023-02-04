/*
Name: Danielius Zurlys
Student ID: 20130611
*/

package binmethod;

import java.util.List;
import java.lang.Math;
import binmethod.BinFormulae;

/*
Abstract class RiceRule
Class used to calculate number of bins
for the specified data using Rice Rule
Class is an extension of BinFormulae
*/
public class RiceRule extends BinFormulae {
    
    //Default Constructor
    //Action: calls parent default constructor
     public RiceRule(){ 
        //using parent constructor
        super();
    }
     
    //constructor
    //Action: calls parent constructor
     public RiceRule(List<Double> data){
         
        //using parent constructor
        super(data);
    }
    
    //Function calculateNumberOfBins()
    //Calculates the nuber of bins using rice rule
    //Arguments: None
    //Return: None
    @Override
    public void calculateNumberOfBins(){
        
        //calculation using rice rule
        numberOfBins = (int)Math.round(2*Math.cbrt((double)storedData.size()));
        
    }
    
}

/*
Name: Danielius Zurlys
Student ID: 20130611
*/

package binmethod;

import java.util.List;
import java.lang.Math;
import binmethod.BinFormulae;

/*
Abstract class SquareRootChoice
Class used to calculate number of bins
for the specified data using Square Root Choice
Class is an extension of BinFormulae
*/
public class SquareRootChoice extends BinFormulae {
    
    //Default Constructor
    //Action: calls parent default constructor
     public SquareRootChoice(){ 
        //using parent constructor
        super();
    }
     
    //constructor
    //Action: calls parent constructor
     public SquareRootChoice(List<Double> data){
         
        //using parent constructor
        super(data);
        
    }
    
    //Function calculateNumberOfBins()
    //Calculates the nuber of bins using Square Root Rule
    //Arguments: None
    //Return: None
    @Override
    public void calculateNumberOfBins(){
        
        //calculation using square root rule
        numberOfBins = (int)Math.round(Math.sqrt(storedData.size()));
        
    }
    
}

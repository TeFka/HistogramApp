package UnitTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;

import mathutils.DataFitter;
import org.junit.Assert;
import org.junit.Test;

/**
* class Unit_test_mathutils
* 
 */
public class Unit_test_mathutils {
    
    @Test
    public void mainTest() {
        
        //Example arrays of data
        List<Double> exampleXData = new ArrayList<Double>();
        List<Double> exampleYData = new ArrayList<Double>();
        
        //fill arrays with test data
        for (int i =-10; i <= 10; i++) {
            exampleXData.add(new Double(i));
            exampleYData.add(new Double(i)+25);
        }
        
        //set a few out of sequence data points
        //which will be used to prove successful fitting
        exampleYData.set(4, 50.0);
        exampleYData.set(8, 50.0);
        exampleYData.set(12, 50.0);
        exampleYData.set(16, 50.0);
        exampleYData.set(19, 50.0);
        
        //retrieve the paramters for data fitting
        double[] parameters = DataFitter.calcFittedData(exampleXData, exampleYData);
        
        //print parameters to user
        System.out.printf("Normalization factor = %.14f, expected: %.14f\n",parameters[0], 38.68160490107167);
        System.out.printf("Mean = %.14f, expected: %.14f\n",parameters[1], 10.70827437329727);
        System.out.printf("Sigma = %.14f, expected %.14f\n",parameters[2], 17.00925289164890); 
        
        //test the parameters
        Assert.assertEquals(38.68160490107167, parameters[0], 0.0000000001);
        Assert.assertEquals(10.70827437329727, parameters[1], 0.0000000001);
        Assert.assertEquals(17.00925289164890, parameters[2], 0.0000000001);
        
        //print the X values of data
        System.out.printf("X Values\n");
        for (int i = 0; i <exampleXData.size(); i++) {
             
              System.out.printf("%f  ",exampleXData.get(i));
        }
        
        //show comparison of the oroginal and fitted Y values
        System.out.printf("\nY Value Comparison.\n");
        
        //print original Y values
        System.out.printf("Original:\n");
        for (int i = 0; i < exampleYData.size(); i++) {
             
              System.out.printf("%f  ",exampleYData.get(i));
        }
        System.out.printf("\nFitted:\n");
        
        //retrieve a list of fitted data
        List<Double> latestFittedData = DataFitter.getPDF_YData(exampleXData,
                                                                parameters[0],
                                                                parameters[1],
                                                                parameters[2]);
        
        for (int i = 0; i < exampleYData.size(); i++) {
             
             System.out.printf("%f  ", latestFittedData.get(i));
        }
        System.out.printf("\n");
    }
    
}

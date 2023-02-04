package PackageUnitTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import binmethod.SturgesFormula;
import java.util.ArrayList;
import java.util.List;
import mathutils.DataFitter;
import statutils.BasicStatistics;
import statutils.StatsDataModification;

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
        
        double expectedPDFnormalizationFactor = 17.72251606484296;
        double expectedpdfMean = 24.05120691358283;
        double expectedpdfWidth = 13.14667905124182;
        
        //Example arrays of data
        List<Double> exampleXData = new ArrayList<Double>();
        List<Double> exampleYData = new ArrayList<Double>();
        
        //fill arrays with test data
        for (int i =0; i <= 15; i++) {
            exampleXData.add(new Double(i));
            exampleYData.add(new Double(i));
        }
        
        //set a few out of sequence data points
        //which will be used to prove successful fitting
        exampleYData.set(2, 10.0);
        exampleYData.set(4, 10.0);
        exampleYData.set(8, 10.0);
        exampleYData.set(11, 10.0);
        exampleYData.set(13, 10.0);
        
        //retrieve the paramters for data fitting
        double[] parameters = DataFitter.calcFittedData(exampleXData, exampleYData);
        
        //print parameters to user
        System.out.printf("Normalization factor = %.14f, expected: %.14f\n",parameters[0], expectedPDFnormalizationFactor);
        System.out.printf("Mean = %.14f, expected: %.14f\n",parameters[1], expectedpdfMean);
        System.out.printf("Sigma = %.14f, expected %.14f\n",parameters[2], expectedpdfWidth); 
        
        //test the parameters by comparing expected and resulting paramter values
        Assert.assertEquals(expectedPDFnormalizationFactor, parameters[0], 0.0000000001);
        Assert.assertEquals(expectedpdfMean, parameters[1], 0.0000000001);
        Assert.assertEquals(expectedpdfWidth, parameters[2], 0.0000000001);
        
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
        
        //create array of expected fitted data
        double[] expectedFittedData = new double[]{3.324736,  3.810095,  4.341120,  4.917619,  5.538540,  6.201874, 6.904588,  7.642578,  8.410643,  9.202498, 10.010817,  10.827310,  11.642837,  12.447562,  13.231133,  13.982892};
        //retrieve a list of fitted data
        List<Double> latestFittedData = DataFitter.getPDF_YData(exampleXData,
                                                                parameters[0],
                                                                parameters[1],
                                                                parameters[2]);
        
        //create array for testing of fitted data
        double[] resultingFittedData = new double[16];
        //print resulting values and add saidvalues to testing array
        for (int i = 0; i < exampleYData.size(); i++) {
             
             System.out.printf("%f  ", latestFittedData.get(i));
             resultingFittedData[i] = latestFittedData.get(i);
        }
        
        //perform test by comparing expected and resulting arrays of fitted data
        Assert.assertArrayEquals(expectedFittedData, resultingFittedData, 0.000001);
    }
    
}

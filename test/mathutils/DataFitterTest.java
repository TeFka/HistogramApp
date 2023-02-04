/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathutils;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class DataFitterTest {
    
    public DataFitterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPDF_YDataPoint method, of class DataFitter.
     */
    @Test
    public void testGetPDF_YDataPoint() {
        System.out.println("\n*getPDF_YDataPoint");
        double xDataPoint = 10.0;
        double PDFnormalizationFactor = 15.0;
        double pdfMean = 20.0;
        double pdfWidth = 50.0;
        double expResult = 14.702980099601328;
        
        //apply fitting formula and retrieve Y value of data point
        double result = DataFitter.getPDF_YDataPoint(xDataPoint, PDFnormalizationFactor, pdfMean, pdfWidth);
        
        System.out.printf("result: %.15f, expected: %.15f\n",result, expResult);
        
        //perform test by comparing expected and resulting Y value of data point
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPDF_YData method, of class DataFitter.
     */
    @Test
    public void testGetPDF_YData() {
        System.out.println("\n*getPDF_YData");
        List<Double> xData = new ArrayList<Double>();
        xData.add(10.0);
        xData.add(20.0);
        xData.add(30.0);
        double PDFnormalizationFactor = 15.0;
        double pdfMean = 10.0;
        double pdfWidth = 7.0;
        List<Double> expResult = new ArrayList<Double>();
        expResult.add(15.000000);
        expResult.add(5.4067168289673155);
        expResult.add(0.2531982622318486);
        
        //apply fitting formula and retrieve a list of Y values of data points
        List<Double> result = DataFitter.getPDF_YData(xData, PDFnormalizationFactor, pdfMean, pdfWidth);
         
        for (int i = 0; i < xData.size(); i++) {
             
              System.out.printf("value at %d: %.16f, expected: %.16f  ",i, result.get(i), expResult.get(i));
        }
        
        //perform test by comparing expected and resulting lists of Y values of data
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcFittedData method, of class DataFitter.
     */
    @Test
    public void testCalcFittedData() {
        System.out.println("\n*testCalcFittedData");
        
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

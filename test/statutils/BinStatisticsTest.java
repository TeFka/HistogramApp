/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statutils;

import binmethod.SquareRootChoice;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author Admin
 */
public class BinStatisticsTest {
    
    private List<Double> exampleData;
    int numberOfBins;
    
    public BinStatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        System.out.println("Tested data range: Range [-100; 100]"); 
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        //create an example list and populate it
        exampleData = new ArrayList<Double>();
        for (int i =-100; i <= 100; i++) {
            exampleData.add(new Double(i));
        }
        
        //use square root choice to calculate number of bins to use
        SquareRootChoice SquareRootInstance = new SquareRootChoice(exampleData);
        SquareRootInstance.calculateNumberOfBins();
        numberOfBins = SquareRootInstance.getNumberOfBins();
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setupLists method, of class BinStatistics.
     */
    @Test
    public void testSetUp() {
        
        System.out.println("\n*setUp");
        BinStatistics instance = new BinStatistics();
        //simply test the setUp() function andcheck if no confilcts happen
        instance.setup(exampleData, numberOfBins);
        
    }

    /**
     * Test of setNumberOfBins method, of class BinStatistics.
     */
    @Test
    public void testSetGetNumberOfBins() {
        System.out.println("\n*setNumberOfBins");
        BinStatistics instance = new BinStatistics(exampleData, numberOfBins);
        int expectedValue = 14;
        //Test by comparing expected value and result
        Assert.assertEquals(expectedValue, instance.getNumberOfBins(), 0);
        
    }

    /**
     * Test of calcSamplesInBins method, of class BinStatistics.
     */
    @Test
    public void testCalcSamplesInBins() {
        System.out.println("*calcSamplesInBins");
        BinStatistics instance = new BinStatistics(exampleData, numberOfBins);
        instance.calcSamplesInBins();
        
        //specifiy expected numbers of samples in bins
        double[] expectedValues = new double[]{15, 14, 14, 15, 14, 14, 14, 15, 14, 14, 15, 14, 14, 14};
  
        System.out.printf("Bin Data:\n");
        double[] actualValues = new double[numberOfBins];
        
        //print resultin values to user and save them in static double array\
        //for comparison test
        for (int i = 0; i < numberOfBins; i++) {
            System.out.printf("%f ",instance.getSampleNumInBin(i));
            actualValues[i] = instance.getSampleNumInBin(i);
        }
        
        //print expected values to user
        System.out.printf("\nExcpected Bin Data:\n");
        for (int i = 0; i < numberOfBins; i++) {
            System.out.printf("%f ",expectedValues[i]);
        }
        
        //perform test by comparing values of expected and resulting arrays
        Assert.assertArrayEquals(expectedValues, actualValues, 0);
                 
    }

    /**
     * Test of getBinEndingValues method, of class BinStatistics.
     */
    @Test
    public void testGetBinEndingValues() {
        System.out.println("*getBinEndingValues");
        BinStatistics instance = new BinStatistics(exampleData, numberOfBins);
        double[] expectedNormalizedValues = new double[]{-85.714286, -71.428571, -57.142857, -42.857143, -28.571429, -14.285714, 0.000000, 14.285714, 28.571429, 42.857143, 57.142857, 71.428571, 85.714286, 100.000000};
        List<Double> result = instance.getBinEndingValues();
        
        double[] actualValues = new double[numberOfBins];
        
        //print resultin values to user and save them in static double array\
        //for comparison test
        System.out.println("Result: ");
        for (int i = 0; i < numberOfBins; i++) {
            System.out.printf("%f ",result.get(i));
            actualValues[i] = result.get(i);
        }
        
        //print expected values to user
        System.out.println("\nExpected: ");
        for (int i = 0; i < numberOfBins; i++) {
            System.out.printf("%f ",expectedNormalizedValues[i]);
        }
        
        //perform test by comparing values of expected and resulting arrays
        Assert.assertArrayEquals(expectedNormalizedValues, actualValues, 0.0001);
        
    }

    /**
     * Test of getEndingOfBin method, of class BinStatistics.
     */
    @Test
    public void testGetEndingOfBin() {
        System.out.println("\n*getEndingOfBin");
        BinStatistics instance = new BinStatistics(exampleData, numberOfBins);
        
        //specify list of expected values
        double[] expectedNormalizedValues = new double[]{-85.714286, -71.428571, -57.142857, -42.857143, -28.571429, -14.285714, 0.000000, 14.285714, 28.571429, 42.857143, 57.142857, 71.428571, 85.714286, 100.000000};
        
        //get bin data
        List<Double> result = instance.getBinEndingValues();
        
        double[] actualValues = new double[numberOfBins];  
        
        //print resultin values to user and save them in static double array\
        //for comparison test
        System.out.println("Result: ");
        for (int i = 0; i < numberOfBins; i++){
            System.out.printf("%f ",instance.getEndingOfBin(i));
            actualValues[i] = instance.getEndingOfBin(i);
        }
        
        //print expected values to user
        System.out.println("\nExpected: ");
        for (int i = 0; i < numberOfBins; i++) {
            System.out.printf("%f ",expectedNormalizedValues[i]);
        }
        Assert.assertArrayEquals(expectedNormalizedValues, actualValues, 0.0001);
    }

    
}

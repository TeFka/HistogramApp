/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statutils;

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
public class BasicStatisticsTest {
    
    private List<Double> exampleData;
            
    public BasicStatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        System.out.println("Tested data list: Range [-100; 100]");       
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        exampleData = new ArrayList<Double>();
        //Create array of data
        for (int i =-100; i <= 100; i++) {
            exampleData.add(new Double(i));
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calcMean method, of class BasicStatistics.
     */
    @Test
    public void testCalcMean() {
        System.out.println("calcMean");
        //set up and calculate the value
        BasicStatistics instance = new BasicStatistics(exampleData);
        instance.calcMean();
        
        double expectedValue = 0.0;
        System.out.printf("Data Mean: %f, expected: %f\n", instance.getMean(), expectedValue);
        //Compare expacted value with calculated values
        Assert.assertEquals(expectedValue, instance.getMean(), 0);
    }

    /**
     * Test of calcVariance method, of class BasicStatistics.
     */
    @Test
    public void testCalcVariance() {
        System.out.println("calcVariance");
        //set up and calculate the value
        BasicStatistics instance = new BasicStatistics(exampleData);
        instance.calcVariance();
        
        double expectedValue = 3383.5;
        System.out.printf("Data Variance: %f, expected: %f\n", instance.getVariance(), expectedValue);
        //Compare expacted value with calculated values
        Assert.assertEquals(expectedValue, instance.getVariance(), 0);
    }

    /**
     * Test of calcMax method, of class BasicStatistics.
     */
    @Test
    public void testCalcMax() {
        System.out.println("calcMax");
        //set up and calculate the value
        BasicStatistics instance = new BasicStatistics(exampleData);
        instance.calcMax();
        
        double expectedValue = 100.0;
        System.out.printf("Data Max: %f, expected: %f\n", instance.getMax(), expectedValue);
        //Compare expacted value with calculated values
        Assert.assertEquals(expectedValue, instance.getMax(), 0);
    }

    /**
     * Test of calcMin method, of class BasicStatistics.
     */
    @Test
    public void testCalcMin() {
        System.out.println("calcMin");
        //set up and calculate the value
        BasicStatistics instance = new BasicStatistics(exampleData);
        instance.calcMin();
        
        double expectedValue = -100.0;
        System.out.printf("Data Min: %f, expected: %f\n", instance.getMin(), expectedValue);
        //Compare expacted value with calculated values
        Assert.assertEquals(expectedValue, instance.getMin(), 0);
    }

    /**
     * Test of calcMedian method, of class BasicStatistics.
     */
    @Test
    public void testCalcMedian() {
        System.out.println("calcMedian");
        //set up and calculate the value
        BasicStatistics instance = new BasicStatistics(exampleData);
        instance.calcMedian();
        
        double expectedValue = 1.0;
        System.out.printf("Data Median: %f, expected: %f\n", instance.getMedian(), expectedValue);
        //Compare expacted value with calculated values
        Assert.assertEquals(expectedValue, instance.getMedian(), 0);
    }

    /**
     * Test of calcStandartDeviation method, of class BasicStatistics.
     */
    @Test
    public void testCalcStandartDeviation() {
        System.out.println("calcStandartDeviation");
        //set up and calculate the value
        BasicStatistics instance = new BasicStatistics(exampleData);
        instance.calcStandartDeviation();
        
        
        double expectedValue = 58.16786054171152;
        System.out.printf("Data Standart Deviation: %f, expected: %f\n", instance.getStandartDeviation(), expectedValue);
        //Compare expacted value with calculated values
        Assert.assertEquals(expectedValue, instance.getStandartDeviation(), 0);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import binmethod.SquareRootChoice;
import java.util.List;
import java.util.ArrayList;

import statutils.BasicStatistics;
import statutils.BinStatistics;
import statutils.StatsDataModification;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class Unit_test_statutils {
    
    @Test
    public void mainTest() {
        
        //Create array of data
        List<Double> exampleData = new ArrayList<Double>();
        for (int i =-100; i <= 100; i++) {
            exampleData.add(new Double(i));
        }
        
        //create basicStats class instance
        BasicStatistics basicStatsTest = new BasicStatistics(exampleData);
        
        //calcuclate all basic statistical values
        basicStatsTest.calcMean();
        basicStatsTest.calcVariance();
        basicStatsTest.calcMax();
        basicStatsTest.calcMin();
        basicStatsTest.calcMedian();
        basicStatsTest.calcStandartDeviation();   
        
        //print the statistical values to user
        System.out.printf("Data mean: %f, expected: %f\n", basicStatsTest.getMean(), 0.0);
        System.out.printf("Data variance: %f, expected: %f\n", basicStatsTest.getVariance(), 3383.5);
        System.out.printf("Data max: %f, expected: %f\n", basicStatsTest.getMax(), 100.0);
        System.out.printf("Data min: %f, expected: %f\n", basicStatsTest.getMin(), -100.0);
        System.out.printf("Data median: %f, expected: %f\n", basicStatsTest.getMedian(), 1.0);
        System.out.printf("Data standart deviation: %.14f, expected: %f\n", basicStatsTest.getStandartDeviation(), 58.16786054171152);
        
        //perform tests by comparing expected and calcuclated values
        Assert.assertEquals(0, basicStatsTest.getMean(), 0);
        Assert.assertEquals(3383.5, basicStatsTest.getVariance(), 0);
        Assert.assertEquals(100, basicStatsTest.getMax(), 0);
        Assert.assertEquals(-100, basicStatsTest.getMin(), 0);
        Assert.assertEquals(1, basicStatsTest.getMedian(), 0);
        Assert.assertEquals(58.16786054171152, basicStatsTest.getStandartDeviation(), 0);
        
        //use Sturge's formula to calculate number of bins
        SquareRootChoice SquareRootInstance = new SquareRootChoice(exampleData);
        SquareRootInstance.calculateNumberOfBins();
        System.out.printf("By Square Root Choice: %d \n", SquareRootInstance.getNumberOfBins());
        
        //create BinStats class instance
        BinStatistics binDataTest = new BinStatistics(exampleData,SquareRootInstance.getNumberOfBins());
        
        //calculate values in bins
        binDataTest.calcSamplesInBins();
        
        //specifiy expected numbers of samples in bins
        double[] expectedValues = new double[]{15, 14, 14, 15, 14, 14, 14, 15, 14, 14, 15, 14, 14, 14};
        System.out.printf("Excpected Bin Data:\n");
        for (int i = 0; i < SquareRootInstance.getNumberOfBins(); i++) {
            System.out.printf("%f ",expectedValues[i]);
        }
        
        //specifiy retrieved numbers of samples in bins
        System.out.printf("\nBin Data:\n");
        double[] actualValues = new double[SquareRootInstance.getNumberOfBins()];
        
        for (int i = 0; i < SquareRootInstance.getNumberOfBins(); i++) {
            System.out.printf("%f ",binDataTest.getSampleNumInBin(i));
            actualValues[i] = binDataTest.getSampleNumInBin(i);
        }
        
        //perform test by comparing all values in expected and calcuclated arrays
        Assert.assertArrayEquals(expectedValues, actualValues, 0.0001);
       
        //retrieve normalised data from the mentioned bin data
        List<Double> testNormalisedBinData = StatsDataModification.getNormalisedData(binDataTest.getBinData(), basicStatsTest.getMax(), basicStatsTest.getMin());    
        
        //define expected normalized values
        double[] expectedNormalizedValues = new double[]{0.005250, 0.004900, 0.004900, 0.005250, 0.004900, 0.004900, 0.004900, 0.005250, 0.004900, 0.004900, 0.005250, 0.004900, 0.004900, 0.004900};
        System.out.printf("\nExcpected Normalized Bin Data:\n");
        //specifiy expected normalized values
        for (int i = 0; i < SquareRootInstance.getNumberOfBins(); i++) {
            System.out.printf("%f ",expectedNormalizedValues[i]);
        }
        
        System.out.printf("\nNormalised Data:\n");
        
        double[] actualNormalizedValues = new double[SquareRootInstance.getNumberOfBins()];
        
        //specifiy calculated normalized values and also save them into a separate list to allow comparison (comparison function required double[] array)
        for (int i = 0; i < SquareRootInstance.getNumberOfBins(); i++) {
            System.out.printf("%f ", testNormalisedBinData.get(i)); 
            actualNormalizedValues[i] = testNormalisedBinData.get(i);
        }
        
        //perform test by comparing all values in expected and calcuclated arrays
        Assert.assertArrayEquals(expectedNormalizedValues, actualNormalizedValues, 0.0000000001);
        
    }
    
}

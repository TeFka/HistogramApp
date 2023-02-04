/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statutils;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class StatsDataModificationTest {
    
    public StatsDataModificationTest() {
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
     * Test of getNormalisedData method, of class StatsDataModification.
     */
    @Test
    public void testGetNormalisedData() {
        System.out.println("getNormalisedData");
        List<Double> exampleData = new ArrayList<Double>(Arrays.asList(15.0, 14.0, 14.0, 15.0, 14.0, 14.0, 14.0, 15.0, 14.0, 14.0, 15.0, 14.0, 14.0, 14.0));
        double maximumVal = 100;
        double minimumVal = -100;
        double[] expectedNormalizedValues = new double[]{0.005250, 0.004900, 0.004900, 0.005250, 0.004900, 0.004900, 0.004900, 0.005250, 0.004900, 0.004900, 0.005250, 0.004900, 0.004900, 0.004900};
        List<Double> result = StatsDataModification.getNormalisedData(exampleData, maximumVal, minimumVal);
        
        System.out.printf("Tested Data list (From 'BasicStatisticsTest'): (");
        for(int i = 0;i<exampleData.size();i++){
            
             System.out.printf("%.1f ", exampleData.get(i));
            
        }
        
        System.out.printf("\nExcpected Normalized Bin Data:\n");
        //specifiy expected normalized values
        for (int i = 0; i < exampleData.size(); i++) {
            System.out.printf("%f ",expectedNormalizedValues[i]);
        }
        
        System.out.printf("\nNormalised Data:\n");
        double[] actualNormalizedValues = new double[result.size()];
        //specifiy calculated normalized values and also save them into a separate list to allow comparison (comparison function required double[] array)
        for (int i = 0; i < result.size(); i++) {
            System.out.printf("%f ", result.get(i)); 
            actualNormalizedValues[i] = result.get(i);
        }

        //perform test by comparing all values in expected and calcuclated arrays
        assertArrayEquals(expectedNormalizedValues, actualNormalizedValues, 0.0000000001);
    }
    
}

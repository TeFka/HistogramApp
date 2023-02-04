
package binmethod;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
Test class RiceRuleTest
Class is used to test relevant functions of the
tested RiceRule class
*/
public class RiceRuleTest {
    
    public RiceRuleTest() {
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
     * Test of calculateNumberOfBins method, of class RiceRule.
     */
    @Test
    public void testCalculateNumberOfBins() {
        
        //Create array of data
        List<Double> exampleData = Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11.);
        
        //test RiceRule class
        System.out.printf("Tested Data list: (");
        for(int i = 0;i<exampleData.size();i++){
            
             System.out.printf("%.1f ", exampleData.get(i));
            
        }
        System.out.printf(")");
        //print expected value to user
        System.out.printf("Expected value for Rice Rule: %d \n", 4);
        
        //Use rice rule  to get number of bins
        RiceRule RiceRuleInstance = new RiceRule(exampleData);
        RiceRuleInstance.calculateNumberOfBins();
        System.out.printf("By Rice Rule Formula: %d \n", RiceRuleInstance.getNumberOfBins());
        
        //perform test by comparing expected and calcuclated values
        Assert.assertEquals(4, RiceRuleInstance.getNumberOfBins(), 0);
    }
    
}

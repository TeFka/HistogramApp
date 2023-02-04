/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Admin
 */
public class SquareRootChoiceTest {
    
    public SquareRootChoiceTest() {
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
     * Test of calculateNumberOfBins method, of class SquareRootChoice.
     */
    @Test
    public void testCalculateNumberOfBins() {
        
        //Create array of data
        List<Double> exampleData = Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11.);
        
        //test SquareRootChoice class
        System.out.printf("Tested Data list: (");
        for(int i = 0;i<exampleData.size();i++){
            
             System.out.printf("%.1f ", exampleData.get(i));
            
        }
        System.out.printf(")");
        //print expected value to user
        System.out.printf("Expected value for Square Root Choice: %d \n", 3);
        
        //Use square root choice to get number of bins
        SquareRootChoice SquareRootChoiceInatnce = new SquareRootChoice(exampleData);
        SquareRootChoiceInatnce.calculateNumberOfBins();
        System.out.printf("By Square Root Formula: %d \n", SquareRootChoiceInatnce.getNumberOfBins());
        
        //perform test by comparing expected and calcuclated values
        Assert.assertEquals(3, SquareRootChoiceInatnce.getNumberOfBins(), 0);
    }
    
}

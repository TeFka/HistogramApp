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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class BinFormulaeTest {
    
    public BinFormulaeTest() {
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
     * Test of getData method, of class BinFormulae.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        List<Double> expResult = Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11.);
        BinFormulaeImpl instance = new BinFormulaeImpl(expResult);
        List<Double> result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataPoint method, of class BinFormulae.
     */
    @Test
    public void testGetDataPoint() {
        System.out.println("getDataPoint");
        int index = 0;
        List<Double> exampleData = Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11.);
        BinFormulaeImpl instance = new BinFormulaeImpl(exampleData);
        double expResult = 5;
        double result = instance.getDataPoint(4);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class BinFormulae.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        List<Double> newData = null;
        BinFormulae instance = null;
        instance.setData(newData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataPoint method, of class BinFormulae.
     */
    @Test
    public void testSetDataPoint() {
        System.out.println("setDataPoint");
        Double newValue = null;
        int index = 0;
        BinFormulae instance = null;
        instance.setDataPoint(newValue, index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfBins method, of class BinFormulae.
     */
    @Test
    public void testGetNumberOfBins() {
        System.out.println("getNumberOfBins");
        BinFormulae instance = null;
        int expResult = 0;
        int result = instance.getNumberOfBins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateNumberOfBins method, of class BinFormulae.
     */
    @Test
    public void testCalculateNumberOfBins() {
        System.out.println("calculateNumberOfBins");
        BinFormulae instance = null;
        instance.calculateNumberOfBins();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class BinFormulaeImpl extends BinFormulae {

        public BinFormulaeImpl(List<Double> data) {
            super(data);
        }

        public void calculateNumberOfBins() {
            
            
            
        }
    }
    
}

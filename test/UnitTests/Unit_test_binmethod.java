/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import java.util.List;
import java.util.Arrays;

import binmethod.SturgesFormula;
import binmethod.SquareRootChoice;
import binmethod.RiceRule;

import org.junit.Assert;
import org.junit.Test;
/**
 *
 * @author Admin
 */
public class Unit_test_binmethod {
    
    @Test
    public void mainTest() {
        
        //Create array of data
        List<Double> exampleData = Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11.);
        
        //test SturgesFormula class
        
        //print expected value to user
        System.out.printf("Expeced value for Sturge's formula rule: %d \n", 4);
        
        //Use Sturge's formula to get number of bins
        SturgesFormula SturgesInstance = new SturgesFormula(exampleData);
        SturgesInstance.calculateNumberOfBins();
        System.out.printf("By Sturges Formula: %d \n", SturgesInstance.getNumberOfBins());
        
        //perform test by comparing expected and calcuclated values
        Assert.assertEquals(4, SturgesInstance.getNumberOfBins(), 0);
        
        //test SquareRootChoice class
        
        //print expected value to user
        System.out.printf("Expeced value for Square Root Choice: %d \n", 4);
        
        //Use square root choice to get number of bins
        SquareRootChoice SquareRootChoiceInatnce = new SquareRootChoice(exampleData);
        SquareRootChoiceInatnce.calculateNumberOfBins();
        System.out.printf("By Square Root Formula: %d \n", SquareRootChoiceInatnce.getNumberOfBins());
        
        //perform test by comparing expected and calcuclated values
        Assert.assertEquals(3, SquareRootChoiceInatnce.getNumberOfBins(), 0);
        
        //test RiceRule class
        
        //print expected value to user
        System.out.printf("Expeced value for Rice Rule: %d \n", 4);
        
        //Use rice rule  to get number of bins
        RiceRule RiceRuleInstance = new RiceRule(exampleData);
        RiceRuleInstance.calculateNumberOfBins();
        System.out.printf("By Rice Rule Formula: %d \n", RiceRuleInstance.getNumberOfBins());
        
        //perform test by comparing expected and calcuclated values
        Assert.assertEquals(4, RiceRuleInstance.getNumberOfBins(), 0);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas
 */
public class BigNumberTest {
    
    public BigNumberTest() {
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
     * Test of getInteger method, of class BigNumber.
     */
    @Test
    public void testGetInteger() {
        System.out.println("getInteger");
        BigNumber instance = new BigNumber("10", 10);
        BigInteger expResult = new BigInteger("10", 10);
        BigInteger result = instance.getInteger();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of getFraction method, of class BigNumber.
     */
    @Test
    public void testGetFraction() {
        System.out.println("getFraction");
        BigNumber instance = new BigNumber(".10", 10);
        BigInteger[] expResult = new BigInteger[]{BigInteger.ONE, BigInteger.TEN};
        BigInteger[] result = instance.getFraction();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of divide method, of class BigNumber.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        BigNumber other = new BigNumber("3", 10);
        BigNumber instance = new BigNumber("1", 10);
        BigNumber expResult = new BigNumber(".3333333333333333333333333333333333", 10);
        BigNumber result = instance.divide(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of multiply method, of class BigNumber.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        BigNumber other = new BigNumber("2", 10);
        BigNumber instance = new BigNumber("1", 10);
        BigNumber expResult = new BigNumber("2", 10);
        BigNumber result = instance.multiply(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of subtract method, of class BigNumber.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        BigNumber other = new BigNumber("1", 10);
        BigNumber instance = new BigNumber("2", 10);
        BigNumber expResult = new BigNumber("1", 10);
        BigNumber result = instance.subtract(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class BigNumber.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        BigNumber other = new BigNumber("1", 10);
        BigNumber instance = new BigNumber("1", 10);
        BigNumber expResult = new BigNumber("2", 10);
        BigNumber result = instance.add(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of mod method, of class BigNumber.
     */
    @Test
    public void testMod() {
        System.out.println("mod");
        BigNumber other = new BigNumber("2", 10);
        BigNumber instance = new BigNumber("5", 10);
        BigNumber expResult = new BigNumber("1", 10);
        BigNumber result = instance.mod(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of xnor method, of class BigNumber.
     */
    @Test
    public void testXnor() {
        System.out.println("xnor");
        BigNumber other = new BigNumber("10", 10);
        BigNumber instance = new BigNumber("15", 10);
        BigNumber expResult = new BigNumber("-6", 10);
        BigNumber result = instance.xnor(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of nor method, of class BigNumber.
     */
    @Test
    public void testNor() {
        System.out.println("nor");
        BigNumber other = new BigNumber("10", 10);
        BigNumber instance = new BigNumber("15", 10);
        BigNumber expResult = new BigNumber("-16", 10);
        BigNumber result = instance.nor(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of nand method, of class BigNumber.
     */
    @Test
    public void testNand() {
        System.out.println("nand");
        BigNumber other = new BigNumber("10", 10);
        BigNumber instance = new BigNumber("15", 10);
        BigNumber expResult = new BigNumber("-11", 10);
        BigNumber result = instance.nand(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of not method, of class BigNumber.
     */
    @Test
    public void testNot() {
        System.out.println("not");
        BigNumber instance = new BigNumber("10", 10);
        BigNumber expResult = new BigNumber("-11", 10);
        BigNumber result = instance.not();
        assertEquals(expResult, result);
    }

    /**
     * Test of xor method, of class BigNumber.
     */
    @Test
    public void testXor() {
        System.out.println("xor");
        BigNumber other = new BigNumber("10", 10);
        BigNumber instance = new BigNumber("15", 10);
        BigNumber expResult = new BigNumber("5", 10);
        BigNumber result = instance.xor(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of and method, of class BigNumber.
     */
    @Test
    public void testAnd() {
        System.out.println("and");
        BigNumber other = new BigNumber("10", 10);
        BigNumber instance = new BigNumber("15", 10);
        BigNumber expResult = new BigNumber("10", 10);
        BigNumber result = instance.and(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of or method, of class BigNumber.
     */
    @Test
    public void testOr() {
        System.out.println("or");
        BigNumber other = new BigNumber("10", 10);
        BigNumber instance = new BigNumber("15", 10);
        BigNumber expResult = new BigNumber("15", 10);
        BigNumber result = instance.or(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class BigNumber.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        BigNumber instance = new BigNumber("10", 10);
        String expResult = "10 0/1";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}

/* Copyright (C) John Celona, Vivian Murga, Brittney Pope, Michael McKenzie, Nick Buehre,
 * John Mask, and Ryan Hutchinson - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by John Celona, Vivian Murga, Brittney Pope, Michael McKenzie, Nick Buehre,
 * John Mask, and Ryan Hutchinson, November 2016
 */
package Testing.Package;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author author
 */
public final class PuzzleTest {

    // Declare variables
    

    @BeforeClass
    public static void setUpClass() {
        System.out.println("@BeforeClass: setUpClass()");
        
        // initialize
        
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("@AfterClass: tearDownClass()");
        
        // assign variables, null
        
    }

    @Before
    public void setUp() {
        System.out.println("Testing a test now.");
    }

    @After
    public void tearDown() {
        System.out.println("Ended a test.");
    }

    /**
     * Test
     */
    @Test
    public void testPuzzle() {
        
        //
        
    } // end testPuzzle
    
    /**
     * Test
     */
    @Test
    public void testGetPanel() {
        
        //
        
    } // end testGetPanel

}

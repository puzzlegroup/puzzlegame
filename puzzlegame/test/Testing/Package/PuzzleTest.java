/* Copyright (C) John Celona, Vivian Murga, Brittney Pope, Michael McKenzie, Nick Buehre,
 * John Mask, and Ryan Hutchinson - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by John Celona, Vivian Murga, Brittney Pope, Michael McKenzie, Nick Buehre,
 * John Mask, and Ryan Hutchinson, November 2016
 */
package Testing.Package;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import puzzlegame.Puzzle;

/**
 *
 * @author author
 */
public final class PuzzleTest {

    // Declare variables
    private static ObjectMapper mapper;
    private static Puzzle puzzle;
    private static String puzzleKeyName;
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("@BeforeClass: setUpClass()");

        // initialize
        mapper = new ObjectMapper();
        puzzle = new Puzzle();
        puzzleKeyName = "puzzledatatest"; // holds puzzledatatest json
        
        try {
            puzzle = mapper.readValue(puzzle.readData(puzzleKeyName), Puzzle.class);
        } catch (IOException ex) {
            Logger.getLogger(PuzzleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("@AfterClass: tearDownClass()");

        // assign variables, null
        mapper = null;
        puzzle = null;
        puzzleKeyName = null;
        
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
    /*@Test
    public void testGetName() {

        // declare and initilize test variables
        String expected = "Test Puzzle";
        String result = "";
        
        result = puzzle.getName();
        System.out.println("@Test testPuzzle(): expected = '" + expected + "', " + "result = '" + result + "'");
        assertEquals(expected, result);
        
    } // end testGetName
    */
    /**
     * Test
     */
    @Test
    public void testGetPanel() {

        //
        
    } // end testGetPanel

}

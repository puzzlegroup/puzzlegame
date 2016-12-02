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
import javax.swing.JPanel;
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

        // initialize variables
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
    @Test
    public void testReadData() {

        // declare and initialize variables
        String expected = "{    \"name\": \"Test Puzzle\",        \"puzzleKeyName\": \"puzzledatatest\",        \"topHeaders\": [\"owners\",\"breeds\"],        \"leftHeaders\": [\"years\", \"dogs\"],        \"topLabels\": [\"Anita\", \"Barbara\", \"Douglas\", \"Fernando\", \"Ginger\", \"Beagle\", \"Bulldog\", \"Chow Chow\", \"Great Dane\", \"Maltese\"],        \"leftLabels\": [\"2006\", \"2007\", \"2008\", \"2009\", \"2010\",\"Max\", \"Molly\", \"Riley\", \"Samson\", \"Shadow\"],        \"dialog\": \"Each dog owner has a name and owns one dog. Each dog has a name \\nand is an unique breed. The years represent when the owner first \\naquired the dog.\",        \"clues\": [\"Anita bought Riley in 2008 and he is a Beagle.\",              \"Barbara adopted Samson in 2006 and he is a Great Dane.\",              \"Douglas found Shadow in 2009 and she is a Bulldog.\",              \"Fernando bought Max, a Chow Chow, in 2007.\",              \"Ginger had Molly the Maltese, since 2010.\"],        \"hints\": [\"Ginger owns Molly.\",              \"Barbara has a Great Dane.\",              \"Shadow was found in 2009.\"],        \"answerMatrix1\": [[0,1,0,0,0],                      [0,0,0,1,0],                      [1,0,0,0,0],                      [0,0,1,0,0],                      [0,0,0,0,1]],                      \"answerMatrix2\": [[0,0,0,1,0],                      [0,0,1,0,0],                      [1,0,0,0,0],                      [0,1,0,0,0],                      [0,0,0,0,1]],        \"answerMatrix3\": [[0,0,0,1,0],                      [0,0,0,0,1],                      [1,0,0,0,0],                      [0,1,0,0,0],                      [0,0,1,0,0]]}";
        String result = "";
        
        // call readData and get json data
        result = puzzle.readData(puzzleKeyName);
        
        System.out.println("@Test testReadData(): \nexpected = " + expected + ", \n" + "result   = " + result );
        
        assertEquals(expected, result);
        
        expected = null;
        result = null;
        
    } // end testReadData
    
    
    /**
     * Test
     */
    @Test
    public void testGetPanel() {

        // declare and initialize
        String expected = "javax.swing.JPanel";
        String result = null;
        JPanel jpanel = null;
        
        // call getPanel and assign to result
        jpanel = puzzle.getPanel();
        result = jpanel.getClass().getName();
        
        System.out.println("@Test testGetPanel(): expected = " + expected + ", result = " + result);
        assertEquals(expected, result);
        
        expected = null;
        result = null;
        jpanel = null;
    } // end testGetPanel
    
    
}

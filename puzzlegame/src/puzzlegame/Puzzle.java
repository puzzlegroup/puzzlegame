/* Copyright (C) John Celona, Vivian Murga, Brittney Pope, Michael McKenzie, Nick Buehre,
 * John Mask, and Ryan Hutchinson - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by John Celona, Vivian Murga, Brittney Pope, Michael McKenzie, Nick Buehre,
 * John Mask, and Ryan Hutchinson, November 2016
 */

// Import package
package puzzlegame;

// Import classes
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;

// Puzzle class for use in PuzzleGUI
public class Puzzle {

	// Declare puzzle variables
        @JsonProperty("name") // jackson annotation
	private String name = "*level screen*";
        @JsonProperty("puzzleKeyName")
	private String puzzleKeyName;
        @JsonProperty("clues")
	private String[] clues;
        @JsonProperty("hints")
	private String[] hints;
        @JsonProperty("topLabels")
	private String[] topLabels;
        @JsonProperty("leftLabels")
	private String[] leftLabels;
        @JsonProperty("topHeaders")
	private String[] topHeaders;
        @JsonProperty("leftHeaders")
	private String[] leftHeaders;
        @JsonProperty("answerMatrix1")
	private int[][] answerMatrix1 = new int[5][5];
        @JsonProperty("answerMatrix2")
	private int[][] answerMatrix2 = new int[5][5];
        @JsonProperty("answerMatrix3")
	private int[][] answerMatrix3 = new int[5][5];
	@JsonProperty("dialog")
        private String dialog;
        
	// Declare utility variables
	private JTable[] tables = new JTable[3];
	private long startTime;
	private long finishTime;
	private double totalTime;
	
	// Declare GUI variables
	private JPanel panel = new JPanel();
	private Border border;
	private JButton submit;
	private JButton restart;
	
	// no arg constructor
        public Puzzle() {
            
        }
	
	// Parameter constructor
	public Puzzle(String name) {
		
		// Read in data
		readData(name);
	}

	// Method for reading in puzzle data
	public String readData(String fileName) {
		
		//panel.add(new JLabel(name)); // TEMPORARY
                
                // read in the selected file name as a string
                String jsonString = ""; // holds the entire json file as a string
                String fileLine;
                BufferedReader inputStream = null;
                
            try {
                inputStream = new BufferedReader(new FileReader("src/puzzlegame/puzzles/" + fileName + ".json"));
                while ((fileLine = inputStream.readLine()) != null) {
                    jsonString += fileLine;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return jsonString;
	}

        // Method for setting up panel components
        public void setUpPanel() {
        
            panel.add(new JLabel(name)); // TEMPORARY
            
        }
        
	// Method for creating a new table
	private JTable newTable() {
		
		
		return null;
	}

	// Method for giving user a hint
	private void giveHint() {
	
		
		
	}
	
	// Method for checking user's answers
	private int checkAnswers() {
		

		return 0;
	}

	// Method for restarting puzzle
	private void restartPuzzle() {
		
		
		
	}
	
	// Method for getting puzzle screen
	public JPanel getPanel() {
		
		return panel;
	}
	
        
        // Getters for each field
        // Method for getting puzzle name
        public String getName() {
            
            return this.name;
        }
    
}

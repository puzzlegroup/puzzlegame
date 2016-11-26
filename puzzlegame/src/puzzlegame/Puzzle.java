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
import javax.swing.*;
import javax.swing.border.*;

// Puzzle class for use in PuzzleGUI
public class Puzzle {

	// Declare puzzle variables
	private String name = "*Level Screen*";
	private String[] clues;
	private String[] hints;
	private String[] topLabels;
	private String[] leftLabels;
	private String[] topHeaders;
	private String[] leftHeaders;
	private int[][] answerMatrix1 = new int[5][5];
	private int[][] answerMatrix2 = new int[5][5];
	private int[][] answerMatrix3 = new int[5][5];
	
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
	
	
	
	// Parameter constructor
	public Puzzle(String name) {
		
		// Read in data
		readData(name);
	}

	// Method for reading in puzzle data
	public void readData(String fileName) {
		
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
	
}

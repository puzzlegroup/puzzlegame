/* Copyright (C) John Celona, Vivian Murga, Brittney Pope, Michael McKenzie, Nick Buehre,
 * John Mask, and Ryan Hutchinson - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by John Celona, Vivian Murga, Brittney Pope, Michael McKenzie, Nick Buehre,
 * John Mask, and Ryan Hutchinson, November-December 2016
 */

// Import package
package puzzlegame;

// Import classes
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import com.fasterxml.jackson.annotation.*;

// Puzzle class for use in PuzzleGUI
public final class Puzzle {

	// Declare puzzle variables
	@JsonProperty("name")
	private String name;
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
	private double totalTime = 0.0;
	private int stars;
	private int hintCounter;

	// Declare GUI variables
	private JPanel panel = new JPanel();
	private Border border;
	private JTextArea textArea;

	// Have JSON ignore buttons
	@JsonIgnore
	private JButton back;
	private JButton hint;
	private JButton submit;
	private JButton restart;



	// Default constructor
	public Puzzle() {}

	// Method for reading in puzzle data
	public String readData(String fileName) {

		// Declare and initialize file string variables
		String fileLine;
		String jsonString = "";
		String fileLocation = "src/puzzlegame/puzzles/" + fileName + ".json";

		// Attempt to read specified file as string
		try {

			// Create new buffered reader at the files location
			BufferedReader inputStream = new BufferedReader(new FileReader(fileLocation));

			// Read entire input file into a single string 
			while ((fileLine = inputStream.readLine()) != null)
				jsonString += fileLine;

			// Close input stream
			inputStream.close();

		}
		// Handle errors if they occur
		catch (Exception e) {

			// Output error message
			JOptionPane.showMessageDialog(null, "Error reading JSON file",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		return jsonString;
	}

	// Method for creating a new table
	private JTable newTable() {

		// Declare and initialize table variables
		int size = 5 ;
		int cellWidth = 32;
		int cellHeight = 30;
		int borderWidth = 4;
		String correct = " O";
		String incorrect = " X";
		Color borderColor = Color.GRAY;
		Font font = new Font(null, Font.BOLD, 25);

		// Create new table model
		DefaultTableModel model = new DefaultTableModel(size, size) {

			// Set cells to not editable
                        @Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}
		};

		// Create table
		JTable newTable = new JTable(model);



		// Set cell height
		newTable.setRowHeight(cellHeight);
		newTable.setCellSelectionEnabled(false);

		// Set cell width
		for (int i = 0; i < size; i++)
			newTable.getColumnModel().getColumn(i).setPreferredWidth(cellWidth);

		// Set table border
		border = BorderFactory.createMatteBorder(borderWidth,
				borderWidth, borderWidth, borderWidth, borderColor);
		newTable.setBorder(border);

		// Set table font
		newTable.setFont(font);



		// Set mouse click action
		newTable.addMouseListener(new MouseAdapter() {

			// Create mouse click method
                        @Override
			public void mouseClicked(MouseEvent event) {

				// Get selected cell
				int row = newTable.getSelectedRow();
				int column = newTable.getSelectedColumn();

				// Look at selected cell contents
				switch(String.valueOf(newTable.getValueAt(row, column))) {

				case " X":

					// Check cross values
					for(int i = 0; i < size; i++)
						for(int j = 0; j < size; j++)
							if((i == row || j == column)) {

								try{

									// Check if value is marked as correct
									if(newTable.getValueAt(i, j).equals(correct)) {

										// Output help dialog
										JOptionPane.showMessageDialog(null,
												"Only one box can be marked\n"
														+ "correct in any row or column.\n"
														+ "Remove the existing correct value\n"
														+ "before setting the new one.\n");
										return;
									}
								}
								catch(Exception error){}

							}

					// Set cross values to incorrect
					for(int i = 0; i < size; i++)
						for(int j = 0; j < size; j++)
							if(i == row || j == column)
								newTable.setValueAt(incorrect, i, j);

					// Set selected value to correct
					newTable.setValueAt(correct, row, column);

					break;

				case " O":

					// Set cross values to null
					for(int i = 0; i < size; i++)
						for(int j = 0; j < size; j++)
							if(i == row || j == column)
								newTable.setValueAt(null, i, j);

					break;

				default:

					// Set selected value to incorrect
					newTable.setValueAt(incorrect, row, column);
					break;

				}

			}

		});

		// Output table
		return newTable;
	}

	// Method for giving user a hint
	private void giveHint(JTextArea textArea) {

		// Create newline constant
		final String newlines = "\n\n";

		// Add puzzle hints to textArea
		if(hintCounter <= hints.length-1) {
			textArea.append(hints[hintCounter] + newlines);
			hintCounter++;
                        totalTime += 20.0; // add 20 seconds to total time
		} else
			JOptionPane.showMessageDialog(null, "There are no more available hints.");
	}

	// Method for checking user's answers
	private void checkAnswers(int levelIndex, JLabel[][] levelStars) {

		// Declare and initialize variables
		int size = tables[0].getRowCount();
		int correctAnswers = 0;
                
                // initialize times
                finishTime = System.nanoTime(); // get finishTime
                totalTime += (finishTime - startTime) / (double) 1_000_000_000; // get totalTime in seconds as a double
                    
		// Create consolidated answer matrix
		int[][][] answers = {answerMatrix1, answerMatrix2, answerMatrix3};

		// Check every value in every matrix
                try {
		for(int i = 0; i < tables.length; i++)
			for(int j = 0; j < size; j++)
				for(int k = 0; k < size; k++) 
					if(answers[i][j][k] == 1 && tables[i].getValueAt(j, k).equals(" O"))
						correctAnswers++;
                                
                } catch (Exception e) {
                    // do nothing
                }

		// Check number of correct answers
		switch(correctAnswers) {

		case 0: case 1: case 2: case 3: case 4:
			stars = 0;
			break;

		case 5: case 6: case 7: case 8: case 9:
			stars = 1;
			break;

		case 10: case 11: case 12: case 13: case 14:
			stars = 2;
			break;

		case 15:
			stars = 3;
			break;

		}

                // Check time for stars
                if(stars == 3) { // only if the puzzle is completely solved do you get extra stars for time
                    if(totalTime <= 2*60.0) { // 2 minutes or less
                        stars += 3;
                    } else  if(totalTime <= 3*60.0) { // 3 minutes or less
                        stars += 2;
                    } else if(totalTime <= 4*60.0) { //  4 minutes or less
                        stars += 1;
                    } else { // more than 4 minutes
                        // no stars
                    }
                }
            
                
                // update levelStars array
                for(int i = 0; i < stars; i++) {
                    levelStars[levelIndex][i].setVisible(true);
                }
	}

	// Method for restarting puzzle
	private void restartPuzzle() {

		// Declare and initialize table size
		int size = tables[0].getRowCount();

		// Reset every value in every table
		for(int i = 0; i < tables.length; i++)
			for(int j = 0; j < size; j++)
				for(int k = 0; k < size; k++)
					tables[i].setValueAt("", j, k);

		// Reset timer
		startTime = System.nanoTime();
	}

	// Method for initializing puzzle screen
	public void initializePanel(int levelIndex, JLabel[][] levelStars) {

		// Declare and initialize GUI variables
		int buttonWidth = 120;
		int buttonHeight = 35;
		int buttonSeperation = 10;
		int tableSeperation = -4;
		int textWidth = 25;
		int textHeight = 26;
		Font font = new Font(null, Font.BOLD, 16);

		// Declare and initialize titles
		String backTitle = "Back";
		String hintTitle = "Hint";
		String restartTitle = "Restart";
		String submitTitle = "Submit";

		// Declare and initialize layout variables
		String top = SpringLayout.NORTH;
		String bottom = SpringLayout.SOUTH;
		String left = SpringLayout.WEST;
		String right = SpringLayout.EAST;

		// Declare and initialize image background
		String image = "src/puzzlegame/images/puzzleImg.jpg";
		JLabel background = new JLabel(new ImageIcon(image));

		// Customize GUI elements
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);



		// Create back button
                ImageIcon backIcon = new ImageIcon("src/puzzlegame/images/voyager-side.png");
                Image backImg = backIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
                backIcon = new ImageIcon(backImg);
		back = new JButton(backTitle);
                back.setIcon(backIcon);
                back.setHorizontalTextPosition(JButton.CENTER);
                back.setVerticalTextPosition(JButton.CENTER);
		back.setFont(font);
                back.setForeground(Color.white);
		back.setPreferredSize(buttonSize);
		panel.add(back);

		// Create hint button
                ImageIcon puzzleIcon = new ImageIcon("src/puzzlegame/images/enterprise.jpg");
                Image hintImg = puzzleIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
                puzzleIcon = new ImageIcon(hintImg);
		hint = new JButton(hintTitle);
                hint.setIcon(puzzleIcon);
                hint.setHorizontalTextPosition(JButton.CENTER);
                hint.setVerticalTextPosition(JButton.CENTER);
		hint.setFont(font);
                hint.setForeground(Color.black);
		hint.setPreferredSize(buttonSize);
		hint.addActionListener(event -> giveHint(textArea));
		panel.add(hint);

		// Create restart button
		restart = new JButton(restartTitle);
                restart.setIcon(puzzleIcon);
                restart.setHorizontalTextPosition(JButton.CENTER);
                restart.setVerticalTextPosition(JButton.CENTER);
		restart.setFont(font);
                restart.setForeground(Color.red);
		restart.setPreferredSize(buttonSize);
		restart.addActionListener(event -> restartPuzzle());
		panel.add(restart);

		// Create submit button
		submit = new JButton(submitTitle);
                submit.setIcon(puzzleIcon);
                submit.setHorizontalTextPosition(JButton.CENTER);
                submit.setVerticalTextPosition(JButton.CENTER);
		submit.setFont(font);
                submit.setForeground(Color.black);
		submit.setPreferredSize(buttonSize);
		submit.addActionListener(event -> {
                    checkAnswers(levelIndex, levelStars);
                    back.doClick();
                });
		panel.add(submit);

		// Create tables
		for(int i = 0; i < tables.length; i++) {

			tables[i] = newTable();
			panel.add(tables[i]);
		}

		// Initialize tables
		restartPuzzle();

		// set dimension of labels
		Dimension labelDim = new Dimension(100,30);
		Dimension labelDimVert = new Dimension(32,154);
		Dimension headerDimVert = new Dimension(32,150);
		Dimension headerDim = new Dimension(160,30);

		// Declare and initialize leftJLabels array
		JLabel[] leftJLabels = new JLabel[leftLabels.length];

		for(int i = 0; i < leftLabels.length; i++ ) {
			leftJLabels[i] = new JLabel(leftLabels[i]);
			leftJLabels[i].setOpaque(true);
			leftJLabels[i].setPreferredSize(labelDim);
			leftJLabels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
			panel.add(leftJLabels[i]);
		}

		// Declare and initialize topJLabels array
		JLabel[] topJLabels = new JLabel[topLabels.length];

		for(int i = 0; i < topLabels.length; i++ ) {
			topJLabels[i] = new JLabel(stringToHtml(topLabels[i]));
			topJLabels[i].setOpaque(true);
			topJLabels[i].setPreferredSize(labelDimVert);
			topJLabels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
			topJLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			topJLabels[i].setVerticalAlignment(SwingConstants.TOP);
			panel.add(topJLabels[i]);
		}

		// Declare and initialize left headers
		JLabel leftHeader0 = new JLabel(stringToHtml(leftHeaders[0]));
		JLabel leftHeader1 = new JLabel(stringToHtml(leftHeaders[1]));
		leftHeader0.setPreferredSize(headerDimVert);
		leftHeader1.setPreferredSize(headerDimVert);
		leftHeader0.setHorizontalAlignment(SwingConstants.CENTER);
		leftHeader1.setHorizontalAlignment(SwingConstants.CENTER);
		leftHeader0.setOpaque(true);
		leftHeader1.setOpaque(true);
		leftHeader0.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		leftHeader1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel.add(leftHeader0);
		panel.add(leftHeader1);

		// Declare and initialize top headers
		JLabel topHeader0 = new JLabel(topHeaders[0]);
		JLabel topHeader1 = new JLabel(topHeaders[1]);
		topHeader0.setPreferredSize(headerDim);
		topHeader1.setPreferredSize(headerDim);
		topHeader0.setHorizontalAlignment(SwingConstants.CENTER);
		topHeader1.setHorizontalAlignment(SwingConstants.CENTER);
		topHeader0.setOpaque(true);
		topHeader1.setOpaque(true);
		topHeader0.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		topHeader1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel.add(topHeader0);
		panel.add(topHeader1);


		// Create scrolling text area
		textArea = new JTextArea(textHeight, textWidth);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.BOLD, 15));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		// Add puzzle clues to text area
		final String newlines = "\n\n";
		textArea.append("\t" + name + newlines);
		textArea.append(dialog + newlines);
		for(int i = 0; i < clues.length; i++)
			textArea.append(clues[i] + newlines);
		panel.add(scrollPane);

		// Add background image
		panel.add(background);
		panel.setComponentZOrder(background, panel.getComponentCount() - 1);



		// Constrain back button
		layout.putConstraint(left, back, buttonSeperation, left, panel);
		layout.putConstraint(top, back, buttonSeperation, top, panel);

		// leftJLabels
		// Constraint first leftJLabels[0]
		layout.putConstraint(right, leftJLabels[0], 0, left, tables[0]);
		layout.putConstraint(top, leftJLabels[0], 150, bottom, back);

		// loop to put constraints leftJLabels 1-4
		for(int i = 1; i < leftJLabels.length/2; i++) {
			layout.putConstraint(right, leftJLabels[i], 0, left, tables[0]);
			layout.putConstraint(top, leftJLabels[i], 0, bottom, leftJLabels[i-1]);
		}

		// Constraint fifth leftJLabels[5]
		layout.putConstraint(right, leftJLabels[5], 0, left, tables[2]);
		layout.putConstraint(top, leftJLabels[5], 135+(32*5), bottom, back);

		// loop to put constraints leftJLabels 6-9
		for(int i = 6; i < leftJLabels.length; i++) {
			layout.putConstraint(right, leftJLabels[i], 0, left, tables[2]);
			layout.putConstraint(top, leftJLabels[i], 0, bottom, leftJLabels[i-1]);
		}
		// end leftJLabels

		// top labels
		// Constraint first topJLabels[0]
		layout.putConstraint(left, topJLabels[0], 0, left, tables[0]);
		layout.putConstraint(bottom, topJLabels[0], 0, top, tables[0]);

		// loop to put constraints topJLabels 1-4
		for(int i = 1; i < topJLabels.length/2; i++) {
			layout.putConstraint(left, topJLabels[i], 0, right, topJLabels[i-1]);
			layout.putConstraint(bottom, topJLabels[i], 0, top, tables[0]);
		}

		// Constraint fifth topJLabels[5]
		layout.putConstraint(left, topJLabels[5], 0, left, tables[1]);
		layout.putConstraint(bottom, topJLabels[5], 0, top, tables[1]);

		// loop to put constraints topJLabels 6-9
		for(int i = 6; i < topJLabels.length; i++) {
			layout.putConstraint(left, topJLabels[i], 0, right, topJLabels[i-1]);
			layout.putConstraint(bottom, topJLabels[i], 0, top, tables[1]);
		}
		// end top labels

		// Constrain scroll pane
		layout.putConstraint(left, scrollPane, buttonSeperation + 5, right, topJLabels[9]);
		layout.putConstraint(top, scrollPane, buttonSeperation, top, panel);

		// leftheader0
		layout.putConstraint(bottom, leftHeader0, 0 , bottom, tables[0]);
		layout.putConstraint(right, leftHeader0, 0 , left, leftJLabels[4]);

		// leftheader1
		layout.putConstraint(bottom, leftHeader1, 0 , bottom, tables[2]);
		layout.putConstraint(right, leftHeader1, 0 , left, leftJLabels[9]);

		// topheader0
		layout.putConstraint(left, topHeader0, 0 , left, tables[0]);
		layout.putConstraint(bottom, topHeader0, 0 , top, topJLabels[0]);

		// topheader1
		layout.putConstraint(left, topHeader1, tableSeperation , right, tables[0]);
		layout.putConstraint(bottom, topHeader1, 0 , top, topJLabels[5]);

		// Constrain first table
		layout.putConstraint(left, tables[0], 150, left, panel);
		layout.putConstraint(top, tables[0], 150, bottom, back);

		// Constrain second table
		layout.putConstraint(left, tables[1], tableSeperation, right, tables[0]);
		layout.putConstraint(top, tables[1], 0, top, tables[0]);

		// Constrain third table
		layout.putConstraint(left, tables[2], 0, left, tables[0]);
		layout.putConstraint(top, tables[2], tableSeperation, bottom, tables[0]);

		// Constrain hint button
		layout.putConstraint(left, hint, buttonSeperation*2, right, tables[2]);
		layout.putConstraint(top, hint, buttonSeperation*2, top, tables[2]);

		// Constrain restart button
		layout.putConstraint(left, restart, 0, left, hint);
		layout.putConstraint(top, restart, buttonSeperation, bottom, hint);

		// Constrain submit button
		layout.putConstraint(left, submit, 0, left, hint);
		layout.putConstraint(top, submit, buttonSeperation, bottom, restart);




		// Start timer
		startTime = System.nanoTime();

	}

	// Method for getting puzzle screen
	public JPanel getPanel() {

		return panel;
	}

	// Method for getting back button
	public JButton getBack() {

		return back;
	}

	// Method for getting html string for vertical JLabel text
	private static String stringToHtml(String s) {
		String result = "<html>";
		String br = "<br>";

		String[] letters = s.split("");

		for(String letter : letters) {
			result += letter + br;
		}

		result += "</html>";
		return result;
	} // end stringToHtml

}

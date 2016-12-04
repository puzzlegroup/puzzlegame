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
public class Puzzle {

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
	private double totalTime;

	// Declare GUI variables
	private JPanel panel = new JPanel();
	private Border border;
	
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
		Border border = BorderFactory.createMatteBorder(borderWidth,
				borderWidth, borderWidth, borderWidth, borderColor);
		newTable.setBorder(border);

		// Set table font
		newTable.setFont(font);



		// Set mouse click action
		newTable.addMouseListener(new MouseAdapter() {

			// Create mouse click method
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
	private void giveHint() {



	}

	// Method for checking user's answers
	private int checkAnswers() {


		return 0;
	}

	// Method for restarting puzzle
	private void restartPuzzle() {
		
		
        
        

	}

	// Method for initializing puzzle screen
	public void initializePanel() {

		// Declare and initialize GUI variables
		int buttonWidth = 120;
        int buttonHeight = 35;
        int buttonSeperation = 10;
        int tableSeperation = -4;
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
        String image = "src/puzzlegame/images/space_2.jpg";
        JLabel background = new JLabel(new ImageIcon(image));
        
        // Customize GUI elements
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
        
        
        
        // Create back button
        back = new JButton(backTitle);
        back.setFont(font);
        back.setPreferredSize(buttonSize);
        panel.add(back);
        
        // Create hint button
        hint = new JButton(hintTitle);
        hint.setFont(font);
        hint.setPreferredSize(buttonSize);
        hint.addActionListener(event -> giveHint());
        panel.add(hint);
        
        // Create restart button
        restart = new JButton(restartTitle);
        restart.setFont(font);
        restart.setPreferredSize(buttonSize);
        restart.addActionListener(event -> restartPuzzle());
        panel.add(restart);
        
        // Create submit button
        submit = new JButton(submitTitle);
        submit.setFont(font);
        submit.setPreferredSize(buttonSize);
        submit.addActionListener(event -> checkAnswers());
        panel.add(submit);
        
        // Create tables
        for(int i = 0; i < tables.length; i++) {
        	
        	tables[i] = newTable();
        	panel.add(tables[i]);
        }
        
        // set dimension of labels
        Dimension labelDim = new Dimension(100,30);
        Dimension labelDimVert = new Dimension(32,150);
        Dimension headerDimVert = new Dimension(32,150);
        Dimension headerDim = new Dimension(160,30);
        
        // declare and intitiate leftJLabels array
        JLabel[] leftJLabels = new JLabel[leftLabels.length];
        
        for(int i = 0; i < leftLabels.length; i++ ) {
            leftJLabels[i] = new JLabel(leftLabels[i]);
            leftJLabels[i].setOpaque(true);
            leftJLabels[i].setPreferredSize(labelDim);
            leftJLabels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            panel.add(leftJLabels[i]);
        }
        
        // declare and intitiate topJLabels array
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
        
        // declare and intiatlize left headers
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
        
        // declare and intiatlize top headers
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
        layout.putConstraint(top, hint, buttonSeperation, top, tables[2]);
        
        // Constrain restart button
        layout.putConstraint(left, restart, 0, left, hint);
        layout.putConstraint(top, restart, buttonSeperation, bottom, hint);
        
        // Constrain submit button
        layout.putConstraint(left, submit, 0, left, hint);
        layout.putConstraint(top, submit, buttonSeperation, bottom, restart);
        

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
        public static String stringToHtml(String s) {
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

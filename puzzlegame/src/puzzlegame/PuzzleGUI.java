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
import java.awt.*;
import javax.swing.*;

// GUI for Team Vulcan's Logic Quest game
public class PuzzleGUI extends JFrame {

	/**
	 *  GLOBAL SETUP
	 */

	// Create area constant
	private static final int NUM_OF_AREAS = 1;

	// Declare GUI variables
	private JButton map;
	private JButton play;
	private JButton tutorial;
	private JButton credits;
	private JButton back_1;
	private JButton back_2;
	private JButton back_3;
	private JTextArea goal;
	private JTextArea howTo;
	private JButton[] areaButtons = new JButton[NUM_OF_AREAS];

	// Declare and initialize screen panels
	private JPanel currentPanel;
	private JPanel introPanel = new JPanel();
	private JPanel mapPanel = new JPanel();
	private JPanel tutorialPanel = new JPanel();
	private JPanel creditsPanel = new JPanel();
	private JPanel[] areaPanels = new JPanel[NUM_OF_AREAS];

	// Declare utility variables
	private String[] areaNames = new String[NUM_OF_AREAS];
	private String[] puzzleNames = {"Level 1", "Level 2"};
	private JButton levelButtons[] = new JButton[puzzleNames.length];



	// Main program
	public static void main(String[] args) {

		// Create GUI
		PuzzleGUI GUI = new PuzzleGUI();
	}

	// GUI constructor
	public PuzzleGUI() {

		/**
		 *  GUI SETUP
		 */

		// Declare and initialize frame variables
		int frameWidth = 800;
		int frameHeight = 600;
		int buttonWidth = 120;
		int buttonHeight = 35;
		int buttonXOffset = (frameWidth / 2) - (buttonWidth / 2);
		int buttonYOffset = 350;
		int buttonSeperation = 10;
		int textRows = 5;
		int textColumns = 20;
		Font font = new Font(null, Font.BOLD, 16);

		// Declare and initialize titles
		String mainTitle = "Logic Quest";
		String backTitle = "Back";
		String playTitle = "Play";
		String tutorialTitle = "Tutorial";
		String creditsTitle = "Credits";
		String goalTitle = "Goal\n\nThe goal is to complete each puzzle\n...time\n...stars";
		String howToTitle = "How to play\n\nStep-by-step mini puzzle goes here";

		// Declare and initialize layout variables
		String top = SpringLayout.NORTH;
		String bottom = SpringLayout.SOUTH;
		String left = SpringLayout.WEST;
		String right = SpringLayout.EAST;

		// Declare and initialize image backgrounds
		String introImage = "src/puzzlegame/images/space_1.jpg";
		String genericImage = "src/puzzlegame/images/space_2.jpg";
		JLabel introBackground = new JLabel(new ImageIcon(introImage));
		JLabel tutorialBackground = new JLabel(new ImageIcon(genericImage));
		JLabel creditsBackground = new JLabel(new ImageIcon(genericImage));

		// Customize GUI elements
		Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
		SpringLayout layout = new SpringLayout();
		introPanel.setLayout(layout);
		mapPanel.setLayout(layout);
		tutorialPanel.setLayout(layout);
		creditsPanel.setLayout(layout);
		for(int i = 0; i < NUM_OF_AREAS; i++) {
			areaPanels[i] = new JPanel();
			areaPanels[i].setLayout(layout);
		}

		// Set frame properties
		setTitle(mainTitle);
		setSize(frameWidth, frameHeight);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		/**
		 *  INTRO SCREEN
		 */

		// Create play button
		play = new JButton(playTitle);
		play.setFont(font);
		play.setPreferredSize(buttonSize);
		play.addActionListener(event -> showPanel(mapPanel));
		introPanel.add(play);

		// Create tutorial button
		tutorial = new JButton(tutorialTitle);
		tutorial.setFont(font);
		tutorial.setPreferredSize(buttonSize);
		tutorial.addActionListener(event -> showPanel(tutorialPanel));
		introPanel.add(tutorial);

		// Create credits button
		credits = new JButton(creditsTitle);
		credits.setFont(font);
		credits.setPreferredSize(buttonSize);
		credits.addActionListener(event -> showPanel(creditsPanel));
		introPanel.add(credits);

		// Add background image
		introPanel.add(introBackground);
		introPanel.setComponentZOrder(introBackground, introPanel.getComponentCount() - 1);

		// Constrain play button
		layout.putConstraint(left, play, buttonXOffset, left, introPanel);
		layout.putConstraint(top, play, buttonYOffset, top, introPanel);

		// Constrain tutorial button
		layout.putConstraint(left, tutorial, 0, left, play);
		layout.putConstraint(top, tutorial, buttonSeperation, bottom, play);

		// Constrain credits button
		layout.putConstraint(left, credits, 0, left, play);
		layout.putConstraint(top, credits, buttonSeperation, bottom, tutorial);



		/**
		 *  MAP SCREEN
		 */

		// Create first back button
		back_1 = new JButton(backTitle);
		back_1.setFont(font);
		back_1.setPreferredSize(buttonSize);
		back_1.addActionListener(event -> showPanel(introPanel));
		mapPanel.add(back_1);

		// Create area buttons
		for(int i = 0; i < NUM_OF_AREAS; i++) {

			// Declare and initialize index constant
			final int areaIndex = i;

			// Create formatted area button
			areaNames[i] = "Area " + (i + 1);
			areaButtons[i] = new JButton(areaNames[i]);
			areaButtons[i].setFont(font);
			areaButtons[i].setPreferredSize(buttonSize);
			areaButtons[i].addActionListener(event -> showArea(areaIndex));
		}

		// Add first area button
		mapPanel.add(areaButtons[0]);

		// Constraint first back button
		layout.putConstraint(left, back_1, buttonSeperation, left, mapPanel);
		layout.putConstraint(top, back_1, buttonSeperation, top, mapPanel);

		// Constrain first area button
		layout.putConstraint(left, areaButtons[0], buttonWidth, left, mapPanel);
		layout.putConstraint(top, areaButtons[0], buttonWidth, top, mapPanel);



		/**
		 *  AREA 1 SCREEN
		 */

		// Create back button
		map = new JButton(backTitle);
		map.setFont(font);
		map.setPreferredSize(buttonSize);
		map.addActionListener(event -> showPanel(mapPanel));
		areaPanels[0].add(map);

		// Create level buttons
		for(int i = 0; i < levelButtons.length; i++) {

			// Declare and initialize index constant
			final int levelIndex = i;

			// Create formatted level button
			levelButtons[i] = new JButton(puzzleNames[i]);
			levelButtons[i].setFont(font);
			levelButtons[i].setPreferredSize(buttonSize);
			levelButtons[i].addActionListener(event -> showPuzzle(puzzleNames[levelIndex]));
		}

		areaPanels[0].add(levelButtons[0]);

		// Constraint back button
		layout.putConstraint(left, map, buttonSeperation, left, areaPanels[0]);
		layout.putConstraint(top, map, buttonSeperation, top, areaPanels[0]);

		// Constrain level button
		layout.putConstraint(left, levelButtons[0], buttonWidth, left, areaPanels[0]);
		layout.putConstraint(top, levelButtons[0], buttonWidth, top, areaPanels[0]);



		/**
		 *  TUTORIAL SCREEN
		 */

		// Create second back button
		back_2 = new JButton(backTitle);
		back_2.setFont(font);
		back_2.setPreferredSize(buttonSize);
		back_2.addActionListener(event -> showPanel(introPanel));
		tutorialPanel.add(back_2);

		// Create goal text area
		goal = new JTextArea(goalTitle, textRows, textColumns);
		goal.setEditable(false);
		goal.setFont(font);
		tutorialPanel.add(goal);

		// Create how-to text area
		howTo = new JTextArea(howToTitle, textRows, textColumns);
		howTo.setEditable(false);
		howTo.setFont(font);
		tutorialPanel.add(howTo);

		// Add background image
		tutorialPanel.add(tutorialBackground);
		tutorialPanel.setComponentZOrder(tutorialBackground, tutorialPanel.getComponentCount() - 1);

		// Constrain second back button
		layout.putConstraint(left, back_2, buttonSeperation, left, tutorialPanel);
		layout.putConstraint(top, back_2, buttonSeperation, top, tutorialPanel);

		// Constrain goal text
		layout.putConstraint(left, goal, buttonWidth, left, tutorialPanel);
		layout.putConstraint(top, goal, buttonWidth, bottom, back_2);

		// Constrain how-to text area
		layout.putConstraint(left, howTo, buttonSeperation, right, goal);
		layout.putConstraint(top, howTo, 0, top, goal);



		/**
		 *  CREDITS SCREEN
		 */

		// Create third back button
		back_3 = new JButton(backTitle);
		back_3.setFont(font);
		back_3.setPreferredSize(buttonSize);
		back_3.addActionListener(event -> showPanel(introPanel));
		creditsPanel.add(back_3);

		// Add background image
		creditsPanel.add(creditsBackground);
		creditsPanel.setComponentZOrder(creditsBackground, creditsPanel.getComponentCount() - 1);

		// Constraint third back button
		layout.putConstraint(left, back_3, buttonSeperation, left, creditsPanel);
		layout.putConstraint(top, back_3, buttonSeperation, top, creditsPanel);



		/**
		 *  GUI INITIALIZATION
		 */

		// Set current panel
		currentPanel = introPanel;

		// Add panel to frame
		add(introPanel);
		setVisible(true);

	}

        

	/**
	 *  METHODS
	 */

	// Method for showing a new screen
	private void showPanel(JPanel newPanel) {

		// Replace the current panel
		remove(currentPanel);
		add(newPanel);
		currentPanel = newPanel;
                
		// Reconfigure screen
		validate();
		repaint();
	}

	// Method for showing area screen
	private void showArea(int areaIndex) {

		// Replace the current panel
		remove(currentPanel);
		add(areaPanels[areaIndex]);
		currentPanel = areaPanels[areaIndex];

		// Reconfigure screen
		validate();
		repaint();
	}

	// Method for showing puzzle screen
	private void showPuzzle(String puzzleName) {

		// Create specified puzzle instance
		Puzzle puzzle = new Puzzle(puzzleName);
		
		// Replace the current panel
		remove(currentPanel);
		add(puzzle.getPanel());
		currentPanel = puzzle.getPanel();
		
		// Reconfigure screen
		validate();
		repaint();
	}

}

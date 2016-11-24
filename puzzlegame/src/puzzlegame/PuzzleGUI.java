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

	// Declare GUI variables
	private JButton intro;
	private JButton play;
	private JButton tutorial;
	private JButton credits;
	
	// Declare screen panels
	private JPanel introPanel = new JPanel();
	private JPanel mapPanel = new JPanel();
	private JPanel tutorialPanel = new JPanel();
	private JPanel creditsPanel = new JPanel();
	private JPanel[] areaPanels;
	
	// Declare utility variables
	private String[] areas;
	private String[] puzzleNames;
	
	
	
	// Main program
	public static void main(String[] args) {
		
		// Create GUI
		PuzzleGUI GUI = new PuzzleGUI();
	}
	
	// GUI constructor
	public PuzzleGUI() {
		
		// Declare and initialize frame variables
		int frameWidth = 800;
		int frameHeight = 600;
		int buttonWidth = 120;
		int buttonHeight = 35;
		int buttonXOffset = (frameWidth / 2) - (buttonWidth / 2);
		int buttonYOffset = 350;
		int buttonSeperation = 10;
		int borderWidth = 8;
		Font font = new Font(null, Font.BOLD, 16);
		
		// Declare and initialize titles
		String mainTitle = "Logic Quest";
		String introTitle = "Back";
		String playTitle = "Play";
		String tutorialTitle = "Tutorial";
		String creditsTitle = "Credits";
		
		
		
		// Customize GUI elements
		Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
		SpringLayout layout = new SpringLayout();
		introPanel.setLayout(layout);
		
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
		play.addActionListener(event -> showMap(introPanel));
		introPanel.add(play);
		
		// Create tutorial button
		tutorial = new JButton(tutorialTitle);
		tutorial.setFont(font);
		tutorial.setPreferredSize(buttonSize);
		tutorial.addActionListener(event -> showTutorial(introPanel));
		introPanel.add(tutorial);
		
		// Create credits button
		credits = new JButton(creditsTitle);
		credits.setFont(font);
		credits.setPreferredSize(buttonSize);
		credits.addActionListener(event -> showCredits(introPanel));
		introPanel.add(credits);
		
		// Add background image
		//JLabel background = new JLabel(new ImageIcon("images/space_1.jpg"));
		//introPanel.add(background);
		//introPanel.setComponentZOrder(background, 3);
		
		
		// Declare and initialize layout variables
		String top = SpringLayout.NORTH;
		String bottom = SpringLayout.SOUTH;
		String left = SpringLayout.WEST;
		String right = SpringLayout.EAST;
		
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
		 *  TUTORIAL SCREEN
		 */
		
		//scrollPane = new JScrollPane(tutorialPanel); I'm not sure if this is right.
				
		//Create back button
		back = new JButton(introTitle);
		back.setFont(font);
		back.setPreferredSize(buttonSize);
		back.addActionListener(event -> showIntro(introPanel));
		tutorialPanel.add(back);
		
		//Create Goal Label and TextBox
		goal = new JLabel("Goal");
		goalTxt = new JTextBox ("The goal is to complete each puzzle...time...stars...");
		goalTxt.setEditable(false);
		goal.setFont(font);
		goalTxt.setFont(font);
		tutorialPanel.add(goal);
		tutorialPanel.add(goalTxt);
		
		//Create HowTo Label and TextBox
		howTo = new JLabel("How to Play");
		howToTxt = new JTextBox ("Step-by-step mini puzzle"); //Not sure if we should use the labels and textBoxes
		howToTxt.setEditable(false);
		howTo.setFont(font);
		howToTxt.setFont(font);
		tutorialPanel.add(howTo);
		tutorialPanel.add(howToTxt);
		
		//Add background image
		//tutorialPanel.add(background);
		//introPanel.setComponentZOrder(background, 3);
		
		//Constrain back button
		layout.putConstraint(left, back, buttonXOffset, left, tutorialPanel);
		layout.putConstraint(top, back, buttonXOffset, left, tutorialPanel);
		
		//Constrain goal Label
		layout.putConstraint(left, goal, 0, left, back);
		layout.putConstraint(top, goal, 0, left, back);
		
		//Constrain goal TextBox
		layout.putConstraint(left, goalTxt, 0, left, back);
		layout.putConstraint(top, goalTxt, 0, left, back);
		
		//Constrain HowTo Label
		layout.putConstraint(left, howTo, 0, left, back);
		layout.putConstraint(top, howTo, 0, left, back);
		
		//Constrain HowTo TextBox
		layout.putConstraint(left, howToTxt, 0, left, back);
		layout.putConstraint(top, howToTxt, 0, left, back);
		
		
		/**
		 *  CREDITS SCREEN
		 */
		
		
		
		
		/**
		 *  MAP SCREEN
		 */
		
		
		
		
		
		
		// Add panel to frame
		add(introPanel);
		setVisible(true);
		
	}

	// Method for showing introduction screen
	private void showIntro(JPanel currentPanel) {
		
		// Replace the current panel
		remove(currentPanel);
		add(introPanel);
		validate();
	}
	
	// Method for showing map screen
	private void showMap(JPanel currentPanel) {
		
		// Replace the current panel
		remove(currentPanel);
		add(mapPanel);
		validate();
	}
	
	// Method for showing area screen
	private void showArea(String area) {
		
	}
	
	// Method for showing puzzle screen
	private void showPuzzle(String puzzleName) {
		
	}
	
	// Method for showing game tutorial
	private void showTutorial(JPanel currentPanel) {
		
		// Replace the current panel
		remove(currentPanel);
		add(tutorialPanel);
		validate();
	}
	
	// Method for showing game credits
	private void showCredits(JPanel currentPanel) {
		
		// Replace the current panel
		remove(currentPanel);
		add(creditsPanel);
		validate();
	}
	
}

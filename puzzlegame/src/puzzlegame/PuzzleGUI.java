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
import java.awt.*;
import javax.swing.*;
import java.io.*;
import sun.audio.*;
import com.fasterxml.jackson.databind.ObjectMapper;

// GUI for Team Vulcan's Logic Quest game
public class PuzzleGUI extends JFrame {

	/**
	 * GLOBAL SETUP
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
	private String[] puzzleKeyNames = {"puzzledatatest", "a01-level01"};
	private JButton levelButtons[] = new JButton[puzzleNames.length];

	// Declare sound wav file sources
	private static String introMusic = "src/puzzlegame/Sound-Wavs/Star_Trek Famous.wav";
	private static String creditsMusic = "src/puzzlegame/Sound-Wavs/Star_Trek End Credit.wav";

	// Main program
	public static void main(String[] args) {

		// Create GUI
		PuzzleGUI GUI = new PuzzleGUI();
		
		playMusic(introMusic);
	}

	// GUI constructor
	public PuzzleGUI() {

		/**
		 * GUI SETUP
		 */

		// Declare and initialize frame variables
		int frameWidth = 800;
		int frameHeight = 600;
		int buttonWidth = 120;
		int buttonHeight = 35;
		int buttonXOffset = (frameWidth / 2) - (buttonWidth / 2);
		int buttonYOffset = 350;
		int buttonSeperation = 10;
		//int textRows = 5;			Not sure if these are needed elsewhere. If so uncomment them
		//int textColumns = 20;
		Font font = new Font(null, Font.BOLD, 16);

		// Declare and initialize titles
		String mainTitle = "Logic Quest";
		String backTitle = "Back";
		String playTitle = "Play";
		String tutorialTitle = "Tutorial";
		String creditsTitle = "Credits";

		// Declare and initialize layout variables
		String top = SpringLayout.NORTH;
		String bottom = SpringLayout.SOUTH;
		String left = SpringLayout.WEST;
		String right = SpringLayout.EAST;

		//Declare and initialize Tutorial content
		String goalImg = "src/puzzlegame/images/goal.jpg";
		String controlsImg = "src/puzzlegame/images/controls.jpg";
		String exampleImg = "src/puzzlegame/images/example.jpg";
		String clueImg_1 = "src/puzzlegame/images/clue1.jpg";
		String clueImg_2 = "src/puzzlegame/images/clue2.jpg";
		String clueImg_3 = "src/puzzlegame/images/clue3.jpg";
		String clueImg_4 = "src/puzzlegame/images/clue4.jpg";
		String crossRefImg_1 = "src/puzzlegame/images/crossRef1.jpg";
		String crossRefImg_2 = "src/puzzlegame/images/crossRef2.jpg";
		String refClueImg = "src/puzzlegame/images/refClue.jpg";
		
		JLabel goal = new JLabel(new ImageIcon(goalImg));
		JLabel controls = new JLabel(new ImageIcon(controlsImg));
		JLabel example = new JLabel(new ImageIcon(exampleImg));
		JLabel clue_1 = new JLabel(new ImageIcon(clueImg_1));
		JLabel clue_2 = new JLabel(new ImageIcon(clueImg_2));
		JLabel clue_3 = new JLabel(new ImageIcon(clueImg_3));
		JLabel clue_4 = new JLabel(new ImageIcon(clueImg_4));
		JLabel crossRef_1 = new JLabel(new ImageIcon(crossRefImg_1));
		JLabel crossRef_2 = new JLabel(new ImageIcon(crossRefImg_2));
		JLabel refClue = new JLabel(new ImageIcon(refClueImg));
		
		//Declare and initialize Credits content
		String creditsImg = "src/puzzlegame/images/credits.jpg";
		JLabel creditsContent = new JLabel(new ImageIcon(creditsImg));
		
		// Declare and initialize image backgrounds
		String introImage = "src/puzzlegame/images/space_1.jpg";
		String genericImage = "src/puzzlegame/images/space_3.jpg";
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
		for (int i = 0; i < NUM_OF_AREAS; i++) {
			areaPanels[i] = new JPanel();
			areaPanels[i].setLayout(layout);
		}

		// Set frame properties
		setTitle(mainTitle);
		setSize(frameWidth, frameHeight);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		/**
		 * INTRO SCREEN
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
		 * MAP SCREEN
		 */

		// Create first back button
		back_1 = new JButton(backTitle);
		back_1.setFont(font);
		back_1.setPreferredSize(buttonSize);
		back_1.addActionListener(event -> showPanel(introPanel));
		mapPanel.add(back_1);

		// Create area buttons
		for (int i = 0; i < NUM_OF_AREAS; i++) {

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
		 * AREA 1 SCREEN
		 */

		// Create back button
		map = new JButton(backTitle);
		map.setFont(font);
		map.setPreferredSize(buttonSize);
		map.addActionListener(event -> showPanel(mapPanel));
		areaPanels[0].add(map);

		// Create level buttons
		for (int i = 0; i < levelButtons.length; i++) {

			// Declare and initialize index constant
			final int levelIndex = i;

			// Create formatted level button
			levelButtons[i] = new JButton(puzzleNames[i]);
			levelButtons[i].setFont(font);
			levelButtons[i].setPreferredSize(buttonSize);
			levelButtons[i].addActionListener(event ->
			showPuzzle(puzzleKeyNames[levelIndex], areaPanels[levelIndex]));
		}

		areaPanels[0].add(levelButtons[0]);

		// Constraint back button
		layout.putConstraint(left, map, buttonSeperation, left, areaPanels[0]);
		layout.putConstraint(top, map, buttonSeperation, top, areaPanels[0]);

		// Constrain level button
		layout.putConstraint(left, levelButtons[0], buttonWidth, left, areaPanels[0]);
		layout.putConstraint(top, levelButtons[0], buttonWidth, top, areaPanels[0]);


		/**
		 * TUTORIAL SCREEN
		 */

		// Create second back button
		back_2 = new JButton(backTitle);
		back_2.setFont(font);
		back_2.setPreferredSize(buttonSize);
		back_2.addActionListener(event -> showPanel(introPanel));
		tutorialPanel.add(back_2);
		
		// Create tutorial content areas
		tutorialPanel.add(goal);
		tutorialPanel.add(controls);
		tutorialPanel.add(example);
		tutorialPanel.add(clue_1);
		tutorialPanel.add(clue_2);
		tutorialPanel.add(clue_3);
		tutorialPanel.add(clue_4);
		tutorialPanel.add(crossRef_1);
		tutorialPanel.add(refClue);
		tutorialPanel.add(crossRef_2);

		// Add background image
		tutorialPanel.add(tutorialBackground);
		tutorialPanel.setComponentZOrder(tutorialBackground, tutorialPanel.getComponentCount() - 1);

		// Constrain second back button
		layout.putConstraint(left, back_2, buttonSeperation, left, tutorialPanel);
		layout.putConstraint(top, back_2, buttonSeperation, top, tutorialPanel);

		// Constrain goal area
		layout.putConstraint(left, goal, buttonWidth, left, tutorialPanel);
		layout.putConstraint(top, goal, buttonWidth, top, back_2);

		// Constrain controls area
		layout.putConstraint(left, controls, buttonSeperation, right, goal);
		layout.putConstraint(top, controls, 0, top, goal);
		
		// Constrain example area
		layout.putConstraint(left, example, buttonSeperation, right, controls);
		layout.putConstraint(top, example, 0, top, controls);
		
		// Constrain clue_1 area
		layout.putConstraint(left, clue_1, buttonSeperation, right, example);
		layout.putConstraint(top, clue_1, 0, top, example);
		
		// Constrain clue_2 area
		layout.putConstraint(left, clue_2, buttonSeperation, right, clue_1);
		layout.putConstraint(top, clue_2, 0, top, clue_1);
		
		// Constrain clue_3 area
		layout.putConstraint(left, clue_3, buttonSeperation, right, clue_2);
		layout.putConstraint(top, clue_3, 0, top, clue_2);
		
		// Constrain clue_4 area
		layout.putConstraint(left, clue_4, buttonSeperation, right, clue_3);
		layout.putConstraint(top, clue_4, 0, top, clue_3);
		
		// Constrain crossRef_1 area
		layout.putConstraint(left, crossRef_1, buttonSeperation, right, clue_4);
		layout.putConstraint(top, crossRef_1, 0, top, clue_4);
		
		// Constrain refClue area
		layout.putConstraint(left, refClue, buttonSeperation, right, crossRef_1);
		layout.putConstraint(top, refClue, 0, top, crossRef_1);
		
		// Constrain crossRef_2 area
		layout.putConstraint(left, crossRef_2, buttonSeperation, right, refClue);
		layout.putConstraint(top, crossRef_2, 0, bottom, refClue);


		/**
		 * CREDITS SCREEN
		 */
		
		//Create credits content area
		creditsPanel.add(creditsContent);
		
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
		
		//Constrain credits content
		layout.putConstraint(left, creditsContent, buttonSeperation, left, back_3);
		layout.putConstraint(top, creditsContent, buttonSeperation, bottom, back_3);


		/**
		 * GUI INITIALIZATION
		 */

		// Set current panel
		currentPanel = introPanel;

		// Add panel to frame
		add(introPanel);
		setVisible(true);
                
	}

	/**
	 * METHODS
	 */

	// Method for playing background music
	private static void playMusic(String string) {
		
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		//AudioData MD;
		//ContinuousAudioDataStream loop = null;
		
		try {
			InputStream file = new FileInputStream(string);
			BGM = new AudioStream(file);
			AudioPlayer.player.start(BGM);
			//MD = BGM.getData();
			//loop = new ContinuousAudioDataStream(MD);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
	private void showPuzzle(String fileName, JPanel previousPanel) {

		// Create new puzzle instance
		Puzzle puzzle = new Puzzle();

		// Create JSON mapping utility
		ObjectMapper mapper = new ObjectMapper();

		// Attempt to read in puzzle data
		try {

			// Map JSON contents to puzzle fields
			puzzle = mapper.readValue(puzzle.readData(fileName), Puzzle.class);
		}
		// Handle errors if they occur
		catch (Exception e) {
			e.printStackTrace();

			// Output error message
			JOptionPane.showMessageDialog(null, "Error mapping JSON contents",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		// Initialize new panel
		puzzle.initializePanel();
		puzzle.getBack().addActionListener(event -> showPanel(previousPanel));
		
		// Replace the current panel
		remove(currentPanel);
		add(puzzle.getPanel());
		currentPanel = puzzle.getPanel();

		// Reconfigure screen
		validate();
		repaint();
	}

}

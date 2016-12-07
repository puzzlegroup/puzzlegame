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
import java.awt.event.*;
import sun.audio.*;
import javax.swing.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.InputStream;

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
	private String[] puzzleNames = {"Level 1", "Level 2"};
	private String[] puzzleKeyNames = {"puzzledatatest", "a01-level01"};
	private JButton levelButtons[] = new JButton[puzzleNames.length];

	// Declare sound WAV file sources
	private static String[] musicArray = new String[] {
			"src/puzzlegame/Sound-Wavs/Star_Trek Back.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Boldly Go.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Enterprising.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Hella Bar.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Nailing.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Nero Fiddle.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Nero Sighted.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Nice.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Run Shoot.wav",
	"src/puzzlegame/Sound-Wavs/Star_Trek Sting.wav" };

	// Declare and initialize music streams
	private static String introMusic = "src/puzzlegame/Sound-Wavs/Star_Trek Famous.wav";
	private static String creditsMusic = "src/puzzlegame/Sound-Wavs/Star_Trek End Credit.wav";
	private static String backMusic = "src/puzzlegame/Sound-Wavs/Star_Trek Back.wav";
	private static String boldMusic = "src/puzzlegame/Sound-Wavs/Star_Trek Boldy Go.wav";
	private static String enterpriseMusic = "src/puzzlegame/Sound-Wavs/Star_Trek Enterprising.wav";
	private static String Music = "src/puzzlegame/Sound-Wavs/Star_Trek Back.wav";

	// Main program
	public static void main(String[] args) {

		// Create GUI
		PuzzleGUI GUI = new PuzzleGUI();

		//playMusic(introMusic);
	}

	// GUI constructor
	public PuzzleGUI() {

		/**
		 * GUI SETUP
		 */

		// Declare and initialize frame variables
		int frameWidth = 800;
		int frameHeight = 600;
		int areaWidth = 50;
		int areaHeight = 50;
		int buttonWidth = 120;
		int buttonHeight = 35;
		int buttonXOffset = (frameWidth / 2) - (buttonWidth / 2);
		int buttonYOffset = 350;
		int buttonSeperation = 10;
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
		String mapImage = "src/puzzlegame/images/map.jpg";
		String area1Image = "src/puzzlegame/images/area1.jpg";
		String[] tutorialImages = {"goalImg","exampleImg","clueImg_1","clueImg_2",
				"clueImg_3","clueImg_4","crossRefImg_1","crossRefImg_2","refClueImg","controlsImg"};
		String creditsImage = "src/puzzlegame/images/credits.jpg";
		JLabel introBackground = new JLabel(new ImageIcon(introImage));
		JLabel mapBackground = new JLabel(new ImageIcon(mapImage));
		JLabel area1Background = new JLabel(new ImageIcon(area1Image));
		JLabel creditsBackground = new JLabel(new ImageIcon(creditsImage));

		// Customize GUI elements
		Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
		Dimension areaSize = new Dimension(areaWidth, areaHeight);
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
			areaButtons[i] = new JButton();
			areaButtons[i].setFont(font);
			areaButtons[i].setPreferredSize(areaSize);
			areaButtons[i].setOpaque(false);
			areaButtons[i].setContentAreaFilled(false);
			areaButtons[i].addActionListener(event -> showArea(areaIndex));
			
			// Add area button
			mapPanel.add(areaButtons[i]);
		}
		
		// Add background image
		mapPanel.add(mapBackground);
		mapPanel.setComponentZOrder(mapBackground, mapPanel.getComponentCount() - 1);

		// Constraint first back button
		layout.putConstraint(left, back_1, buttonSeperation, left, mapPanel);
		layout.putConstraint(top, back_1, buttonSeperation, top, mapPanel);

		// Constrain first area button
		layout.putConstraint(left, areaButtons[0], 53, left, mapPanel);
		layout.putConstraint(top, areaButtons[0], 292, top, mapPanel);


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
			levelButtons[i].setOpaque(false);
			levelButtons[i].setContentAreaFilled(false);
			levelButtons[i].setForeground(Color.WHITE);
			levelButtons[i].setPreferredSize(buttonSize);
			levelButtons[i].addActionListener(event ->
			showPuzzle(puzzleKeyNames[levelIndex], areaPanels[levelIndex]));
			
			// Add level button
			areaPanels[0].add(levelButtons[i]);
		}

		// Add background image
		areaPanels[0].add(area1Background);
		areaPanels[0].setComponentZOrder(area1Background, areaPanels[0].getComponentCount() - 1);
		
		// Constraint back button
		layout.putConstraint(left, map, buttonSeperation, left, areaPanels[0]);
		layout.putConstraint(top, map, buttonSeperation, top, areaPanels[0]);

		// Constrain first level button
		layout.putConstraint(left, levelButtons[0], 370, left, areaPanels[0]);
		layout.putConstraint(top, levelButtons[0], 150, top, areaPanels[0]);
		
		// Constrain second level button
		layout.putConstraint(left, levelButtons[1], 430, left, areaPanels[0]);
		layout.putConstraint(top, levelButtons[1], 430, top, areaPanels[0]);


		/**
		 * TUTORIAL SCREEN
		 */

		// Create second back button
		back_2 = new JButton(backTitle);
		back_2.setFont(font);
		back_2.setPreferredSize(buttonSize);
		back_2.addActionListener(event -> showPanel(introPanel));
		tutorialPanel.add(back_2);

		// Constrain second back button
		layout.putConstraint(left, back_2, buttonSeperation, left, tutorialPanel);
		layout.putConstraint(top, back_2, buttonSeperation, top, tutorialPanel);

		// Create first info label
		String infoTitle_1 = " Left click to go left      ";
		JLabel info_1 = new JLabel(infoTitle_1);
		info_1.setOpaque(true);
		info_1.setBackground(Color.LIGHT_GRAY);
		tutorialPanel.add(info_1);
		
		// Create second info label
		String infoTitle_2 = " Right click to go right ";
		JLabel info_2 = new JLabel(infoTitle_2);
		info_2.setOpaque(true);
		info_2.setBackground(Color.LIGHT_GRAY);
		tutorialPanel.add(info_2);
		
		// Constrain first info label
		layout.putConstraint(left, info_1, 0, left, back_2);
		layout.putConstraint(top, info_1, buttonSeperation, bottom, back_2);
		
		// Constrain second info label
		layout.putConstraint(left, info_2, 0, left, back_2);
		layout.putConstraint(top, info_2, 0, bottom, info_1);
		
		// Declare image array
		String source = "src/puzzlegame/images/";
		String type = ".jpg";
		JLabel[] tutorialBackgrounds = new JLabel[tutorialImages.length];

		// Create 
		for(int i = 0; i < tutorialImages.length; i++)
			tutorialBackgrounds[i] = new JLabel(new ImageIcon(source + tutorialImages[i] + type));

		// Add background image
		tutorialPanel.add(tutorialBackgrounds[0]);
		tutorialPanel.setComponentZOrder(tutorialBackgrounds[0], tutorialPanel.getComponentCount() - 1);

		// Add mouse listener to handle clicks for tutorial viewing
		tutorialPanel.addMouseListener(new MouseAdapter() {

			// Declare and initialize index variable
			int currentIndex = 0;

			// Create mouse click method
			public void mouseClicked(MouseEvent event) {

				// Check mouse button
				switch(event.getButton()) {

				// Go left an image if left clicked
				case 1:

					// Check if user is at first image
					if(currentIndex == 0)
						return;

					// Replace background image
					tutorialPanel.remove(tutorialBackgrounds[currentIndex]);
					tutorialPanel.add(tutorialBackgrounds[currentIndex - 1]);
					tutorialPanel.setComponentZOrder(tutorialBackgrounds[currentIndex - 1],
							tutorialPanel.getComponentCount() - 1);

					// Update current index
					currentIndex--;			

					break;

				case 3:

					// Check if user is at last image
					if(currentIndex == tutorialImages.length - 1)
						return;

					// Replace background image
					tutorialPanel.remove(tutorialBackgrounds[currentIndex]);
					tutorialPanel.add(tutorialBackgrounds[currentIndex + 1]);
					tutorialPanel.setComponentZOrder(tutorialBackgrounds[currentIndex + 1],
							tutorialPanel.getComponentCount() - 1);

					// Update current index
					currentIndex++;	

					break;

				default:
					break;
				}

				// Reconfigure screen
				tutorialPanel.validate();
				tutorialPanel.repaint();

			}

		});


		/**
		 * CREDITS SCREEN
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
		} catch (Exception e) {
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

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
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.logging.*;
import javax.sound.sampled.*;
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
	private JButton mute;
	private JButton newSong;

	// Declare and initialize screen panels
	private JPanel currentPanel;
	private JPanel introPanel = new JPanel();
	private JPanel mapPanel = new JPanel();
	private JPanel tutorialPanel = new JPanel();
	private JPanel creditsPanel = new JPanel();
	private JPanel[] areaPanels = new JPanel[NUM_OF_AREAS];

	// Declare utility variables
	private String[] puzzleNames = {"Level 1", "Level 2"};
	private String[] puzzleKeyNames = {"puzzledatatest", "puzzledatatest"};//"a01-level01"};
	private JButton levelButtons[] = new JButton[puzzleNames.length];
	
	// Declare sound variables
	private static Clip clip = null;
	private static AudioInputStream ais = null;	
	private static long durationInSeconds;

	// Declare sound WAV file sources
	private static String[] musicArray = new String[] {
			"src/puzzlegame/Sound-Wavs/Star_Trek Back.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Famous.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Enterprising.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Hella Bar.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Nailing.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Nero Fiddle.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Nero Sighted.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Nice.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Run Shoot.wav",
			"src/puzzlegame/Sound-Wavs/Star_Trek Sting.wav" };

        // Declare and initialize level stars
        private JLabel[][] levelStars = new JLabel[puzzleNames.length][6];
        
	// Main program
	public static void main(String[] args) {

		// Create GUI
		PuzzleGUI GUI = new PuzzleGUI();

		// Create PuzzleSound
		playMusic();
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
		Font font = new Font(null, Font.BOLD, 18);
		Font font2 = new Font(null, Font.BOLD, 16);
                
		// Declare and initialize titles
		String mainTitle = "Logic Quest";
		String backTitle = "Back";
		String playTitle = "Play";
		String tutorialTitle = "Tutorial";
		String creditsTitle = "Credits";
		String goalTitle = "Goal\n\nThe goal is to complete each puzzle\n...time\n...stars";
		String howToTitle = "How to play\n\nStep-by-step mini puzzle goes here";
		String muteTitle = "Mute";
		String newSongTitle = "New Song";

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
		
		// Declare and initialize sound variables and files
		String random = (musicArray[new Random().nextInt(musicArray.length)]);
		File file = new File(random);			
				
		try {			
			clip = AudioSystem.getClip();
			// getAudioInputStream() also accepts a File or InputStream
			ais = AudioSystem.getAudioInputStream(file);
					
				
					
		} catch (UnsupportedAudioFileException ex) {
			//Logger.getLogger(PuzzleSound2.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			//Logger.getLogger(PuzzleSound2.class.getName()).log(Level.SEVERE, null, ex);
		} catch (LineUnavailableException ex) {
			//Logger.getLogger(PuzzleSound2.class.getName()).log(Level.SEVERE, null, ex);
		}	

		/**
		 * INTRO SCREEN
		 */

		// Create play button
                ImageIcon playIcon = new ImageIcon("src/puzzlegame/images/nebula.jpg");
                Image playImg = playIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
                playIcon = new ImageIcon(playImg);
		play = new JButton(playTitle);
                play.setIcon(playIcon);
                play.setHorizontalTextPosition(JButton.CENTER);
                play.setVerticalTextPosition(JButton.CENTER);
		play.setFont(font);
                play.setForeground(Color.black);
		play.setPreferredSize(buttonSize);
		play.addActionListener(event -> showPanel(mapPanel));
		introPanel.add(play);

		// Create tutorial button
                ImageIcon tutorialIcon = new ImageIcon("src/puzzlegame/images/galaxy-venture.jpg");
                Image tutorialImg = tutorialIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
                tutorialIcon = new ImageIcon(tutorialImg);
		tutorial = new JButton(tutorialTitle);
                tutorial.setIcon(tutorialIcon);
                tutorial.setHorizontalTextPosition(JButton.CENTER);
                tutorial.setVerticalTextPosition(JButton.CENTER);
		tutorial.setFont(font);
                tutorial.setForeground(Color.black);
		tutorial.setPreferredSize(buttonSize);
		tutorial.addActionListener(event -> showPanel(tutorialPanel));
		introPanel.add(tutorial);

		// Create credits button
                ImageIcon creditsIcon = new ImageIcon("src/puzzlegame/images/quantum-class-13.jpg");
                Image creditsImg = creditsIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
                creditsIcon = new ImageIcon(creditsImg);
		credits = new JButton(creditsTitle);
                credits.setIcon(creditsIcon);
                credits.setHorizontalTextPosition(JButton.CENTER);
                credits.setVerticalTextPosition(JButton.CENTER);
		credits.setFont(font);
                credits.setForeground(Color.black);
		credits.setPreferredSize(buttonSize);
		credits.addActionListener(event -> showPanel(creditsPanel));
		introPanel.add(credits);
		
		// Create intro mute button
                ImageIcon glowIcon = new ImageIcon("src/puzzlegame/images/glow.png");
                Image glowImg = glowIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
                glowIcon = new ImageIcon(glowImg);
		mute = new JButton(muteTitle);
                mute.setIcon(glowIcon);
                mute.setHorizontalTextPosition(JButton.CENTER);
                mute.setVerticalTextPosition(JButton.CENTER);
		mute.setFont(font2);
                mute.setForeground(Color.black);
                mute.setContentAreaFilled(false);
		mute.setPreferredSize(buttonSize);
		mute.addActionListener(event -> stopMusic());	
		introPanel.add(mute);

		// Create new song button
		newSong = new JButton(newSongTitle);
                newSong.setIcon(glowIcon);
                newSong.setHorizontalTextPosition(JButton.CENTER);
                newSong.setVerticalTextPosition(JButton.CENTER);
		newSong.setFont(font2);
                newSong.setForeground(Color.black);
                newSong.setContentAreaFilled(false);
		newSong.setPreferredSize(buttonSize);
		newSong.addActionListener(event -> newMusic());
		introPanel.add(newSong);
				
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
		
		// Constrain mute button
		layout.putConstraint(left, mute, buttonSeperation, left, introPanel);
		layout.putConstraint(bottom, mute, -buttonSeperation, bottom, introPanel);
		
		// Constrain new song button
		layout.putConstraint(left, newSong, buttonSeperation, right, mute);
		layout.putConstraint(bottom, newSong, -buttonSeperation, bottom, introPanel);

		/**
		 * MAP SCREEN
		 */

		// Create first back button
                ImageIcon backIcon = new ImageIcon("src/puzzlegame/images/voyager-side.png");
                Image back_1Img = backIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
                backIcon = new ImageIcon(back_1Img);
		back_1 = new JButton(backTitle);
                back_1.setIcon(backIcon);
                back_1.setHorizontalTextPosition(JButton.CENTER);
                back_1.setVerticalTextPosition(JButton.CENTER);
		back_1.setFont(font);
                back_1.setForeground(Color.black);
		back_1.setPreferredSize(buttonSize);
		back_1.addActionListener(event -> showPanel(introPanel));
		mapPanel.add(back_1);
		
		// Create map mute button
		mute = new JButton(muteTitle);
                mute.setIcon(glowIcon);
                mute.setHorizontalTextPosition(JButton.CENTER);
                mute.setVerticalTextPosition(JButton.CENTER);
		mute.setFont(font2);
                mute.setForeground(Color.black);
                mute.setContentAreaFilled(false);
		mute.setPreferredSize(buttonSize);
		mute.addActionListener(event -> stopMusic());	
		mapPanel.add(mute);
		
		// Create new song button
		newSong = new JButton(newSongTitle);
		newSong.setIcon(glowIcon);
                newSong.setHorizontalTextPosition(JButton.CENTER);
                newSong.setVerticalTextPosition(JButton.CENTER);
		newSong.setFont(font2);
                newSong.setForeground(Color.black);
                newSong.setContentAreaFilled(false);
		newSong.setPreferredSize(buttonSize);
		newSong.addActionListener(event -> newMusic());
		mapPanel.add(newSong);		

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
		
		// Constrain map screen mute button
		layout.putConstraint(left, mute, buttonSeperation, left, mapPanel);
		layout.putConstraint(bottom, mute, -buttonSeperation, bottom, mapPanel);
		
		// Constrain map screen new song button
		layout.putConstraint(left, newSong, buttonSeperation, right, mute);
		layout.putConstraint(bottom, newSong, -buttonSeperation, bottom, mapPanel);

		// Constrain first area button
		layout.putConstraint(left, areaButtons[0], 53, left, mapPanel);
		layout.putConstraint(top, areaButtons[0], 292, top, mapPanel);


		/**
		 * AREA 1 SCREEN
		 */

		// Create back button
		map = new JButton(backTitle);
                map.setIcon(backIcon);
                map.setHorizontalTextPosition(JButton.CENTER);
                map.setVerticalTextPosition(JButton.CENTER);
		map.setFont(font);
                map.setForeground(Color.black);
		map.setPreferredSize(buttonSize);
		map.addActionListener(event -> showPanel(mapPanel));
		areaPanels[0].add(map);
		
		// Create area mute button
		mute = new JButton(muteTitle);
                mute.setIcon(glowIcon);
                mute.setHorizontalTextPosition(JButton.CENTER);
                mute.setVerticalTextPosition(JButton.CENTER);
		mute.setFont(font2);
                mute.setForeground(Color.black);
                mute.setContentAreaFilled(false);
		mute.setPreferredSize(buttonSize);
		mute.addActionListener(event -> stopMusic());	
		areaPanels[0].add(mute);
		
		// Create new song button
		newSong = new JButton(newSongTitle);
		newSong.setIcon(glowIcon);
                newSong.setHorizontalTextPosition(JButton.CENTER);
                newSong.setVerticalTextPosition(JButton.CENTER);
		newSong.setFont(font2);
                newSong.setForeground(Color.black);
                newSong.setContentAreaFilled(false);
		newSong.setPreferredSize(buttonSize);
		newSong.addActionListener(event -> newMusic());
		areaPanels[0].add(newSong);

		// Create level buttons for first area
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
			showPuzzle(puzzleKeyNames[levelIndex], areaPanels[0], levelIndex));
			
			// Add level button
			areaPanels[0].add(levelButtons[i]);
		}

                // Create star image icon
                ImageIcon star = new ImageIcon("src/puzzlegame/images/star-20.png");
                
		// Initialize levelStars
                for(int level = 0; level < puzzleNames.length; level++) {
                    for(int stars = 0; stars < 6; stars++) {
                        levelStars[level][stars] = new JLabel(star);
                    }
                }
                
                // Add six stars to each level
                for(int level = 0; level < puzzleNames.length; level++) {
                    for(int stars = 0; stars < 6; stars++) {
                        areaPanels[0].add(levelStars[level][stars]);
                    }
                }
                
		// Add background image
		areaPanels[0].add(area1Background);
		areaPanels[0].setComponentZOrder(area1Background, areaPanels[0].getComponentCount() - 1);
		
		// Constraint back button
		layout.putConstraint(left, map, buttonSeperation, left, areaPanels[0]);
		layout.putConstraint(top, map, buttonSeperation, top, areaPanels[0]);
		
		// Constrain areas mute button
		layout.putConstraint(left, mute, buttonSeperation, left, areaPanels[0]);
		layout.putConstraint(bottom, mute, -buttonSeperation, bottom, areaPanels[0]);
		
		// Constrain new song button
		layout.putConstraint(left, newSong, buttonSeperation, right, mute);
		layout.putConstraint(bottom, newSong, -buttonSeperation, bottom, areaPanels[0]);

		// Constrain first level button
		layout.putConstraint(left, levelButtons[0], 370, left, areaPanels[0]);
		layout.putConstraint(top, levelButtons[0], 150, top, areaPanels[0]);
		
                // Constraint first star per level
                for(int i = 0; i < puzzleNames.length; i++) {
                    layout.putConstraint(left, levelStars[i][0], 0, left, levelButtons[i]);
                    layout.putConstraint(top, levelStars[i][0], 5, bottom, levelButtons[i]);
                    // hide star
                    levelStars[i][0].setVisible(false);
                }
                // Constraint set the remainder 5 stars
                for(int i = 0; i < puzzleNames.length; i++) {
                    for(int j = 1; j < 6; j++) {
                        layout.putConstraint(left, levelStars[i][j], 0, right, levelStars[i][j-1]);
                        layout.putConstraint(top, levelStars[i][j], 5, bottom, levelButtons[i]);
                        // hide stars
                        levelStars[i][j].setVisible(false);
                    }
                }
                
		// Constrain second level button
		layout.putConstraint(left, levelButtons[1], 430, left, areaPanels[0]);
		layout.putConstraint(top, levelButtons[1], 430, top, areaPanels[0]);


                
                
		/**
		 * TUTORIAL SCREEN
		 */

		// Create second back button
		back_2 = new JButton(backTitle);
                back_2.setIcon(backIcon);
                back_2.setHorizontalTextPosition(JButton.CENTER);
                back_2.setVerticalTextPosition(JButton.CENTER);
		back_2.setFont(font);
                back_2.setForeground(Color.black);
		back_2.setPreferredSize(buttonSize);
		back_2.addActionListener(event -> showPanel(introPanel));
		tutorialPanel.add(back_2);
		
		// Create tutorial mute button
		////mute = new JButton(muteTitle);
		////mute.setFont(font);
		////mute.setPreferredSize(buttonSize);
		////mute.addActionListener(event -> stopMusic());	
		////tutorialPanel.add(mute);
		
		// Create tutorial new song button
		////newSong = new JButton(newSongTitle);
		////newSong.setFont(font);
		////newSong.setPreferredSize(buttonSize);
		////newSong.addActionListener(event -> newMusic());
		////tutorialPanel.add(newSong);

		// Constrain second back button
		layout.putConstraint(left, back_2, buttonSeperation, left, tutorialPanel);
		layout.putConstraint(top, back_2, buttonSeperation, top, tutorialPanel);
		
		// Constrain tutorial mute button
		////layout.putConstraint(left, mute, buttonSeperation, left, tutorialPanel);
		////layout.putConstraint(bottom, mute, -buttonSeperation, bottom, tutorialPanel);
		
		// Constrain tutorial new song button
		////layout.putConstraint(left, newSong, buttonSeperation, right, mute);
		////layout.putConstraint(bottom, newSong, -buttonSeperation, bottom, tutorialPanel);

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
                back_3.setIcon(backIcon);
                back_3.setHorizontalTextPosition(JButton.CENTER);
                back_3.setVerticalTextPosition(JButton.CENTER);
		back_3.setFont(font);
                back_3.setForeground(Color.black);
		back_3.setPreferredSize(buttonSize);
		back_3.addActionListener(event -> showPanel(introPanel));
		creditsPanel.add(back_3);
		
		// Create credits mute button
		////mute = new JButton(muteTitle);
		////mute.setFont(font);
		////mute.setPreferredSize(buttonSize);
		////mute.addActionListener(event -> stopMusic());	
		////creditsPanel.add(mute);
		
		// Create credits new song button
		////newSong = new JButton(newSongTitle);
		////newSong.setFont(font);
		////newSong.setPreferredSize(buttonSize);
		////newSong.addActionListener(event -> newMusic());
		////creditsPanel.add(newSong);

		// Add background image
		creditsPanel.add(creditsBackground);
		creditsPanel.setComponentZOrder(creditsBackground, creditsPanel.getComponentCount() - 1);

		// Constraint third back button
		layout.putConstraint(left, back_3, buttonSeperation, left, creditsPanel);
		layout.putConstraint(top, back_3, buttonSeperation, top, creditsPanel);
		
		// Constrain credits mute button
		////layout.putConstraint(left, mute, buttonSeperation, left, creditsPanel);
		////layout.putConstraint(bottom, mute, -buttonSeperation, bottom, creditsPanel);
		
		// Constrain credits new song button
		////layout.putConstraint(left, newSong, buttonSeperation, right, mute);
		////layout.putConstraint(bottom, newSong, -buttonSeperation, bottom, creditsPanel);

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
	private void showPuzzle(String fileName, JPanel previousPanel, int levelIndex) {

		// Declare and initialize star counter
		int starCounter = 0;
		
		// Count level 1 stars
		for(int i = 0; i < levelStars[0].length; i++)
			if(levelStars[0][i].isVisible())
				starCounter++;
		
		// If level 2, check if user has completed level 1
		if(levelIndex == 1 && starCounter < 3) {
			
			// Output message to user
			JOptionPane.showMessageDialog(null,
					"Complete level 1 with 3 stars\nor more to unlock this level!");
			
			// End function call
			return;
		}
		
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
		puzzle.initializePanel(levelIndex, levelStars);
		puzzle.getBack().addActionListener(event -> showPanel(previousPanel));

		// Replace the current panel
		remove(currentPanel);
		add(puzzle.getPanel());
		currentPanel = puzzle.getPanel();
                
                // Reconfigure screen
		validate();
		repaint();
	}
	
	// Methods for playing background music
	public static void playMusic() {			
	
		String random = (musicArray[new Random().nextInt(musicArray.length)]);
		File file = new File(random);			
				
		try {			
			clip = AudioSystem.getClip();
			// getAudioInputStream() also accepts a File or InputStream
			ais = AudioSystem.getAudioInputStream(file);
					
			// calculate duration of clip
			javax.sound.sampled.AudioFormat format = ais.getFormat();
			long audioFileLength = file.length();
			int frameSize = format.getFrameSize();
			long frameRate = (long) format.getFrameRate();
			long durationInSeconds = (audioFileLength / (frameSize * frameRate));
			////System.out.println("Clip length is " + durationInSeconds + " seconds.");
			////System.out.println(file.getName());
					
		} catch (UnsupportedAudioFileException ex) {
			//Logger.getLogger(PuzzleSound2.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			//Logger.getLogger(PuzzleSound2.class.getName()).log(Level.SEVERE, null, ex);
		} catch (LineUnavailableException ex) {
			//Logger.getLogger(PuzzleSound2.class.getName()).log(Level.SEVERE, null, ex);
		}	
			
		try {					
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);			
			Thread.sleep(durationInSeconds*1000);						
								
		} catch (IOException ex) {
			//Logger.getLogger(PuzzleSound2.class.getName()).log(Level.SEVERE, null, ex);
		} catch (LineUnavailableException ex) {
			//Logger.getLogger(PuzzleSound2.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
	}
		
	public void stopMusic() {
		clip.stop();
		clip.close();	
	}
	
	public void newMusic() {
		clip.stop();
		clip.close();
		playMusic();
	}
}

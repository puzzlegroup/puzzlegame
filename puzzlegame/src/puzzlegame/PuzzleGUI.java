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
    private static final int NUM_OF_AREAS = 1;
    private static final int NUM_OF_LEVELS = 2;
    private JButton intro;
    private JButton play;
    private JButton tutorial;
    private JButton credits;
    private JButton creditsBack;
    private JButton mapsBack;
    private JButton levelsBack;
    private JButton[] mapAreas = new JButton[NUM_OF_AREAS];
    private JButton[] levels = new JButton[NUM_OF_LEVELS];
    private JTextArea goal;
    private JTextArea howTo;
    
    
    // Declare screen panels
    private JPanel introPanel = new JPanel();
    private JPanel mapPanel = new JPanel();
    private JPanel tutorialPanel = new JPanel();
    private JPanel creditsPanel = new JPanel();
    private JPanel[] areaPanels = new JPanel[NUM_OF_AREAS]; // holds 2 areas [1,2]
    
    
    // Declare utility variables
    private String[] areas = new String[NUM_OF_AREAS];
    private String[] puzzleNames = new String[NUM_OF_LEVELS];

    // Main program
    public static void main(String[] args) {

        // Create GUI
        PuzzleGUI GUI = new PuzzleGUI();
    }

    // GUI constructor
    public PuzzleGUI() {

        /**
         * SETUP
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
        String introTitle = "Back";
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
        String introImage = "images/space_1.jpg";
        String tutorialImage = "images/space_2.jpg";
        JLabel introBackground = new JLabel(new ImageIcon(introImage));
        JLabel tutorialBackground = new JLabel(new ImageIcon(tutorialImage));

        // Customize GUI elements
        Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
        SpringLayout layout = new SpringLayout();
        introPanel.setLayout(layout);
        tutorialPanel.setLayout(layout);
        creditsPanel.setLayout(layout); // set layout for the ability to put constraints in that panel
        mapPanel.setLayout(layout); // set layout for the ability to put constraints in that panel
        areaPanels[0] = new JPanel(); // initialize area 1 jpanel
        areaPanels[0].setLayout(layout); // set layout for the ability to put constraints in that panel
        
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
         * TUTORIAL SCREEN
         */
        // Create back button
        intro = new JButton(introTitle);
        intro.setFont(font);
        intro.setPreferredSize(buttonSize);
        intro.addActionListener(event -> showIntro(tutorialPanel));
        tutorialPanel.add(intro);

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

        // Constrain back button
        layout.putConstraint(left, intro, buttonSeperation, left, tutorialPanel);
        layout.putConstraint(top, intro, buttonSeperation, top, tutorialPanel);

        // Constrain goal text
        layout.putConstraint(left, goal, buttonWidth, left, tutorialPanel);
        layout.putConstraint(top, goal, buttonWidth, bottom, intro);

        // Constrain how-to text area
        layout.putConstraint(left, howTo, buttonSeperation, right, goal);
        layout.putConstraint(top, howTo, 0, top, goal);

        /**
         * CREDITS SCREEN
         */
        // Create creditsBack button
        creditsBack = new JButton(introTitle);
        creditsBack.setFont(font);
        creditsBack.setPreferredSize(buttonSize);
        creditsBack.addActionListener(event -> showIntro(creditsPanel));
        creditsPanel.add(creditsBack);

        // Constraint creditsBack button
        layout.putConstraint(left, creditsBack, buttonSeperation, left, creditsPanel);
        layout.putConstraint(top, creditsBack, buttonSeperation, top, creditsPanel);

        /**
         * MAP SCREEN
         */
        // create mapsBack button
        mapsBack = new JButton(introTitle);
        mapsBack.setFont(font);
        mapsBack.setPreferredSize(buttonSize);
        mapsBack.addActionListener(event -> showIntro(mapPanel));
        mapPanel.add(mapsBack);

        // create area 1 button
        areas[0] = new String("Area 1");
        mapAreas[0] = new JButton(areas[0]);
        mapAreas[0].setFont(font);
        mapAreas[0].setPreferredSize(buttonSize);
        mapAreas[0].addActionListener(event -> showArea(areas[0], mapPanel));
        mapPanel.add(mapAreas[0]);

        // Constraint mapsBack button
        layout.putConstraint(left, mapsBack, buttonSeperation, left, mapPanel);
        layout.putConstraint(top, mapsBack, buttonSeperation, top, mapPanel);

	// Constraint mapAreas[0] button
        layout.putConstraint(left, mapAreas[0], buttonWidth, left, mapPanel);
        layout.putConstraint(top, mapAreas[0], buttonWidth, bottom, mapsBack);
		
	/**
         * LEVEL SCREEN
         */
        levelsBack = new JButton(introTitle);
        levelsBack.setFont(font);
        levelsBack.setPreferredSize(buttonSize);
        levelsBack.addActionListener(event -> showMap(areaPanels[0]));
        areaPanels[0].add(levelsBack);
        
        // create level 1 button
        puzzleNames[0] = new String("Level 1");
        levels[0] = new JButton(puzzleNames[0]);
        levels[0].setFont(font);
        levels[0].setPreferredSize(buttonSize);
        levels[0].addActionListener(event -> showPuzzle(puzzleNames[0], areaPanels[0]));
        areaPanels[0].add(levels[0]);
        
        // Constraint levesBack button
        layout.putConstraint(left, levelsBack, buttonSeperation, left, areaPanels[0]);
        layout.putConstraint(top, levelsBack, buttonSeperation, top, areaPanels[0]);
        
        // Constraint mapAreas[0] button
        layout.putConstraint(left, levels[0], buttonWidth, left, mapPanel);
        layout.putConstraint(top, levels[0], buttonWidth, bottom, levelsBack);
        
        
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
        repaint();
    }

    // Method for showing map screen
    private void showMap(JPanel currentPanel) {

        // Replace the current panel
        remove(currentPanel);
        add(mapPanel);
        validate();
        repaint();
    }

    // Method for showing area screen
    private void showArea(String area, JPanel currentPanel) {

        // Replace the current panel
        remove(currentPanel);
        // switch
        switch(area) {
            case "Area 1":
                add(areaPanels[0]);
                break;
            default:
                add(mapPanel);
                break;
        }
        
        validate();
        repaint();

    }

    // Method for showing puzzle screen
    private void showPuzzle(String puzzleName, JPanel currentPanel) {

        Puzzle puzzle = new Puzzle(puzzleName);
        
        // Replace the current panel
        remove(currentPanel);
        // switch
        switch(puzzleName) {
            case "Level 1":
                add(puzzle.getPanel());
                System.out.println("Level 1");
                break;
            default:
                add(introPanel);
                break;
        }
        
        validate();
        repaint();
        
    }

    // Method for showing game tutorial
    private void showTutorial(JPanel currentPanel) {

        // Replace the current panel
        remove(currentPanel);
        add(tutorialPanel);
        validate();
        repaint();
    }

    // Method for showing game credits
    private void showCredits(JPanel currentPanel) {

        // Replace the current panel
        remove(currentPanel);
        add(creditsPanel);
        validate();
        repaint();
    }

}

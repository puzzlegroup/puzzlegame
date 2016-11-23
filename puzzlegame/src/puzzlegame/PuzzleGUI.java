/* Copyright (C) John Celona, Vivian Murga, Brittney Pope, Michael McKenzie, Nick Buehre,
 * John Mask, and Ryan Hutchinson - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by John Celona, Vivian Murga, Brittney Pope, Michael McKenzie, Nick Buehre,
 * John Mask, and Ryan Hutchinson, November 2016
 */
package puzzlegame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author author
 */
public class PuzzleGUI extends JFrame {

    // Declare GUI variables
    private JButton play; // main screen play button
    private JButton howToPlay; // main screen how to play button
    private JButton credits; // main screen credits button
    private JButton creditsBack; // credits screen button

    private JPanel introOptions = new JPanel(); // holds main screen components
    private JPanel creditOptions = new JPanel(); // holds credits screen components 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PuzzleGUI gui = new PuzzleGUI();
        
        gui.showIntro(); // call showIntro
        
    } // end main

    public PuzzleGUI() {

        // set frame properties
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // set title
        setTitle("LogicQuest");

    }

    /**
     * showIntro()
     */
    private void showIntro() {

        // declare and initialize frame variables
        play = new JButton("Play");
        howToPlay = new JButton("How To Play");
        credits = new JButton("Credits");
        introOptions = new JPanel();

        // add buttons to panel
        introOptions.add(play);
        introOptions.add(howToPlay);
        introOptions.add(credits);

        // button action listeners
        credits.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                showCredits(); // call showCredits
            }
        });

        // add panel to frame
        add(introOptions, BorderLayout.SOUTH);

        setVisible(true); // show frame

    }

    /**
     * showCredits()
     */
    private void showCredits() {

        creditsBack = new JButton("Back");
        creditsBack.setBounds(100, 100, 50, 50);
        creditOptions.add(creditsBack);

        getContentPane().removeAll();
        getContentPane().repaint();
        

        // button action listeners
        creditsBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // remove components
                creditOptions.remove(creditsBack);
        
                // call showIntro
                showIntro();
            }
        });

        // add panel to frame
        add(creditOptions, BorderLayout.SOUTH);

        setVisible(true); // show frame

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttondemo;
import java.awt.*; 
import java.awt.image.BufferedImage;
import javax.swing.*;

class ButtonFrame extends JFrame
{
    private static final long serialVersionUID = 1L;
  int buttonWidth = 120;
  int buttonHeight = 20;
Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
  // constructor for ButtonFrame
  ButtonFrame(String title) 
  {
    super( title );                     
    setLayout( new FlowLayout() );     
    
    
    ImageIcon icon2 = (new ImageIcon(getClass().getClassLoader().getResource("nebula.jpg")));
       Image img = icon2.getImage();
Image newimg = img.getScaledInstance(buttonWidth,buttonHeight,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
    JButton voyButton = new JButton(newIcon);
    voyButton.setPreferredSize(buttonSize);
    add(voyButton);
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );   
  }
}

public class ButtonDemo
{
  public static void main ( String[] args )
  {
    ButtonFrame frm = new ButtonFrame("Button Demo");

    frm.setSize( 300, 300 );     
    frm.setVisible( true );   
  }
}

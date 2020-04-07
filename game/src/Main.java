
/**
* @project         : COSC 3011 Group Project
* 
* @authors         : Chance McCormick, Brent Pearce, Tim Bourque, Coghan Spery,
* and Stephen Williams
* 
* @purpose         : Main class, stages the game and creates the display
* window
* 
* @date_created    : January 29, 2020
* 
* @last_modified   : March 13, 2020
**/

import javax.swing.*;
import java.awt.*;

public class Main {

    // Probably should declare any buttons here
    // public JButton lbutton, rbutton, mbutton;

    public static void main(String[] args) {
        // This is the play area
        // HEY FIX THE NAME, WHAT IS ****YOUR**** GROUP
        GameWindow game = new GameWindow("Group Papa aMaze");

        // have to override the default layout to reposition things!!!!!!!
        // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // System.out.print(screenSize); // display screen resolution in console
        game.setSize(new Dimension(1282, 720));

        // So the debate here was, do I make the GameWindow object the game
        // or do I make main() the game, manipulating a window?
        // Should GameWindow methods know what they store?
        // Answer is, have the "game" do it.

        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use colors that are viewable on ALL DEVICES, Stay away from yellows,
        // do
        // NOT use black or white.
        game.getContentPane().setBackground(new Color(66, 135, 245));
        game.setUp();

        // May or may not need this

        game.setVisible(true);

        // You will HAVE to read some documentation and catch exceptions so get
        // used
        // to it.

        try {
            // The 4 that are installed on Linux here
            // May have to test on Windows boxes to see what is there.
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // This is the "Java" or CrossPlatform version and the default
            // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            // Linux only
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            // really old style Motif
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            // handle possible exception
        } catch (ClassNotFoundException e) {
            // handle possible exception
        } catch (InstantiationException e) {
            // handle possible exception
        } catch (IllegalAccessException e) {
            // handle possible exception
        }

    }

};

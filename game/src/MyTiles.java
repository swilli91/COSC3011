
/**
* @project         : COSC 3011 Group Project
* 
* @authors         : Chance McCormick, Brent Pearce, Tim Bourque, Coghan Spery,
* and Stephen Williams
* 
* @purpose         : Creates the game tiles and the game board
* 
* @date_created    : January 29, 2020
* 
* @last_modified   : April 2, 2020
**/

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MyTiles extends JPanel implements MouseListener {
    public static final long serialVersionUID = 1;

    /*
     * Initializes all tiles for use in GameWindow
     */
    private int[] coords = new int[4];
    private int pieceID;
    public Mover pieceMover;
    boolean isOccupied;
    

    public MyTiles(int size, Color color, int[] coord) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	tileSetUp(size, color, coord);
        addMouseListener(this);
        
    }

    public void setCoords(int[] coord) {
        for (int i = 0; i < 4; i++) {

            coords[i] = coord[i];
        }
    }

    public JPanel tileSetUp(int size, Color color, int[] coord) {
        this.setPreferredSize(new Dimension(size, size));
        this.setBackground(color);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setMinimumSize(new Dimension(size, size));
        this.setCoords(coord);
        return this;
    }
    
    public void setIsOccupied(boolean b) {
    	this.isOccupied = b;
    }
    
    public void setMover(Mover mover) {
    	pieceMover = mover;
    }

    public int getCoord(int i) {
        return coords[i];
    }
    
    public void setPieceID(int id) {
    	this.pieceID = id;
    }
    
    public int getPieceID() {
    	return this.pieceID;
    }
	
	public void mouseClicked(MouseEvent e) {/*used mousePressed instead*/}

	
	public void mouseEntered(MouseEvent e) {/*not needed*/}

	
	public void mouseExited(MouseEvent e) {/*not needed*/}

	
	public void mousePressed(MouseEvent e) {
		if(!this.isOccupied) {
			pieceMover.notifyMover(pieceID);
		}
	}
	
	public void mouseReleased(MouseEvent e) {/*not needed*/}
    
}

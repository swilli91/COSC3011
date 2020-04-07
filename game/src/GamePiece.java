
/**
* @project         : COSC 3011 Group Project
* 
* @authors         : Chance McCormick, Brent Pearce, Tim Bourque, Coghan Spery,
* and Stephen Williams
* 
* @purpose         : Game piece creation for tiles used in game
* 
* @date_created    : January 29, 2020
* 
* @last_modified   : March 13, 2020
**/
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GamePiece extends JPanel implements MouseListener {

    public static final long serialVersionUID = 1;
    

    private int[] coords = { 0, 0 };
    private String labelShow;
    private int label;
    private JLabel gamePieceLabel;
    private Mover pieceMover;
    private int pieceID;
    private int storedAt; 
    
    private Color gamePieceColor = new Color(46, 201, 80);

    public GamePiece(int size, Color color, int[] coord, int i)

    {
        gamePieceSetUp(size, color, coord, i);
        addMouseListener(this);
        
    }

    public JPanel gamePieceSetUp(int size, Color color, int[] coord, int i) {
        this.setPreferredSize(new Dimension(size, size));
        this.setMinimumSize(new Dimension(size, size));
        this.setBackground(color);
        this.setLabel(i);
        this.setCoords(coord);
        return this;

    }

    public void setCoords(int[] coord) {
        coords[0] = coord[0];
        coords[1] = coord[1];
    }

    public int getCoords(int i) {
        return coords[i];
    }

    public void setLabel(int i) {
        if (i <= 9) {
            labelShow = "0" + i;
        } else {
            labelShow = "" + i;
        }
        label = i;
        gamePieceLabel = new JLabel(labelShow);
        add(gamePieceLabel);
    }

    public int getLabel() {
        return label;
    }
    
    public void setMover(Mover mover) {
    	pieceMover = mover;
    }
    
    public void setPieceID(int id) {
    	pieceID = id;
    }

    public void setStoredAt(int id) {
    	storedAt = id;
    }
    
    public int getStoredAt() {
    	return storedAt;
    }
    
    public void mouseClicked(MouseEvent e) {
		//does nothing using mousePressed instead;
	}

	
	public void mouseEntered(MouseEvent e) {
		//not needed
	}

	
	public void mouseExited(MouseEvent e) {
		// not needed
		
	}

	
	public void mousePressed(MouseEvent e) {
		
		pieceMover.notifyMover(this.pieceID);
	}

	
	public void mouseReleased(MouseEvent e) {
		//not needed
	}
	
    
    public void paintComponent (Graphics g)
    {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(gamePieceColor);
        graphics.fillRect(0, 0, 75, 75);
    }
}

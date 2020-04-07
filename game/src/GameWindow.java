
/**
* @project         : COSC 3011 Group Project
* 
* @authors         : Chance McCormick, Brent Pearce, Tim Bourque, Coghan Spery,
* and Stephen Williams
* 
* @purpose         : Handle the game window creation, rules, and piece movement
* 
* @date_created    : January 29, 2020
* 
* @last_modified   : Apirl 2, 2020
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameWindow extends JFrame implements ActionListener {
    // size for the tiles and buttons
    public Dimension tileSize = new Dimension(50, 50);
    public int size = 75;

    private GamePiece[] gamePiece = new GamePiece[16];
    private MyTiles[][] gameBoard = new MyTiles[4][4];
    private MyTiles[] pieceHolders = new MyTiles[16];
    private Mover pieceMover = new Mover();
    JPanel gameBoard_ = new JPanel();

    // for the colors
    public Color gameBoardColor = new Color(31, 125, 52);
    public Color sideTileColor = new Color(176, 53, 44);
    public Color gamePieceColor = new Color(46, 201, 80);

    GridBagConstraints gbc = new GridBagConstraints();
    private int x;
    private int y;

    /**
     * because it is a serializable object, need this or java complains <b>a
     * lot</b>, the ID can be any integer.
     */
    public static final long serialVersionUID = 1;

    /*
     * This is so I can try changing the starting point easily. Can certainly be
     * left out altogether.
     */
    private int startAt = 1;

    /**
     * Constructor sets the window name using super(), changes the layout, which
     * you really need to read up on, and maybe you can see why I chose this
     * one.
     *
     * @param s
     */

    public GameWindow(String s) {
        super(s);
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

    }

    /**
     * For the buttons
     * 
     * @param e is the ActionEvent
     * 
     *          BTW can ask the event for the name of the object generating
     *          event. The odd syntax for non-java people is that "exit" for
     *          instance is converted to a String object, then that object's
     *          equals() method is called.
     */

    public void actionPerformed(ActionEvent e) {
        if ("exit".equals(e.getActionCommand()))
            System.exit(0);
        if ("reset".equals(e.getActionCommand()))
            //System.out.println("reset pressed\n");
            pieceMover.resetGame();
        if ("new".equals(e.getActionCommand()))
            System.out.println("new pressed\n");
    }

    /**
     * Establishes the initial board
     */

    public void setUp() {
        /*
         * actually create the array for elements, make sure it is big enough
         * 
         * Need to play around with the dimensions and the gridx/y values These
         * constraints are going to be added to the pieces/parts I stuff into
         * the "GridBag". YOU CAN USE any type of constraints you like. Just
         * make it work.
         * 
         * GridBagConstraints basic = new GridBagConstraints();
         * basic.gridx=startAt; basic.gridy=0; basic.gridwidth=1;
         * basic.gridheight=1;
         * 
         * This is really a constant in the GrdiBagConstraints. This way we
         * don't need to know what type/value it is
         * basic.fill=GridBagConstraints.BOTH;
         */
    	
        gbc.gridx = startAt;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        
        
        try {
            this.addTiles();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //this.addTiles();
        this.addGameBoard();
        this.addButtons();
        this.pieceMover.setGamePieces(gamePiece);
		this.pieceMover.setGameboard(gameBoard);
		this.pieceMover.setSideHolders(pieceHolders);
    }

    /**
     * 
     * Used by setUp() to configure the buttons on a button bar and add it to
     * the gameBoard
     * 
     */

    public Button makeButton(String name, GridBagLayout gridBag,
            GridBagConstraints gbc) {
        Button button = new Button(name);
        gridBag.setConstraints(button, gbc);
        gbc.anchor = GridBagConstraints.NORTH;
        button.setPreferredSize(new Dimension(100, 50));
        button.setMinimumSize(new Dimension(100, 50));
        return button;
    }

    /**
     * addButtons() isused by setUp() to add a JPanel at the top of the screen.
     * This menuPanel acts as a container for the buttons that are created in
     * the makeButton() class.
     * 
     */

    public void addButtons() {
        JPanel menuPanel = new JPanel();
        menuPanel.setMinimumSize(new Dimension(650, 100));
        menuPanel.setBackground(new Color(42, 86, 156));

        GridBagLayout buttonLay = new GridBagLayout();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Button lbutton = makeButton("New Game", buttonLay, gbc);
        Button mbutton = makeButton("Reset", buttonLay, gbc);
        Button rbutton = makeButton("Quit", buttonLay, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        add(menuPanel, gbc);

        menuPanel.add(lbutton, gbc);
        menuPanel.add(mbutton, gbc);
        menuPanel.add(rbutton, gbc);
        rbutton.setActionCommand("exit");
        rbutton.addActionListener(this);
        mbutton.setActionCommand("reset");
        mbutton.addActionListener(this);

    }



    public void addTiles() throws FileNotFoundException, IOException {
        
        //MZEFileReader fileData = new MZEFileReader();

        gbc.weightx = 1;
        gbc.weighty = 1;
        int[] tileCoord = { 0, 0, 0, 0 };

        for (int i = 0; i <= 15; i++) {
            // loop to add the GamePiece objects

            gbc.fill = GridBagConstraints.NONE;
            gbc.insets = new Insets(1, 1, 1, 1);
            
            // if i < 8 then gridx=0; else gridx=2
            gbc.gridx = i < 8 ? 0 : 2;
            tileCoord[0] = i < 8 ? 133 : 1058;

            // if i < 8 then gridy=(i+1);else gridy=(i-7)
            gbc.gridy = i < 8 ? (i + 1) : (i - 7);
            tileCoord[1] = i < 8 ? (i * 77) + 64 : ((i - 8) * 77) + 64;

            GamePiece piece = new GamePiece(size, gamePieceColor, tileCoord, i);
            piece.setPieceID(i);
            piece.setMover(pieceMover);
            piece.setStoredAt(100+i);
            add(piece, gbc);

            gamePiece[i] = piece;

     //       handleDrag(gamePiece[i]);
        }

        for (int i = 0; i <= 15; i++)
        //for loop to add the side holders
        {
            gbc.fill = GridBagConstraints.NONE;
            gbc.insets = new Insets(1, 1, 1, 1);

            // if i < 8 then gridx=0; else gridx=2
            gbc.gridx = i < 8 ? 0 : 2;
            tileCoord[0] = i < 8 ? 133 : 1058;
            
            // if i < 8 then gridy=(i+1);else gridy=(i-7)
            gbc.gridy = i < 8 ? (i + 1) : (i - 7);
            // tileCoord[1] = gbc.gridy;
            tileCoord[1] = i < 8 ? (i * 77) + 64 : ((i - 8) * 77) + 64;

            MyTiles pane = new MyTiles(size, sideTileColor, tileCoord);

            pane.setBorder(BorderFactory.createLineBorder(Color.black));
            pane.setPieceID(100+i);
            pane.setMover(pieceMover);
            pane.setIsOccupied(true);
            add(pane, gbc);

            pieceHolders[i] = pane;
            
           
        }
    }

    private void addGameBoard() {

        gameBoard_.setPreferredSize(new Dimension(300, 300));
        gameBoard_.setBackground(gameBoardColor);
        gameBoard_.setLayout(new GridBagLayout());

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        add(gameBoard_, gbc);

        JPanel gbTiles = new JPanel();
        gbTiles.setPreferredSize(tileSize);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        int tileCoord[] = { 0, 0, 0, 0 };

        // loop to add game board
        for (int i = 0; i < 4; i++) {
            gbc.gridy = i;
            tileCoord[1] = (i * 75) + 221;
            tileCoord[3] = ((i + 1) * 75) + 221;

            for (int j = 0; j < 4; j++) {
                gbc.gridx = j;
                tileCoord[0] = (j * 75) + 483;
                tileCoord[2] = ((j + 1) * 75) + 483;

                MyTiles pane = new MyTiles(size, gameBoardColor, tileCoord);
                int k = 200 + (i*10) + j;
                pane.setPieceID(k);
                pane.setMover(pieceMover);
                pane.setIsOccupied(false);
                gameBoard[i][j] = pane;

                gameBoard_.add(pane, gbc);        
            }
        }
        return;
    }
};

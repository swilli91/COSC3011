import java.awt.Color;

import javax.swing.BorderFactory;

///** 
//* @project         : COSC 3011 Group Project
//* 
//* @authors         : Chance McCormick, Brent Pearce, Tim Bourque, Coghan
//* Spery, and Stephen Williams
//* 
//* @purpose         : helper class to control movement of GamePiece objects.
//* 
//* @date_created    : March 26, 2020
//* 
//* @last_modified   : March 26, 2020
//**/

public class Mover {
	private int pieceToMoveID;
	private int moveToID;
	private GamePiece[] gamePieces;
	private MyTiles[][] gameboard;
	private MyTiles[] sideHolders;
	
	public Mover()
	{	//Initialize all the attributes to -1(the null state)
		this.pieceToMoveID = -1;
		this.moveToID =  -1;
	}
	
	public void setGamePieces(GamePiece[] gamePieces)	{
		this.gamePieces = gamePieces;
	}
	
	public void setGameboard(MyTiles[][] gameboard) {
		this.gameboard = gameboard;
	}
	
	public void setSideHolders(MyTiles[] sideHolders) {
		this.sideHolders = sideHolders;
	}
	
	public void notifyMover(int piece) 
	{
		if (piece < 100) {
			pieceToMoveID = piece;
		}
			
		else if((piece >= 100) && (pieceToMoveID != -1)) {
			moveToID = piece;
			
			if(piece<200) {
				moveToHolders();
			}
			else {
				moveToGameboard();
			}

		}
		else {
			resetMover();
		}
		
	}
	
	private void resetMover() {
		pieceToMoveID = -1;
		moveToID = -1;	
	}
	
	
	
	private void moveToHolders() {
		
		int[] indx1 = {0,0};
		int[] indx2 = {0,0};
		
		// save the last holder location in a temp var
		int tempIDHolder = gamePieces[pieceToMoveID].getStoredAt();
		
		//get the needed index to add the piece to the new holder
		idToIndex(tempIDHolder, indx1);
		
		// remove the piece from the old holder
		remove(tempIDHolder, indx1);
		
		// add the piece to the new holder
		add(moveToID, indx2);
		
		resetMover();
	}
	
	private void remove(int tempIDHolder, int[] indx1)
	{
	       if(tempIDHolder < 200) {
	            sideHolders[indx1[0]].remove(gamePieces[pieceToMoveID]);
	            sideHolders[indx1[0]].setIsOccupied(false);
	            sideHolders[indx1[0]].setBorder(
	                    BorderFactory.createLineBorder(Color.black));
	            sideHolders[indx1[0]].revalidate();
	            sideHolders[indx1[0]].repaint();
	        }
	        else {
	        
	            gameboard[indx1[0]][indx1[1]].remove(gamePieces[pieceToMoveID]);
	            gameboard[indx1[0]][indx1[1]].setIsOccupied(false);
	            gameboard[indx1[0]][indx1[1]].setBorder(
	                    BorderFactory.createLineBorder(Color.black));
	            gameboard[indx1[0]][indx1[1]].repaint();
	            gameboard[indx1[0]][indx1[1]].revalidate();
	        }
	}
	
	private void add(int moveToID, int[] indx2)
	{
	    if(moveToID < 200)
	    {
	        idToIndex(moveToID, indx2);
	        sideHolders[indx2[0]].add(gamePieces[pieceToMoveID]);
	        sideHolders[indx2[0]].setIsOccupied(true);
	        gamePieces[pieceToMoveID].setStoredAt(moveToID);
	        sideHolders[indx2[0]].setBorder(BorderFactory.createEmptyBorder());
	        sideHolders[indx2[0]].revalidate();
	        sideHolders[indx2[0]].repaint();
	    }else
	    {
	        idToIndex(moveToID, indx2);
	        gameboard[indx2[0]][indx2[1]].add(gamePieces[pieceToMoveID]);
	        gameboard[indx2[0]][indx2[1]].setIsOccupied(true);
	        gamePieces[pieceToMoveID].setStoredAt(moveToID);
	        gameboard[indx2[0]][indx2[1]].setBorder(
	                BorderFactory.createEmptyBorder());
	        gameboard[indx2[0]][indx2[1]].revalidate();
	        gameboard[indx2[0]][indx2[1]].repaint();
	    }
	    
	}
	private void moveToGameboard() {
		
		int[] indx1 = {0,0};
		int[] indx2 = {0,0};
		
		// save the last holder location in a temp var
		int tempIDHolder = gamePieces[pieceToMoveID].getStoredAt();
		
		// get the needed index to add the piece to the new holder
		idToIndex(tempIDHolder, indx1);
		
		// remove the piece from the old holder
		remove(tempIDHolder, indx1);
				
		// add the piece to the new holder
		add(moveToID, indx2);
		
		resetMover();
	}
	
	private void idToIndex(int id, int[] indx) {
		if(id < 200) {
			indx[0] = id % 100;
		}
		
		else {
			indx[1] = id % 10;
			int temp = id - indx[0];
			indx[0] = (temp%100)/10;
		}

	}
	
	public void resetGame()
	{
	    for(int i=0;i<15;i++)
	    {
	        //gamePieces[i].getStoredAt()
	        if((gamePieces[i].getStoredAt()-100) != i)
	        {
	            pieceToMoveID = i;
	            moveToID = 100 + i;
	            
	            moveToHolders();
	        }
	    }
	}
};

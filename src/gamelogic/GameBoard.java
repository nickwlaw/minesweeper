package gamelogic;
import java.util.Arrays;

import ui.Console;

public class GameBoard {

	private String[][] gameDisplay;
	private MineField mineField;
	private int correctFlags = 0;
	private final int TOTAL_MINES = 15;

	public GameBoard() {
		mineField = new MineField();
		gameDisplay = new String[10][10];
		for (String[] row : gameDisplay)
			Arrays.fill(row, "///");
	}
	
	public boolean flagGameSpace(int x, int y) {
		if (!gameDisplay[x][y].equals("-@-") && mineField.getMineFieldValue(x, y) == -1)
			correctFlags++;
		if (gameDisplay[x][y].equals("-@-") && mineField.getMineFieldValue(x, y) == -1)
			correctFlags--;
		if (gameDisplay[x][y].equals("-@-"))
			gameDisplay[x][y] = "///";
		else if (gameDisplay[x][y].equals("///"))
			gameDisplay[x][y] = "-@-";

		if (correctFlags == TOTAL_MINES) {
			Console.println("");
			Console.println("You found all of the mines!");
			return false;
		}
		return true;
	}

	public boolean checkGameSpace(int x, int y) {
		// comment out next two lines to demonstrate an ArrayIndexOutOfBoundsException
		if (x < 0 || x > 9 || y < 0 || y > 9)
			return true;
		// comment out next two lines lines to demonstrate the dangers of recursion
		if (gameDisplay[x][y].equals("   "))
			return true;
		int mineFieldValue = mineField.getMineFieldValue(x, y);
		
		if (mineFieldValue == -1) {
			for (int i = 0; i <= 9; i++) {
				for (int j = 0; j <= 9; j++) {
					if (mineField.getMineFieldValue(i, j) == -1)
						gameDisplay[i][j] = "O~*";
				}
			}
			Console.println("");
			Console.println("Oh no... you detonated a mine!");
			return false;
		}
		if (mineFieldValue == 0) {
			gameDisplay[x][y] = "   ";
			if (y + 1 <= 9)
				checkGameSpace(x, y + 1);
			if (x + 1 <= 9 && y + 1 <= 9)
				checkGameSpace(x + 1, y + 1);
			if (x + 1 <= 9)
				checkGameSpace(x + 1, y);
			if (x + 1 <= 9 && y - 1 >= 0)
				checkGameSpace(x + 1, y - 1);
			if (y - 1 >= 0)
				checkGameSpace(x, y - 1);
			if (x - 1 >= 0 && y - 1 >= 0)
				checkGameSpace(x - 1, y - 1);
			if (x - 1 >= 0)
				checkGameSpace(x - 1, y);
			if (x - 1 >= 0 && y + 1 <= 9)
				checkGameSpace(x - 1, y + 1);
		}
		if (mineFieldValue > 0)
			gameDisplay[x][y] = " " + mineFieldValue + " ";
		return true;
	}
	
	@Override
	public String toString() {

		String gameBoardHeader =  "     0   1   2   3   4   5   6   7   8   9  \n";
		String gameBoardDivider = "   +---+---+---+---+---+---+---+---+---+---+\n";
		String gameBoardFormatted = gameBoardHeader + gameBoardDivider;
		for (int row = 0; row <= 9; row++) {
			String gameBoardRow = " " + row + " |";
			for (int column = 0; column <= 9; column++) {
				gameBoardRow += gameDisplay[column][row] + "|";
			}
			gameBoardRow += "\n" + gameBoardDivider;
			gameBoardFormatted += gameBoardRow;
		}
		return gameBoardFormatted;

		
// I left in the following lines of code to demonstrate how much more clean and effecient the nested for loop is.

//		String gameBoardRow0 =    " 0 |";
//		for (int i = 0; i <= 9; i++) {
//			gameBoardRow0 += gb[i][0]+"|";
//		}
//		gameBoardRow0 += "\n";
//		String gameBoardRow1 =    " 1 |";
//		for (int i = 0; i <= 9; i++) {
//			gameBoardRow1 += gb[i][1]+"|";
//		}
//		gameBoardRow1 += "\n";
//		String gameBoardRow2 =    " 2 |";
//		for (int i = 0; i <= 9; i++) {
//			gameBoardRow2 += gb[i][2]+"|";
//		}
//		gameBoardRow2 += "\n";
//		String gameBoardRow3 =    " 3 |";
//		for (int i = 0; i <= 9; i++) {
//			gameBoardRow3 += gb[i][3]+"|";
//		}
//		gameBoardRow3 += "\n";
//		String gameBoardRow4 =    " 4 |";
//		for (int i = 0; i <= 9; i++) {
//			gameBoardRow4 += gb[i][4]+"|";
//		}
//		gameBoardRow4 += "\n";
//		String gameBoardRow5 =    " 5 |";
//		for (int i = 0; i <= 9; i++) {
//			gameBoardRow5 += gb[i][5]+"|";
//		}
//		gameBoardRow5 += "\n";
//		String gameBoardRow6 =    " 6 |";
//		for (int i = 0; i <= 9; i++) {
//			gameBoardRow6 += gb[i][6]+"|";
//		}
//		gameBoardRow6 += "\n";
//		String gameBoardRow7 =    " 7 |";
//		for (int i = 0; i <= 9; i++) {
//			gameBoardRow7 += gb[i][7]+"|";
//		}
//		gameBoardRow7 += "\n";
//		String gameBoardRow8 =    " 8 |";
//		for (int i = 0; i <= 9; i++) {
//			gameBoardRow8 += gb[i][8]+"|";
//		}
//		gameBoardRow8 += "\n";
//		String gameBoardRow9 =    " 9 |";
//		for (int i = 0; i <= 9; i++) {
//			gameBoardRow9 += gb[i][9]+"|";
//		}
//		gameBoardRow9 += "\n";

//		String gameBoardFormatted = gameBoardHeader
//								  + gameBoardDivider
//								  + gameBoardRow0
//								  + gameBoardDivider
//								  + gameBoardRow1
//								  + gameBoardDivider
//								  + gameBoardRow2
//								  + gameBoardDivider
//								  + gameBoardRow3
//								  + gameBoardDivider
//								  + gameBoardRow4
//								  + gameBoardDivider
//								  + gameBoardRow5
//								  + gameBoardDivider
//								  + gameBoardRow6
//								  + gameBoardDivider
//								  + gameBoardRow7
//								  + gameBoardDivider
//								  + gameBoardRow8
//								  + gameBoardDivider
//								  + gameBoardRow9
//								  + gameBoardDivider;
//		return gameBoardFormatted;

	}
}

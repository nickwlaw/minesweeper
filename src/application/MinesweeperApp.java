package application;
import gamelogic.GameBoard;
import ui.Console;

public class MinesweeperApp {

	public static void main(String[] args) {
		Console.println("Welcome to Minesweeper!\n");
		Console.println("The goal is to flag all of the mines on the map.");
		Console.println("Dig on the map to uncover clues about where the mines are.");
		Console.println("You can dig at a space by choosing a column and a row.\n");
		Console.println("If you see a number... LOOK OUT!!");
		Console.println("There are that many mines surrounding this space.\n");
		Console.println("Flag a space by entering \"10\" for the dig column and choosing a column and row to flag.");
		Console.println("... or you detonate a mine.\n");
		Console.println("Let's get started!\n");
		boolean cont = true;
		while (cont == true) {
			GameBoard gameBoard = new GameBoard();
			Console.println(gameBoard);
			boolean safeSpace = true;
			while (safeSpace == true) {
				int x = Console.getInt("Choose column to dig: ", 0, 10);
				if (x == 10) {
					Console.println("");
					int x2 = Console.getInt("Choose column to flag: ", 0, 9);
					int y2 = Console.getInt("Choose row to flag:    ", 0, 9);
					safeSpace = gameBoard.flagGameSpace(x2, y2);
				} else {
					int y = Console.getInt("Choose row to dig:    ", 0, 9);
					safeSpace = gameBoard.checkGameSpace(x, y);
				}
				Console.println("");
				Console.println(gameBoard);
				Console.println("");
			}
			cont = Console.askToContinue();
		}
		Console.println("");
		Console.println("Goodbye!");
	}

}

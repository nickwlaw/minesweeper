package gamelogic;

public class MineField {
	
	private int[][] mineField;

	public MineField() {
		mineField = new int[10][10];
		for (int i = 0; i < 15; i++) {
			int x = (int)(Math.random()*10);
			int y = (int)(Math.random()*10);
			if (mineField[x][y] != -1) {
				mineField[x][y] = -1;
				if (x < 9 && mineField[x+1][y] != -1)
					mineField[x+1][y] += 1;
				if (x > 0 && mineField[x-1][y] != -1)
					mineField[x-1][y] += 1;
				if (y < 9 && mineField[x][y+1] != -1)
					mineField[x][y+1] += 1;
				if (y > 0 && mineField[x][y-1] != -1)
					mineField[x][y-1] += 1;
				if (x < 9 && y > 0 && mineField[x+1][y-1] != -1)
					mineField[x+1][y-1] +=1;
				if (x < 9 && y < 9 && mineField[x+1][y+1] != -1)
					mineField[x+1][y+1] +=1;
				if (x > 0 && y < 9 && mineField[x-1][y+1] != -1)
					mineField[x-1][y+1] +=1;
				if (x > 0 && y > 0 && mineField[x-1][y-1] != -1)
					mineField[x-1][y-1] +=1;
				}
			else
				i--;
		}
	}
	
	public int getMineFieldValue(int x, int y) {
		return mineField[x][y];
	}
	
}

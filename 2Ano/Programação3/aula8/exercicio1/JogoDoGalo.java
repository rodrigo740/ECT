package aula8.exercicio1;

public class JogoDoGalo{
	
	private int[][] grid;
	private int next;
	private int count;
	
	public JogoDoGalo(String str) {
		
		grid = new int[3][3];
		count = 0;
		
		if (str.toLowerCase().contentEquals("x")) {
			next = 1;
		}else if (str.toLowerCase().contentEquals("o")) {
			next = 2;
		}
	}
	
	public boolean play(int bID) {
		
		if (checkVictory() != 0) {
			return false;
		}
		
		int x = bID/3;
		int o = bID%3;
		
		if (grid[x][o] != 0) {
			return false;
		}
		
		grid[x][o] = next;
		
		next();
		count++;
		
		return true;
	}
	
	public int checkVictory() {
		
		if (count == 9) {
			return 3;
		}
		
		if (grid[0][0] != 0 && grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
			return grid[0][0];
		}else if (grid[0][2] != 0 && grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0]) {
			return grid[0][2];
		}
		
		for (int i = 0; i < 3; i++) {
			if (grid[i][0] != 0 && grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2]) {
				return grid[i][0];
			}else if (grid[0][i] != 0 && grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i]) {
				return grid[0][i];
			}
		}
		return 0;
	}
	
	public void next() {
		if (next == 1) {
			next = 2;
		}else {
			next = 1;
		}		
	}

	public int getNext() {
		return next;
	}
	
	public static String getString(int i) {
		
		if (i == 1) {
			return "X";
		}else if (i == 2) {
			return "O";
		}else {
			return "";
		}
	}
	
}

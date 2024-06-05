import javax.swing.*;
import java.awt.*;
import java.net.*;
import javax.imageio.*;
import java.io.*;


public class NQueens {
	
	final static int N = 8; // Initializes board size, rows, columns and number of queens
	
	public static void main(String[] args) {
		int[][] board = new int[N][N];
		
		if (solve(board, 0))
			printBoard(board);
		else
			System.out.println("No solution found.");
		
	}
	
	
	
	//******************** Solve NQueens functions ********************
	public static boolean isSafe(int[][]board, int row, int col) {
		
		// Checks left side of row
		for (int i = 0; i < col; i++) {
			if (board[row][i] == 1) {
				return false;
			}
		}
		
		// Checks left side ascending diagonally
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		
		// Checks left side descending diagonally
		for (int i = row, j = col; i < N && j >= 0; i++, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean solve(int[][]board, int col) {
		// Base case
		if (col >= N)
			return true;
		
		// Places queen in each column until its requirements are met
		for (int row = 0; row < N; row++) {
			if (isSafe(board, row, col)) {
				board[row][col] = 1; // Places queen
				if (solve(board, col + 1)) // Recursion to place the other queens, moving onto another column
					return true;
				board[row][col] = 0; // If a solution can't be found, backtrack and remove queen
			}
		}
		
		return false; // Doesn't reach base case then return false
	}
	
	//******************** Swing Function ********************
	public static void printBoard(int board[][]) {
		JFrame frame = new JFrame("Chess Board"); // Holds the entire frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(); // Holds the black and white buttons
        panel.setLayout(new GridLayout(N, N));
        frame.add(panel);
        
        // Declares ImageIcon that holds queen image
        ImageIcon queenIcon = null;
        
        // Try catch block needs to be declared to use URL and ImageIO
        try {
            URL url = new URL("https://static.wikia.nocookie.net/chess/images/4/42/LightQueen.png/revision/latest/scale-to-width/360?cb=20230320152643");
            Image image = ImageIO.read(url); // Reads image from the URL
            Image scaledImage = image.getScaledInstance(50, 50, 50); // Resizes image
            queenIcon = new ImageIcon(scaledImage); // Creates an ImageIcon with the scaled image
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                JButton button = new JButton(); // Initializes JButtons for the squares of the chess board
                
                if ((row + col) % 2 == 0)
                    button.setBackground(Color.WHITE); // Sets white on even positions
                else
                    button.setBackground(Color.BLACK); // Sets black on odd positions

                if (board[row][col] == 1) // Place the queen if there is a 1 in the board
                    button.setIcon(queenIcon);
                    
                panel.add(button);
            }
        }

        frame.pack();
        frame.setSize(500,500); // Set size of window
        frame.setVisible(true);
	}

}	

 
package pt314.blocks.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

=======
import java.io.File;
import java.util.Random;

import javax.swing.BorderFactory;
>>>>>>> a40725cfc0dde6137ad474766052bcb7c3cb4f79
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import pt314.blocks.game.Board;
import pt314.blocks.game.Puzzle;
import pt314.blocks.game.PuzzleLoader;
import pt314.blocks.game.block.Block;
import pt314.blocks.game.block.Direction;
import pt314.blocks.game.block.HorizontalBlock;
import pt314.blocks.game.block.TargetBlock;
import pt314.blocks.game.block.VerticalBlock;

/**
 * Simple GUI test...
 */
public class SimpleGUI extends JFrame implements ActionListener {

<<<<<<< HEAD
	private static int NUM_ROWS;
	private static int NUM_COLS;
	private static String[] boardSetup;
	
	private GameBoard board;
=======
	private Puzzle puzzle;
>>>>>>> a40725cfc0dde6137ad474766052bcb7c3cb4f79
	
	// currently selected block
	private Block selectedBlock;
	private int selectedBlockRow;
	private int selectedBlockCol;

	private GridButton[][] buttonGrid;
	
	private JMenuBar menuBar;
	private JMenu gameMenu, helpMenu;
	private JMenuItem newGameMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem aboutMenuItem;
	
	public SimpleGUI() throws NumberFormatException, IOException, FileNotFoundException {
		super("Blocks");
		FileReader reader = new FileReader("res/puzzles/puzzle-003.txt");
	    BufferedReader inputFile = new BufferedReader(reader);
		String line = null;
		int count = 0;
		
		// Read in file containing number of rows and columns in first line, 
		// the configuration of the rows and columns in the rest of the lines
        while ((line = inputFile.readLine())!= null) {
            if(count == 0) {
            	// Find number of rows and columns for the game board
            	String[] arrayLine= line.split("\\s+");
	            NUM_ROWS = Integer.parseInt(arrayLine[0]);
	            NUM_COLS = Integer.parseInt(arrayLine[1]);
	            if (NUM_ROWS < 1 || NUM_COLS < 1) {
	            	throw new NumberFormatException("Row and column values must be 1 or greater.");
	            }
	            boardSetup = new String[NUM_ROWS];
            }
            else {
            	// Get the configurations of the rows and columns on the board
            	boardSetup[count-1] = line;
            }
            count++;
	    }
		
		initMenus();
		
		loadPuzzle();
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void initMenus() {
		menuBar = new JMenuBar();
		
		gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		newGameMenuItem = new JMenuItem("New game");
		newGameMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
				try {
					new SimpleGUI();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
=======
				loadPuzzle();
>>>>>>> a40725cfc0dde6137ad474766052bcb7c3cb4f79
			}
		});
		gameMenu.add(newGameMenuItem);
		
		gameMenu.addSeparator();
		
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		gameMenu.add(exitMenuItem);
		
		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(SimpleGUI.this,
						"Move the target block (yellow) to the rightmost column of the board.\n"
						+ "Horizontal blocks (blue and yellow) can only move horizontally.\n"
						+ "Vertical blocks (red) can only move vertically.",
						"Sliding blocks!", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		helpMenu.add(aboutMenuItem);
		
		setJMenuBar(menuBar);
	}

	private void loadPuzzle() {
		
		// get random puzzle number
		Random rand = new Random();
		int puzzleNumber = rand.nextInt(3) + 1;
		
		try {
			puzzle = PuzzleLoader.load(puzzleNumber);
			setUpBoard(puzzle);
		}
		catch (Exception e) {
			System.err.println("Error loading puzzle: " + puzzleNumber);
			e.printStackTrace();
			JOptionPane.showMessageDialog(SimpleGUI.this,
					"Error loading puzzle.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
<<<<<<< HEAD
	private void initBoard() {
		board = new GameBoard(NUM_COLS, NUM_ROWS);
		buttonGrid = new GridButton[NUM_ROWS][NUM_COLS];
		char[] cellIdentifier = new char[NUM_COLS];
		int targetBlockCount = 0;
		int targetBlockLocationInRow = 0;
		boolean targetBlockFound = false;
=======
	private void setUpBoard(Puzzle puzzle) {

		// Remove previous board
		getContentPane().removeAll();
		getContentPane().revalidate();

		Board board = puzzle.getBoard();
		int numRows = board.getNumRows();
		int numCols = board.getNumCols();
>>>>>>> a40725cfc0dde6137ad474766052bcb7c3cb4f79
		
		// Setup new board
		buttonGrid = new GridButton[numRows][numCols];
		setLayout(new GridLayout(numRows, numCols));
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				GridButton cell = new GridButton(row, col);
				cell.setPreferredSize(new Dimension(64, 64));
				cell.addActionListener(this);
				cell.setBackground(Color.LIGHT_GRAY);
				cell.setOpaque(true);
				buttonGrid[row][col] = cell;
				add(cell);
			}
		}
<<<<<<< HEAD
		// Put color blocks on the board
		for(int row = 0; row < boardSetup.length; row++) {
			cellIdentifier = boardSetup[row].toCharArray();
			for(int col = 0; col < cellIdentifier.length; col++) {
				if(cellIdentifier[col] == 'H')
					board.placeBlockAt(new HorizontalBlock(), row, col);
				if(cellIdentifier[col] == 'V')
					board.placeBlockAt(new VerticalBlock(), row, col);
				if(cellIdentifier[col] == 'T') {
					board.placeBlockAt(new TargetBlock(), row, col);
					targetBlockLocationInRow = col;
					targetBlockFound = true;
					targetBlockCount++;	
				}
			}
			// Verify that there are no horizontal blocks to the right of the target block
			if(targetBlockFound == true) {
				for(int i = targetBlockLocationInRow; i < NUM_COLS; i++) {
					if(cellIdentifier[i] == 'H')
						throw new NumberFormatException("Can't have any horizontal blocks to the right of the target block.");
				}
				targetBlockFound = false;
			}
		}
		// Verify that there is only one target block.
		if(targetBlockCount != 1)
			throw new NumberFormatException("Must have one target block.");
		
=======

		pack();
>>>>>>> a40725cfc0dde6137ad474766052bcb7c3cb4f79
		updateUI();
	}

	/**
	 * Update display based on the state of the board...
	 */
	// TODO: make this more efficient
	private void updateUI() {
<<<<<<< HEAD
		ImageIcon grey_block = new ImageIcon("res/images/grey_block.jpg");
		ImageIcon yellow_block = new ImageIcon("res/images/yellow_block.jpg");
		ImageIcon blue_block = new ImageIcon("res/images/blue_block.jpg");
		ImageIcon red_block = new ImageIcon("res/images/red_block.jpg");
		
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				Block block = board.getBlockAt(row, col);
				JButton cell = buttonGrid[row][col];
				if (block == null)
					cell.setIcon(grey_block);
				else if (block instanceof TargetBlock)
					cell.setIcon(yellow_block);
				else if (block instanceof HorizontalBlock)
					cell.setIcon(blue_block);
				else if (block instanceof VerticalBlock)
					cell.setIcon(red_block);
=======
		Board board = puzzle.getBoard();
		int numRows = board.getNumRows();
		int numCols = board.getNumCols();
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				Block block = board.getBlockAt(row, col);
				JButton cell = buttonGrid[row][col];
				if (block == null) {
					cell.setIcon(null);
				}
				else if (block instanceof TargetBlock) {
					cell.setIcon(new ImageIcon("res/images/block-yellow.png"));
				}
				else if (block instanceof HorizontalBlock) {
					cell.setIcon(new ImageIcon("res/images/block-blue.png"));
				}
				else if (block instanceof VerticalBlock) {
					cell.setIcon(new ImageIcon("res/images/block-red.png"));
				}
>>>>>>> a40725cfc0dde6137ad474766052bcb7c3cb4f79
			}
		}
		repaint();
	}

	/**
	 * Handle board clicks.
	 * 
	 * Movement is done by first selecting a block, and then
	 * selecting the destination.
	 * 
	 * Whenever a block is clicked, it is selected, even if
	 * another block was selected before.
	 * 
	 * When an empty cell is clicked after a block is selected,
	 * the block is moved if the move is valid.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Handle grid button clicks...
		GridButton cell = (GridButton) e.getSource();
		int row = cell.getRow();
		int col = cell.getCol();
		System.out.println("Clicked (" + row + ", " + col + ")");
		
		Board board = puzzle.getBoard();
		if (selectedBlock == null || board.getBlockAt(row, col) != null) {
			selectBlockAt(row, col);
		}
		else {
			moveSelectedBlockTo(row, col);
			if (puzzle.isSolved()) {
				JOptionPane.showMessageDialog(SimpleGUI.this,
						"Puzzle solved! Good job!", "Puzzle solved!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Select block at a specific location.
	 * 
	 * If there is no block at the specified location,
	 * the previously selected block remains selected.
	 * 
	 * If there is a block at the specified location,
	 * the previous selection is replaced.
	 */
	private void selectBlockAt(int row, int col) {
		Board board = puzzle.getBoard();
		Block block = board.getBlockAt(row, col);
		if (block != null) {
			selectedBlock = block;
			selectedBlockRow = row;
			selectedBlockCol = col;
		}
	}
	
	/**
	 * Try to move the currently selected block to a specific location.
	 * 
	 * If the move is not possible, nothing happens.
	 */
	private void moveSelectedBlockTo(int row, int col) {
		
		int vertDist = row - selectedBlockRow;
		int horzDist = col - selectedBlockCol;
		
		if (vertDist != 0 && horzDist != 0) {
			System.err.println("Invalid move!");
			return;
		}
		
		Direction dir = getMoveDirection(selectedBlockRow, selectedBlockCol, row, col);
		int dist = Math.abs(vertDist + horzDist);
		
		Board board = puzzle.getBoard();
		try {
			if (board.moveBlock(selectedBlockRow, selectedBlockCol, dir, dist)) {
				selectedBlock = null;
				updateUI();
			}
		}
		catch (Exception e) {
			System.err.println("Invalid move!");
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Determines the direction of a move based on
	 * the starting location and the destination.
	 *  
	 * @return <code>null</code> if both the horizontal distance
	 * 	       and the vertical distance are not zero. 
	 */
	private Direction getMoveDirection(int startRow, int startCol, int destRow, int destCol) {
		int vertDist = destRow - startRow;
		int horzDist = destCol - startCol;
		if (vertDist < 0)
			return Direction.UP;
		if (vertDist > 0)
			return Direction.DOWN;
		if (horzDist < 0)
			return Direction.LEFT;
		if (horzDist > 0)
			return Direction.RIGHT;
		return null;
	}
}

package pt314.blocks.game;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the moveBlock method in the GameBoard class.
 */
public class GameBoardTest {
	
	private GameBoard board;

	@Before
	public void setUp() {
		board = new GameBoard(3,3);
	}

	@Test
	public void testHorizontalBlockMovement() throws NumberFormatException, IOException {
		board = new GameBoard(5, 5);
		Block block1 = new HorizontalBlock();
		Block block2 = new HorizontalBlock();
		board.placeBlockAt(block1, 0, 0);
		board.placeBlockAt(block2, 0, 2);
		
		// valid directions
		assertTrue(board.moveBlock(0, 0, Direction.RIGHT, 1));
		assertTrue(board.moveBlock(0, 1, Direction.LEFT, 1));
		
		// invalid directions
		assertFalse(board.moveBlock(0, 0, Direction.DOWN, 1));
		assertFalse(board.moveBlock(0, 0, Direction.UP, 1));
		
		// out of bounds
		assertFalse(board.moveBlock(0, 0, Direction.LEFT, 1));
		assertFalse(board.moveBlock(0, 2, Direction.RIGHT, 6));
		
		// moving with a block in the way
		assertFalse(board.moveBlock(0, 0, Direction.RIGHT, 2));
		
		// moving multiple spaces
		assertTrue(board.moveBlock(0, 2, Direction.RIGHT, 2));
	}
	
	@Test
	public void testVerticalBlockMovement() throws NumberFormatException, IOException {
		board = new GameBoard(5, 5);
		Block block1 = new VerticalBlock();
		Block block2 = new VerticalBlock();
		board.placeBlockAt(block1, 2, 1);
		board.placeBlockAt(block2, 0, 1);
		
		// valid directions
		assertTrue(board.moveBlock(2, 1, Direction.DOWN, 1));
		assertTrue(board.moveBlock(3, 1, Direction.UP, 1));
		
		// invalid directions
		assertFalse(board.moveBlock(2, 1, Direction.RIGHT, 1));
		assertFalse(board.moveBlock(2, 1, Direction.LEFT, 1));
		
		// out of bounds
		assertFalse(board.moveBlock(2, 1, Direction.DOWN, 5));
		assertFalse(board.moveBlock(0, 1, Direction.UP, 2));
		
		// moving with a block in the way
		assertFalse(board.moveBlock(2, 1, Direction.UP, 2));
		
		// moving multiple spaces
		assertTrue(board.moveBlock(2, 1, Direction.DOWN, 2));
	}
	
	@Test
	public void testTargetBlockMovement() throws NumberFormatException, IOException {
		board = new GameBoard(5, 5);
		Block block1 = new TargetBlock();
		Block block2 = new VerticalBlock();
		board.placeBlockAt(block1, 2, 0);
		board.placeBlockAt(block2, 2, 3);
		
		// valid directions
		assertTrue(board.moveBlock(2, 0, Direction.RIGHT, 1));
		assertTrue(board.moveBlock(2, 1, Direction.LEFT, 1));
		
		// invalid directions
		assertFalse(board.moveBlock(2, 0, Direction.UP, 1));
		assertFalse(board.moveBlock(2, 0, Direction.DOWN, 1));
		
		// out of bounds
		assertFalse(board.moveBlock(2, 0, Direction.LEFT, 2));
		
		// moving with a block in the way
		assertFalse(board.moveBlock(2, 0, Direction.RIGHT, 3));
		
		// moving multiple spaces
		assertTrue(board.moveBlock(2, 0, Direction.RIGHT, 2));
	}
}

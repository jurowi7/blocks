package pt314.blocks.game.block;

import pt314.blocks.game.Direction;

/**
 * This type of block can only move horizontally (left or right).
 */
public class HorizontalBlock extends Block {

	public HorizontalBlock() {}

	@Override
	public boolean isValidDirection(Direction dir) {
		return dir == Direction.LEFT || dir == Direction.RIGHT;
	}
}

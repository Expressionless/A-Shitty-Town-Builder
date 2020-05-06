package main.game.props;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import main.game.entities.Prop;
import main.game.map.Map;
import main.util.ResourceLoader;

public class Tree extends Prop {

	private static final SpriteSheet TREES = ResourceLoader.SPRITE_SHEETS.get("trees");
	private static final int VARIANT_COUNT = TREES.getHorizontalCount();
	
	public Tree(Map m, float x, float y) {
		// Call super constructor with a randomly picked variant image from the trees sprite_sheet
		super(m, x, y, TREES.getSubImage(new Random().nextInt(VARIANT_COUNT - 1), 0).copy());
		sprite.rotate(new Random().nextInt(360));
	}

	@Override
	public void step() {
	}

	@Override
	public void draw(Graphics g) {
		
	}

	
	
}

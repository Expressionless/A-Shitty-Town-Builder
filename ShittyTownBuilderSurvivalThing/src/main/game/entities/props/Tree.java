package main.game.entities.props;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import main.game.entities.Prop;
import main.game.map.Map;
import main.game.map.MapConstants;
import main.util.ResourceLoader;

public class Tree extends Prop {

	private static final SpriteSheet TREES = ResourceLoader.SPRITE_SHEETS.get("trees");

	public Tree(Map m, float x, float y, int type) {
		// Call super constructor with a randomly picked variant image from the trees
		// sprite_sheet
		super(m, x, y, ResourceLoader.toSpriteSheet(TREES.getSubImage(type, 0).copy()), MapConstants.TREE_DEPTH);
		sprite.rotate(new Random().nextInt(360));
	}
	
	@Override
	public void initAnimations() {
		
	}
	

	@Override
	public void step() {
	}

	@Override
	public void draw(Graphics g) {

	}

}

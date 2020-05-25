package main.game.entities.mobs.neutral.villagers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import main.game.entities.mobs.neutral.Villager;
import main.game.map.Map;
import main.util.ResourceLoader;

public class Woodcutter extends Villager {

	public static final SpriteSheet	 SPRITE = ResourceLoader.SPRITE_SHEETS.get("player");
	
	public Woodcutter(Map m, float x, float y) {
		super(m, x, y, SPRITE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initAnimations() {
		
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public void handleStates() {
		
	}

}

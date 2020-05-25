package main.game.entities.mobs.hostile.bandits;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import main.game.entities.mobs.hostile.Bandit;
import main.game.map.Map;
import main.util.Utils;

public class BanditLeader extends Bandit {

	public BanditLeader(Map m, float x, float y) {
		super(m, x, y, Utils.pickFromSpriteSheet(Bandit.SPRITES.get("archer")));
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

	@Override
	public void handleStates() {
		// TODO Auto-generated method stub
		
	}

}

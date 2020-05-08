package main.game.entities.mobs.hostile;

import org.newdawn.slick.Image;

import main.GameConstants;
import main.game.Entity;
import main.game.entities.Mob;
import main.game.map.Map;

public abstract class Bandit extends Mob {

	protected Entity target;
	
	public Bandit(Map m, float x, float y, Image sprite) {
		super(m, x, y, sprite, GameConstants.HUMAN_DEPTH);
		// TODO Auto-generated constructor stub
	}

}

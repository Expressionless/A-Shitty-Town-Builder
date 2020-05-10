package main.game.entities.mobs.hostile;

import org.newdawn.slick.SpriteSheet;

import main.game.Entity;
import main.game.entities.Mob;
import main.game.map.Map;
import main.game.map.MapConstants;

public abstract class Bandit extends Mob {

	protected Entity target;
	
	public Bandit(Map m, float x, float y, SpriteSheet sprite) {
		super(m, x, y, sprite, MapConstants.HUMAN_DEPTH);
		// TODO Auto-generated constructor stub
	}

}

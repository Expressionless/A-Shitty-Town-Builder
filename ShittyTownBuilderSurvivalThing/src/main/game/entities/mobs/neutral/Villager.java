package main.game.entities.mobs.neutral;

import org.newdawn.slick.Image;

import main.GameConstants;
import main.game.entities.Mob;
import main.game.map.Map;

public abstract class Villager extends Mob {
	
	public Villager(Map m, float x, float y, Image sprite) {
		super(m, x, y, sprite, GameConstants.HUMAN_DEPTH);
		// TODO Auto-generated constructor stub
	}

}

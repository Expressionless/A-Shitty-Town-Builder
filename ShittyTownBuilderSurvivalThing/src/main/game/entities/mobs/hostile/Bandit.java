package main.game.entities.mobs.hostile;

import java.util.HashMap;

import org.newdawn.slick.SpriteSheet;

import main.game.Entity;
import main.game.entities.Mob;
import main.game.map.Map;
import main.game.map.MapConstants;

import static main.util.ResourceLoader.loadSpriteSheet;

public abstract class Bandit extends Mob {

	protected Entity target;
	
	public static final HashMap<String, SpriteSheet> SPRITES = new HashMap<String, SpriteSheet>();	
	
	public static void loadResources() {
		String path = "\\res\\sprites\\mobs\\bandit\\archer\\";
		
		SPRITES.put("archer", loadSpriteSheet(path + "archer", 24, 24));
		SPRITES.put("idle_hands", loadSpriteSheet(path + "idle_hands", 48, 48));
		SPRITES.put("draw_cycle", loadSpriteSheet(path + "draw_cycle", 48, 48));
		SPRITES.put("legs", loadSpriteSheet(path + "legs", 48, 48));
	}
	
	public Bandit(Map m, float x, float y, SpriteSheet sprite) {
		super(m, x, y, sprite, MapConstants.HUMAN_DEPTH);
		// TODO Auto-generated constructor stub
	}

}

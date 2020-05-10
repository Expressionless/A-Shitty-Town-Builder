package main.game.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import main.game.Entity;
import main.game.map.Map;

public abstract class Prop extends Entity {

	public Prop(Map m, float x, float y, SpriteSheet sprite, float depth) {
		super(m, x, y, sprite, depth);
	}
	
	@Override
	public void prerender(Graphics g) {
		
	}
}

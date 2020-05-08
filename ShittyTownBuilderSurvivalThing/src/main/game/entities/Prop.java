package main.game.entities;

import org.newdawn.slick.Image;

import main.game.Entity;
import main.game.map.Map;

public abstract class Prop extends Entity {

	public Prop(Map m, float x, float y, Image sprite, float depth) {
		super(m, x, y, sprite, depth);
	}
}

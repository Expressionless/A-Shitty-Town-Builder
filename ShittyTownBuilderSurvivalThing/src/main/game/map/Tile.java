package main.game.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;

public class Tile {

	private Rectangle bounds;
	
	private Image tileSprite;
	
	public Tile(int x, int y, Image sprite) {
		bounds = new Rectangle(x, y, GameConstants.TILE_WIDTH, GameConstants.TILE_HEIGHT);
		tileSprite = sprite;
	}
	
	public void tick() {
		// Update anything that needs updating, like seasonal changes
	}
	
	public void render(Graphics g) {
		// Render the sprite
		g.drawImage(tileSprite, (int)Math.floor(bounds.getX()), (int)Math.floor(bounds.getY()));
	}
	
	// Getters and Setters
	public Image getSprite() {
		return tileSprite;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
}

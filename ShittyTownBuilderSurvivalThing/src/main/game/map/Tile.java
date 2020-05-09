package main.game.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;


public class Tile {

	private Rectangle bounds;
	
	private Image tileSprite;
	
	public Tile(float t_x, float t_y, Image sprite) {
		bounds = new Rectangle(t_x, t_y, MapConstants.TILE_WIDTH, MapConstants.TILE_HEIGHT);
		tileSprite = sprite;
	}
	
	public void tick() {
		// Update anything that needs updating, like seasonal changes
	}
	
	public void render(Graphics g) {
		render(g, 255);
	}
	
	public void render(Graphics g, int climate) {
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

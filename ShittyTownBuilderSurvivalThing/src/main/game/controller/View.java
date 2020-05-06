package main.game.controller;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.game.Entity;

public class View {

	private Rectangle bounds;
	private Point focalPoint;
	
	// For views with a focalpoint
	public View(float x, float y, float width, float height, Entity focus) {
		bounds = new Rectangle(x, y, width, height);
		focalPoint = focus.getCentrePoint();
	}
	
	// For views without a focalpoint
	public View(float x, float y, float width, float height) {
		bounds = new Rectangle(x, y, width, height);
		focalPoint = null;
	}
	
	// Conversion methods
	
	public Point gameToView(Point p) {
		 //Do some translations and return the point in the view's perspective from the game
		return null;
	}
	
	public Point viewToGame(Point p) {
		// Do some translations and return the point in the game's perspective from the view
		return null;
	}
	
}

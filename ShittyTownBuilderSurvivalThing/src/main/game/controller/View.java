package main.game.controller;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;
import main.game.Entity;
import main.util.Utils;

public class View {

	private Rectangle bounds;
	private Rectangle renderBounds;

	private Point focalPoint;
	private Point targetPoint;

	private static final int T_BUFFER = 1;

	// For views with a focal point
	public View(float x, float y, float width, float height, Entity focus) {

		initRects(x, y, width, height);
		focalPoint = focus.getCentrePoint();
		targetPoint = new Point(focalPoint.getX(), focalPoint.getY());
	}

	// For views without a focal point
	public View(float x, float y, float width, float height) {
		initRects(x, y, width, height);
		focalPoint = null;
		targetPoint = new Point(x + width / 2, y + height / 2);
	}

	// Initialize View Rectangles
	public void initRects(float x, float y, float width, float height) {
		// Setup the view rectangle enclosing the view in the window
		// Setup the rendering rectangle that renders a 'buffer' around the window to
		// prevent black borders

		float render_x = x - T_BUFFER * GameConstants.TILE_WIDTH;
		float render_y = y - T_BUFFER * GameConstants.TILE_HEIGHT;

		float render_width = width + 2 * T_BUFFER * GameConstants.TILE_WIDTH;
		float render_height = height + 2 * T_BUFFER * GameConstants.TILE_HEIGHT;

		renderBounds = new Rectangle(render_x, render_y, render_width, render_height);

		bounds = new Rectangle(x, y, width, height);
	}

	// Update
	public void tick(Input input) {
		// Poll for inputs and update rectangles accordingly
		Point move_vec = pollInput(input);
		updateRectangles(move_vec);
	}

	public void updateRectangles(Point move_vec) {
		// Update bounds based on centre point
		
		final float LERP_FACTOR = 0.1f;
		
		targetPoint.setX(targetPoint.getX() + move_vec.getX());
		targetPoint.setY(targetPoint.getY() + move_vec.getY());
		
		bounds.setX(Utils.lerp(bounds.getX(), targetPoint.getX(), LERP_FACTOR));
		bounds.setY(Utils.lerp(bounds.getY(), targetPoint.getY(), LERP_FACTOR));

		// Update render bounds based on bounds position
		renderBounds.setX(bounds.getX() - T_BUFFER * GameConstants.TILE_WIDTH);
		renderBounds.setY(bounds.getY() - T_BUFFER * GameConstants.TILE_HEIGHT);
	}

	public Point pollInput(Input input) {
		int dir = 0;
		float xDir = 0, yDir = 0;
		float mspeed = 10;

		if (input.isKeyDown(GameConstants.CAMERA_RIGHT))
			dir |= 1;
		if (input.isKeyDown(GameConstants.CAMERA_LEFT))
			dir |= 2;
		if (input.isKeyDown(GameConstants.CAMERA_UP))
			dir |= 4;
		if (input.isKeyDown(GameConstants.CAMERA_DOWN))
			dir |= 8;

		if ((dir & 1) != 0)
			xDir = mspeed;
		else if ((dir & 2) != 0)
			xDir = -mspeed;
		if ((dir & 4) != 0)
			yDir = -mspeed;
		else if ((dir & 8) != 0)
			yDir = mspeed;

		return new Point(xDir, yDir);
	}

	// Conversion methods

	public Point gameToView(Point p) {
		// Do some translations and return the point in the view's perspective from the
		// game
		return null;
	}

	public Point viewToGame(Point p) {
		// Do some translations and return the point in the game's perspective from the
		// view
		return null;
	}

	// State checkers
	public boolean rectInView(Rectangle r) {
		float[] float_point1 = r.getPoint(0);
		float[] float_point2 = r.getPoint(1);
		float[] float_point3 = r.getPoint(2);
		float[] float_point4 = r.getPoint(3);

		Point p1 = new Point(float_point1[0], float_point1[1]);
		Point p2 = new Point(float_point2[0], float_point2[1]);
		Point p3 = new Point(float_point3[0], float_point3[1]);
		Point p4 = new Point(float_point4[0], float_point4[1]);

		return bounds.contains(p1) || bounds.contains(p2) || bounds.contains(p3) || bounds.contains(p4);
	}

	// Getters and Setters

	public Rectangle getViewBounds() {
		return bounds;
	}

	public Rectangle getRenderBounds() {
		return renderBounds;
	}
}

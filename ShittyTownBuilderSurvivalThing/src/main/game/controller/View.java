package main.game.controller;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Dimension;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;
import main.game.Entity;
import main.game.entities.mobs.Player;
import main.game.map.Map;
import main.game.map.MapConstants;
import main.util.Utils;

public class View {

	// View Stuff
	private Rectangle bounds;
	private Rectangle renderBounds;
	private Point targetPoint;
	private Point zoomOffset;
	private float zoom;
	private float targetZoom;
	
	
	private Point focalPoint;
	private Entity focusedEntity;
	private Rectangle focalBounds;

	private Map map;
	
	private Dimension base_size;
	
	private float mspeed = GameConstants.CAMERA_SPEED;

	private static final int T_BUFFER = 1;

	// For views with a focal point
	public View(Map map, float x, float y, float width, float height, Entity focus) {
		zoomOffset = new Point(0,0);
		this.map = map;
		initRects(x, y, width, height);
		focalPoint = focus.getCentrePoint();
		float focal_width = width / 4;
		float focal_height = height / 4;
		
		base_size = new Dimension();
		base_size.setWidth((int) width);
		base_size.setHeight((int) height);
		
		focalBounds = new Rectangle(focalPoint.getX() - focal_width / 2, focalPoint.getY() - focal_width / 2, focal_width,
				focal_height);

		focusedEntity = focus;

		targetZoom = 1f;
		zoom = targetZoom;
		
		targetPoint = new Point(focalPoint.getX(), focalPoint.getY());
	}

	// For views without a focal point
	public View(Map map, float x, float y, float width, float height) {
		this.map = map;
		initRects(x, y, width, height);
		focalPoint = null;
		focalBounds = null;
		targetPoint = new Point(x + width / 2, y + height / 2);
	}

	// Initialize View Rectangles
	public void initRects(float x, float y, float width, float height) {
		// Setup the view rectangle enclosing the view in the window
		// Setup the rendering rectangle that renders a 'buffer' around the window to
		// prevent black borders

		float render_x = x - T_BUFFER * MapConstants.TILE_WIDTH;
		float render_y = y - T_BUFFER * MapConstants.TILE_HEIGHT;

		float render_width = width + 2 * T_BUFFER * MapConstants.TILE_WIDTH;
		float render_height = height + 2 * T_BUFFER * MapConstants.TILE_HEIGHT;

		renderBounds = new Rectangle(render_x, render_y, render_width, render_height);

		bounds = new Rectangle(x, y, width, height);
	}

	// Update
	public void tick(Input input) {
		// Poll for inputs and update rectangles accordingly
		Point move_vec;

		Point currentPos;

		if (focalPoint == null)
			move_vec = pollInput(input);
		else {
			currentPos = new Point(focalBounds.getX() + focalBounds.getWidth() / 2,
					focalBounds.getY() + focalBounds.getHeight() / 2);

			if (!focalBounds.contains(focalPoint)) {
				float angle = Utils.getPointDirection(currentPos, focalPoint);

				float dX = (float) (mspeed * Math.cos(Math.toRadians(angle)));
				float dY = (float) (mspeed * Math.sin(Math.toRadians(angle)));
				
				move_vec = new Point(dX, dY);

			} else
				move_vec = new Point(0, 0);
		}
		updateRectangles(move_vec);
		//updateZoom();
		
	}
	
	// For drawing to the game
	public void render(Graphics g) {
		if(focalPoint != null) {
			Point currentPos = new Point(focalBounds.getX() + focalBounds.getWidth() / 2,
				focalBounds.getY() + focalBounds.getHeight() / 2);
			g.drawLine(currentPos.getX(), currentPos.getY(), focalPoint.getX(), focalPoint.getY());
		}

		// Render the view stuff
		g.setColor(Color.red);
		g.draw(bounds);
		g.setColor(Color.blue);
		g.draw(renderBounds);
		
		g.setColor(Color.white);
		Rectangle focus = focalBounds;
		if(focus != null) {
			g.draw(focus);
		}
	}
	
	
	// For drawing to the screen directly
	public void renderUI(Graphics g) {
		
		Player p = map.getPlayer();
		g.setColor(Color.white);
		g.drawString("Player X: " + Float.toString(p.getPos().getX()), 16, 16);
		g.drawString("Player Y: " + Float.toString(p.getPos().getY()), 16, 48);
	}
	
	public void updateZoom() {
		// Amount the zoom will change by
		float deltaZoom = 0.005f;
		int mouse_wheel = (int)Math.signum(Mouse.getDWheel());
		targetZoom += mouse_wheel * deltaZoom;
		
		
		
		float previousZoom = zoom;
		zoom = Utils.lerp(zoom, targetZoom, 0.075f);

		float diffX = base_size.getWidth() * (previousZoom - zoom);
		float diffY = base_size.getHeight() * (previousZoom - zoom);

		zoomOffset.setX((float) (diffX * 0.5f / Math.pow((previousZoom + zoom) / 2, 2)));
		zoomOffset.setY((float) (diffY * 0.5f / Math.pow((previousZoom + zoom) / 2, 2)));

		if (zoom != previousZoom) {
			targetPoint.setX(targetPoint.getX() - zoomOffset.getX());
			targetPoint.setY(targetPoint.getY() - zoomOffset.getY());
		}
	}

	public void updateRectangles(Point move_vec) {
		// Update bounds based on centre point

		final float LERP_FACTOR = 0.03f;

		targetPoint.setX(targetPoint.getX() + move_vec.getX());
		targetPoint.setY(targetPoint.getY() + move_vec.getY());

		float dX = targetPoint.getX() - bounds.getX();
		float dY = targetPoint.getY() - bounds.getY();
		
		bounds.setX(bounds.getX() + zoomOffset.getX() + dX * LERP_FACTOR);
		bounds.setY(bounds.getY() + zoomOffset.getY() + dY * LERP_FACTOR);

		// Update render bounds based on bounds position
		renderBounds.setX(bounds.getX() - T_BUFFER * MapConstants.TILE_WIDTH);
		renderBounds.setY(bounds.getY() - T_BUFFER * MapConstants.TILE_HEIGHT);
		
		if (focalPoint != null) {

			// Create the focus box and centre it in the screen
			if (focalBounds == null) {

				float width = bounds.getWidth() / 4;
				float height = bounds.getHeight() / 4;

				float x = focalPoint.getX() - width / 2;
				float y = focalPoint.getY() - height / 2;

				focalBounds = new Rectangle(x, y, width, height);
			}

			focalPoint = focusedEntity.getPos();

			float x = bounds.getX() + bounds.getWidth() / 2;
			float y = bounds.getY() + bounds.getHeight() / 2;

			focalBounds.setX(x - focalBounds.getWidth() / 2);
			focalBounds.setY(y - focalBounds.getHeight() / 2);
		}
	}

	public Point pollInput(Input input) {
		int dir = 0;
		float xDir = 0, yDir = 0;

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
		return new Point(p.getX() - bounds.getX(), p.getY() - bounds.getY());
	}

	public Point viewToGame(Point p) {
		// Do some translations and return the point in the game's perspective from the
		// view
		return new Point((p.getX() + bounds.getX()), (p.getY() + bounds.getY()));
	}
	
	public Point getGameMouse() {
		Point mouse_ui = getUIMouse();
		
		Point p = new Point(mouse_ui.getX() + bounds.getX(), mouse_ui.getY() + bounds.getY());
		return p;
	}
	
	public Point getUIMouse() {
		float mouse_x = Mouse.getX();
		float mouse_y = GameConstants.HEIGHT - Mouse.getY();
		
		return new Point(mouse_x, mouse_y);
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

	public Rectangle getFocalBounds() {
		return focalBounds;
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public float getTargetZoom() {
		return targetZoom;
	}
}

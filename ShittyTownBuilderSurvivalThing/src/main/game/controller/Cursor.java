package main.game.controller;

import static main.GameConstants.TILE_HEIGHT;
import static main.GameConstants.TILE_WIDTH;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.Engine;
import main.game.map.Map;
import main.util.ResourceLoader;


public class Cursor implements MouseListener {

	private Rectangle bounds;
	private Animation sprite;
	
	private Color color;
	
	private Point mouse;
	
	private Map map;
	
	public Cursor(Map map, float x, float y) {
		Engine.getInput().addMouseListener(this);
		mouse = new Point(x, y);
		
		color = new Color(255, 255, 255);
		SpriteSheet ss = ResourceLoader.SPRITE_SHEETS.get("cursor");
		sprite = new Animation(ss, 100);
		sprite.setLooping(true);
		
		bounds = new Rectangle(x, y, TILE_WIDTH, TILE_HEIGHT);
		this.map = map;
	}
	
	public void update() {
		Rectangle offset = map.getView().getViewBounds();
		
		bounds.setX((float)Math.floor((mouse.getX() + offset.getX()) / TILE_WIDTH) * TILE_WIDTH);
		bounds.setY((float)Math.floor((mouse.getY() + offset.getY()) / TILE_HEIGHT) * TILE_HEIGHT);
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		if(sprite != null)
			g.drawAnimation(sprite, (int)Math.floor(bounds.getX()), (int)Math.floor(bounds.getY()));
	}
	
	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return Engine.getCurrentState() == Engine.GAME_STATE;
	}

	@Override
	public void setInput(Input in) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		mouse.setX(newx);
		mouse.setY(newy);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		mouse.setX(newx);
		mouse.setY(newy);
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		color.b = 0;
		color.g = 0;
		color.r = 255;
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub
		color.b = 255;
		color.g = 255;
		color.r = 255;
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}

}

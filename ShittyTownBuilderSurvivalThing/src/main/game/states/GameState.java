package main.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.State;
import main.game.controller.Cursor;
import main.game.controller.View;
import main.game.map.Map;

public class GameState extends State {

	private Map map;
	private Cursor cursor;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		map = new Map(3, 3);
		cursor = new Cursor(map, 0, 0);
		loaded = true;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		map.tick();
		cursor.update();
	}

	@Override
	public void render(Graphics g) {
		// Set Color to the grass default color
		g.setBackground(new Color(36, 70, 47));
		// TODO Auto-generated method stub
		View view = map.getView();

		Point offset = new Point(view.getViewBounds().getX(), view.getViewBounds().getY());

		g.translate(-offset.getX(), -offset.getY());
		g.scale(view.getZoom(), view.getZoom());
		map.render(g);
		//map.getView().render(g);
		cursor.render(g);
		g.scale(1/view.getZoom(), 1/view.getZoom());
		g.translate(offset.getX(), offset.getY());
		
		// UI Rendering
		view.renderUI(g);
	}

}

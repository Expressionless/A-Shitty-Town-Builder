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
		map = new Map(10000, 10000);
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
		View v = map.getView();

		Point offset = new Point(v.getViewBounds().getX(), v.getViewBounds().getY());

		g.translate(-offset.getX(), -offset.getY());
		map.render(g);
		cursor.render(g);
		g.translate(offset.getX(), offset.getY());
	}

}

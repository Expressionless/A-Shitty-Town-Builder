package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Engine implements Game {

	private AppGameContainer appgc;
	
	public Engine() {
		
		// Initialize the Frame Window
		try {
			appgc = new AppGameContainer(this);
			appgc.setTargetFrameRate(60);
			appgc.setShowFPS(false);
			appgc.setDisplayMode(GameConstants.WIDTH, GameConstants.HEIGHT, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	public boolean closeRequested() {
		// TODO Auto-generated method stub
		System.exit(0);
		return false;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return GameConstants.TITLE;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}

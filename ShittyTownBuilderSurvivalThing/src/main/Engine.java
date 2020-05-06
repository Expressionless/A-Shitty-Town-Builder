package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.game.State;
import main.game.states.GameState;
import main.game.states.LoadState;
import main.game.states.MenuState;

public class Engine implements Game {

	private AppGameContainer appgc;

	
	// States
	public static final State LOAD_STATE = new LoadState();
	public static final State MENU_STATE = new MenuState();
	public static final State GAME_STATE = new GameState();
	
	private static State currentState = null;
	
	private static Input input;
	
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
		input = gc.getInput();
		if(currentState == null)
			switchState(LOAD_STATE);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if(currentState != null) {
			currentState.render(g);
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if(currentState != null)
			currentState.tick();
	}

	public static void switchState(State s) {
		currentState = s;
		if(!s.isLoaded())
			s.init();
	}
	
	// Getters and Setters
	
	public static State getCurrentState() {
		return currentState;
	}

	public static Input getInput() {
		return input;
	}
	
}

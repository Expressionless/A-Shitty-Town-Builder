package main.game.states;

import org.newdawn.slick.Graphics;

import main.Engine;
import main.game.State;
import main.util.ResourceLoader;

public class LoadState extends State {

	public LoadState() {
		super();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		ResourceLoader.initResources();
		loaded = true;
		System.out.println("Loaded resources");
		Engine.switchState(Engine.GAME_STATE);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
	}
	
}

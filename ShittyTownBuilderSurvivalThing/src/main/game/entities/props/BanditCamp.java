package main.game.entities.props;

import java.util.ArrayList;
import java.util.Random;

import main.game.entities.Mob;
import main.game.entities.mobs.hostile.bandits.BanditArcher;
import main.game.entities.mobs.hostile.bandits.BanditClubman;
import main.game.entities.mobs.hostile.bandits.BanditLeader;
import main.game.map.Map;

public class BanditCamp {

	private ArrayList<Mob> mobs;
	private BanditLeader leader;
	
	private Map map;
	private int level;
	
	private Campfire campfire;
	
	private static int RADIUS_INCREMENT = 48;
	
	public BanditCamp(Map m, float x, float y, int level) {
		
		this.map = m;
		this.level = level;
		
		mobs = new ArrayList<Mob>();
		
		int mob_count = new Random().nextInt(level) + 2;
		
		// Spawn a couple of supporting bandits
		for(int i = 0; i < mob_count; i++) {
			mobs.add(spawnMobNearby(x, y));
		}
		
		// Spawn a campfire
		campfire = new Campfire(m, x, y);
		leader = (BanditLeader) spawnMobNearby(x, y, 2);
		
		mobs.add(leader);
	}
	
	public Mob spawnMobNearby(float x, float y) {
		Random r = new Random();
		// Pick either a melee or ranged unit
		int mob_type = r.nextInt(2);
		
		double spawn_dir = r.nextDouble() * 360;

		float mob_x = x + (float) (Math.cos(spawn_dir) * RADIUS_INCREMENT * (r.nextInt(level) + 1));
		float mob_y = y + (float) (Math.sin(spawn_dir) * RADIUS_INCREMENT * (r.nextInt(level) + 1));
		
		switch(mob_type) {
			case 0: //Melee
				return new BanditClubman(map, mob_x, mob_y);
			case 1: //Ranged
				return new BanditArcher(map, mob_x, mob_y);
			default:
				System.err.println("Failed to spawn mob at: " + x + "," + y);
				return null;
		}
		
	}

	public Mob spawnMobNearby(float x, float y, int mob_type) {
		Random r = new Random();
		
		double spawn_dir = r.nextDouble() * 360;

		float mob_x = x + (float) (Math.cos(spawn_dir) * RADIUS_INCREMENT * (r.nextInt(level) + 1));
		float mob_y = y + (float) (Math.sin(spawn_dir) * RADIUS_INCREMENT * (r.nextInt(level) + 1));
		
		switch(mob_type) {
			case 0: // Melee
				return new BanditClubman(map, mob_x, mob_y);
			case 1: // Ranged
				return new BanditArcher(map, mob_x, mob_y);
			case 2: // Leader
				return new BanditLeader(map, mob_x, mob_y);
			default:
				System.err.println("Failed to spawn mob at: " + x + "," + y);
				return null;
		}
		
	}
	
}

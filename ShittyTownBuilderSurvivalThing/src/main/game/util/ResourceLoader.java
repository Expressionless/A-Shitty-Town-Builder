package main.game.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

import static main.GameConstants.*;

import main.game.map.Map;

public class ResourceLoader {

	public static final int FONT_SIZE = 60;

	public static final HashMap<String, Image> SPRITES = new HashMap<String, Image>();
	public static final HashMap<String, SpriteSheet> SPRITE_SHEETS = new HashMap<String, SpriteSheet>();

	public static final HashMap<String, Image> UI = new HashMap<String, Image>();

	public static final HashMap<String, Map> MAPS = new HashMap<String, Map>();
	public static final HashMap<String, TrueTypeFont> FONTS = new HashMap<String, TrueTypeFont>();

	public static Image missing;
	public static SpriteSheet missingSS;

	public static void loadSprites() {
		String path = "res\\sprites\\";
	}

	public static void loadUI() {
		String path = "res\\sprites\\UI\\";
	}

	public static void loadMenuSprites() {
		SPRITE_SHEETS.put("menu_button", loadSpriteSheet("res\\sprites\\menu\\button_anim", 220, 60));
		SPRITE_SHEETS.put("menu_buttonR", loadSpriteSheet("res\\sprites\\menu\\button_animR", 220, 60));
	}
	
	public static void loadFonts() {
		FONTS.put("Menu", loadFont("FantaisieArtistique.ttf", 45));
	}

	public static void initResources() {

		try {
			missing = new Image(ABS_PATH + "res\\sprites\\missingtex.png");
			missingSS = new SpriteSheet(ABS_PATH + "res\\sprites\\missingtex.png", 48, 48);
		} catch (SlickException e) {
			System.err.println("Failed to load missingtex.png");
			e.printStackTrace();
		}
		// Don't change the load order please
		loadFonts();
		loadMenuSprites();
		loadSprites();
		loadUI();
	}

	public static TrueTypeFont loadFont(String font, int size) {
		InputStream is = org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res\\font\\" + font);
		TrueTypeFont ttf = null;
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, is);
			f = f.deriveFont(size * 1f);
			ttf = new TrueTypeFont(f, false);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ttf;
	}

	public static Image loadImage(String dir) {
		Image i;
		try {
			i = new Image(ABS_PATH + dir + ".png");
			System.out.println("Loaded " + dir + ".png");
			i.setFilter(Image.FILTER_NEAREST);
			i.clampTexture();
			return i;
		} catch (SlickException e) {
			System.err.println("Failed to load image at: " + dir);
			e.printStackTrace();
			return missing;
		} catch (RuntimeException e) {
			System.err.println("Failed to load image: " + dir);
			return missing;
		}
	}

	public static Image loadImageFromSS(SpriteSheet ss, int x, int y) {
		ss.startUse();
		Image i = ss.getSprite(x, y);
		i.setFilter(Image.FILTER_NEAREST);
		i.clampTexture();
		ss.endUse();
		return i;
	}

	public static SpriteSheet loadSpriteSheet(String dir, int tw, int th) {
		SpriteSheet ss;
		try {
			ss = new SpriteSheet(ABS_PATH + dir + ".png", tw, th);
			System.out.println("Loaded " + dir + ".png as Sprite Sheet with Tile: " + tw + "x" + th);
			ss.setFilter(Image.FILTER_NEAREST);
			ss.clampTexture();
			return ss;
		} catch (SlickException e) {
			System.err.println("Failed to load Sprite Sheet at: " + dir + " (SlickException)");
			e.printStackTrace();
			return missingSS;
		} catch (RuntimeException e) {
			System.err.println("Failed to load Sprite Sheet at: " + dir + " (RuntimeException)");
			return missingSS;
		}
	}
	
}

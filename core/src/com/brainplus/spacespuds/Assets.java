package com.brainplus.spacespuds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* Assets:

through space.ogg (CC-BY-SA 3.0) by maxstack (http://opengameart.org/content/through-space)
logo.png: created by Johan Berntsson using CoolText.com (free to use license)
gameover.png: created by Johan Berntsson using CoolText.com (free to use license)
you_win.png: created by Johan Berntsson using CoolText.com (free to use license)
explosion.wav: converted from explosion.flac (CC-BY-SA 3.0) by Blender Foundation: http://opengameart.org/content/rockbreaking
text.fnt: 04B_19 by Yuji Oshimoto (freeware (public domain?)): http://www.04.jp.org/
fighter.png: MiG-51S copy.PNG by Prinz Eugn, aka Mark Simpson: http://prinzeugn.deviantart.com/
space.jpg: from Space_Basics: http://www.roencia.com/scifigraphics.html
ufo.jpg: from Space_Basics: http://www.roencia.com/scifigraphics.html
missile.png: by Jonas Wagner: http://opengameart.org/content/asteroid-explosions-rocket-mine-and-laser
explosion3.png: by Jonas Wagner: http://opengameart.org/content/asteroid-explosions-rocket-mine-and-laser
road.jpg: Gnome project background (CC-BY-SA 3.0)

Copyrighted? Replace with free assets before releasing the game
potato.png

Licenses:
CC-BY-SA 3.0: Creative Commons Attribution-ShareAlike 3.0 Unported

 */

public class Assets {
	public static Texture splashscreenTexture;
	public static Texture backgroundEarth;
	public static Texture backgroundSpace;
	public static Texture logoTexture;
	public static Texture gameOverTexture;
	public static Texture youWinTexture;

	public static BitmapFont textFont;

	public static Animation ufoAnimation;
	public static Animation jetAnimation;
	public static Animation potatoAnimation;
	public static Animation explosionAnimation;
	public static Animation missileAnimation;

	private static boolean mute = false;
	//private static TextureAtlas jetTextureAtlas;
	private static Texture ufoSprites;
	private static Texture potatoSprites;
	private static Texture explosionSprites;
	private static Texture missileSprites;
	private static Texture jetSprites;
	private static Sound explosionSound;

	public static Texture createBackground(String filename, int numBackgrounds) {
		Pixmap backgroundTile = new Pixmap(Gdx.files.internal(filename));
		int backWidth = backgroundTile.getWidth() * numBackgrounds;
		int backHeight = backgroundTile.getHeight();
		Pixmap p = new Pixmap(backWidth, backHeight, Pixmap.Format.RGBA8888);
		for (int i = 0; i < numBackgrounds; i++) {
			p.drawPixmap(backgroundTile, backgroundTile.getWidth() * i, 0);
		}
		Texture background = new Texture(p, Pixmap.Format.RGB888, false);
		//background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		background.setWrap(TextureWrap.MirroredRepeat, TextureWrap.ClampToEdge);
		backgroundTile.dispose();
		p.dispose();
		return background;
	}

	public static void load() {
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("data/explosion.wav"));

		textFont = new BitmapFont(Gdx.files.internal("data/text.fnt"));

		// creating a bigger background by merging multiple ones
		backgroundEarth = createBackground("data/road.jpg", 1);
		backgroundSpace = createBackground("data/space.jpg", 2);
		backgroundSpace.setWrap(TextureWrap.MirroredRepeat, TextureWrap.MirroredRepeat);


		potatoSprites = new Texture(Gdx.files.internal("data/potato.png"));
		TextureRegion[][] tmp = TextureRegion.split(potatoSprites, potatoSprites.getWidth() / 10, potatoSprites.getHeight() / 5);
		TextureRegion[] frames = new TextureRegion[20];
		int index = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 10; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		potatoAnimation = new Animation(0.05f, frames);

		ufoSprites = new Texture(Gdx.files.internal("data/ufo.png"));
		tmp = TextureRegion.split(ufoSprites, ufoSprites.getWidth() / 24, ufoSprites.getHeight() / 1);
		frames = new TextureRegion[24];
		index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 24; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		ufoAnimation = new Animation(0.05f, frames);

		explosionSprites = new Texture(Gdx.files.internal("data/explosion3.png"));
		tmp = TextureRegion.split(explosionSprites, explosionSprites.getWidth() / 17, explosionSprites.getHeight() / 1);
		frames = new TextureRegion[17];
		index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 17; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		explosionAnimation = new Animation(0.05f, frames);

		missileSprites = new Texture(Gdx.files.internal("data/missile.png"));
		tmp = TextureRegion.split(missileSprites, missileSprites.getWidth() / 1, missileSprites.getHeight() / 1);
		frames = new TextureRegion[1];
		index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		missileAnimation = new Animation(0.05f, frames);

		jetSprites = new Texture(Gdx.files.internal("data/fighter.png"));
		tmp = TextureRegion.split(jetSprites, jetSprites.getWidth() / 4, jetSprites.getHeight() / 6);
		frames = new TextureRegion[16];
		index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		jetAnimation = new Animation(0.05f, frames);

		logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
		gameOverTexture = new Texture(Gdx.files.internal("data/gameover.png"));
		youWinTexture = new Texture(Gdx.files.internal("data/you_win.png"));
		splashscreenTexture = new Texture(Gdx.files.internal("data/splashscreen.png"));
	}

	public static void setMute(boolean newState) {
		mute = newState;
	}

	public static void playMusic() {
		if(mute) return;
		Music music = Gdx.audio.newMusic(Gdx.files.internal("data/through_space.ogg"));
		music.play();
		music.setVolume(1.0f);
	}

	public static void playGameOverSound() {
		if(mute) return;
		//gameOverSound.play();
	}
	public static void playStartSound() {
		if(mute) return;
		//startSound.play();
	}
	public static void playExplosionSound() {
		if(mute) return;
		explosionSound.play();
	}

	public static void dispose() {
		splashscreenTexture.dispose();
		backgroundEarth.dispose();
		backgroundSpace.dispose();
		ufoSprites.dispose();
		potatoSprites.dispose();
		explosionSprites.dispose();
		missileSprites.dispose();
		jetSprites.dispose();
		logoTexture.dispose();
		gameOverTexture.dispose();
		youWinTexture.dispose();
		textFont.dispose();
		explosionSound.dispose();
	}
}

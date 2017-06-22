package com.brainplus.spacespuds.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brainplus.spacespuds.SpaceSpuds;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Space Spuds";
		//config.width = 480;	config.height = 640;
		config.width = 540;	config.height = 960;
		new LwjglApplication(new SpaceSpuds(), config);
	}
}

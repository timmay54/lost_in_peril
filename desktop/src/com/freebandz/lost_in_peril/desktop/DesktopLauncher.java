package com.freebandz.lost_in_peril.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//add stuff here before start screen shows
		config.title = "Lost in Peril";
		config.width = Lost_In_Peril.WIDTH;
		config.height = Lost_In_Peril.HEIGHT;
		config.resizable = false;
		config.foregroundFPS = 60;
		new LwjglApplication(new Lost_In_Peril(), config);
		
	}
}

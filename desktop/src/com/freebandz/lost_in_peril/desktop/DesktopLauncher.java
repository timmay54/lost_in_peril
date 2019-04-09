package com.freebandz.lost_in_peril.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.freebandz.lost_in_peril.Lost_In_Peril;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//add shit here before start screen shows
		config.title = "Lost in Peril";
		config.width = 800;
		config.height = 480;
		config.foregroundFPS = 60;
		new LwjglApplication(new Lost_In_Peril(), config);
	}
}

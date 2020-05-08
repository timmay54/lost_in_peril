package com.freebandz.lost_in_peril.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.MainMenuScreen;

//import sun.security.tools.keytool.Main;

/*  DIRECTIONS FOR DESKTOP (as of 4-12):
 * TO COMPILE AND OUTPUT TO FOLDER:
 * First check/change permissions of gradlew file in folder (chmod +x gradlew) 
 * run this in project directory(in terminal, not eclipse): 
 * sudo ./gradlew desktop:dist
 * That will place the output into lost_in_peril/desktop/build/libs/
 * 
 * TO SIMPLY RUN FILE IF NOT WORKING IN ECLIPSE:
 * sudo ./gradlew desktop:run
 */ 


public class DesktopLauncher {

	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//add stuff here before start screen shows

		Lost_In_Peril.platformName = "desktop";

		//System.out.println(args.length);

		if(args.length != 0){
			if(args[0].equals("GodMode")){
				MainMenuScreen.godMode = true;
			}
		}

		config.title = "Lost in Peril";
		config.width = Lost_In_Peril.WIDTH;
		config.height = Lost_In_Peril.HEIGHT;
		config.resizable = false;
		config.foregroundFPS = 60;
		config.backgroundFPS = 0;
		config.vSyncEnabled = false;
		config.addIcon("badlogic.jpg", FileType.Internal);//256
		config.addIcon("badlogic128.jpg", FileType.Internal);//128
		config.addIcon("badlogic32.jpg", FileType.Internal);//32
		config.addIcon("badlogic16.jpg", FileType.Internal);//16

		new LwjglApplication(new Lost_In_Peril(), config);
		
	}
}

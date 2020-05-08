/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen & Dennis McMeekan of Western Illinois University
 */

//Testing of Screen classes, with certain instances to determine if working

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;
import com.freebandz.lost_in_peril.screens.MainMenuScreen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScreenTest {
	@Test
	public void testGetMap() {

		GameScreen x = new GameScreen(new Lost_In_Peril());
		TiledMap a = x.getMap();
		TmxMapLoader mapLoader = new TmxMapLoader();
		TiledMap b = mapLoader.load("MAP2_lip.tmx");
		assertEquals(b, a);
	}
}


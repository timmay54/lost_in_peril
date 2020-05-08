/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen & Dennis McMeekan of Western Illinois University
 */

//Testing of Attributes class, with certain instances to determine if working

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.badlogic.gdx.Game;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;
import com.freebandz.lost_in_peril.screens.MainMenuScreen;

public class AttributesTest {
	@Test
	public void testMineCollision() {
		int a = Lost_In_Peril.MINE_BIT;
		assertEquals(64, a, 0.0);
	}

	@Test
	public void testRandyCollision() {
		int a = Lost_In_Peril.RANDY_BIT;
		assertEquals(2, a, 0.0);
	}

	@Test
	public void testCoinCollision() {
		int a = Lost_In_Peril.COIN_BIT;
		assertEquals(8, a, 0.0);
	}

	@Test
	public void testChestCollision() {
		int a = Lost_In_Peril.CHEST_BIT;
		assertEquals(4, a, 0.0);
	}

	@Test
	public void testMusicVolume() {
		float a = MainMenuScreen.musicVolume;
		assertEquals(.5, a, 0.0);
	}

	@Test
	public void RandySpeed() {
		double a = GameScreen.SPEED;
		assertEquals(10, a, 0.0);
	}
}

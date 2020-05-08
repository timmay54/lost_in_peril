/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.Sprites.Dragon;
import com.freebandz.lost_in_peril.Sprites.Randy;
import com.freebandz.lost_in_peril.screens.GameScreen;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import box2dLight.RayHandler;

public class RandyTest {
    HeadlessApplication game;
    Randy test;

    @Before
    public void setUp() throws Exception {
        test= Mockito.mock(Randy.class);
        //test.b2body.get (c.getX()).andReturn(102f).anyTimes();
        //replay(c);
    }

    /*@Test
    public void testRandy(){
        Lost_In_Peril game = Mockito.mock(Lost_In_Peril.class);
        //HeadlessApplication headless = Mockito.mock(HeadlessApplication<Lost_In_Peril.class>);
        //game.setScreen(new GameScreen(game));
        //World world = new World(new Vector2(0f,0f),false);
        GameScreen screen = (GameScreen) game.getScreen();
        World world = (World) screen.getWorld();
        Dragon drag = new Dragon(screen,44f,44f, new RayHandler( world , 5,5));

        assertEquals(test.b2body.getType(),drag.b2body.getType());
    }*/

    @Test
    public void testLostInPeril(){
        Lost_In_Peril game = Mockito.mock(Lost_In_Peril.class);
        Batch batch = game.batch;

        assertEquals(new Lost_In_Peril().batch, batch);
    }
}

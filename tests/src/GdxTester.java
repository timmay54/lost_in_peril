/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.Sprites.Randy;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class GdxTester {

    private static Application app;

    @BeforeClass
    public static void init() {
        app = new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {
            }

            @Override
            public void resize(int width, int height) {

            }

            @Override
            public void render() {

            }

            @Override
            public void pause() {

            }

            @Override
            public void resume() {

            }

            @Override
            public void dispose() {

            }
        });

        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }

    @Test
    public void testImage(){
        assertTrue("This test will only pass when the badlogic.jpg file coming with a new project setup has not been deleted.", Gdx.files.internal("../android/assets/badlogic.jpg").exists());
    }

    @Test
    public void testGame() {

        assertEquals(app.getType(), Application.ApplicationType.HeadlessDesktop);

    }
}

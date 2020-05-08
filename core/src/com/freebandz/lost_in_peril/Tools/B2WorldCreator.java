/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

package com.freebandz.lost_in_peril.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.Sprites.Chest;
import com.freebandz.lost_in_peril.Sprites.Teleporter;
import com.freebandz.lost_in_peril.screens.GameScreen;

public class B2WorldCreator {
    public B2WorldCreator(GameScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //WALLS
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() /2) / Lost_In_Peril.PPM, (rect.getY() + rect.getHeight()/2) / Lost_In_Peril.PPM);
            body = world.createBody(bdef);
            shape.setAsBox((rect.getWidth()) /2/ Lost_In_Peril.PPM, (rect.getHeight()) /2/ Lost_In_Peril.PPM);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("Wall");
        }

        //TELEPORTERS
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            /*bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() /2) / Lost_In_Peril.PPM, (rect.getY() + rect.getHeight()/2) / Lost_In_Peril.PPM);
            body = world.createBody(bdef);
            shape.setAsBox((rect.getWidth()) /2/ Lost_In_Peril.PPM, (rect.getHeight()) /2/ Lost_In_Peril.PPM);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("Teleporter");*/

            new Teleporter(world, map, rect);
        }

        //CHESTS
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            new Chest(world, map, rect);
        }



    }


}

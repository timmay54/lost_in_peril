/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

package com.freebandz.lost_in_peril.Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.freebandz.lost_in_peril.Lost_In_Peril;
import com.freebandz.lost_in_peril.screens.GameScreen;

public class Chest extends InteractiveTileObject{
    public Chest(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Lost_In_Peril.CHEST_BIT);
    }

    @Override
    public void onHit(){
        setCategoryFilter(Lost_In_Peril.DESTROYED_BIT);
        GameScreen.hud.setScore(GameScreen.hud.getScore() + 50);
        getCell().setTile(null);
        
    }
}

/*
 * Copyright (c) 2019.
 *
 * Tim Mardesen of Western Illinois University
 */

package com.freebandz.lost_in_peril.Sprites

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.EdgeShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.utils.Array
import com.freebandz.lost_in_peril.Lost_In_Peril
import com.freebandz.lost_in_peril.screens.GameScreen

import box2dLight.PointLight
import box2dLight.RayHandler
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class Dragon(screen: GameScreen, x: Float, y: Float, internal var rayHandler: RayHandler) : BadGuy(screen, x, y) {
    var currentState: Dragon.State
    var previousState: Dragon.State
    val atlas: TextureAtlas
    private val baddieUp: Animation<*>
    private val baddieDown: Animation<*>
    private val baddieLeft: Animation<*>
    private val baddieRight: Animation<*>
    private val runningRight: Boolean
    private var stateTimer: Float = 0.toFloat()
    internal var light: PointLight? = null
    private val baddieStand: TextureRegion

    val state: State
        get() = if (b2body.linearVelocity.y > 0) {
            Dragon.State.UP
        } else if (b2body.linearVelocity.x > 0) {
            Dragon.State.RIGHT
        } else if (b2body.linearVelocity.x < 0) {
            Dragon.State.LEFT
        } else if (b2body.linearVelocity.y < 0) {
            Dragon.State.DOWN
        } else {
            Dragon.State.STANDING
        }

    enum class State {
        STANDING, LEFT, UP, DOWN, RIGHT
    }

    init {
        //super.screen.getAtlas().findRegion("AH_SpriteSheet_People1");
        atlas = TextureAtlas("dragoon.atlas")
        currentState = Dragon.State.STANDING
        previousState = Dragon.State.STANDING
        stateTimer = 0f
        runningRight = true
        //ANIMATION INITIALIZATION
        //DOWN
        val frames = Array<TextureRegion>()
        for (i in 0..2) {
            frames.add(TextureRegion(this.atlas.findRegion("AH_SpriteSheet_Dragoon"), i * 16, 0, 16, 16))
        }
        frames.add(TextureRegion(this.atlas.findRegion("AH_SpriteSheet_Dragoon"), 16, 0, 16, 16))
        baddieDown = Animation(0.1f, frames)
        frames.clear()

        //RUNNING UPWARDS
        for (i in 0..2) {
            frames.add(TextureRegion(this.atlas.findRegion("AH_SpriteSheet_Dragoon"), i * 16, 0, 16, 16))
        }
        frames.add(TextureRegion(this.atlas.findRegion("AH_SpriteSheet_Dragoon"), 16, 0, 16, 16))
        baddieUp = Animation(0.1f, frames)
        frames.clear()

        //LEFT
        for (i in 0..2) {
            frames.add(TextureRegion(this.atlas.findRegion("AH_SpriteSheet_Dragoon"), i * 16, 16, 16, 16))
        }
        frames.add(TextureRegion(this.atlas.findRegion("AH_SpriteSheet_Dragoon"), 16, 16, 16, 16))
        baddieLeft = Animation(0.1f, frames)
        frames.clear()

        //RIGHT
        for (i in 0..2) {
            frames.add(TextureRegion(this.atlas.findRegion("AH_SpriteSheet_Dragoon"), i * 16, 32, 16, 16))
        }
        baddieRight = Animation(0.1f, frames)
        frames.clear()

        baddieStand = TextureRegion(this.atlas.findRegion("AH_SpriteSheet_Dragoon"), 16, 32, 16, 16)

        setBounds(getX(), getY(), 16 / Lost_In_Peril.PPM, 16 / Lost_In_Peril.PPM)
        setRegion(baddieStand)
    }

    fun update(dt: Float) {
        setPosition(b2body.position.x - 16, b2body.position.y - 16)
        setRegion(getFrame(dt))
    }

    fun getFrame(dt: Float): TextureRegion {
        currentState = state
        val region: TextureRegion
        when (currentState) {
            Dragon.State.UP -> region = baddieUp.getKeyFrame(stateTimer, true) as TextureRegion

            Dragon.State.LEFT -> region = baddieLeft.getKeyFrame(stateTimer, true) as TextureRegion

            Dragon.State.RIGHT -> region = baddieRight.getKeyFrame(stateTimer, true) as TextureRegion

            Dragon.State.DOWN -> region = baddieDown.getKeyFrame(stateTimer, true) as TextureRegion

            else -> region = baddieStand
        }

        /*
		if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
			region.flip(true, false);
			runningRight = false;
		}
		else if((b2body.getLinearVelocity().x > 0 || runningRight) &&region.isFlipX()){
			region.flip(true, false);
			runningRight = true;
		}

		 */



        stateTimer = if (currentState == previousState) stateTimer + dt else 0f
        previousState = currentState
        return region
    }

    override fun defineBadGuy(x: Float, y: Float) {
        val bdef = BodyDef()
        bdef.position.set(4100f, 850f) //Exact coordinates, do not divide by PPM
        bdef.type = BodyDef.BodyType.DynamicBody
        b2body = world.createBody(bdef)
        val fdef = FixtureDef()
        val shape = CircleShape()
        shape.radius = 7 / Lost_In_Peril.PPM

        //light = new box2dLight.PointLight(rayHandler, 1000, Color.WHITE,50/Lost_In_Peril.PPM,0f,0f);
        //light.attachToBody(b2body);

        fdef.shape = shape
        b2body.createFixture(fdef).userData = "Dragon"

        val motto = "lets get em"

        //collision detection stuff, dunno what it does tho????


        val head = EdgeShape()
        head.set(Vector2(-2 / Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM), Vector2(2 / Lost_In_Peril.PPM, 5 / Lost_In_Peril.PPM))
        fdef.shape = head
        fdef.isSensor = true
        b2body.createFixture(fdef).userData = "head"
    }

    override fun onHit() {

    }
}

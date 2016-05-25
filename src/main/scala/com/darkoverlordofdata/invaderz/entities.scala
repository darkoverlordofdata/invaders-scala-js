package com.darkoverlordofdata.invaderz

import com.darkoverlordofdata.entitas.Pool
import com.darkoverlordofdata.entitas.{Entity, Matcher, IMatcher, IComponent}
import com.scalawarrior.scalajs.createjs.{SpriteSheet, Sprite, Bitmap}
import com.darkoverlordofdata.invaderz.EntityExtensions.ExtendEntity


object PoolExtensions {
  lazy val master = new Sprite(Invaderz.sprites, "invader")

  implicit class ExtendPool(val pool:Pool) {

    def createPlayer(): Entity = {
      val sprite = new Sprite(Invaderz.sprites, "ship")
      val bounds = sprite.getBounds()
      sprite.regX = bounds.width/2
      sprite.regY = bounds.height/2
      sprite.y = Invaderz.height
      sprite.x = Invaderz.width/2
      pool.createEntity("player")
        .addView(sprite)
        .addPosition(sprite.x.toFloat, sprite.y.toFloat)
        .addBounds(bounds.width.toFloat, bounds.height.toFloat)
        .addVelocity(0f, 0f)
        .setPlayer(true)
    }

    def createBullet(position:PositionComponent): Entity = {
      val sprite = new Sprite(Invaderz.sprites, "bullet")
      val bounds = sprite.getBounds()
      sprite.regX = bounds.width/2
      sprite.regY = bounds.height/2
      sprite.x = position.x
      sprite.y = position.y
      pool.createEntity("bullet")
        .addView(sprite)
        .addPosition(sprite.x.toFloat, sprite.y.toFloat)
        .addBounds(bounds.width.toFloat, bounds.height.toFloat)
        .addVelocity(0f, -100f)
        .setBullet(true)
    }

    def createAlien(x:Float, y:Float, speed:Float): Entity = {
      val sprite = master.clone()
      val bounds = sprite.getBounds()
      sprite.regX = bounds.width/2
      sprite.regY = bounds.height/2
      sprite.x = x
      sprite.y = y
      pool.createEntity("invader")
        .addView(sprite)
        .addPosition(sprite.x.toFloat, sprite.y.toFloat)
        .addBounds(bounds.width.toFloat, bounds.height.toFloat)
        .addVelocity(speed, 0f)
        .setAlien(true)
    }
  }
}

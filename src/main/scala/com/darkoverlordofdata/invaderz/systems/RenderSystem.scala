package com.darkoverlordofdata.invaderz.systems

import com.darkoverlordofdata.entitas._
import com.darkoverlordofdata.invaderz.{Invaderz, Match}
import com.scalawarrior.scalajs.createjs.{Sprite, Bitmap}
import com.darkoverlordofdata.invaderz.EntityExtensions._

class RenderSystem(pool:Pool)
  extends IInitializeSystem with IExecuteSystem {

  lazy val views = pool.getGroup(Match.View)

  lazy val background = new Sprite(Invaderz.sprites, "black")

  override def initialize(): Unit = {

    background.scaleX = Invaderz.width / background.getBounds().width
    background.scaleY = Invaderz.height / background.getBounds().height
    Invaderz.stage.addChild(background)
  }

  override def execute(): Unit = {
    Invaderz.stage.update()
    
  }


  views.onEntityAdded += {e: GroupChangedArgs =>
    //println(s"onEntityAdded ${e.entity}")
    Invaderz.stage.addChild(e.entity.view.sprite)
  }

}

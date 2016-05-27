package com.darkoverlordofdata.invaderz

import co.technius.scalajs.pixi.Container
import co.technius.scalajs.pixi.Sprite
import co.technius.scalajs.pixi.Pixi
import co.technius.scalajs.pixi.Pixi.Texture
import co.technius.scalajs.pixi.loaders.{ResourceDictionary, Loader}

import com.darkoverlordofdata.entitas.{Systems, Pool}
import com.darkoverlordofdata.invaderz.systems._
import org.scalajs.dom.raw.HTMLCanvasElement
import scala.scalajs.js
import org.scalajs.dom
import js.Dynamic.{ global => g }
import scala.scalajs.js.JSApp

object Invaderz extends JSApp {
  var delta = 0.0f
  var previousTime = 0.0f
  var shoot = false
  var mouseX = 0.0f
  var mouseY = 0.0f

  val keys = new scala.collection.mutable.HashMap[Int, Boolean]()
  lazy val width = { dom.window.innerWidth }
  lazy val height = { dom.window.innerHeight }

  lazy val renderer = Pixi.autoDetectRenderer(width, height)
  lazy val stage = new Container

  lazy val pool: Pool = { new Pool(Component.TotalComponents.id) }
  lazy val systems: Systems = { new Systems() }

  def main(): Unit = {
    Pixi.loader
      .add ("black", "images/black.png")
      .add ("bullet", "images/bullet.png")
      .add ("invader", "images/invader.png")
      .add ("ship", "images/ship.png")
      .load(loaded)

    dom.document.body.appendChild(renderer.view)

    lazy val loaded = (loader:Loader, res:ResourceDictionary) => {
      val bgd = new Sprite(Texture.fromImage("images/black.png"))
      stage.addChild(bgd)
      systems
        .add(pool.createSystem(new RenderSystem(pool)))
        .add(pool.createSystem(new PhysicsSystem(pool)))
        .add(pool.createSystem(new InputSystem(pool)))
        .add(pool.createSystem(new CreateAliensSystem(pool)))
        .add(pool.createSystem(new CollisionSystem(pool)))
        .add(pool.createSystem(new DestroySystem(pool)))

      systems.initialize()
      g.addEventListener("keydown", (e: dom.KeyboardEvent) => {keys.put(e.keyCode, true)}, false)
      g.addEventListener("keyup", (e: dom.KeyboardEvent) => {keys.remove(e.keyCode)}, false)


//      stage.canvas.addEventListener("mousedown", (e: dom.MouseEvent) => {shoot = true}, useCapture=true)
//      stage.canvas.addEventListener("mouseup", (e: dom.MouseEvent) => {shoot = false}, useCapture=true)
//      stage.canvas.addEventListener("mousemove",
//        (e: dom.MouseEvent) => {
//          mouseX = e.clientX.toFloat
//          mouseY = height - e.clientY.toFloat
//        }, useCapture=true
//      )


      dom.document.addEventListener("touchstart",
        (e: dom.raw.TouchEvent) => {
          println("touchstart")
          shoot = true
          mouseX = e.touches.item(0).clientX.toFloat
          mouseY = height - e.touches.item(0).clientY.toFloat
        }, useCapture=true
      )
      dom.document.addEventListener("touchend",
        (e: dom.raw.TouchEvent) => {
          println("touchend")
          shoot = false
        }, useCapture=true
      )
      dom.document.addEventListener("touchmove",
        (e: dom.raw.TouchEvent) => {
          println("touchmove")
          mouseX = e.touches.item(0).clientX.toFloat
          mouseY = height - e.touches.item(0).clientY.toFloat
        }, useCapture=true
      )

    }


    lazy val render: (Double) => Unit = { time =>
      val prev = if (previousTime == 0) time else previousTime
      previousTime = time.toFloat
      delta = ((time - prev) * 0.001).toFloat
      systems.execute()
      dom.window.requestAnimationFrame(render)
    }

    //render(0)
    dom.window.requestAnimationFrame(render)

  }
}
//    resources.loadManifest(js.Array(js.Dictionary("src" -> "images/invaderz.json", "id" -> "resources", "type" -> "spritesheet")))
//
//    resources.addEventListener("complete", (e: Object)  => {
//      systems
//        .add(pool.createSystem(new RenderSystem(pool)))
//        .add(pool.createSystem(new PhysicsSystem(pool)))
//        .add(pool.createSystem(new InputSystem(pool)))
//        .add(pool.createSystem(new CreateAliensSystem(pool)))
//        .add(pool.createSystem(new CollisionSystem(pool)))
//        .add(pool.createSystem(new DestroySystem(pool)))
//
//      systems.initialize()
//      println("INITIALIZED")
//      g.addEventListener("keydown", (e: dom.KeyboardEvent) => {keys.put(e.keyCode, true)}, false)
//      g.addEventListener("keyup", (e: dom.KeyboardEvent) => {keys.remove(e.keyCode)}, false)
//
//
//      stage.canvas.addEventListener("mousedown", (e: dom.MouseEvent) => {shoot = true}, useCapture=true)
//      stage.canvas.addEventListener("mouseup", (e: dom.MouseEvent) => {shoot = false}, useCapture=true)
//      stage.canvas.addEventListener("mousemove",
//        (e: dom.MouseEvent) => {
//          mouseX = e.clientX.toFloat
//          mouseY = height - e.clientY.toFloat
//        }, useCapture=true
//      )
//
//
//      dom.document.addEventListener("touchstart",
//        (e: dom.raw.TouchEvent) => {
//          println("touchstart")
//          shoot = true
//          mouseX = e.touches.item(0).clientX.toFloat
//          mouseY = height - e.touches.item(0).clientY.toFloat
//        }, useCapture=true
//      )
//      dom.document.addEventListener("touchend",
//        (e: dom.raw.TouchEvent) => {
//          println("touchend")
//          shoot = false
//        }, useCapture=true
//      )
//      dom.document.addEventListener("touchmove",
//        (e: dom.raw.TouchEvent) => {
//          println("touchmove")
//          mouseX = e.touches.item(0).clientX.toFloat
//          mouseY = height - e.touches.item(0).clientY.toFloat
//        }, useCapture=true
//      )
//      Ticker.setFPS(30.0)
//      Ticker.addEventListener("tick", (e: TickerEvent) => {
//        delta = e.delta.toFloat/1000.0f
//        systems.execute()
//      })
//      true
//    })
//    resources.load()
//  }

//  def createCanvas()  = {
//    val canvas = dom.document.createElement("canvas")
//    val width = dom.window.innerWidth
//    val height = dom.window.innerHeight
//
//    canvas.setAttribute("width", width.toString)
//    canvas.setAttribute("height", height.toString)
//    dom.document.body.appendChild(canvas)
//    canvas.asInstanceOf[HTMLCanvasElement]
//  }

//}

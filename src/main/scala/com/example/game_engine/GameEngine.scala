package com.example.game_engine

import com.example.game_engine.chess.ChessDrawer
import scalafx.scene.Scene
import scalafx.stage.Stage

object GameEngine {
  // two functionalities:
  // 1) Drawer
  // 2) Controller

  def factory(start: String): Unit = {
    // drawer: Drawer, controller: Controller
    println(start)
    // start.show()

    val d: Drawer = new ChessDrawer

    val stage = new Stage() {
      scene = new Scene() {
        content = d.draw()
      }
    }
    stage.show()
    // d.draw()

    // DrawerChess
    // DrawerXO
    // Dr..
    // new DrawerChess
    // ControllerChess

    // controllerChess : Instance
    // controllerChess.move(input)

    // GameEngine.factory(new DrawerChess, new ControllerChess)
    // drawer.draw()
    // controller.move(input)
  }

}

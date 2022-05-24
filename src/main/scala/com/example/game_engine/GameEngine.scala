package com.example.game_engine

import com.example.game_engine.chess.{Board, ChessController, ChessDrawer}
import scalafx.scene.Scene
import scalafx.scene.layout.GridPane
import scalafx.stage.Stage

object GameEngine {

  // two functionalities:
  // 1) Drawer
  // 2) Controller
  def factory(start: String): Unit = {
    // drawer: Drawer, controller: Controller

    new ChessController(new Board)
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

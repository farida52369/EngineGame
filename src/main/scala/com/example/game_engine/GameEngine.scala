package com.example.game_engine

import com.example.game_engine.tictactoe.Board
import com.example.game_engine.tictactoe.TicTacToeController

object GameEngine {

  // two functionalities:
  // 1) Drawer
  // 2) Controller
  def factory(start: String): Unit = {
    // drawer: Drawer, controller: Controller

    val board: Board = new Board
    new TicTacToeController(board)

    //new ChessController(board)
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

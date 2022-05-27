package com.example.game_engine.tic_tac_toe

import com.example.game_engine.Controller
import scalafx.scene.layout._

class XOController extends Controller[XOBoard] {

  override def control(input: String, gridPane: GridPane, board: XOBoard, turn: Boolean): Boolean = {
    if (input.length != 1) return false

    val a: Int = Char.char2int(input.charAt(0)) - 49
    if (!board.valid_move(a / 3, a % 3)) {
      return false
    }
    println(a)
    move((a / 3, a % 3), gridPane, board, turn)
    true
  }

  def move(dest: (Int, Int), gridPane: GridPane, board: XOBoard, turn: Boolean): Unit = {
    board.setTurn(turn)
    board.make_move(dest._1, dest._2)
    gridPane.add(board.board(dest._1)(dest._2).getPieceSpirit, dest._2, dest._1)
  }
}
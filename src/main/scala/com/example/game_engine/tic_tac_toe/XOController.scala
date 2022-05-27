package com.example.game_engine.tic_tac_toe

import com.example.game_engine.Controller
import scalafx.scene.layout._

class XOController extends Controller[XOBoard] {

  override def control(input: String, gridPane: GridPane, board: XOBoard,turn: Boolean): Boolean = {
    if (input.length != 2) return false

    val a: Int = input.charAt(0).toUpper - 65
    val b: Int = 2-(Char.char2int(input.charAt(1)) - 49)
    println(b+"hhhh"+a)
    if (!board.valid_move(b, a)) {
      return false
    }

    move((b, a), gridPane, board,turn)
  }

  def move(dest: (Int, Int), gridPane: GridPane, board: XOBoard,turn: Boolean): Boolean = {
    board.setTurn(turn);
    val p = new XOPiece(dest._1, dest._2, board.xTurn)
    val moves: Int = p.validMoves(board)
    if (moves != -1) {
      board.make_move(dest._1, dest._2)
      gridPane.add(board.board(dest._1)(dest._2).getPieceSpirit, dest._2, dest._1)
      return true
    }
    false
  }
}
package com.example.game_engine.connect4

import com.example.game_engine.Controller
import scalafx.scene.layout._

class Connect4Controller extends Controller[Connect4Board] {

  override def control(input: String, gridPane: GridPane, board: Connect4Board): Boolean = {
    if (input.length != 1) return false

    val a: Int = Char.char2int(input.charAt(0)) - 49
    if (!board.valid_move(a)) {
      return false
    }

    // println("Input: " + a + " " + b + " " + c + " " + d)
    move(a, gridPane, board)
  }

  def move(dest: Int, gridPane: GridPane, board: Connect4Board): Boolean = {
    val p = new Connect4Piece(dest, board.redPlayerTurn)
    val moves: Int = p.validMoves(board)
    if (moves != -1) {
      board.make_move(moves, dest)
      gridPane.add(board.board(moves)(dest).getPieceSpirit, dest + 1, moves + 1)
      return true
    }
    false
  }
}
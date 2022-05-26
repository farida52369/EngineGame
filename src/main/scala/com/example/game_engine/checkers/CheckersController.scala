package com.example.game_engine.checkers

import com.example.game_engine.checkers.pieces.Piece
import com.example.game_engine.{Constants, Controller}
import scalafx.scene.layout._

import scala.math.abs

class CheckersController extends Controller[CheckersBoard] {

  override def control(input: String, gridPane: GridPane, board: CheckersBoard): Boolean = {
    if (input.length != 4) return false

    val a: Int = input.charAt(0).toUpper - 65
    val b: Int = 7 - (Char.char2int(input.charAt(1)) - 49)
    val c: Int = input.charAt(2).toUpper - 65
    val d: Int = 7 - (Char.char2int(input.charAt(3)) - 49)

    // println("Input: " + a + " " + b + " " + c + " " + d)

    if (!board.piece_at_coordination(b, a) || !board.in_bound(d, c) || board.board(b)(a).color != board.play_turn() ||
      (board.piece_at_coordination(d, c) && board.board(b)(a).color == board.board(d)(c).color))
      return false

    move((b, a), (d, c), gridPane, board)
  }


  def move(src: (Int, Int), dest: (Int, Int), gridPane: GridPane, board: CheckersBoard): Boolean = {

    // println(src._1 + "src" + src._2)
    // println(dest._1 + "dest" + dest._2)
    val moves: List[(Int, Int)] = board.board(src._1)(src._2).validMoves(board)

    for (move <- moves) {
      if (dest == move) {
        var piece: Piece = board.board(src._1)(src._2)
        board.make_move(src, dest)

        var field: StackPane = new StackPane()
        field.setBackground(if (((src._1 + src._2) & 1) == 0) Constants.WHITE else Constants.GREY)
        gridPane.add(field, src._2 + 1, src._1 + 1)

        if (board.board(dest._1)(dest._2) != null) {
          field = new StackPane()
          field.setBackground(if (((dest._1 + dest._2) & 1) == 0) Constants.WHITE else Constants.GREY)
          gridPane.add(field, dest._2 + 1, dest._1 + 1)
        }

        if (board.board(dest._1)(dest._2).name == "Checker" && abs(dest._1 - src._1) == 2) {
          field = new StackPane()
          field.setBackground(if ((((abs(dest._1 + src._1) / 2) + ((dest._2 + src._2) / 2)) & 1) == 0) Constants.WHITE else Constants.GREY)
          gridPane.add(field, abs(dest._2 + src._2) / 2 + 1, abs(dest._1 + src._1) / 2 + 1)
        } else if (board.board(dest._1)(dest._2).name == "CrownChecker" && abs(dest._1 - src._1) == 2) {
          field = new StackPane()
          field.setBackground(if (((((dest._1 + src._1) / 2) + ((dest._2 + src._2) / 2)) & 1) == 0) Constants.WHITE else Constants.GREY)
          gridPane.add(field, (dest._2 + src._2) / 2 + 1, (dest._1 + src._1) / 2 + 1)
        }

        piece = board.board(dest._1)(dest._2)
        gridPane.add(piece.getPieceSpirit, dest._2 + 1, dest._1 + 1)

        return true
      }
    }
    false
  }
}
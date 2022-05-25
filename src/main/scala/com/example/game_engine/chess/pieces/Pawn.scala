package com.example.game_engine.chess.pieces

import com.example.game_engine.chess.Board
import scalafx.scene.image.{Image, ImageView}

class Pawn(x: Int, y: Int, color: Boolean, hasMoved: Boolean = false) extends Piece(x, y, color, hasMoved) {
  override def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_chess/%s_pawn.png", if (color) "white" else "black")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitHeight(OPTIMAL_SIZE)
    imageView.setFitWidth(OPTIMAL_SIZE)

    imageView
  }

  override def validMoves(board: Board): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()

    // -1 -> white player, 1 -> black player
    val change = if (color) -1 else 1

    /*
    println("YARAB")
    board.board.foreach(e => {
      e.foreach(m => {
        if (m != null)
          println(m.x + " " + m.y)
      })
    })
    */

    // Move forward 1 cell __ Pawn can't attack in a straight line
    if (board.valid_move((x + change, y), color) && !board.piece_at_coordination(x + change, y)) {
      moves = (x + change, y) :: moves


      // tab what about Move forward 2 cell
      if (board.valid_move((x + 2 * change, y), color) && !hasMoved && !board.piece_at_coordination(x + 2 * change, y))
        moves = (x + 2 * change, y) :: moves
    }

    // Attack Diagonal __ Left
    if (board.valid_move((x + change, y - 1), color) && board.enemy_at_coordination((x + change, y - 1), color))
      moves = (x + change, y - 1) :: moves

    // Attack Diagonal __ Right
    if (board.valid_move((x + change, y + 1), color) && board.enemy_at_coordination((x + change, y + 1), color))
      moves = (x + change, y + 1) :: moves

    moves
  }

  override def name: String = "Pawn"
}

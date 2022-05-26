package com.example.game_engine.chess.pieces

import com.example.game_engine.chess.ChessBoard
import scalafx.scene.image._

class King(x: Int, y: Int, color: Boolean, hasMoved: Boolean = false) extends Piece(x, y, color, hasMoved) {
  override def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_chess/%s_king.png", if (color) "white" else "black")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)

    imageView
  }

  override def validMoves(board: ChessBoard): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()

    // King Moves
    for (i <- x - 1 until (x + 2)) {
      for (j <- y - 1 until (y + 2)) {
        if (board.valid_move((i, j), color)) {
          moves = (i, j) :: moves
        }
      }
    }
    moves
  }

  override def name: String = "King"
}

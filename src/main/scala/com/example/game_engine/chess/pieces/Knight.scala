package com.example.game_engine.chess.pieces

import com.example.game_engine.chess.Board
import scalafx.scene.image.{Image, ImageView}

class Knight(x: Int, y: Int, color: Boolean, hasMoved: Boolean = false) extends Piece(x, y, color, hasMoved) {
  override def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_chess/%s_knight.png", if (color) "white" else "black")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)

    imageView
  }

  override def validMoves(board: Board): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()

    // Knight moves
    for (i <- x - 2 to x + 2) {
      for (j <- y - 2 to y + 2) {
        if ((math.abs(x - i) == 2 && math.abs(y - j) == 1) || (math.abs(x - i) == 1 && math.abs(y - j) == 2)) {
          if (board.valid_move((i, j), color)) {
            moves = (i, j) :: moves
          }
        }
      }
    }
    moves
  }

  override def name: String = "Knight"
}

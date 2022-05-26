package com.example.game_engine.chess.pieces

import com.example.game_engine.chess.ChessBoard
import scalafx.scene.image.{Image, ImageView}

import scala.util.control.Breaks.{break, breakable}

class Rook(x: Int, y: Int, color: Boolean, hasMoved: Boolean = false) extends Piece(x, y, color, hasMoved) {
  override def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_chess/%s_rook.png", if (color) "white" else "black")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)

    imageView
  }

  override def validMoves(board: ChessBoard): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()

    // Rook Moves __ In the four dimensions
    val dim: Array[(Int, Int)] = Array((1, 0), (0, 1), (-1, 0), (0, -1))
    for (i <- 0 until 4) {
      // maximum moves __ 8
      breakable {
        for (j <- 0 until 8) {
          val xNew = x + dim(i)._1 * j
          val yNew = y + dim(i)._2 * j
          if (x != xNew || y != yNew) {
            if (board.valid_move((xNew, yNew), color))
              moves = (xNew, yNew) :: moves
            else break
          }
          if (board.piece_at_coordination(xNew, yNew)) break
        }
      }
    }
    moves
  }

  override def name: String = "Rook"
}

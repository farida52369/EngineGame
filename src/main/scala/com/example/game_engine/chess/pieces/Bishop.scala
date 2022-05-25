package com.example.game_engine.chess.pieces

import com.example.game_engine.chess.Board
import scalafx.scene.image.{Image, ImageView}

import scala.util.control.Breaks.{break, breakable}

class Bishop(x: Int, y: Int, color: Boolean, hasMoved: Boolean = false) extends Piece(x, y, color, hasMoved) {
  override def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_chess/%s_bishop.png", if (color) "white" else "black")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)

    imageView
  }

  override def validMoves(board: Board): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()

    // Bishop Could move in four dimensions (diagonally)
    val dim: Array[(Int, Int)] = Array((1, 1), (-1, 1), (-1, -1), (1, -1))
    for (i <- 0 until 4) {
      var xNew = x
      var yNew = y
      breakable {
        while (board.valid_move((xNew + dim(i)._1, yNew + dim(i)._2), color)) {
          moves = (xNew + dim(i)._1, yNew + dim(i)._2) :: moves
          if (board.piece_at_coordination((xNew + dim(i)._1, yNew + dim(i)._2)))
            break
          xNew += dim(i)._1
          yNew += dim(i)._2
        }
      }
    }
    moves
  }

  override def name: String = "Bishop"
}

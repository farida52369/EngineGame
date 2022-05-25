package com.example.game_engine.chess.pieces

import com.example.game_engine.chess.Board
import scalafx.scene.image.{Image, ImageView}

class Queen(x: Int, y: Int, color: Boolean, hasMoved: Boolean = false) extends Piece(x, y, color, hasMoved) {
  override def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_chess/%s_queen.png", if (color) "white" else "black")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)

    imageView
  }

  override def validMoves(board: Board): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()
    moves = List.concat(new Rook(x, y, color).validMoves(board), new Bishop(x, y, color).validMoves(board))
    moves
  }

  override def name: String = "Queen"
}

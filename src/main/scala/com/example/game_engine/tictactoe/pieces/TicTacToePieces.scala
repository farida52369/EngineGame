package com.example.game_engine.tictactoe.pieces

import com.example.game_engine.tictactoe
import scalafx.scene.image.{Image, ImageView}

class TicTacToePieces(x:Int, y: Int, color: Boolean) extends Piece(x , y, color) {

  override def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_TicTacToe/%s.png", if (color) "img_1" else "img")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)
    imageView
  }

  override def validMoves(board: tictactoe.Board): Int = {
    for (i <- 0 to 2) {
      if (board.board(2 - i)(y) == null) {
        // println(5 - i)
        return 2 - i
      }
    }
    -1
  }

  override def name: String = "tictactoe"

}

package com.example.game_engine.tic_tac_toe

import scalafx.scene.image.{Image, ImageView}

class XOPiece(x: Int, y: Int, color: Boolean) {

  val OPTIMAL_SIZE = 85

  def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_TicTacToe/%s.png", if (color) "x" else "o")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)

    imageView
  }

  def validMoves(board: XOBoard): Int = {
    for (i <- 0 to 2) {
      if (board.board(2 - i)(y) == null) {
        // println(5 - i)
        return 2 - i
      }
    }
    -1
  }

}

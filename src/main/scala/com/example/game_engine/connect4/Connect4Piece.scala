package com.example.game_engine.connect4

import scalafx.scene.image.{Image, ImageView}

class Connect4Piece(y: Int, color: Boolean) {

  val OPTIMAL_SIZE = 75

  def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_connect4/%s.png", if (color) "yellow" else "red")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)

    imageView
  }

  def validMoves(board: Connect4Board): Int = {
    for (i <- 0 to 5) {
      if (board.board(5 - i)(y) == null) {
        return 5 - i
      }
    }
    -1
  }
}

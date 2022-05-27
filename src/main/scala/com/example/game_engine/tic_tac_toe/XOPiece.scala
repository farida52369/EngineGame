package com.example.game_engine.tic_tac_toe

import scalafx.scene.image.{Image, ImageView}

class XOPiece(color: Boolean) {

  val OPTIMAL_SIZE = 115

  def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_TicTacToe/%s.png", if (color) "x" else "o")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)

    imageView
  }
}

package com.example.game_engine.connect4.pieces

import com.example.game_engine.connect4.Board
import scalafx.scene.image.{Image, ImageView}

import scala.util.control.Breaks.break

class Connect4Pieces( y: Int, color: Boolean) extends Piece(y, color) {

  override def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_Connect4/%s.png", if (color) "img_1" else "img")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)
    imageView
  }

  override def validMoves(board: Board): Int = {


       for (i <- 0 to 5) {
         if(board.board(5-i)(y)==null){
           println(5-i)
           return 5-i
         }

       }

    -1
  }

  override def name: String = "connect4"

}

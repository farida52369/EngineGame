package com.example.game_engine.connect4.pieces

import com.example.game_engine.connect4.Board
import scalafx.scene.image.ImageView

abstract class Piece(var y: Int, var color: Boolean) {

  def move(yNew: Int): Unit = {
    y = yNew
  }

  def getPieceSpirit: ImageView

  def validMoves(board: Board): Int

  def name: String

  val OPTIMAL_SIZE = 75
}

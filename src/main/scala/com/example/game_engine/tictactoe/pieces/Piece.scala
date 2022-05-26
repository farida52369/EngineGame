package com.example.game_engine.tictactoe.pieces

import com.example.game_engine.tictactoe
import scalafx.scene.image.ImageView

abstract class Piece(var x: Int, var y: Int, var color: Boolean) {

  def move(xNew:Int, yNew: Int): Unit = {
    x = xNew;
    y = yNew
  }

  def getPieceSpirit: ImageView

  def validMoves(board: tictactoe.Board): Int

  def name: String

  val OPTIMAL_SIZE = 75
}

package com.example.game_engine.chess.pieces

import com.example.game_engine.chess.ChessBoard
import scalafx.scene.image.ImageView

// 0 -> Black, 1 -> White
abstract class Piece(var x: Int, var y: Int, var color: Boolean, var hasMoved: Boolean = false) {

  def move(xNew: Int, yNew: Int): Unit = {
    x = xNew
    y = yNew
  }

  def getPieceSpirit: ImageView

  def validMoves(board: ChessBoard): List[(Int, Int)]

  def name: String

  val OPTIMAL_SIZE = 64
}

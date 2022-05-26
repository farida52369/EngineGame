package com.example.game_engine.checkers.pieces

import com.example.game_engine.checkers.CheckersBoard
import scalafx.scene.image.ImageView

// 0 -> Black, 1 -> White
abstract class Piece(var x: Int, var y: Int, var color: Boolean, var hasCrowned: Boolean = false) {

  def getPieceSpirit: ImageView

  def validMoves(board: CheckersBoard): List[(Int, Int)]

  def name: String

  val OPTIMAL_SIZE = 64
}

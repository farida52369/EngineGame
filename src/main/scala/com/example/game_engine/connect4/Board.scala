package com.example.game_engine.connect4

import com.example.game_engine.connect4.pieces._

class Board {

  // Initialization :)
  val board: Array[Array[Piece]] = Array.ofDim[Piece](6, 7)
  var redPlayerTurn: Boolean = true
  def play_turn(): Boolean = redPlayerTurn

  {
    for (i <- 0 until 6; j <- 0 until 7) {
      board(i)(j) = null
    }
    // board(1)(3)=new Connect4Pieces(1,3,false)
  }

  def valid_move(dest: Int): Boolean = {
    if (dest >= 0 && dest < 7)
      return true
    false
  }

  def next_turn(): Unit = {
    redPlayerTurn = !redPlayerTurn
  }

  def make_move(x: Int, y: Int): Unit = {
    board(x)(y) = new Connect4Pieces(y, redPlayerTurn)
    // Move piece from source to destination
    next_turn()
  }
}

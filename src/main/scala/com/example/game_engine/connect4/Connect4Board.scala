package com.example.game_engine.connect4

class Connect4Board {

  // Initialization :)
  val board: Array[Array[Connect4Piece]] = Array.ofDim[Connect4Piece](6, 7)
  var redPlayerTurn: Boolean = true

  def setTurn(Turn: Boolean): Unit = {
    redPlayerTurn = Turn
  }

  def play_turn(): Boolean = redPlayerTurn

  {
    for (i <- 0 until 6; j <- 0 until 7) {
      board(i)(j) = null
    }
  }

  def valid_move(dest: Int): Boolean = {
    if (dest >= 0 && dest < 7)
      return true
    false
  }

  def make_move(x: Int, y: Int): Unit = {
    board(x)(y) = new Connect4Piece(y, redPlayerTurn)
    // Move piece from source to destination
  }
}

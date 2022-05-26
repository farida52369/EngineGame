package com.example.game_engine.tictactoe

class Board {

  // Initialization :)
  val board: Array[Array[pieces.Piece]] = Array.ofDim[pieces.Piece](3, 3)
  var xTurn: Boolean = true
  def play_turn(): Boolean = xTurn

  {
    for (i <- 0 until 3; j <- 0 until 3) {
      board(i)(j) = null
    }
    // board(1)(3)=new Connect4Pieces(1,3,false)
  }

  def valid_move(dest: (Int, Int)): Boolean = {
    if (in_bound(dest) && (!piece_at_coordination(dest)))
      return true
    false
  }

  def in_bound(dest: (Int, Int)): Boolean = {
    dest._1 >= 0 && dest._1 < 3 && dest._2 >= 0 && dest._2 < 3
  }

  def piece_at_coordination(dest: (Int, Int)): Boolean = {
    in_bound(dest) && board(dest._1)(dest._2) != null
  }

  def next_turn(): Unit = {
    xTurn = !xTurn
  }



  def make_move(x: Int, y: Int): Unit = {
    board(x)(y) = new pieces.TicTacToePieces(x,y, xTurn)
    // Move piece from source to destination
    next_turn()
  }
}

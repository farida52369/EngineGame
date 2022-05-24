package com.example.game_engine.Checkers

import com.example.game_engine.Checkers.pieces._

import scala.math.abs
import scala.util.control.Breaks.{break, breakable}

class Board {

  // Initialization :)
  val board: Array[Array[Piece]] = Array.ofDim[Piece](8, 8)
  var redPlayerTurn: Boolean = true

  def play_turn(): Boolean = redPlayerTurn

  {
    for (i <- 0 until 8; j <- 0 until 8) {
      board(i)(j) = null
    }
    init_game()
  }

  private def init_game(): Unit = {
    // Place black pieces according to correct position on board.
    for (i <- 0 to 2) {
      for ( j <- 0 to (board.length-1)) {
        if(i%2==0 && j%2==1){
          board(i)(j)=new CheckersPiece(i,j,false)
        }
        else if (i%2==1 && j%2==0){
          board(i)(j)=new CheckersPiece(i,j,false)
        }
      }
    }

    // Place red pieces according to correct position on board.
    for (i <- 5 to 7) {
      for ( j <- 0 to (board.length-1)) {
        if(i%2==0 && j%2==1){
          board(i)(j)=new CheckersPiece(i,j,true)
        }
        else if (i%2==1 && j%2==0){
          board(i)(j)=new CheckersPiece(i,j,true)
        }
      }
    }
  }

  def valid_move(dest: (Int, Int), color: Boolean): Boolean = {
    if (in_bound(dest) && (!piece_at_coordination(dest) || enemy_at_coordination(dest, color)))
      return true
    false
  }

  def in_bound(dest: (Int, Int)): Boolean = {
    dest._1 >= 0 && dest._1 < 8 && dest._2 >= 0 && dest._2 < 8
  }

  def piece_at_coordination(dest: (Int, Int)): Boolean = {
    in_bound(dest) && board(dest._1)(dest._2) != null
  }

  def enemy_at_coordination(dest: (Int, Int), color: Boolean): Boolean = {
    if (piece_at_coordination(dest)) {
      return board(dest._1)(dest._2).color != color
    }
    false
  }


  def next_turn(): Unit = {
    redPlayerTurn = !redPlayerTurn
  }


  def make_move(src: (Int, Int), dest: (Int, Int)): Unit = {

    val src_piece: Piece = board(src._1)(src._2)
    val dest_piece: Piece = board(dest._1)(dest._2)
    val change = if (redPlayerTurn) -1 else 1;
    // Move piece from source to destination
    board(dest._1)(dest._2) = new CheckersPiece(dest._1, dest._2, src_piece.color)
    board(dest._1)(dest._2).hasMoved = true
    board(src._1)(src._2) = null

    ////Remove eaten piece if 2 diagonal places are moved
    if (abs(dest._1-src._1)==2&&enemy_at_coordination((src._1+change, src._2 - 1),redPlayerTurn)) {
      board(src._1+change)( src._2- 1)=null
    }else if (abs(dest._1-src._1)==2&&enemy_at_coordination((src._1+change, src._2 + 1),redPlayerTurn)) {
      board(src._1+change)( src._2+ 1)=null
    }
    next_turn()
  }
}

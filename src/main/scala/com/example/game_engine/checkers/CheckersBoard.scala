package com.example.game_engine.checkers

import com.example.game_engine.checkers.pieces._

import scala.math.abs

class CheckersBoard {

  // Initialization :)
  val board: Array[Array[Piece]] = Array.ofDim[Piece](8, 8)
  var redPlayerTurn: Boolean = true

  def setTurn(Turn: Boolean):Unit={
    redPlayerTurn = Turn;
  }


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
      for (j <- board.indices) {
        if (i % 2 == 0 && j % 2 == 1) {
          board(i)(j) = new CheckersPiece(i, j, false)
        }
        else if (i % 2 == 1 && j % 2 == 0) {
          board(i)(j) = new CheckersPiece(i, j, false)
        }
      }
    }

    // l tagrobt l crown
    // board(0)(1) = null;
    // board(1)(2) = new CheckersPiece(1,2,true)
    // Place red pieces according to correct position on board.
    for (i <- 5 to 7) {
      for (j <- board.indices) {
        if (i % 2 == 0 && j % 2 == 1) {
          board(i)(j) = new CheckersPiece(i, j, true)
        } else if (i % 2 == 1 && j % 2 == 0) {
          board(i)(j) = new CheckersPiece(i, j, true)
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

    // Move piece from source to destination
    src_piece.name match {
      case "Checker" => board(dest._1)(dest._2) = new CheckersPiece(dest._1, dest._2, src_piece.color)
      case "CrownChecker" => board(dest._1)(dest._2) = new CrownedPiece(dest._1, dest._2, src_piece.color)
    }

    if (dest._1 == 0 && !src_piece.hasCrowned && redPlayerTurn ||
      dest._1 == 7 && !src_piece.hasCrowned && !redPlayerTurn) {
      src_piece.hasCrowned = true
      board(dest._1)(dest._2) = new CrownedPiece(dest._1, dest._2, redPlayerTurn)
    }

    board(src._1)(src._2) = null
    // Remove eaten piece if 2 diagonal places are moved
    if (src_piece.name == "Checker" && abs(dest._1 - src._1) == 2) {
      board((dest._1 + src._1) / 2)((dest._2 + src._2) / 2) = null
    } else if (src_piece.name == "CrownChecker" && abs(dest._1 - src._1) == 2) {
      board((dest._1 + src._1) / 2)((dest._2 + src._2) / 2) = null
    }
    next_turn()
  }
}

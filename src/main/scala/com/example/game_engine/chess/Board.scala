package com.example.game_engine.chess

import com.example.game_engine.chess.pieces._

import scala.util.control.Breaks.{break, breakable}

class Board {

  // Initialization :)
  val board: Array[Array[Piece]] = Array.ofDim[Piece](8, 8)
  var whitePlayerTurn: Boolean = true
  var blackKingCell: (Int, Int) = (0, 4)
  var whiteKingCell: (Int, Int) = (7, 4)
  var gameOver: (String, Any) = _ // (howEnded, TheWinnerIfExist)

  def play_turn(): Boolean = whitePlayerTurn

  {
    for (i <- 0 until 8; j <- 0 until 8) {
      board(i)(j) = null
    }
    init_game()
  }

  private def init_game(): Unit = {
    // Black
    board(0)(0) = new Rook(0, 0, false);
    board(0)(1) = new Knight(0, 1, false);
    board(0)(2) = new Bishop(0, 2, false);
    board(0)(3) = new Queen(0, 3, false);
    board(0)(4) = new King(0, 4, false);
    board(0)(5) = new Bishop(0, 5, false);
    board(0)(6) = new Knight(0, 6, false);
    board(0)(7) = new Rook(0, 7, false);

    // Pawn Black
    for (i <- 0 until 8) {
      board(1)(i) = new Pawn(1, i, false);
    }

    // White
    board(7)(0) = new Rook(7, 0, true);
    board(7)(1) = new Knight(7, 1, true);
    board(7)(2) = new Bishop(7, 2, true);
    board(7)(3) = new Queen(7, 3, true);
    board(7)(4) = new King(7, 4, true);
    board(7)(5) = new Bishop(7, 5, true);
    board(7)(6) = new Knight(7, 6, true);
    board(7)(7) = new Rook(7, 7, true);

    // Pawn White
    for (i <- 0 until 8) {
      board(6)(i) = new Pawn(6, i, true);
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

  def king_in_check(color: Boolean): Boolean = {

    // Checking the eight directions
    val dim: Array[(Int, Int)] = Array((1, 0), (0, 1), (-1, 0), (0, -1), (1, 1), (-1, 1), (-1, -1), (1, -1))
    val kingCell: (Int, Int) = if (color) whiteKingCell else blackKingCell

    var piece: Piece = null

    // Checking UP _ Down _ LEFT _ RIGHT
    for (j <- 0 until 4) {
      breakable {
        for (i <- 1 to 8) {
          val xNew = kingCell._1 + dim(j)._1 * i
          val yNew = kingCell._2 + dim(j)._2 * i
          if (in_bound((xNew, yNew))) {
            piece = board(xNew)(yNew)
            if (piece_at_coordination(xNew, yNew)) {
              // if what we faced the same color as the king, break
              if (board(kingCell._1)(kingCell._2).color == piece.color)
                break
              if (piece.name == "Queen" || piece.name == "Rook")
                return true
              break
            }
          } else break
        }
      }
    }

    // Checking Diagonals RIGHT DOWN _ RIGHT UP _ LEFT DOWN _ LEFT UP
    for (j <- 4 until 8) {
      breakable {
        for (i <- 1 to 8) {
          val xNew = kingCell._1 + dim(j)._1 * i
          val yNew = kingCell._2 + dim(j)._2 * i
          if (in_bound((xNew, yNew))) {
            piece = board(xNew)(yNew)
            if (piece_at_coordination(xNew, yNew)) {
              // if what we faced the same color as the king, break
              if (board(kingCell._1)(kingCell._2).color == piece.color)
                break
              if (piece.name == "Queen" || piece.name == "Bishop")
                return true
              break
            }
          } else break
        }
      }
    }

    // Checking Knight
    val knightMoves: Array[(Int, Int)] = Array((1, 2), (2, 1), (-2, 1), (-1, 2), (-2, -1), (-1, -2), (1, -2), (-2, -1))
    for (i <- 0 until 8) {
      val xNew = kingCell._1 + knightMoves(i)._1
      val yNew = kingCell._2 + knightMoves(i)._2
      if (piece_at_coordination(xNew, yNew)) {
        piece = board(xNew)(yNew)
        if (board(kingCell._1)(kingCell._2).color != piece.color) {
          if (piece.name == "Knight")
            return true
        }
      }
    }

    // Finally, Checking if it's a Pawn :))
    val colorMode: Int = if (board(kingCell._1)(kingCell._2).color) -1 else 1
    // Case 1 :(
    var xPawn: Int = colorMode + kingCell._1
    var yPawn: Int = colorMode + kingCell._2
    if (enemy_at_coordination((xPawn, yPawn), board(kingCell._1)(kingCell._2).color)) {
      if (piece.name == "Pawn")
        return true
    }
    // Case 2 :(
    xPawn = colorMode + kingCell._1
    yPawn = -1 * colorMode + kingCell._2
    if (enemy_at_coordination((xPawn, yPawn), board(kingCell._1)(kingCell._2).color)) {
      if (piece.name == "Pawn")
        return true
    }

    // No Check?
    false
  }

  def next_turn(): Unit = {
    whitePlayerTurn = !whitePlayerTurn
  }

  /**
   * return true if king is under check after the move from src -> dest
   *
   * @param src   piece moved from (tuple)
   * @param dest  piece moved to (tuple)
   * @param color color of player that moved
   * @return bool value
   */
  def king_in_check_after_move(src: (Int, Int), dest: (Int, Int), color: Boolean): Boolean = {

    var in_check: Boolean = false
    val src_piece: Piece = board(src._1)(src._2)
    val dest_piece: Piece = board(dest._1)(dest._2)

    // Save king coordinates __ as a lot of play will happen
    var kingCell: (Int, Int) = null

    if (src_piece.name == "King") {
      kingCell = if (color) whiteKingCell else blackKingCell
    }

    // Move piece from source to destination
    board(dest._1)(dest._2) = src_piece
    board(dest._1)(dest._2).move(src._1, src._2)
    board(src._1)(src._2) = null

    // Set king co-ordinations
    if (src_piece.name == "King") {
      if (color) whiteKingCell = (dest._1, dest._2)
      else blackKingCell = (dest._1, dest._2)
    }

    // player turn
    next_turn()

    if (king_in_check(color)) in_check = true

    // Restore king ordinates
    if (src_piece.name == "King") {
      if (color) whiteKingCell = kingCell
      else blackKingCell = kingCell
    }

    // restore player turn
    next_turn()

    // Move piece back
    board(src._1)(src._2) = src_piece
    board(dest._1)(dest._2) = dest_piece
    in_check
  }

  def checkMate_staleMate(): Unit = {

    // Check all the board for legal moves
    var legal_moves: Int = 0
    for (i <- 0 until 8) {
      for (j <- 0 until 8) {
        if (piece_at_coordination(i, j) && board(i)(j).color == whitePlayerTurn) {
          val moves = board(i)(j).validMoves(this)
          for (move <- moves) {
            if (!king_in_check_after_move((i, j), move, board(i)(j).color))
              legal_moves += 1
          }
        }
      }
    }

    // StaleMate or CheckMate
    if (legal_moves == 0 && !king_in_check(whitePlayerTurn))
      gameOver = ("StaleMate", -1)
    else if (legal_moves == 0)
      gameOver = ("CheckMate", !whitePlayerTurn)
  }

  def make_move(src: (Int, Int), dest: (Int, Int)): Unit = {

    val src_piece: Piece = board(src._1)(src._2)
    val dest_piece: Piece = board(dest._1)(dest._2)

    // Move piece from source to destination
    src_piece.hasMoved = true
    board(dest._1)(dest._2) = src_piece
    board(dest._1)(dest._2).move(src._1, src._2)
    board(src._1)(src._2) = null

    // Set king co-ordinations
    if (src_piece.name == "King") {
      if (src_piece.color) whiteKingCell = (dest._1, dest._2)
      else blackKingCell = (dest._1, dest._2)
    }

    println("YES")
    next_turn()
  }
}

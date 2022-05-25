package com.example.game_engine.checkers.pieces

import com.example.game_engine.checkers.Board
import scalafx.scene.image.{Image, ImageView}

class CrownedPiece(x: Int, y: Int, color: Boolean) extends Piece(x, y, color) {

  override def getPieceSpirit: ImageView = {
    val location = String.format("src/resources/images_Checkers/%s.png", if (color) "img_14" else "img_13")
    val image: Image = new Image("file:" + location)

    val imageView: ImageView = new ImageView(image)
    imageView.setFitWidth(OPTIMAL_SIZE)
    imageView.setFitHeight(OPTIMAL_SIZE)

    imageView
  }

  override def validMoves(board: Board): List[(Int, Int)] = {
    var moves: List[(Int, Int)] = List()

    println(x)
    println(y)
    //Diagonal_Left
    if (board.valid_move((x + 1, y - 1), color) && !board.piece_at_coordination((x + 1, y - 1))) {
      moves = (x + 1, y - 1) :: moves
    }
    if (board.valid_move((x - 1, y - 1), color) && !board.piece_at_coordination((x - 1, y - 1))) {
      moves = (x - 1, y - 1) :: moves
    }
    //Diagonal_Right
    if (board.valid_move((x + 1, y + 1), color) && !board.piece_at_coordination((x + 1, y + 1))) {
      moves = (x + 1, y + 1) :: moves
    }
    if (board.valid_move((x - 1, y + 1), color) && !board.piece_at_coordination((x - 1, y + 1))) {
      moves = (x - 1, y + 1) :: moves
    }

    //Eating_Diagonal_Left
    if (board.valid_move((x + 2, y - 2), color) && !board.piece_at_coordination((x + 2, y - 2)) && board.enemy_at_coordination((x + 1, y - 1), color)) {
      moves = (x + 2, y - 2) :: moves
      println("tt")
    }

    if (board.valid_move((x + 2, y - 2), color) && !board.piece_at_coordination((x + 2, y - 2)) && board.enemy_at_coordination((x - 1, y - 1), color)) {
      moves = (x + 2, y - 2) :: moves
      println("tt")
    }

    //Eating_Diagonal_Right
    if (board.valid_move((x + 2, y + 2), color) && !board.piece_at_coordination((x + 2, y + 2)) && board.enemy_at_coordination((x + 1, y + 1), color)) {
      moves = (x + 2, y + 2) :: moves
      println("kkk")
    }

    if (board.valid_move((x - 2, y + 2), color) && !board.piece_at_coordination((x - 2, y + 2)) && board.enemy_at_coordination((x - 1, y + 1), color)) {
      moves = (x - 2, y + 2) :: moves
      println("kkk")
    }

    moves
  }

  override def name: String = "CrownChecker"

}

package com.example.game_engine.chess

import com.example.game_engine.Constants
import com.example.game_engine.chess.pieces.Piece
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.scene.text.Font
import scalafx.stage.Stage

import scala.util.control.Breaks.{break, breakable}

class ChessController(board: Board) {

  // Initialization :)
  start_controller()

  def start_controller(): Unit = {
    val stage = new Stage() {
      scene = new Scene(560, 640) {

        val pane: AnchorPane = new AnchorPane()
        val gridPane: GridPane = new ChessDrawer(board.board)
        pane.getChildren.add(gridPane)

        val label = new Label("Input (e1h3):")
        label.setFont(new Font(20))
        label.layoutX = 250
        label.layoutY = 595
        label.setTextFill(Color.Red)
        pane.getChildren.add(label)

        val textField = new TextField()
        textField.setFont(new Font(20))
        textField.prefWidth = 90
        textField.prefHeight = 30
        textField.layoutX = 400
        textField.layoutY = 590
        pane.getChildren.add(textField)

        textField.onAction = (_: ActionEvent) => {
          if (validInputForCurrentPlayer(textField.getText, gridPane)) {
            println("Happy Input")
          }
          textField.setText("")
        }
        content = pane
      }
    }
    stage.show()
  }

  def validInputForCurrentPlayer(input: String, gridPane: GridPane): Boolean = {
    if (input.length != 4) return false

    val a: Int = input.charAt(0).toUpper - 65
    val b: Int = 7 - (Char.char2int(input.charAt(1)) - 49)
    val c: Int = input.charAt(2).toUpper - 65
    val d: Int = 7 - (Char.char2int(input.charAt(3)) - 49)

    // println("Input: " + a + " " + b + " " + c + " " + d)

    if (!board.piece_at_coordination(b, a) || !board.in_bound(d, c) || board.board(b)(a).color != board.play_turn() ||
      (board.piece_at_coordination(d, c) && board.board(b)(a).color == board.board(d)(c).color))
      return false

    move((b, a), (d, c), gridPane)
    true
  }

  def move(src: (Int, Int), dest: (Int, Int), gridPane: GridPane): Unit = {

    val moves: List[(Int, Int)] = board.board(src._1)(src._2).validMoves(board)

    println("Valid Moves: ")
    moves.foreach(a => {
      println(a._1 + " " + a._2)
    })

    breakable {
      for (move <- moves) {
        if (dest == move) {
          val piece: Piece = board.board(src._1)(src._2)
          board.make_move(src, dest)

          var field: StackPane = new StackPane()
          field.setBackground(if (((src._1 + src._2) & 1) == 0) Constants.WHITE else Constants.GREY)
          gridPane.add(field, src._2 + 1, src._1 + 1)

          if (board.board(dest._1)(dest._2) != null) {
            field = new StackPane()
            field.setBackground(if (((dest._1 + dest._2) & 1) == 0) Constants.WHITE else Constants.GREY)
            gridPane.add(field, dest._2 + 1, dest._1 + 1)
          }
          gridPane.add(piece.getPieceSpirit, dest._2 + 1, dest._1 + 1)
          break
        }
      }
    }
  }
}

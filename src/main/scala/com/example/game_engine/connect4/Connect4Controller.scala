package com.example.game_engine.connect4

import com.example.game_engine.connect4.pieces.Connect4Pieces
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.scene.text.Font
import scalafx.stage.Stage

class Connect4Controller(board: Board) {

  // Initialization :)
  start_controller()

  def start_controller(): Unit = {
    val stage = new Stage() {
      scene = new Scene(581, 610) {

        val pane: AnchorPane = new AnchorPane()
        val gridPane: GridPane = new Connect4Drawer(board.board)
        pane.getChildren.add(gridPane)

        val label = new Label("Input (3):")
        label.setFont(new Font(20))
        label.layoutX = 280
        label.layoutY = 555
        label.setTextFill(Color.Red)
        pane.getChildren.add(label)

        val textField = new TextField()
        textField.setFont(new Font(20))
        textField.prefWidth = 90
        textField.prefHeight = 25
        textField.layoutX = 400
        textField.layoutY = 550
        pane.getChildren.add(textField)

        textField.onAction = (_: ActionEvent) => {
          if (validInputForCurrentPlayer(textField.getText, gridPane)) {
            println("Happy Input")
          }
        }
        content = pane
      }
    }
    stage.show()
  }

  def validInputForCurrentPlayer(input: String, gridPane: GridPane): Boolean = {
    if (input.length != 1) return false

    val a: Int = Char.char2int(input.charAt(0)) - 49

    if (!board.valid_move(a)) {
      return false
    }
    // println("Input: " + a + " " + b + " " + c + " " + d)
    move(a, gridPane)
    true
  }

  def move(dest: Int, gridPane: GridPane): Unit = {
    val p = new Connect4Pieces(dest, board.redPlayerTurn)
    val moves: Int = p.validMoves(board)
    if (moves != -1) {
      board.make_move(moves, dest)
      gridPane.add(board.board(moves)(dest).getPieceSpirit, dest + 1, moves + 1)
    }
  }
}
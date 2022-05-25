package com.example.game_engine.connect4

import com.example.game_engine.Constants
import com.example.game_engine.connect4.pieces.{Connect4Pieces, Piece}
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.{Label, TextField}
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.scene.text.Font
import scalafx.stage.Stage

import scala.math.abs
import scala.util.control.Breaks.{break, breakable}

class Connect4Controller(board: Board) {

  // Initialization :)
  start_controller()

  def start_controller(): Unit = {
    val stage = new Stage() {
      scene = new Scene(580, 640) {

        val pane: AnchorPane = new AnchorPane()
        val gridPane: GridPane = new Connect4Drawer(board.board)
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
        }
        content = pane
      }
    }
    stage.show()
  }

  def validInputForCurrentPlayer(input: String, gridPane: GridPane): Boolean = {
    if (input.length != 1) return false

    val a: Int = (Char.char2int(input.charAt(0)) - 49)

    println(a)
    if(!board.valid_move(a)){
      return false
    }
    // println("Input: " + a + " " + b + " " + c + " " + d)
    move(a,gridPane)
    true
  }

  def move(dest: Int, gridPane: GridPane): Unit = {
     var p= new Connect4Pieces(dest,board.redPlayerTurn)
     val moves: Int = p.validMoves(board)
     if(moves == -1){
       return
     }else {
       board.make_move(moves,dest)
       gridPane.add(board.board(moves)(dest).getPieceSpirit, dest + 1, moves+1)
     }
  }
}
package com.example.game_engine

import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Label, TextField}
import scalafx.scene.layout.{AnchorPane, GridPane}
import scalafx.scene.paint.Color
import scalafx.scene.text.Font
import scalafx.stage.Stage

object GameEngine {

  // two functionalities:
  // 1) Drawer
  // 2) Controller

  /**
   *
   * @param draw     higher order function to draw the board
   * @param control  higher order function to (valid input _ make the move if valid input)
   * @param array_2d the board itself which represent the pieces
   * @param board    the board that has the 2D array and the rules
   * @tparam T array_2D
   * @tparam M board
   */

  var turn: Boolean = true


  def start[T, M](name: String,draw: (T) => GridPane, control: (String, GridPane, M,Boolean) => Boolean, array_2d: T, board: M): Unit = {
    val gridPane: GridPane = draw(array_2d)
    view_board(name,control, gridPane, board,turn)
  }

  private def view_board[M](name: String,control: (String, GridPane, M,Boolean) => Boolean, gridPane: GridPane, board: M, turn: Boolean ): Unit = {
    val stage = new Stage() {

      scene = new Scene(580, 640) {

        val pane: AnchorPane = new AnchorPane()
        pane.getChildren.add(gridPane)
        var label=new Label("Input (a1):")
        if(name=="XO"){
          label = new Label("Input :")
        }else if(name.equals("Connect4")){
          label = new Label("Input Col. No.:")
        }else{
          label = new Label("Input (e1h3):")
        }

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
          if (!control(textField.getText, gridPane, board,GameEngine.turn)) {
            new Alert(AlertType.Information){
              title = "Ooops, Warning!"
              headerText = "Input Provided Error"
              contentText = "Please, provide a valid input"
            }.showAndWait()
          }else{
            GameEngine.turn= !GameEngine.turn;
          }
          textField.setText("")
        }

        content = pane
      }

    }

    stage.show()
  }
}

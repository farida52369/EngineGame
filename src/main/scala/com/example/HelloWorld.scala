package com.example

import com.example.game_engine.GameEngine
import javafx.scene.paint.Color
import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ComboBox, Label, TextField}
import scalafx.scene.layout.GridPane
import scalafx.stage.Stage

object HelloWorld extends JFXApp3 {
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Hello"
      scene = new Scene(400, 400) {
        val button = new Button("Click Me")
        button.layoutX = 200
        button.layoutY = 200


        val comboBox = new ComboBox(List("Chess", "Connect4", "Checkers", "Tic Tac Toe"));
        comboBox.layoutX = 100
        comboBox.layoutY = 100

        val label = new Label()
        label.setText("Input Format: e1h2")
        label.setTextFill(Color.RED)
        label.layoutX = 40
        label.layoutY = 50

        val textField = new TextField()
        textField.layoutX = 300
        textField.layoutY = 200
        textField.promptText = "Input"
        textField.prefWidth = 60
        textField.onAction = (e: ActionEvent) => {
          // println(textField.getText)

        }

        content = List(button, comboBox, label, textField)

        button.onAction = (e: ActionEvent) => {
          GameEngine.factory("stage")
          /*
          println("Clicked On: " + comboBox.selectionModel.apply().getSelectedItem)
          val secondStage = new Stage() {
            scene = new Scene() {

              val gridPane = new GridPane()
              gridPane.add(new Label("Children"), 2, 3)
              content = gridPane
            }
          }
          secondStage.show()
          stage.hide()

           */
        }

        comboBox.onAction = (e: ActionEvent) => {
          println(comboBox.selectionModel.apply().getSelectedItem)
        }

      }
    }
  }
}

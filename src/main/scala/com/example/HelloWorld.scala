package com.example

import com.example.game_engine.GameEngine
import com.example.game_engine.checkers.{CheckersBoard, CheckersController, CheckersDrawer}
import com.example.game_engine.chess.{ChessBoard, ChessController, ChessDrawer}
import com.example.game_engine.connect4.{Connect4Board, Connect4Controller, Connect4Drawer}
import com.example.game_engine.tic_tac_toe.{XOBoard, XOController, XODrawer}
import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.text.Font


object HelloWorld extends JFXApp3 {
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Game Engine :)"
      scene = new Scene(600, 440) {

        val label = new Label("Game Engine")
        label.setFont(new Font(40))
        label.layoutX = 160
        label.layoutY = 10

        val image_1: Image = new Image("file:src/resources/images_games/chess3.png")
        val imageView_1: ImageView = new ImageView(image_1)
        imageView_1.setFitWidth(140)
        imageView_1.setFitHeight(130)
        val button_1: Button = new Button()
        button_1.setLayoutX(100)
        button_1.setLayoutY(90)
        button_1.setGraphic(imageView_1)

        val image_2: Image = new Image("file:src/resources/images_games/checkers.jpg")
        val imageView_2: ImageView = new ImageView(image_2)
        imageView_2.setFitWidth(140)
        imageView_2.setFitHeight(130)
        val button_2: Button = new Button()
        button_2.setLayoutX(320)
        button_2.setLayoutY(90)
        button_2.setGraphic(imageView_2)

        val image_3: Image = new Image("file:src/resources/images_games/connect4.png")
        val imageView_3: ImageView = new ImageView(image_3)
        imageView_3.setFitWidth(140)
        imageView_3.setFitHeight(130)
        val button_3: Button = new Button()
        button_3.setLayoutX(100)
        button_3.setLayoutY(250)
        button_3.setGraphic(imageView_3)

        val image_4: Image = new Image("file:src/resources/images_games/tic_tac_toe.png")
        val imageView_4: ImageView = new ImageView(image_4)
        imageView_4.setFitWidth(140)
        imageView_4.setFitHeight(130)
        val button_4: Button = new Button()
        button_4.setLayoutX(320)
        button_4.setLayoutY(250)
        button_4.setGraphic(imageView_4)

        button_1.onAction = (_: ActionEvent) => {
          val board: ChessBoard = new ChessBoard
          GameEngine.start(new ChessDrawer().draw, new ChessController().control, board.board, board)
        }

        button_2.onAction = (_: ActionEvent) => {
          val board: CheckersBoard = new CheckersBoard
          GameEngine.start(new CheckersDrawer().draw, new CheckersController().control, board.board, board)
        }

        button_3.onAction = (_: ActionEvent) => {
          val board: Connect4Board = new Connect4Board
          GameEngine.start(new Connect4Drawer().draw, new Connect4Controller().control, board.board, board)
        }

        button_4.onAction = (_: ActionEvent) => {
          val board: XOBoard = new XOBoard
          GameEngine.start(new XODrawer().draw, new XOController().control, board.board, board)
        }

        content = List(label, button_1, button_2, button_3, button_4)
      }
    }
  }
}

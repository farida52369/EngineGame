package com.example.game_engine.tictactoe

import com.example.game_engine.{Constants, Drawer}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Label
import scalafx.scene.layout.{GridPane, StackPane}

class TicTacToeDrawer(board: Array[Array[pieces.Piece]]) extends GridPane with Drawer {

  // Initialization :)
  boardVisualization()

  private def boardVisualization(): Unit = {

    // Properties for the GridPane
    padding = Insets(25, 25, 25, 25)
    hgap = 0.5
    vgap = 0.5

    // Numbers and Letters in the Board
    for (i <- 0 until 3) {
      add(newColLabel(i), i + 1, 3, 1, 1)
    }

    // For the Background Colors
    for (i <- 1 to 3) {
      for (j <- 1 to 3) {
        val field: StackPane = new StackPane()
        field.setMinWidth(75)
        field.setMinHeight(75)
        field.setBackground(Constants.WHITE)
        add(field, i, j)
      }
    }

    // For the Pieces
    for (i <- 0 until 2) {
      for (j <- 0 until 2) {
        if (board(i)(j) != null)
          add(board(i)(j).getPieceSpirit, j + 1, i + 1)
      }
    }
  }



  private def newColLabel(i: Int): Label = {
    val l: Label = new Label(i+1+"")
    l.setMinSize(75, 75)
    l.setAlignment(Pos.Center)
    l
  }

}

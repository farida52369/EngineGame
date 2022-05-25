package com.example.game_engine.connect4

import com.example.game_engine.connect4.pieces.Piece
import com.example.game_engine.{Constants, Drawer}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Label
import scalafx.scene.layout.{GridPane, StackPane}

class Connect4Drawer(board: Array[Array[Piece]]) extends GridPane with Drawer {

  // Initialization :)
  boardVisualization()

  private def boardVisualization(): Unit = {

    // Properties for the GridPane
    padding = Insets(10, 10, 10, 10)
    hgap = 0.5
    vgap = 0.5

    // Numbers and Letters in the Board
    for (i <- 0 until 7) {
      add(newRowLabel(i), 0, i + 1, 1, 1)
      add(newRowLabel(i), 8, i + 1, 1, 1)
      add(newColLabel(i), i + 1, 7, 1, 1)
    }

    // For the Background Colors
    for (i <- 1 to 7) {
      for (j <- 1 to 6) {
        val field: StackPane = new StackPane()
        field.setBackground(Constants.BLUE)
        add(field, i, j)
      }
    }

    // For the Pieces
    for (i <- 0 until 6) {
      for (j <- 0 until 7) {
        if (board(i)(j) != null)
          add(board(i)(j).getPieceSpirit, j + 1, i + 1)
      }
    }
  }

  // For the Numbers and Letters in The Checker Board
  private def newRowLabel(i: Int): Label = {
    val l: Label = new Label("")
    l.setMinSize(20, 64)
    l.setAlignment(Pos.Center)
    l
  }

  private def newColLabel(i: Int): Label = {
    val l: Label = new Label(i+1+"")
    l.setMinSize(70, 75)
    l.setAlignment(Pos.Center)
    l
  }

}

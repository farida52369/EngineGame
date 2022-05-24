package com.example.game_engine.chess

import com.example.game_engine.{Constants, Drawer}
import com.example.game_engine.chess.pieces.Piece
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Label
import scalafx.scene.layout.{GridPane, StackPane}

class ChessDrawer(board: Array[Array[Piece]]) extends GridPane with Drawer {

  // Initialization :)
  boardVisualization()

  private def boardVisualization(): Unit = {

    // Properties for the GridPane
    padding = Insets(10, 10, 10, 10)
    hgap = 0.5
    vgap = 0.5

    // Numbers and Letters in the Board
    for (i <- 0 until 8) {
      add(newRowLabel(i), 0, i + 1, 1, 1)
      add(newRowLabel(i), 9, i + 1, 1, 1)
      add(newColLabel(i), i + 1, 0, 1, 1)
      add(newColLabel(i), i + 1, 9, 1, 1)
    }

    // For the Background Colors
    for (i <- 1 to 8) {
      for (j <- 1 to 8) {
        val field: StackPane = new StackPane()
        field.setBackground(if (((i + j) & 1) == 0) Constants.WHITE else Constants.GREY)
        add(field, i, j)
      }
    }

    // For the Pieces
    for (i <- 0 until 8) {
      for (j <- 0 until 8) {
        if (board(i)(j) != null)
          add(board(i)(j).getPieceSpirit, j + 1, i + 1)
      }
    }

    // println("Drawer: " + board(0)(0).getPieceSpirit)
  }

  // For the Numbers and Letters in The Chess Board
  private def newRowLabel(i: Int): Label = {
    val l: Label = new Label(8 - i + "")
    l.setMinSize(20, 64)
    l.setAlignment(Pos.Center)
    l
  }

  private def newColLabel(i: Int): Label = {
    val l = new Label((i + 65).toChar + "")
    l.setMinSize(64, 20)
    l.setAlignment(Pos.Center)
    l
  }
}

package com.example.game_engine.checkers

import com.example.game_engine.checkers.pieces.Piece
import com.example.game_engine.{Constants, Drawer}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Label
import scalafx.scene.layout.{GridPane, StackPane}

class CheckersDrawer extends Drawer[Array[Array[Piece]]] {

  override def draw(board: Array[Array[Piece]]): GridPane = {
    val gridPane: GridPane = new GridPane()

    // Properties for the GridPane
    gridPane.padding = Insets(10, 10, 10, 10)
    gridPane.hgap = 0.5
    gridPane.vgap = 0.5

    // Numbers and Letters in the Board
    for (i <- 0 until 8) {
      gridPane.add(newRowLabel(i), 0, i + 1, 1, 1)
      gridPane.add(newRowLabel(i), 9, i + 1, 1, 1)
      gridPane.add(newColLabel(i), i + 1, 0, 1, 1)
      gridPane.add(newColLabel(i), i + 1, 9, 1, 1)
    }

    // For the Background Colors
    for (i <- 1 to 8) {
      for (j <- 1 to 8) {
        val field: StackPane = new StackPane()
        field.setBackground(if (((i + j) & 1) == 0) Constants.WHITE else Constants.GREY)
        gridPane.add(field, i, j)
      }
    }

    // For the Pieces
    for (i <- 0 until 8) {
      for (j <- 0 until 8) {
        if (board(i)(j) != null)
          gridPane.add(board(i)(j).getPieceSpirit, j + 1, i + 1)
      }
    }

    gridPane
  }

  // For the Numbers and Letters in The Checker Board
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

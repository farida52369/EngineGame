package com.example.game_engine.connect4

import com.example.game_engine.{Constants, Drawer}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Label
import scalafx.scene.layout.{GridPane, StackPane}

class Connect4Drawer extends Drawer[Array[Array[Connect4Piece]]] {

  override def draw(board: Array[Array[Connect4Piece]]): GridPane = {
    val gridPane: GridPane = new GridPane()

    // Properties for the GridPane
    gridPane.padding = Insets(25, 25, 25, 25)
    gridPane.hgap = 0.5
    gridPane.vgap = 0.5

    // Numbers and Letters in the Board
    for (i <- 0 until 7) {
      gridPane.add(newColLabel(i), i + 1, 7, 1, 1)
    }

    // For the Background Colors
    for (i <- 1 to 7) {
      for (j <- 1 to 6) {
        val field: StackPane = new StackPane()
        field.setMinWidth(75)
        field.setMinHeight(75)
        field.setBackground(Constants.BLUE)
        gridPane.add(field, i, j)
      }
    }

    // For the Pieces
    for (i <- 0 until 6) {
      for (j <- 0 until 7) {
        if (board(i)(j) != null)
          gridPane.add(board(i)(j).getPieceSpirit, j + 1, i + 1)
      }
    }

    gridPane
  }

  private def newColLabel(i: Int): Label = {
    val l: Label = new Label(i+1+"")
    l.setMinSize(75, 75)
    l.setAlignment(Pos.Center)
    l
  }

}

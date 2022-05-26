package com.example.game_engine.tic_tac_toe

import com.example.game_engine.{Constants, Drawer}
import scalafx.geometry.Insets
import scalafx.scene.layout.{GridPane, StackPane}

class XODrawer extends Drawer[Array[Array[XOPiece]]] {

  override def draw(board: Array[Array[XOPiece]]): GridPane = {
    val gridPane: GridPane = new GridPane()

    // Properties for the GridPane
    gridPane.padding = Insets(25, 25, 25, 25)
    gridPane.hgap = 0.5
    gridPane.vgap = 0.5

    // For the Background Colors
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        val field: StackPane = new StackPane()
        field.setMinWidth(85)
        field.setMinHeight(85)
        field.setBackground(Constants.BLUE)
        gridPane.add(field, i, j)
      }
    }

    // For the Pieces
    for (i <- 0 until 2) {
      for (j <- 0 until 2) {
        if (board(i)(j) != null)
          gridPane.add(board(i)(j).getPieceSpirit, j, i)
      }
    }
    gridPane
  }

}

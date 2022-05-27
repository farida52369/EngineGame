package com.example.game_engine.tic_tac_toe

import com.example.game_engine.{Constants, Drawer}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Label
import scalafx.scene.layout.{GridPane, StackPane}

class XODrawer extends Drawer[Array[Array[XOPiece]]] {

  override def draw(board: Array[Array[XOPiece]]): GridPane = {
    val gridPane: GridPane = new GridPane()

    // Properties for the GridPane
    gridPane.padding = Insets(100, 100, 100, 100)
    gridPane.hgap = 1
    gridPane.vgap = 1

    for (i <- 0 until 3) {
      //gridPane.add(newRowLabel(i), 0, i + 1, 1, 1)
      gridPane.add(newRowLabel(i), 4, i , 1, 1)
     // gridPane.add(newColLabel(i), i + 1, 0, 1, 1)
      gridPane.add(newColLabel(i), i , 4, 1, 1)
    }
    // For the Background Colors
    for (i <- 0 to 2) {
      for (j <- 0 to 2) {
        val field: StackPane = new StackPane()
        field.setMinWidth(115)
        field.setMinHeight(115)
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
  // For the Numbers and Letters in The Chess Board
  private def newRowLabel(i: Int): Label = {
    val l: Label = new Label(3 - i + "")
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

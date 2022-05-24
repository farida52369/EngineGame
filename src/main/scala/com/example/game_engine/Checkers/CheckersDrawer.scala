package com.example.game_engine.Checkers
import com.example.game_engine.Drawer
import com.example.game_engine.Checkers.pieces.Piece
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Label
import scalafx.scene.layout.{Background, BackgroundFill, GridPane, StackPane}
import scalafx.scene.paint.Color
class CheckersDrawer(board: Array[Array[Piece]]) extends GridPane with Drawer{


    val GREY = new Background(Array(new BackgroundFill(Color.Gray, null, null)))
    val WHITE = new Background(Array(new BackgroundFill(Color.White, null, null)))

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
          field.setBackground(if (((i + j) & 1) == 0) WHITE else GREY)
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

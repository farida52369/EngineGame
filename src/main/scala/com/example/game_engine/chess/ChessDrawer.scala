package com.example.game_engine.chess

import com.example.game_engine.Drawer
import scalafx.scene.control.Label
import scalafx.scene.layout.GridPane

class ChessDrawer extends Drawer {

  override def draw(): GridPane = {
    // boardVisualization()
    val gridPane = new GridPane()
    gridPane.add(new Label("Children"), 1, 3)
    gridPane
  }

  def boardVisualization(): Unit = {




  }
}

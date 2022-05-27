package com.example.game_engine

import scalafx.scene.layout.GridPane

trait Controller[U] {

  def control(input: String, gridPane: GridPane, board: U,turn: Boolean): Boolean

}

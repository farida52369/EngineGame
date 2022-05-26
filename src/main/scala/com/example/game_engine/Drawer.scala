package com.example.game_engine

import scalafx.scene.layout.GridPane

trait Drawer[T] {
  def draw(board: T): GridPane
}

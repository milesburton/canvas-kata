package model

import command.{DrawLineCommand, DrawRectangleCommand}

class Canvas(width: Int, height: Int, backgroundChar: Char = '-') {

  private def bufferSizeAs1DimensionalArray = height * width

  private def fillBufferWithEmptyChars = Array.tabulate[Char](bufferSizeAs1DimensionalArray)((i) => backgroundChar)

  private var buffer: Array[Char] = fillBufferWithEmptyChars

  def drawLine(command: DrawLineCommand): Unit = {

    buffer = buffer
      .zipWithIndex
      .map { case (backgroundChar, idx) =>

        if (command.isHorizontal) {

          drawLineHorizontal(command, backgroundChar, idx)

        } else if (command.isVertical) {

          drawLineVertical(command, backgroundChar, idx)

        } else {
          throw new NotImplementedError()
        }
      }

  }

  private def drawLineVertical(command: DrawLineCommand, char: Char, idx: Int): Char = {

    val y: Int = Math.floor(idx / width).toInt
    val x: Int = idx - (y * width)

    val yTop = Math.min(command.y1, command.y2) - 1
    val yBottom = Math.max(command.y1, command.y2) - 1

    if (y >= yTop && y <= yBottom && command.x1 - 1 == x) {
      'x'
    } else {
      char
    }

  }

  private def drawLineHorizontal(command: DrawLineCommand, char: Char, idx: Int) = {

    val startIndex = ((command.y1 - 1) * width) + command.x1 - 1
    val endIndex = ((command.y2 - 1) * width) + command.x2 - 1

    if (idx >= startIndex && idx <= endIndex) {
      'x'
    } else {
      char
    }

  }

  def drawRectangle(command: DrawRectangleCommand) = {

    drawLine(DrawLineCommand(command.x1, command.y1, command.x2, command.y1))
    drawLine(DrawLineCommand(command.x1, command.y1, command.x1, command.y2))
    drawLine(DrawLineCommand(command.x1, command.y2, command.x2, command.y2))
    drawLine(DrawLineCommand(command.x2, command.y1, command.x2, command.y2))

  }

  def getBuffer: Array[Char] = buffer

  def getWidth = width

  def getHeight = height
}
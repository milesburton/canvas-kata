package model

import command.DrawLineCommand

class Canvas(width: Int, height: Int) {

  private var buffer: Array[Char] = fillBufferWithEmptyChars

  private def fillBufferWithEmptyChars = Array.tabulate[Char](height * width)((i) => '-')

  def drawLine(command: DrawLineCommand): Unit = {

    buffer = buffer
      .zipWithIndex
      .map { case (char, idx) =>

        if (command.y1 == command.y2) {

          drawLineHorizontal(command, char, idx)

        } else if (command.y1 != command.y2 && command.x1 == command.y2) {

          drawLineVertical(command, char, idx)

        } else {
          throw new NotImplementedError()
        }


      }

  }

  private def drawLineVertical(command: DrawLineCommand, char: Char, idx: Int) = {
    'x'
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


  def getBuffer: Array[Char] = buffer

  def getWidth = width

  def getHeight = height
}
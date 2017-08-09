package model

import command.{BucketFillCommand, DrawLineCommand, DrawRectangleCommand}

class Canvas(width: Int, height: Int, backgroundChar: Char = '-') {

  private def bufferSizeAs1DimensionalArray = height * width

  private def fillBufferWithEmptyChars = Array.tabulate[Char](bufferSizeAs1DimensionalArray)((i) => backgroundChar)

  private var buffer: Array[Char] = fillBufferWithEmptyChars

  def drawLine(command: DrawLineCommand): Unit = {

    buffer = buffer
      .zipWithIndex
      .map { case (idxChar, idx) =>

        if (command.isHorizontal) {

          drawLineHorizontal(command, idxChar, idx)

        } else if (command.isVertical) {

          drawLineVertical(command, idxChar, idx)

        } else {
          throw new NotImplementedError()
        }
      }

  }

  private def drawLineVertical(command: DrawLineCommand, char: Char, idx: Int): Char = {

    val (x1, y1) = convertXYToBufferDimension(command.x1, command.y1)
    val (x2, y2) = convertXYToBufferDimension(command.x2, command.y2)

    val (x, y) = convertBufferIdxToXYTuple(idx)

    val yTop = Math.min(y1, y2)
    val yBottom = Math.max(y1, y2)

    if (y >= yTop && y <= yBottom && x1 == x) {
      'x'
    } else {
      char
    }

  }

  private def drawLineHorizontal(command: DrawLineCommand, char: Char, idx: Int) = {

    val (x1, y1) = convertXYToBufferDimension(command.x1, command.y1)
    val (x2, y2) = convertXYToBufferDimension(command.x2, command.y2)

    val startIndex = convertXYToBufferIdx(x1, y1)
    val endIndex = convertXYToBufferIdx(x2, y2)

    if (idx >= startIndex && idx <= endIndex) {
      'x'
    } else {
      char
    }

  }

  def drawRectangle(command: DrawRectangleCommand) = {

    val drawLineFromTopLeftToTopRight = DrawLineCommand(command.x1, command.y1, command.x2, command.y1)
    val drawLineFromTopLeftToBottomLeft = DrawLineCommand(command.x1, command.y1, command.x1, command.y2)
    val drawLineFromBottomLeftToBottomRight = DrawLineCommand(command.x1, command.y2, command.x2, command.y2)
    val drawLineFromBottomRightToTopRight = DrawLineCommand(command.x2, command.y1, command.x2, command.y2)

    drawLine(drawLineFromTopLeftToTopRight)
    drawLine(drawLineFromTopLeftToBottomLeft)
    drawLine(drawLineFromBottomLeftToBottomRight)
    drawLine(drawLineFromBottomRightToTopRight)

  }

  // TODO: WARNING Mutable command!
  def bucketFill(command: BucketFillCommand): Unit = {

    // Shamelessly borrowed from: https://en.wikipedia.org/wiki/Flood_fill
    def floodFill(idx: Int, replacementColour: Char): Unit = {

      val (x, y) = convertBufferIdxToXYTuple(idx)

      if (x < 0 || y < 0 || x > (width - 1) || y > (height - 1)) {
        return
      }

      if (buffer(idx) == replacementColour) {
        return
      }

      buffer(idx) = replacementColour


      val southOfNodeIdx = convertXYToBufferIdx(x, y + 1)
      val northOfNodeIdx = convertXYToBufferIdx(x, y - 1)
      val eastOfNodeIdx = convertXYToBufferIdx(x - 1, y)
      val westOfNodeIdx = convertXYToBufferIdx(x + 1, y)

      floodFill(southOfNodeIdx, command.color)
      floodFill(northOfNodeIdx, command.color)
      floodFill(eastOfNodeIdx, command.color)
      floodFill(westOfNodeIdx, command.color)
    }

    val idx = convertXYToBufferIdx(command.x, command.y)
    floodFill(idx, command.color)
  }

  def convertXYToBufferIdx(x: Int, y: Int): Int = {
    ((y) * width) + x
  }

  def convertBufferIdxToXYTuple(idx: Int): (Int, Int) = {

    // In hindsight, using a single dimension array wasn't the best option. The reason for a single dimension array is a result of developing simple game engines on 8 bit processors which have very little memory.  It allows you to take advantage of bit packing etc. The reason for sticking with this approach is it should be less expensive than mutable arrays.
    val y: Int = Math.floor(idx / width).toInt
    val x: Int = idx - (y * width)

    (x, y)
  }

  def convertXYToBufferDimension(x: Int, y: Int): (Int, Int) = {
    (x - 1, y - 1)
  }

  def getBuffer: Array[Char] = buffer

  def getWidth = width

  def getHeight = height
}
package helper

import model.Canvas

object TestRendererUtil {

  def render(canvas: Canvas): String = {

    def isLessThanArrayLength(idx: Int): Boolean = {
      idx < canvas.getBuffer.length - 1
    }

    def isEndOfLine(idx: Int): Boolean = {
      (idx + 1) % canvas.getWidth == 0
    }

    canvas
      .getBuffer
      .zipWithIndex
      .map {
        case (char, idx) =>
          if (isEndOfLine(idx) && isLessThanArrayLength(idx)) {
            char.toString + '\n'
          } else {
            char.toString
          }
      }
      .mkString

  }


}

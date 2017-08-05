import scala.util.matching.Regex

class CommandInterpreter() {

  val isCreateCanvasStringRegEx: Regex = "^C\\s(\\d+)\\s(\\d+)$".r

  val isDrawLineStringRegEx: Regex = "^L\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)$".r

  val isFillBucketStringRegEx: Regex = "^B\\s(\\d+)\\s(\\d+)\\s([a-z])$".r

  // implicit def convertStringToInt(d: String): Int = d.toInt

  def interpret(userInput: String) = {

    userInput match {

      case isCreateCanvasStringRegEx(width, height) =>
        CreateCanvasCommand(width.toInt, height.toInt)

      case isDrawLineStringRegEx(x1, y1, x2, y2) =>
        DrawLineCommand(x1.toInt, y1.toInt, x2.toInt, y2.toInt)

      case isFillBucketStringRegEx(x, y, colour) =>
        BucketFillCommand(x.toInt, y.toInt, colour)

      case _ =>
        None

    }

  }


}

case class CreateCanvasCommand(width: Int, height: Int)

case class DrawLineCommand(x1: Int, y1: Int, x2: Int, y2: Int)

case class BucketFillCommand(x: Int, y: Int, color: String)
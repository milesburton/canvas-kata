class CommandInterpreter() {

  val isCreateCanvasStringRegEx = "^C\\s(\\d+)\\s(\\d+)$".r

  val isDrawLineStringRegEx = "^L\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)$".r

  implicit def convertStringToInt(string: String): Int = string.toInt

  def interpret(userInput: String) = {

    userInput match {

      case isCreateCanvasStringRegEx(width, height) =>
        CreateCanvasCommand(width, height)

      case isDrawLineStringRegEx(x1, y1, x2, y2) =>
        DrawLineCommand(x1, y1, x2, y2)

      case _ =>
        None

    }

  }


}

case class CreateCanvasCommand(width: Int, height: Int)

case class DrawLineCommand(x1: Int, y1: Int, x2: Int, y2: Int)
class CommandInterpreter() {

  val isCreateCanvasStringRegEx = "^C\\s(\\d+)\\s(\\d+)$".r

  val isDrawLineStringRegEx = "^L\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)$".r

  def interpret(userInput: String) = {

    userInput match {

      case isCreateCanvasStringRegEx(width, height) =>
        CreateCanvasCommand(width.toInt, height.toInt)

      case isDrawLineStringRegEx(x1, y1, x2, y2) =>
        DrawLineCommand(x1.toInt, y1.toInt, x2.toInt, y2.toInt)

      case _ =>
        None

    }

  }


}

case class CreateCanvasCommand(width: Int, height: Int)

case class DrawLineCommand(x1: Int, y1: Int, x2: Int, y2: Int)
class CommandInterpreter() {


  val isCreateCanvasStringRegEx = "^C\\s(\\d+)\\s(\\d+)$".r

  def interpret(userInput: String) = {

    userInput match {

      case isCreateCanvasStringRegEx(width, height) =>
        CreateCanvasCommand(width.toInt, height.toInt)
      case _ =>
        None

    }

  }


}

case class CreateCanvasCommand(width: Int, height: Int)
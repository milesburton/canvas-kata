import command.{BucketFillCommand, CreateCanvasCommand, DrawLineCommand, ExitApplicationCommand}

import scala.util.matching.Regex

class CommandInterpreter() {

  // Todo can be enhanced using strategies, or maybe a chain of responsibility? A later refactor
  // Also worth considering the need for command validation. If a line command is only horizontal + vertical, we should reject invalid rules

  val isCreateCanvasStringRegEx: Regex = "^C\\s(\\d+)\\s(\\d+)$".r

  val isDrawLineStringRegEx: Regex = "^L\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)$".r

  val isFillBucketStringRegEx: Regex = "^B\\s(\\d+)\\s(\\d+)\\s([a-z])$".r

  val isExitApplicationStringRegEx: Regex = "^Q$".r


  def interpret(userInput: String) = {

    userInput match {

      case isCreateCanvasStringRegEx(width, height) =>
        CreateCanvasCommand(width.toInt, height.toInt)

      case isDrawLineStringRegEx(x1, y1, x2, y2) =>
        DrawLineCommand(x1.toInt, y1.toInt, x2.toInt, y2.toInt)

      case isFillBucketStringRegEx(x, y, colour) =>
        BucketFillCommand(x.toInt, y.toInt, colour)

      case isExitApplicationStringRegEx() =>
        ExitApplicationCommand()

      case _ =>
        None

    }

  }

}


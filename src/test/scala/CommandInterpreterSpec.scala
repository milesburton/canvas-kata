import command._
import org.scalatest.{FlatSpec, Matchers}

class CommandInterpreterSpec extends FlatSpec with Matchers {

  it should "return nothing if command is not supported" in {

    val userInput = "Z 0 0"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(None)
  }

  it should "return create canvas command" in {

    val userInput = "C 20 4"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(CreateCanvasCommand(20, 4))

  }

  it should "gracefully handle invalid create canvas input" in {

    val userInput = "C 20.5 4.6"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(None)

  }

  it should "return draw line command" in {

    val userInput = "L 1 2 6 2"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(DrawLineCommand(1,2,6,2))

  }

  it should "gracefully handle invalid draw line input" in {

    val userInput = "L 1 A 2 6.5 2"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(None)

  }

  it should "return bucket fill command" in {

    val userInput = "B 10 3 o"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(BucketFillCommand(10,3,'o'))

  }


  it should "handle an invalid bucket fill command" in {

    val userInput = "B 10 3 ooo"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(None)

  }

  it should "gracefully handle invalid bucket fill command" in {

    val userInput = "B 1 2 X"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(None)

  }

  it should "return rectangle command" in {

    val userInput = "R 14 1 18 3"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(DrawRectangleCommand(14,1,18,3))

  }

  it should "return exit application command" in {

    val userInput = "Q"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(ExitApplicationCommand())

  }



}

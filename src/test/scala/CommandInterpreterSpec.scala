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

  it should "not crash if create canvas input it invalid" in {

    val userInput = "C 20.5 4.6"

    val command = new CommandInterpreter().interpret(userInput)

    command should be(None)

  }

}

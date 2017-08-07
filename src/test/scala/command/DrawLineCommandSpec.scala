package command

import org.scalatest.{FlatSpec, Matchers, WordSpec}

class DrawLineCommandSpec extends FlatSpec with Matchers {

  it should "be horizontal if y1 and y2 are the same" in {

    val command = DrawLineCommand(0,1,0,1)

    command.isHorizontal should be(true)
  }

  it should "be not horizontal if y1 and y2 are the same" in {

    val command = DrawLineCommand(0,1,0,2)

    command.isHorizontal should be(false)
  }

  it should "be horizontal if y1 and y2 are the same. Changing x should have no impact" in {

    val command = DrawLineCommand(0,1,0,1)

    command.isHorizontal should be(true)
  }

  it should "be vertical if y1 and y2 are the different" in {

    val command = DrawLineCommand(0,1,0,12)

    command.isVertical should be(true)
  }

  it should "be not vertical if y1 and y2 are the same" in {

    val command = DrawLineCommand(0,1,0,1)

    command.isVertical should be(false)
  }

}

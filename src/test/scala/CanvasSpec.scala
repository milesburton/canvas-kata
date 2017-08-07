import command.DrawLineCommand
import helper.TestRendererUtil
import model.Canvas
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class CanvasSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

  it should "setup an initial grid" in {

    val canvas = new Canvas(10,10)

    val renderedCanvas = TestRendererUtil.render(canvas)

    val expectedCanvas = cleanCanvasExpectation(
      """----------
        |----------
        |----------
        |----------
        |----------
        |----------
        |----------
        |----------
        |----------
        |----------""")

    renderedCanvas should be(expectedCanvas)
  }

  it should "draw a horizontal line" in {

    val canvas = new Canvas(10,10)

    canvas.drawLine(DrawLineCommand(1,2,6,2))

    val renderedCanvas = TestRendererUtil.render(canvas)

    val expectedCanvas = cleanCanvasExpectation(
      """----------
        |xxxxxx----
        |----------
        |----------
        |----------
        |----------
        |----------
        |----------
        |----------
        |----------""")

    renderedCanvas should be(expectedCanvas)
  }

  it should "draw a vertical line" in {

    val canvas = new Canvas(10,10)

    canvas.drawLine(DrawLineCommand(6,3,6,4))

    val renderedCanvas = TestRendererUtil.render(canvas)

    val expectedCanvas = cleanCanvasExpectation(
      """----------
        |----------
        |-----x----
        |-----x----
        |----------
        |----------
        |----------
        |----------
        |----------
        |----------""")

    renderedCanvas should be(expectedCanvas)
  }
  def cleanCanvasExpectation(expectation: String) = expectation.stripMargin.replace("\r\n", "\n")
}

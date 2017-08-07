package command

case class DrawLineCommand(x1: Int, y1: Int, x2: Int, y2: Int) extends Command {

  def isHorizontal = y1 == y2

  def isVertical = y1 != y2 && x1 == x2
}

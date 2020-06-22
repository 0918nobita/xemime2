package vision.kodai.xemime

class Point(private val x: Int, private val y: Int) {
  override def toString = s"Point($x, $y)"

  override def equals(obj: Any): Boolean = {
    if (!obj.isInstanceOf[Point]) return false
    val point = obj.asInstanceOf[Point]
    this.x == point.x && this.y == point.y
  }
}

// companion object
object Point {
  def shiftInXDirection(point: Point)(distance: Int) =
    new Point(point.x + distance, point.y)

  def apply(x: Int, y: Int) = new Point(x, y)

  def unapply(point: Point): Option[(Int, Int)] = Some((point.x, point.y))
}

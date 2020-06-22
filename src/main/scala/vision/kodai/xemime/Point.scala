package vision.kodai.xemime

class Point(val x: Int, val y: Int) {
  override def toString = s"Point($x, $y)"
}

// companion object
object Point {
  def shiftInXDirection(point: Point)(distance: Int) =
    new Point(point.x + distance, point.y)
}

package vision.kodai.xemime

/** ソースコード上の 1 文字の位置を表す */
final case class Point(val line: Int, val column: Int) {
  override def toString: String = s"$line:$column"
}

/** ソースファイルと範囲を表す */
sealed trait Position {
  val filePath: String
  val startingPoint: Point
  val endPoint: Point
}

object Position {
  def unapply(position: Position): Option[(String, Point, Point)] =
    Some((position.filePath, position.startingPoint, position.endPoint))
}

/** 1 文字だけを指す Position */
final class CharPosition(override val filePath: String, point: Point)
    extends Position {
  override val startingPoint: Point = point
  override val endPoint: Point      = point
  override def toString: String     = s"$point of $filePath"
}

object CharPosition {
  def apply(filePath: String, line: Int, column: Int): CharPosition =
    new CharPosition(filePath, Point(line, column))

  def unapply(charPosition: CharPosition): Option[(String, Int, Int)] =
    Some(
      (
        charPosition.filePath,
        charPosition.startingPoint.line,
        charPosition.startingPoint.column
      )
    )
}

/** 連続した複数の文字を指す Position */
final case class StringPosition(
    override val filePath: String,
    override val startingPoint: Point,
    override val endPoint: Point
) extends Position {
  override def toString: String = s"$startingPoint~$endPoint of $filePath"
}

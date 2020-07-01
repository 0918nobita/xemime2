package vision.kodai.xemime.ast

import vision.kodai.xemime.Position

case class DoubleConst(override val pos: Position, val value: Double)
    extends Ast

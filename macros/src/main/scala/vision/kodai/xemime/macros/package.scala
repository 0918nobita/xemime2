package vision.kodai.xemime

package object macros {
  import scala.language.experimental.macros
  import scala.reflect.macros.blackbox

  def desugar(expr: Any): String = macro desugarImpl

  def desugarImpl(c: blackbox.Context)(expr: c.Expr[Any]): c.Expr[Nothing] = {
    import c.universe._
    val s = show(expr.tree)
    c.Expr(Literal(Constant(s)))
  }

  def osDependent(osNamePrefix: String, procedure: Unit): Unit =
    macro osDependentImpl

  def osDependentImpl(
      c: blackbox.Context
  )(osNamePrefix: c.Expr[String], procedure: c.Expr[Unit]): c.Expr[Unit] = {
    import c.universe._
    val osName = System.getProperty("os.name")
    osNamePrefix match {
      case c.Expr(Literal(Constant(s: String))) if osName.startsWith(s) =>
        procedure
      case _ => c.Expr(Literal(Constant(())))
    }
  }
}

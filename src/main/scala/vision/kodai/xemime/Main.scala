package vision.kodai.xemime

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.concurrent.Task
import scalafx.scene.{Group, Scene}
import scalafx.scene.text.Text
import vision.kodai.xemime.ast.{AddOp, Ast, BinExpr, IntConst}

object Main extends JFXApp {
  val pos: Position = CharPosition("internal", 0, 0)

  val expr: Ast = BinExpr(
    pos,
    lhs = IntConst(pos, 3),
    op = AddOp(pos),
    rhs = IntConst(pos, 4)
  )

  println(Runtime.run(expr))

  val task: Task[Unit] = Task {
    println("Task started")
    Thread.sleep(5000)
    println("Task completed")
  }

  val thread: Thread = new Thread(task)
  thread.setDaemon(true)
  thread.start()

  stage = new PrimaryStage {
    title = "Xemime"
    resizable = false
    scene = new Scene(320.0, 160.0) {
      root = new Group {
        layoutX = 30.0
        layoutY = 80.0
        children = new Text("Hello") {
          onMouseClicked = _ => println("Hello")
        }
      }
    }
  }
}

package vision.kodai.xemime

import java.net.URL
import javafx.{fxml => jfxml}
import javafx.{scene => jfxs}

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.concurrent.Task
import scalafx.scene.Scene
import scalafx.scene.image.Image
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

  val fxmlUrl: URL = getClass.getResource("/main.fxml")
  if (fxmlUrl == null) {
    println("Failed to load resource: main.fxml")
    sys.exit(1)
  }

  val root: jfxs.Parent = jfxml.FXMLLoader.load(fxmlUrl)

  stage = new PrimaryStage {
    title = "Xemime"
    scene = new Scene(root, 320, 160)
  }

  stage.getIcons.add(new Image("/example.png"))
}

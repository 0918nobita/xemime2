package vision.kodai.xemime

import java.net.URL
import javafx.{fxml => jfxml}
import javafx.{scene => jfxs}
import javafx.application.Application
import javafx.stage.Stage

import scalafx.Includes._
import scalafx.concurrent.Task
import scalafx.scene.Scene
import scalafx.scene.image.Image
import vision.kodai.xemime.ast.{AddOp, Ast, BinExpr, IntConst}

class App extends Application {
  override def start(stage: Stage): Unit = {
    val task: Task[Unit] = Task {
      val pos: Position = CharPosition("internal", 0, 0)

      val expr: Ast = BinExpr(
        pos,
        lhs = IntConst(pos, 3),
        op = AddOp(pos),
        rhs = IntConst(pos, 4)
      )

      println(Runtime.run(expr))
    }

    val thread: Thread = new Thread(task)
    thread.setDaemon(true)
    thread.start()

    stage.title = "Xemime"

    val fxmlUrl: URL = getClass.getResource("/main.fxml")
    if (fxmlUrl == null) {
      println("Failed to load resource: main.fxml")
      sys.exit(1)
    }

    val root: jfxs.Parent = jfxml.FXMLLoader.load(fxmlUrl)
    stage.scene = new Scene(root, 320, 160)

    stage.getIcons.add(new Image("/example.png"))

    stage.show()
  }
}

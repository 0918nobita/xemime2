package vision.kodai.xemime

import com.sun.javafx.application.LauncherImpl
import java.net.URL
import javafx.application.{Application, Preloader}
import javafx.stage.Stage
import javafx.{fxml => jfxml}
import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafx.concurrent.Task
import scalafx.scene.Scene
import scalafx.scene.image.Image
import vision.kodai.xemime.ast.{AddOp, Ast, BinExpr, IntConst}

class AppPreloader extends Preloader {
  override def start(stage: Stage): Unit = {
    com.sun.glass.ui.Application.GetApplication().setName("Xemime")
  }
}

class App extends Application {
  override def start(stage: Stage): Unit = {
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

object Main {
  def main(args: Array[String]): Unit =
    LauncherImpl.launchApplication(classOf[App], classOf[AppPreloader], args)
}

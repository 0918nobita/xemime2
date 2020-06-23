package vision.kodai.xemime

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Scene}
import scalafx.scene.text.Text

object Main extends JFXApp {
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

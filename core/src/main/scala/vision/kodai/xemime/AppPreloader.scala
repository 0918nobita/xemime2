package vision.kodai.xemime

import javafx.application.Preloader
import javafx.stage.Stage
import javax.swing.ImageIcon

import vision.kodai.xemime.macros._

class AppPreloader extends Preloader {
  override def start(stage: Stage): Unit = {
    osDependent(
      "Linux", {
        com.sun.glass.ui.Application.GetApplication().setName("Xemime")
      }
    )

    osDependent(
      "Mac", {
        val url   = getClass.getResource("/example.png")
        val image = new ImageIcon(url).getImage
        java.awt.Taskbar.getTaskbar.setIconImage(image)
      }
    )
  }
}

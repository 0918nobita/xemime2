package vision.kodai.xemime

import com.sun.glass.ui.Application
import javafx.application.Preloader
import javafx.stage.Stage

class AppPreloader extends Preloader {
  override def start(stage: Stage): Unit =
    Application.GetApplication().setName("Xemime")
}

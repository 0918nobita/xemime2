package vision.kodai.xemime

import com.sun.glass.ui.Application
import javafx.application.Preloader
import javafx.stage.Stage
import javax.swing.ImageIcon

class AppPreloader extends Preloader {
  override def start(stage: Stage): Unit =
    System.getProperty("os.name") match {
      case name if name.startsWith("Linux") =>
        Application.GetApplication().setName("Xemime")

      case name if name.startsWith("Mac") =>
        val url   = getClass.getResource("/example.png")
        val image = new ImageIcon(url).getImage
      // TODO: マクロを用いて、非 macOS 環境で com.apple.eawt パッケージをビルドしないようにする
      // com.apple.eawt.Application.getApplication.setDockIconImage(image)
    }
}

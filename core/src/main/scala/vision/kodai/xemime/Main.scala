package vision.kodai.xemime

import com.sun.javafx.application.LauncherImpl

object Main {
  def main(args: Array[String]): Unit =
    LauncherImpl.launchApplication(classOf[App], classOf[AppPreloader], args)
}

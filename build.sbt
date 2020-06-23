name := "xemime2"

version := "0.1"

scalaVersion := "2.13.2"

lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux")   => "linux"
  case n if n.startsWith("Mac")     => "mac"
  case n if n.startsWith("Windows") => "win"
  case _                            => throw new Exception("Unknown platform")
}

lazy val javaFXModules = Seq("controls", "fxml", "media")

libraryDependencies ++= javaFXModules.map(m =>
  "org.openjfx" % s"javafx-$m" % "14.0.1" classifier osName
)

libraryDependencies ++= Seq(
  "org.typelevel"              %% "cats-core"    % "2.1.1",
  "com.github.julien-truffaut" %% "monocle-core" % "2.0.5",
  "org.scalafx"                %% "scalafx"      % "14-R19",
  "org.scalatest"              %% "scalatest"    % "3.2.0" % Test
)

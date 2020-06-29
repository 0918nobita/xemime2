name := "xemime2"

version := "0.1"

scalaVersion := "2.13.2"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")
scalacOptions in (Compile, console) ~= { _.filterNot(_ == "-Xlint") }

// Run in separate VM, so there are no issues with double initialization of JavaFX
fork := true
fork in Test := true

lazy val osName = System.getProperty("os.name") match {
  case "Linux" => "linux"
  case "Mac" => "mac"
  case name if name.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform")
}

lazy val javaFXModules = Seq("controls", "fxml", "media")

libraryDependencies ++= javaFXModules.map(m =>
  "org.openjfx" % s"javafx-$m" % "14.0.1" classifier osName
)

libraryDependencies ++= Seq(
  "org.typelevel"              %% "cats-core"                % "2.1.1",
  "com.github.julien-truffaut" %% "monocle-core"             % "2.0.5",
  "org.scala-lang.modules"     %% "scala-parser-combinators" % "1.1.2",
  "org.scalafx"                %% "scalafx"                  % "14-R19",
  "org.scalatest"              %% "scalatest"                % "3.2.0"   % Test,
  "org.scalacheck"             %% "scalacheck"               % "1.14.3"  % Test,
  "org.scalatestplus"          %% "scalacheck-1-14"          % "3.1.2.0" % Test
)

assemblyJarName in assembly := "xemime.jar"
test in assembly := {}
assemblyMergeStrategy in assembly := {
  case "module-info.class" => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
mainClass in assembly := Some("vision.kodai.xemime.Main")

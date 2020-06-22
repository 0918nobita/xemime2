name := "xemime2"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "org.typelevel"              %% "cats-core"    % "2.1.1",
  "com.github.julien-truffaut" %% "monocle-core" % "2.0.5",
  "org.scalatest"              %% "scalatest"    % "3.2.0" % Test
)

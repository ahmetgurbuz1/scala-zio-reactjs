ThisBuild / version := "1.0.0"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("api"))
  .settings(
    name := "scala-zio-reactjs",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.15",
      "dev.zio" %% "zio-streams" % "2.0.15",
      "dev.zio" %% "zio-http" % "3.0.0-RC2"
    )
  )

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2022",
    idePackagePrefix := Some("works.redcode")
  )

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.2",
  "org.scalactic" %% "scalactic" % "3.2.14",
  "org.scalatest" %% "scalatest" % "3.2.14" % "test",
  "com.lihaoyi" %% "upickle" % "2.0.0",
  "com.lihaoyi" %% "os-lib" % "0.8.1"
)
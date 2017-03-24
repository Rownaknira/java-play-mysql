name := """java-play-mysql"""

version := "1.0-SNAPSHOT"
lazy val root = (project in file(".")).enablePlugins(PlayJava)
scalaVersion := "2.11.7"

unmanagedClasspath  in Runtime += file("target/scala-2.11/classes")

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  filters,
  "mysql" % "mysql-connector-java" % "5.1.35",
  "org.modelmapper" % "modelmapper" % "0.7.7", // for object mapping
  "org.modelmapper.extensions" % "modelmapper-guice" % "0.7.7",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.3.Final"
)

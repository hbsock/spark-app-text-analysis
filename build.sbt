name := "Simple Project"

version := "1.0"

scalaVersion := "2.12.8"


libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.2"

lazy val scalacheck = "org.scalacheck" %% "scalacheck" % "1.13.4"
libraryDependencies += scalacheck % Test

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0-SNAP10" % Test

/*
fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx1024M", "-XX:MaxPermSize=1024M", "-XX:+CMSClassUnloadingEnabled")

parallelExecution in Test := false
*/

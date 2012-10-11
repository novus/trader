name := "trader"

organization := "com.novus"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

initialCommands := "import com.novus.trader._"

mainClass in (Compile) := Some("com.novus.tradesim.Client")

scalaJSUseRhino in Global := false

enablePlugins(ScalaJSPlugin)

name := "Invaders"

scalaVersion := "2.11.8"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.0"

skip in packageJSDependencies := false

jsDependencies += ProvidedJS / "createjs-2015.11.26.min.js"
jsDependencies += RuntimeDOM

// uTest settings
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0" % "test"
libraryDependencies += "com.darkoverlordofdata" %%% "entitas" % "0.0.1"
testFrameworks += new TestFramework("utest.runner.Framework")

persistLauncher in Compile := true
persistLauncher in Test := false



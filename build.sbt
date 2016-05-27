scalaJSUseRhino in Global := false

enablePlugins(ScalaJSPlugin)

name := "Invaders"

scalaVersion := "2.11.7"

resolvers += "Artifactory" at "https://oss.jfrog.org/artifactory/oss-snapshot-local/"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.0"

skip in packageJSDependencies := false

// http://mvnrepository.com/artifact/org.webjars/pixi.js
jsDependencies += "org.webjars" % "pixi.js" % "3.0.7" / "pixi.js"
jsDependencies += RuntimeDOM

// uTest settings
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0" % "test"
libraryDependencies += "com.darkoverlordofdata" %%% "entitas" % "0.0.0-SNAPSHOT"
libraryDependencies += "co.technius.scalajs-pixi" %%% "core" % "0.0.1-SNAPSHOT"
testFrameworks += new TestFramework("utest.runner.Framework")

persistLauncher in Compile := true
persistLauncher in Test := false



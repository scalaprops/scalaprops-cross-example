import sbtcrossproject.CrossPlugin.autoImport.crossProject

val example = crossProject(
  JSPlatform, JVMPlatform, NativePlatform
).in(file(".")).settings(
  scalapropsCoreSettings,
  scalacOptions ++= Seq(
    "-deprecation",
    "-unchecked",
    "-Xfuture",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
  ),
  scalaVersion := "2.13.7",
  name := "scalaprops-cross-example",
  libraryDependencies ++= Seq(
    "com.github.scalaprops" %%% "scalaprops" % "0.8.4" % "test"
  )
).nativeSettings(
  scalapropsNativeSettings
)

val exampleJVM = example.jvm
val exampleJS = example.js
val exampleNative = example.native

publishLocal := {}
publish := {}
Compile / publishArtifact := false
Test / publishArtifact := false

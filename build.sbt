val example = crossProject(
  JSPlatform, JVMPlatform, NativePlatform
).in(file(".")).settings(
  scalapropsCoreSettings,
  scalacOptions ++= Seq(
    "-deprecation",
    "-unchecked",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
  ),
  scalaVersion := "2.13.8",
  crossScalaVersions += "3.1.2",
  name := "scalaprops-cross-example",
  libraryDependencies ++= Seq(
    "com.github.scalaprops" %%% "scalaprops" % "0.9.0" % "test"
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

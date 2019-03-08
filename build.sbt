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
    "-Yno-adapted-args"
  ),
  scalaVersion := "2.11.12",
  resolvers += Opts.resolver.sonatypeReleases,
  name := "scalaprops-cross-example",
  libraryDependencies ++= Seq(
    "com.github.scalaprops" %%% "scalaprops" % "0.6.0" % "test"
  )
).nativeSettings(
  scalapropsNativeSettings
)

val exampleJVM = example.jvm
val exampleJS = example.js
val exampleNative = example.native

publishLocal := {}
publish := {}
publishArtifact in Compile := false
publishArtifact in Test := false

val scalaVersions = Seq("3.9.0", "3.9.0")

val example = projectMatrix
  .in(file("."))
  .defaultAxes()
  .settings(
    scalapropsCoreSettings,
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked",
      "-language:existentials",
      "-language:implicitConversions",
    ),
    name := "scalaprops-cross-example",
    libraryDependencies ++= Seq(
      "com.github.scalaprops" %% "scalaprops" % "0.11.1" % "test"
    )
  )
  .jvmPlatform(scalaVersions)
  .jsPlatform(scalaVersions)
  .nativePlatform(
    scalaVersions,
    scalapropsNativeSettings
  )

val exampleRoot = rootProject.autoAggregate.settings(
  Compile / scalaSource := baseDirectory.value / "dummy",
  Test / scalaSource := baseDirectory.value / "dummy",
  TaskKey[Unit]("testSequential") := Def
    .sequential(
      example
        .allProjects()
        .map(_._1)
        .sortBy(_.id)
        .flatMap(p =>
          Seq[Def.Initialize[Task[Unit]]](
            Def.task(streams.value.log.info(s"start ${p.id} test")),
            (p / Test / testFull).map(_ => ())
          )
        )
    )
    .value,
  autoScalaLibrary := false,
  publishLocal := {},
  publish := {},
  Compile / publishArtifact := false,
  Test / publishArtifact := false,
)

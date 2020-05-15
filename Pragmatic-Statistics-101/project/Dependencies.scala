import sbt._

object Dependencies {

  object Versions {
    val cats       = "2.1.1"
    val catsEffect = "2.1.2"
    val newtype    = "0.4.3"
    val refined    = "0.9.14"
  }

  object Libraries {
    val cats       = "org.typelevel" %% "cats-core"   % Versions.cats
    val catsEffect = "org.typelevel" %% "cats-effect" % Versions.catsEffect

    val refinedCore = "eu.timepit" %% "refined"      % Versions.refined
    val refinedCats = "eu.timepit" %% "refined-cats" % Versions.refined

    val newtype = "io.estatico" %% "newtype" % Versions.newtype
  }

  object LibrariesRuntime {}

  object CompilerPlugins {}

}

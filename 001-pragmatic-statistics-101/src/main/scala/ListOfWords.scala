import cats.effect.{ ExitCode, IO, IOApp, Resource }

import scala.io.BufferedSource

object ListOfWords extends IOApp {

  val headers = "word"

  private val acquire: IO[BufferedSource] =
    IO {
      scala.io.Source.fromResource("little_prince.txt")
    }

  private def buildCsv(content: String): List[String] =
    headers ::
        content
          .replaceAll("[^A-Za-z\\s]", "")
          .split("\\s+")
          .filter(_.nonEmpty)
          .map(_.toLowerCase().trim())
          .toList

  def run(args: List[String]): IO[ExitCode] =
    Resource
      .fromAutoCloseable(acquire)
      .use(source =>
        IO(
          buildCsv(source.mkString).foreach(println)
        )
      )
      .map(_ => ExitCode.Success)
}

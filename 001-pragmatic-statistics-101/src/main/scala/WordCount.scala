import cats.effect.{ ExitCode, IO, IOApp, Resource }

import scala.io.BufferedSource

object WordCount extends IOApp {

  val headers = "word,count"

  private val acquire: IO[BufferedSource] =
    IO {
      scala.io.Source.fromResource("little_prince.txt")
    }

  private def buildCsv(content: String): List[String] =
    headers ::
        content
          .replaceAll("[^A-Za-z0-9\\s]", "")
          .split("\\s+")
          .filter(_.nonEmpty)
          .groupBy(identity)
          .view
          .mapValues(_.length)
          .toMap
          .map {
            case (k, v) => s"$k,$v"
          }
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

import zio._
import zio.http._
import zio.http.Server
import java.io.File

object Api extends ZIOAppDefault {
  private val buildDirectory: List[String] = List("ui", "build")
  private val staticDir: List[String]      = buildDirectory.:+("static")
  private val indexHtmlDir                 = buildDirectory.:+("index.html").mkString(File.separator)

  val app =
    Http.collectHttp[Request] {
      case Method.GET -> Root        => Http.fromFile(new File(indexHtmlDir))
      case Method.GET -> Root / "static" / content / file =>
        val path: List[String] = content match {
          case "js"    => staticDir.:+("js").:+(file)
          case "css"   => staticDir.:+("css").:+(file)
          case "media" => staticDir.:+("media").:+(file)
        }
        Http.fromFile(new File(path.mkString(File.separator)))
    }

  override val run: ZIO[Any, Throwable, Nothing] = Server
    .serve(app.withDefaultErrorResponse)
    .provide(Server.defaultWithPort(8080))
}

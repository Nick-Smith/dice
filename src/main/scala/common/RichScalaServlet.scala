package common

import org.scalatra.ScalatraServlet

trait RichScalatraServlet extends ScalatraServlet with Logger {

  def cacheFor(seconds: Int) {
    seconds match {
      case 0 =>
        response.setHeader("Cache-Control", "private, no-cache, no-cache=Set-Cookie, proxy-revalidate")
        response.setHeader("Pragma", "no-cache")

      case _ =>
        response.setHeader("Cache-Control", "public, max-age=%s;" format seconds)
    }
  }

  protected def noCache() {
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate")
    response.setHeader("Pragma", "no-cache")
    response.setDateHeader("Expires", 0)
  }

  protected def privateCache() {
    response.setHeader("Cache-Control", "private")
  }

  error {
    case e =>
      log.error(e.toString, e)
      val stackTrace = e.getStackTraceString.split("\n") map { "\tat " + _ } mkString "\n"
      e.toString + "\n" + stackTrace
  }
}


package common

import org.slf4j.LoggerFactory

trait Logger {
  protected val log = LoggerFactory getLogger getClass

  def quietly[T](action: => T): Option[T] = try {
    Option(action)
  } catch {
    case e: Throwable => {
      log.error("Suppressed error:", e)
      None
    }
  }

  def retry[T](retries: Int)(f: () => T): T = {
    try { f.apply() } catch {
      case e: Throwable =>
        if (retries > 1) {
          log.warn("Exception thrown in retry.", e)
          retry(retries - 1)(f)
        } else
          throw e
    }
  }

}

trait Logging extends Logger {
  def trace(msg: => String) {
    log trace msg
  }

  def trace(msg: => String, e: Throwable) {
    log.trace(msg, e)
  }

  def debug(msg: => String) {
    log debug msg
  }

  def debug(msg: => String, e: Throwable) {
    log.debug(msg, e)
  }

  def info(msg: => String) {
    log info msg
  }

  def info(msg: => String, e: Throwable) {
    log.info(msg, e)
  }

  def warn(msg: => String) {
    log warn msg
  }

  def warn(msg: => String, e: Throwable) {
    log.warn(msg, e)
  }

  def error(e: Throwable) {
    log.error(e.toString, e)
  }

  def error(msg: => String) {
    log error msg
  }

  def error(msg: => String, e: Throwable) {
    log.error(msg, e)
  }
}

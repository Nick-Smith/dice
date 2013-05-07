package dice

import org.scalatra.ScalatraServlet
import common.RichScalatraServlet

class Dispatcher extends RichScalatraServlet {

  before("*") {
    response setCharacterEncoding "UTF-8"
    noCache()
  }

  get("/roll") {
    val rolled = params get "rolled" map { _.toInt } getOrElse 1
    val kept = params get "kept" map { _.toInt } getOrElse 0
    val sides = params get "sides" map { _.toInt } getOrElse 6
    html.rolls(Dice(rolled, kept, sides))
  }

  get("/roll/:dice") {
    val split = params("dice") split "k"
    if (split.size != 2)
      pass()
    else {
      val Array(rolled, kept) = split map { _.toInt }
      val sides = params get "sides" map { _.toInt } getOrElse 6
      html.rolls(Dice(rolled, kept, sides))
    }
  }
}


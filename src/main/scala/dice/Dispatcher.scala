package dice

import java.util.concurrent.atomic.AtomicReference

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
    html.rolls(rollDice(rolled, kept, sides))
  }

  get("/roll/:dice") {
    val split = params("dice") split "k"
    if (split.size != 2)
      pass()
    else {
      val Array(rolled, kept) = split map { _.toInt }
      val sides = params get "sides" map { _.toInt } getOrElse 6
      html.rolls(rollDice(rolled, kept, sides))
    }
  }

  private var rng: AtomicReference[RNG] = new AtomicReference[RNG](RNG(System.nanoTime()))

  private def rollDice(rolled: Int, kept: Int, sides: Int): DiceResult = {
    val (nextRng, result) = Dice(rolled, kept, sides)(rng)
    rng.set(nextRng)
    result
  }
}

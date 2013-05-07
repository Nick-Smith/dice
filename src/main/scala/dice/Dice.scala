package dice

import scala.util.Random

object Dice {

  def apply(rolled: Int = 1, kept: Int = 0, sides: Int = 6): DiceResult = {
    val results = rolls(sides, rolled).sorted.reverse
    val actualKept: Int = if (kept > 0) kept else rolled
    val total: Int = results.take(actualKept) reduce { (s: Int, i: Int) => s + i }
    DiceResult(total, sides, rolled, actualKept, results)
  }

  def rolls(sides: Int, rolled: Int): List[Int] = rolled match {
    case 0 => Nil
    case i if (i > 0) => roll(sides) :: rolls(sides, rolled - 1)
    case _ => throw new IllegalArgumentException("Negative number of rolls not allowed!")
  }

  def roll(sides: Int): Int = Random.nextInt(sides) + 1

}

case class DiceResult(total: Int, sides: Int, rolled: Int, kept: Int, rolls: List[Int])

package dice

import scalaz._

trait RNG {
  def nextInt: (RNG, Int)
}

object RNG {
  type Rand[A] = State[RNG, A]

  val int: Rand[Int] = State { _.nextInt }

  val nonNegativeInt: Rand[Int] = int map { i => if (i < 0) -(i + 1) else i }

  def nonNegativeLessThan(n: Int): Rand[Int] = {
    nonNegativeInt.flatMap { i =>
      val mod = i % n
      if (i + (n - 1) - mod >= 0) State.state(mod) else nonNegativeLessThan(n)
    }
  }

  def apply(seed: Long): RNG = Simple(seed)
}

case class Simple(seed: Long) extends RNG {
  def nextInt: (RNG, Int) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL // `&` is bitwise AND. We use the current seed to generate a new seed.
    val nextRNG = Simple(newSeed) // The next state, which is an `RNG` instance created from the new seed.
    val n = (newSeed >>> 16).toInt // `>>>` is right binary shift with zero fill. The value `n` is our new pseudo-random integer.
    (nextRNG, n) // The return value is a tuple containing both a pseudo-random integer and the next `RNG` state.
  }
}
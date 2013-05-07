package dice

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DiceTest extends FlatSpec with ShouldMatchers {

  "Dice" should "roll and sum one die" in {
    val result1 = Dice(1)
    result1.rolled should be(1)
    result1.kept should be(1)
    result1.sides should be(6)
    result1.rolls.size should be(1)
    result1.total should be(result1.rolls.head)
  }

  it should "roll and sum multiple dice" in {
    val result2 = Dice(2)
    result2.rolled should be(2)
    result2.kept should be(2)
    result2.sides should be(6)
    val rolls2 = result2.rolls
    rolls2.size should be(2)
    result2.total should be(rolls2(0) + rolls2(1))

    val result3 = Dice(3)
    val rolls3 = result3.rolls
    result3.total should be(rolls3(0) + rolls3(1) + rolls3(2))
  }

  it should "keep the highest dice" in {
    val result = Dice(3, 2)
    result.rolled should be(3)
    result.kept should be(2)
    val rolls = result.rolls
    val min = rolls reduce { (l, r) => min(l, r) }
    result.total should be(rolls(0) + rolls(1) + rolls(2) - min)
  }

  it should "drop the 2 lowest if specified" in{
    val result = Dice(3, 1)
    result.rolled should be(3)
    result.kept should be(1)
    val rolls = result.rolls
    val max = rolls reduce { (l, r) => max(l, r) }
    result.total should be(max)
  }
}
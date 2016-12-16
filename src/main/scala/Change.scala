import java.util.Scanner

import scala.annotation.tailrec

object Change {

  private val denom = 10 :: 5 :: 1 :: Nil

   private def getChangeRec(m: Int): Int = {
    @tailrec
    def changeRec(m: Int, count: Int = 0, d: List[Int] = denom): Int = {
      if (d.isEmpty)
        count
      else {
        val numCoins = m / d.head
        changeRec(m - (d.head * numCoins), count + numCoins, d.tail)
      }
    }
    changeRec(m)
  }

  private def getChange(m: Int): Int = {
    var coins = 0
    var rem = m
    for (d <- denom) {
      val num = rem / d
      coins += num
      rem -= d * num
    }
    assert(rem == 0, "Remainder not equals to zero!")
    coins
  }

  def main(args: Array[String]) {
    val scanner = new Scanner(System.in)
    val m = scanner.nextInt()
    //Constraints. 1 ≤ m ≤ 10^3
    require(m >= 1 && m <= 10e3)
    println(getChangeRec(m))
  }
}
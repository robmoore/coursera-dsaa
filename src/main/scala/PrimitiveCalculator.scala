import java.util.Scanner

import scala.annotation.tailrec

object PrimitiveCalculator {

  def main(args: Array[String]) {
    val scanner = new Scanner(System.in)
    val n = scanner.nextInt()
    val sequence = optimal_sequence(n)
    println(sequence.size - 1)
    for (x <- sequence) {
      print(s"$x ")
    }
  }

  private def optimal_sequence(n: Int): List[Int] = {
    @tailrec
    def helper(acc: List[Int], n: Int): List[Int] = {
      if (n < 1)
        acc
      else {
        val nextN = if (n % 3 == 0) {
          n / 3
        } else if (n % 2 == 0) {
          n / 2
        } else {
          n - 1
        }
        helper(n :: acc, nextN)
      }
    }

    helper(List[Int](), n)
  }
}

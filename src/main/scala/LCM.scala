import java.util.Scanner

import scala.annotation.tailrec

object LCM {
    /*
      for (long l = 1; l <= (long) a * b; ++l)
            if (l % a == 0 && l % b == 0)
                return l;

        return (long) a * b;
     */

  private def lcm_naive(a: Int, b: Int): Long = {
    var l = 1L
    while (l <= a.toLong * b) {
      if (l % a == 0 && l % b == 0)
        return l
      l += 1
    }
    a.toLong * b
  }

  @tailrec
  private def euclidGcd(a: Int, b: Int): Int = {
    if (b == 0)
      return a
    val aPrime = a % b
    euclidGcd(b, aPrime)
  }

  private def lcm(a: Int, b: Int): Long = {
    //LCM(a, b) · GCD(a, b) = a · b -> LCM(a, b) = (a * b) / GCD(a, b)
    (a.toLong * b) / euclidGcd(a, b)
  }

  def main(args: Array[String]) {
    val scanner: Scanner = new Scanner(System.in)
    val a: Int = scanner.nextInt
    val b: Int = scanner.nextInt
    //println(lcm_naive(a, b))
    println(lcm(a, b))
  }
}
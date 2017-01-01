import java.util.Scanner

import scala.annotation.tailrec

object PrimitiveCalculator extends App {
  val scanner = new Scanner(System.in)
  val n = scanner.nextInt()
  require(n >= 1 && n <= 10e6)
  val sequence = optimalSequence(n)

  def optimalSequence(n: Int): List[Int] = {
    val t = Array.ofDim[Int](n)

    // calculate list of shortest paths
    for (i <- 1 until t.length) {
      // create path counts for each option using priors indexed by x / 3, x / 2, and x - 1
      val candidates = (2 to 3).foldLeft(List(t(i - 1)))((cs, d) =>
        if ((i + 1) % d == 0) cs :+ t((i + 1) / d - 1) else cs)
      // find shortest of 3 paths
      t(i) = candidates.min + 1
    }

    // work out shortest path from n
    @tailrec
    def derivePath(acc: List[Int]): List[Int] = {
      if (acc.head == 1) // we've reached the starting point
        acc
      else {
        val last = acc.head // actual last value
        val lastPathLength = t(last - 1) // what the path length was for the last value
        val v = last match {
          case x if x % 3 == 0 && t(x / 3 - 1) + 1 == lastPathLength => x / 3
          case x if x % 2 == 0 && t(x / 2 - 1) + 1 == lastPathLength => x / 2
          case x => x - 1
        }
        derivePath(v :: acc)
      }
    }

    derivePath(List(n))
  }

  println(sequence.size - 1)
  sequence.foreach(x => print(s"$x "))
}

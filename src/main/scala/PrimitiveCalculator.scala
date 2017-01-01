import java.util.Scanner

import scala.annotation.tailrec

/*
  Task.
    Given an integer n, compute the minimum number of operations needed to obtain the number n starting from the number 1.
  Input Format.
    The input consists of a single integer 1 ≤ n ≤ 10^6.
  Output Format.
    In the first line, output the minimum number k of operations needed to get n from 1.
    In the second line output a sequence of intermediate numbers. That is, the second line should contain
    positive integers a_0, a_2, ..., a_k−1 such that a_0 = 1, a_k−1 = n and for all 0 ≤ i < k−1, a_i+1 is equal to
    either a_i+1, 2a+i, or 3a+i. If there are many such sequences, output any one of them.
 */
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

import java.util._

object DotProduct {

  private def maxDotProduct(a: Array[Int], b: Array[Int]): Long =
    a.sortBy(-_).zip(b.sortBy(-_)).foldLeft(0L)((x, y) => x + y._1.toLong * y._2) //Math.multiplyExact

  /*
    Task. Given two sequences a_1, a_2, ..., a_n (a_i is the profit per click of the i-th ad) and
    b_1, b_2, ..., b_n (b_i is the average number of clicks per day of the i-th slot), we need to
    partition them into n pairs (a_i , b_j) such that the sum of their products is maximized.

    Input Format. The first line contains an integer n, the second one contains a sequence of integers
    a_1, a_2,..., a_n, the third one contains a sequence of integers b_1, b_2, ..., b_n.

    Constraints. 1 ≤ n ≤ 10^3 ; −10^5 ≤ a_i , b_i ≤ 10^ 5 for all 1 ≤ i ≤ n.

    Output Format. Output the maximum value of ∑︀ i=1 -> n of a_i * c_1, where c_1, c_2,...,c_n
    is a permutation of b_1, b_2, ..., b_n.
   */
  def main(args: Array[String]) {
    val scanner = new Scanner(System.in)
    val n = scanner.nextInt()
    require(n >= 1 && n <= 10e3)
    val a = Array.ofDim[Int](n)
    for (i <- 0 until n) {
      a(i) = scanner.nextInt()
      require(a(i) >= -10e5 && a(i) <= 10e5)
    }
    val b = Array.ofDim[Int](n)
    for (i <- 0 until n) {
      b(i) = scanner.nextInt()
      require(b(i) >= -10e5 && b(i) <= 10e5)
    }
    println(maxDotProduct(a, b))
  }
}

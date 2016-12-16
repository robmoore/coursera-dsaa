import java.util.Scanner

object FractionalKnapsack {

  /*
  Could be recursive like coin problem?
   */
  private def getOptimalValue(capacity: Int, values: Array[Int], weights: Array[Int]): Double = {
    val items = values.zip(weights).map(x => (x, x._1/x._2.toDouble)).sortBy(-_._2)
    var knapsack = 0
    var value = 0.0
    for {item <- items if knapsack < capacity} {
      val w = item._1._2
      val p = item._2
      val s = capacity - knapsack
      val d = if (w <= s) w else s
      knapsack += d
      value += d * p
    }
    value
  }

  /*
    Task.
      The goal of this code problem is to implement an algorithm for the fractional knapsack problem.

    Input Format.
      The first line of the input contains the number of items and the capacity of a knapsack.
    The next lines define the values and weights of the items. The i-th line contains integers v and w
    — the value and the weight of i-th item, respectively.

    Constraints.
      1≤n≤10^3, 0≤W≤2·10^6, 0≤v≤2·10^6, 0<w≤2·10^6 for all 1 <- i <- n.
      All the numbers are integers.

    Output Format.
      Output the maximal value of fractions of items that fit into the knapsack. The absolute
    value of the difference between the answer of your program and the optimal value should be at most
    10−3. To ensure this, output your answer with at least four digits after the decimal point (otherwise
    your answer, while being computed correctly, can turn out to be wrong because of rounding issues).
   */
  def main(args: Array[String]) {
    val scanner = new Scanner(System.in)
    val n = scanner.nextInt()
    val capacity = scanner.nextInt()
    val values = Array.ofDim[Int](n)
    val weights = Array.ofDim[Int](n)
    for (i <- 0 until n) {
      values(i) = scanner.nextInt()
      weights(i) = scanner.nextInt()
    }
    printf("%.4f", getOptimalValue(capacity, values, weights))
  }
}

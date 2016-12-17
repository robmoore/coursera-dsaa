import java.util._

object CoveringSegments {

  private def optimalPoints(segments: Array[Segment]): Array[Int] = {
    val points = Array.ofDim[Int](2 * segments.length)
    for (i <- segments.indices) {
      points(2 * i) = segments(i).start
      points(2 * i + 1) = segments(i).end
    }
    points
  }

  private case class Segment(var start: Int, var end: Int)

  /*
    Task. Given a set of n segments {[a_0, b_0], [a_1, b_1], ..., [a_n−1 , b_n−1]} with integer coordinates on a line,
    find the minimum number m of points such that each segment contains at least one point. That is, find a
    set of integers X of the minimum size such that for any segment [a i , b i ] there is a point x ∈ X such
    that a i ≤ x ≤ b i .

    Input Format. The first line of the input contains the number n of segments. Each of the following n lines
    contains two integers a i and b i (separated by a space) defining the coordinates of endpoints of the i-th
    segment.

    Constraints. 1 ≤ n ≤ 100; 0 ≤ a_i ≤ b_i ≤ 10^9 for all 0 ≤ i < n.

    Output Format. Output the minimum number m of points on the first line and the integer coordinates
    of m points (separated by spaces) on the second line. You can output the points in any order. If there
    are many such sets of points, you can output any set. (It is not difficult to see that there always exist
    a set of points of the minimum size such that all the coordinates of the points are integers.)
   */
  def main(args: Array[String]) {
    val scanner = new Scanner(System.in)
    val n = scanner.nextInt()
    val segments = Array.ofDim[Segment](n)
    for (i <- 0 until n) {
      var start: Int = 0
      var end: Int = 0
      start = scanner.nextInt()
      end = scanner.nextInt()
      segments(i) = Segment(start, end)
    }
    val points = optimalPoints(segments)
    println(points.length)
    for (point <- points) {
      System.out.print(point + " ")
    }
  }
}

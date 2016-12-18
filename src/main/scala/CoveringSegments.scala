import java.util.Scanner

import scala.annotation.tailrec

object CoveringSegments {
  private case class Segment(start: Int, end: Int)

  private def partitionSegments(segments: Seq[Segment], acc: Seq[Seq[Segment]] = List()): Seq[Seq[Segment]] = {
    @tailrec
    def helper(segments: Seq[Segment], acc: Seq[Seq[Segment]] = List()): Seq[Seq[Segment]] = {
      if (segments.isEmpty)
        acc
      else {
        // ensure that end point exists in segment
        val x = segments.span(s => s.start <= segments.head.end && s.end >= segments.head.end)
        helper(x._2, acc ++ List(x._1))
      }
    }
    // sort by end points
    helper(segments.sortBy(x => x.end))
  }

  private def findPoint(segments: Seq[Segment]): Int = {
    @tailrec
    def helper(p: Int): Int = {
      if (p < 0)
        throw new RuntimeException("Reached 0!")
      else if (segments.forall(x => x.start <= p && p <= x.end))
        p
      else
        helper(p - 1)
    }
    // sort segment group by end points and pass in first end point
    helper(segments.map(x => x.end).sorted.head)
  }

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
    require(n >= 1 && n <= 100)
    def makeSegment(i: Int): Segment = {
      val start = scanner.nextInt()
      val end = scanner.nextInt()
      require(0 <= start && start <= 10e9)
      require(start <= end && end <= 10e9)
      Segment(start, end)
    }
    val segments = List.tabulate(n)(makeSegment).distinct
    val partitionedSegments = partitionSegments(segments)
    println(partitionedSegments.length)

    val points = partitionedSegments.map(x => findPoint(x))
    println(points.mkString(" "))
  }
}

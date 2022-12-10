package works.redcode
package day4

object AssignmentHandler:
  type Assignment = (Set[Int], Set[Int])

  def parseSets(assignments: List[String]): List[Assignment] = assignments
    .map(
      _.split(',').flatMap(_.split('-').map(_.toInt))
    )
    .map(arr => ((arr(0) to arr(1)).toSet, (arr(2) to arr(3)).toSet))

  def findSubsets(assignments: List[Assignment]): List[Boolean] = assignments
    .map((s1, s2) => (s1 subsetOf s2) || (s2 subsetOf s1))
  
  def findIntersects(assignments: List[Assignment]): List[Boolean] = assignments
    .map((s1, s2) => (s1 intersect s2).nonEmpty)

  def countTrues(bs: List[Boolean]): Int = bs.count(_ == true)


  private lazy val assignments = util.Using(io.Source.fromResource("day4/input-data.txt")) {_.getLines().toList}

  private def doPart1: List[String] => Int = parseSets.andThen(findSubsets).andThen(countTrues)
  private def doPart2: List[String] => Int = parseSets.andThen(findIntersects).andThen(countTrues)
  
  lazy val part1: Int = doPart1(assignments.get)
  lazy val part2: Int = doPart2(assignments.get)


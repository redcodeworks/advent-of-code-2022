package works.redcode
package day4

object AssignmentHandler:
  type Assignment = (Set[Int], Set[Int])

  def parseSets(assignments: List[String]): List[Assignment] = assignments
    .map(
      _.split(',').flatMap(_.split('-').map(_.toInt))
    )
    .map(arr => ((arr(0) to arr(1)).toSet, (arr(2) to arr(3)).toSet))

  def findSubsets(assignmentSets: List[Assignment]): List[Boolean] = assignmentSets
    .map((s1, s2) => (s1 subsetOf s2) || (s2 subsetOf s1))

  def countTrues(bs: List[Boolean]): Int = bs.count(_ == true)


  lazy val assignments = util.Using(io.Source.fromResource("day4/input-data.txt")) {_.getLines().toList}

  def doPart1: List[String] => Int = parseSets.andThen(findSubsets).andThen(countTrues)
  
  lazy val part1 = doPart1(assignments.get)


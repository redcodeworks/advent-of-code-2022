package works.redcode
package day3

import scala.annotation.tailrec

object SackHandler:
  val prioritySchedule: Map[Char, Int] = (
    ('a' to 'z').map(c => c -> (c.toInt - 96)) ++
      ('A' to 'Z').map(c => c -> (c.toInt - 38))
    ).toMap

  lazy val rucksacks = util.Using(io.Source.fromResource("day3/input-data.txt")) { _.getLines.toList}.get
  lazy val compartmentsByRucksacks = rucksacks.map(s => (s.take(s.length / 2), s.drop(s.length / 2)))

  lazy val priortyByCompartmentCommonItems = compartmentsByRucksacks
    .map((s, t) => s intersect t)
    .map(_.head)
    .groupMapReduce(identity)(prioritySchedule)(_ + _)

  lazy val rucksackTeams: List[(Char, List[String])] =
    @tailrec
    def intersectItemsInList(rs: List[String], acc: String = ""): String = rs match
      case Nil => acc
      case head :: tail if acc.isBlank => intersectItemsInList(tail, head)
      case head :: tail => intersectItemsInList(tail, head intersect acc)

    rucksacks
      .grouped(3)
      .map(xs => (intersectItemsInList(xs).charAt(0), xs))
      .toList


  def priorityByTeam: Map[Char, Int] = rucksackTeams
    .map((c, xs) => (c, prioritySchedule.getOrElse(c, 0)))
    .groupMapReduce(_._1)(_._2)(_ + _)
package works.redcode
package day3

object SackHandler:
  val prioritySchedule: Map[Char, Int] = (
    ('a' to 'z').map(c => c -> (c.toInt - 96)) ++
      ('A' to 'Z').map(c => c -> (c.toInt - 38))
    ).toMap

  lazy val rucksacks = io.Source.fromResource("day3/input-data.txt")
    .getLines
    .toList
    .map(s => (s.take(s.length / 2), s.drop(s.length / 2)))

  lazy val priortyByItem = rucksacks
    .map((s, t) => s intersect t)
    .map(_.head)
    .groupMapReduce(identity)(prioritySchedule)(_ + _)
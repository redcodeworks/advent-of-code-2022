package works.redcode
package day1

import com.typesafe.config.{Config, ConfigFactory}

import scala.annotation.tailrec

object Expedition {
  def apply(i: Int): Elf  = elves(i)

  // Load Config File
  private val applicationConf: Config = ConfigFactory.load("application.conf")

  // Create lazy sequence for Id generation
  private def genSeqId(n: Int): LazyList[Int] =
    n #:: genSeqId(
      n + rng42.between(1, applicationConf.getInt("sequence.randomRange"))
    )

  private val seqIds = genSeqId(applicationConf.getInt("sequence.start"))
  private val rng42 = util.Random(42)
  private var idIndex: Int = 0
  private def nextId: Int = { idIndex += 1; seqIds(idIndex) }

  // Parse Input Data and create List of case classes
  lazy val elves: List[Elf] =
    @tailrec
    def recur(xs: List[String], currElf: Elf, acc: List[Elf] = Nil): List[Elf] = xs match
      case Nil => currElf :: acc
      case head :: tail if head.isBlank => recur(tail, Elf(nextId), currElf :: acc)
      case head :: tail => recur(tail, currElf.addSnack(head.toInt), acc)

    // Initate recursive function from text stream
    util.Using(io.Source.fromResource("day1/input-data.txt")){
      stream => recur(stream.getLines.toList, Elf(0))
    }.getOrElse(Nil)

  // Calculate metrics
  lazy val snackiest: List[Elf] = elves.sortBy(-_.kcals)
  def totalSnackiness(top: Int): Int = snackiest.take(top).map(_.kcals).sum
  def totalSnackiness: Int = snackiest.map(_.kcals).sum


}

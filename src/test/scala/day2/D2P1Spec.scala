package works.redcode
package day2

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

import scala.collection.mutable.Stack

class D2P1Spec extends AnyFlatSpec with should.Matchers {

  val resourceName = "day2/input-data.txt"
  val rps1 = GamePart1(resourceName)

  val inputData = io.Source.fromResource(resourceName).getLines.toList

  "The input data" should "have 2500 lines" in {
    inputData.size should equal (2500)
  }

  it should "have three characters on each line" in {
    all (inputData.map(_.length)) should equal (3)
  }

  it should "have a ' ' in the middle character" in {
    all (inputData.map(xs => xs(1))) should equal (' ')
  }

  "The parsed strategy guide" should "have the same number of lines as the input data" in {
    inputData.size should equal(rps1.getStrategyGuideP1.size)

  }

  "Rock" should "have valid outcomes" in {
    rps1.doRoShamBeaux(Pick.Rock, Pick.Paper) should be (day2.Outcome.Loss)
    rps1.doRoShamBeaux(Pick.Rock, Pick.Scissors) should be (day2.Outcome.Win)
    rps1.doRoShamBeaux(Pick.Rock, Pick.Rock) should be (day2.Outcome.Draw)
  }

  "Scores" should "compute as expected" in {
    rps1.scoreRound(Pick.Paper, Pick.Rock) should equal (8)
    rps1.scoreRound(Pick.Rock, Pick.Paper) should equal (1)
    rps1.scoreRound(Pick.Scissors, Pick.Scissors) should equal (6)
  }

  it should "compute as expected from encoded values" in {
    rps1.scoreRound(rps1.decodePick("Y"), rps1.decodePick("A")) should equal (8)
    rps1.scoreRound(rps1.decodePick("X"), rps1.decodePick("B")) should equal (1)
    rps1.scoreRound(rps1.decodePick("C"), rps1.decodePick("C")) should equal (6)

  }

}
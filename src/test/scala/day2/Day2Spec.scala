package works.redcode
package day2

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

import scala.collection.mutable.Stack

class Day2Spec extends AnyFlatSpec with should.Matchers {
  import Game._

  val inputData = io.Source.fromResource("day2/input-data.txt").getLines.toList

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
    inputData.size should equal(strategyGuide.size)

  }

  "Rock" should "have valid outcomes" in {
    doRoShamBeau(Pick.Rock, Pick.Paper) should be (Outcome.Loss)
    doRoShamBeau(Pick.Rock, Pick.Scissors) should be (Outcome.Win)
    doRoShamBeau(Pick.Rock, Pick.Rock) should be (Outcome.Draw)
  }

  "Scores" should "compute as expected" in {
    computeScore(Pick.Paper, Pick.Rock) should equal (8)
    computeScore(Pick.Rock, Pick.Paper) should equal (1)
    computeScore(Pick.Scissors, Pick.Scissors) should equal (6)
  }

  it should "compute as expected from encoded values" in {
    computeScore(decodeInput("Y"), decodeInput("A")) should equal (8)
    computeScore(decodeInput("X"), decodeInput("B")) should equal (1)
    computeScore(decodeInput("C"), decodeInput("C")) should equal (6)

  }

}
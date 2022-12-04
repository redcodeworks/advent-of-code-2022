package works.redcode
package day2

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

import scala.collection.mutable.Stack

class D2P2Spec extends AnyFlatSpec with should.Matchers {

  val resourceName = "day2/input-data.txt"
  val rps2 = GamePart2(resourceName)

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
    inputData.size should equal(rps2.getStrategyGuideP2.size)

  }


}
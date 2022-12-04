package works.redcode
package day3

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

import scala.collection.mutable.Stack

class D3Spec extends AnyFlatSpec with should.Matchers {
  import SackHandler._

  val resourceName = "day3/input-data.txt"

  val inputData = io.Source.fromResource(resourceName).getLines.toList

  "The input data" should "have 300 lines" in {
    inputData.size should equal(300)
  }


}

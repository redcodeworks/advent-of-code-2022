package works.redcode
package day1

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

import scala.collection.mutable.Stack

class D1Spec extends AnyFlatSpec with should.Matchers {
  "The Expedition object" should "have valid input data" in {
    Expedition.elves.size should be > 0
  }

  it should "have a first element that evaluates to" in {
    Expedition.elves.head.kcals should equal (50009)
  }


}
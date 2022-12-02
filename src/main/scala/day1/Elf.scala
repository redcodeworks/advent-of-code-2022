package works.redcode
package day1

case class Elf(id: Int, snacks: List[Int] = Nil) extends Person:
  def addSnack(snack: Int): Elf = Elf(
    this.id,
    snack :: this.snacks
  )

  lazy val kcals: Int = snacks.sum

  override def toString: String = s"Elf(id: ${this.id}, kcals: ${this.kcals})"
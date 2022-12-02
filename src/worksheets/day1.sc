import com.typesafe.config.{Config, ConfigFactory}
import upickle.default._


val applicationConf: Config = ConfigFactory.load("application.conf")

applicationConf.getString("app.owner")


val lines = util.Using(
  io.Source.fromResource("day1/input-data.txt")
  ) { _.getLines.toList}.get

def genSeqId(n: Int): LazyList[Int] =
  n #:: genSeqId(
    n + util.Random.nextInt(applicationConf.getInt("sequence.randomRange"))
  )

val seqIds = genSeqId(applicationConf.getInt("sequence.start"))


trait Person
case class Elf(id: Int, snacks: List[Int] = Nil) extends Person:
  def addSnack(snack: Int): Elf = Elf(
      this.id,
      snack :: this.snacks
    )

var i = 0

def buildExpedition(lines: List[String]): List[Elf] =
  def recur(xs: List[String], currElf: Elf, acc: List[Elf] = Nil): List[Elf] = xs match
    case Nil => acc
    case head :: tail if head.isBlank => i += 1; recur(tail, Elf(i), currElf :: acc)
    case head :: tail => recur(tail, currElf.addSnack(head.toInt), acc)

  recur(lines, Elf(i))

buildExpedition(lines).foreach(println)
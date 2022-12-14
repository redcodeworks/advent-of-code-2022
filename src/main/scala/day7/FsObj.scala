package works.redcode
package day7


import scala.annotation.tailrec

trait FsObj:
  def calcSize: Long
  def name: String
  val parent: Option[Dir]

  def absolutePath: String =
    @tailrec
    def recur(fs: FsObj, acc: List[String] = Nil): List[String] = fs.parent match
      case None => name :: acc
      case p => recur(p.get, fs.name :: acc)

    recur(this).mkString("/")

case class Dir(name: String, parent: Option[Dir] = None, contents: Map[String, FsObj] = Map()) extends FsObj:
  def mkDir(dir: Dir): Dir =
    this.copy(contents = contents + (dir.name -> dir.copy(parent = Some(this))))

  def mkDir(name: String): Dir =
    this.copy(contents = contents + (name -> Dir(name, Some(this))))

  def touch(file: File): Dir =
    this.copy(contents = contents + (file.name -> file.copy(parent = Some(this))))

  def touch(name: String, size: Long): Dir =
    this.copy(contents = contents + (name -> File(name, size, Some(this))))

  def ls: String = s"${name}\n\t${contents.values.mkString("\n\t")}"

  def cd(dir: String): Dir = dir match
    case "." => this
    case ".." => this.parent.getOrElse(this)
    case _ => this.contents.getOrElse(dir, this).asInstanceOf[Dir]

  def calcSize: Long =
    def recur(fs: FsObj, acc: List[Long] = Nil): Long = fs match
      case file: File => file.size
      case dir: Dir => dir.contents.values.map(_.calcSize).sum

    recur(this)

  override def toString: String = s"Dir($name)"

case class File(name: String, size: Long = 0, parent: Option[Dir] = None) extends FsObj:
  def calcSize: Long = this.size


  override def toString: String = s"File($name, $size)"

package works.redcode
package day7

import collection.mutable

enum Command:
  case cd(dir: String)
  case ls

enum Term:
  case Cmd(cmd: Command)
  case Dir(name: String)
  case File(name: String, size: Long)


object Filesystem:

  case class Directory(
                        name: String,
                        parent: Option[Directory],
                        subdirs: mutable.Map[String, Directory] = mutable.Map(),
                        files: mutable.Map[String, Long] = mutable.Map()
                      ):

    def absPath: String = parent match
      case None => name
      case Some(p) if p.name == "/" => s"/${name}"
      case Some(p) =>  s"${p.absPath}/${name}"

    def size: Long =
      def recur(dir: Directory): Long = dir.files.values.sum + dir.subdirs.values.map(_.size).sum
      recur(this)

    def collectSizes: Map[String, Long] =
      def recur(dir: Directory): List[(String, Long)] = (dir.absPath, dir.size) :: dir.subdirs.values.map(d => recur(d)).toList.flatten
      recur(this).toMap

    def diskSpaceRemaining(total: Long) = total - this.size

    def findFilesOfSize(f: Long => Boolean): Map[String, Long] = collectSizes.filter(e => f(e._2))

    override def toString: String = s"Dir($name)"

  def parseInput(line: String): Term = line match
    case s"$$ cd $dir" => Term.Cmd(Command.cd(dir))
    case s"$$ ls" => Term.Cmd(Command.ls)
    case s"dir $dir" => Term.Dir(dir)
    case s"$size $name" => Term.File(name, size.toLong)

  def interpret(termSequence: List[Term], currentDir: Option[Directory], rootDir: Directory): Unit = termSequence match
    case Nil => ()
    case Term.Cmd(Command.cd("/")) :: tail => interpret(tail, Some(rootDir), rootDir)
    case Term.Cmd(Command.cd(".")) :: tail => interpret(tail, currentDir, rootDir)
    case Term.Cmd(Command.cd("..")) :: tail => interpret(tail, currentDir.get.parent, rootDir)
    case Term.Cmd(Command.cd(name)) :: tail => interpret(tail, currentDir.get.subdirs.get(name), rootDir)
    case Term.Cmd(Command.ls) :: tail => interpret(tail, currentDir, rootDir)
    case Term.File(name, size) :: tail => { currentDir.get.files.put(name, size); interpret(tail, currentDir, rootDir) }
    case Term.Dir(name) :: tail => { currentDir.get.subdirs.put(name, Directory(name, currentDir)); interpret(tail, currentDir, rootDir) }


  def buildTree(termSequence: List[Term]): Directory =
    val root = Directory("/", None)
    interpret(termSequence, None, root)
    root

  lazy val termSequence = util.Using(io.Source.fromResource("day7/input-data.txt")) {_.getLines.toList.map(parseInput)}.get

  lazy val tree: Directory = buildTree(termSequence)

  def doPart1 = tree.collectSizes.values.filter(_ <= 100000)

  def doPart2 = tree.findFilesOfSize(_ > 30000000L - tree.diskSpaceRemaining(70000000))

package android.buildinfo

import scala.xml.Node

trait AndroidBuildInfo {
  implicit def string(entry: (String, String)) = new Entry {
    override def toXml: Node = <string name={entry._1}>{entry._2}</string>
  }

  implicit def stringArray(entry: (String, Array[String])) = new Entry {
    override def toXml: Node = <string-array name={entry._1}>{
        for {
          itemValue <- entry._2
        } yield <item>{itemValue}</item>
      }</string-array>
  }

  implicit def bool(entry: (String, Boolean)) = new Entry {
    override def toXml: Node = <bool name={entry._1}>{entry._2}</bool>
  }

  implicit def integer(entry: (String, Int)) = new Entry {
    override def toXml: Node = <integer name={entry._1}>{entry._2}</integer>
  }

  implicit def integerArray(entry: (String, Array[Int])) = new Entry {
    override def toXml: Node = <integer name={entry._1}>{
        for {
          itemValue <- entry._2
        } yield <item>{itemValue}</item>
      }</integer>
  }


  trait Entry {
    def toXml: Node
  }

  type AndroidBuildInfo = Entry
}

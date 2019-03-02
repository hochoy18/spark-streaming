package com.hochoy.spark.utils

import java.sql.ResultSet

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 14:43
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object ClickhouseResultSetExt {

  implicit class ResultSetExt(rs: ResultSet) {

    def map[T](delegate: ResultSet ⇒ T): Seq[T] = {
      var results = List[T]()
      while (rs.next()) {
        results = delegate(rs) :: results
      }
      results
    }

    def toTab: Unit = {
      val header = getMeta.map(v ⇒ s"${v._2}").mkString("\t")
      val body = getData.map { row ⇒
        row.map(v ⇒ s"$v").mkString("\t")
      }.mkString("\n")

      val table = List(header, body).mkString("\n")
      println(s"%table $table")
    }

    def getData: List[Seq[AnyRef]] = {
      val meta = getMeta
      val result = collection.mutable.MutableList[Seq[AnyRef]]()
      while (rs.next()) {
        val row = meta map { i ⇒ rs.getObject(i._1) }
        result += row
      }
      result.toList
    }

    def getMeta: IndexedSeq[(Int, String, String)] = {
      1 to rs.getMetaData.getColumnCount map { i ⇒
        (i, rs.getMetaData.getColumnName(i), rs.getMetaData.getColumnTypeName(i))
      }
    }
  }

}

package com.hochoy.spark.hbase

import com.hochoy.spark.common.ScalaType.{HBaseKey, HBaseQualifierValue, HBaseQualifierValueLong}
import org.apache.hadoop.hbase.client.{Put, Table}
import org.apache.hadoop.hbase.util.Bytes

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 19:02
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object HBaseUtils {

  def putKV(table: Table, rfq: HBaseKey, value: String): Unit = {
    val put = new Put(Bytes.toBytes(rfq._1))
    put.addColumn(Bytes.toBytes(rfq._2), Bytes.toBytes(rfq._3), Bytes.toBytes(value))
    table.put(put)
  }

  def putKV(table: Table, rfq: HBaseKey, value: Long): Unit = {
    val put = new Put(Bytes.toBytes(rfq._1))
    put.addColumn(Bytes.toBytes(rfq._2), Bytes.toBytes(rfq._3), Bytes.toBytes(value))
    table.put(put)
  }

  def putQualifiersValues(table: Table, row: String, family: String, qvs: List[HBaseQualifierValue]): Unit = {
    val put = new Put(Bytes.toBytes(row))
    qvs.foreach(qv ⇒ put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qv._1), Bytes.toBytes(qv._2)))
    table.put(put)
  }

  def putQualifiersValuesLong(table: Table, row: String, family: String, qvs: List[HBaseQualifierValueLong]): Unit = {
    val put = new Put(Bytes.toBytes(row))
    qvs.foreach(qv ⇒ put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qv._1), Bytes.toBytes(qv._2)))
    table.put(put)
  }

  def main(args: Array[String]): Unit = {
    putQualifiersValues(null, "", "", List(("afdfad", "sss"), ("", "")))
  }
}

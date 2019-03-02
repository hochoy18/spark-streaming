package com.hochoy.spark.utils

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 14:40
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
case class ClusterResultSet(clusterRS: Seq[(String, java.sql.ResultSet)]) {

  import com.hochoy.spark.utils.ClickhouseResultSetExt._

  def get: Seq[(String, java.sql.ResultSet)] = clusterRS
  def toTab ={
    val firstRow = clusterRS.head
    val firstRowRs = firstRow._2

    val metaTab = if (firstRowRs != null){
      val meta = firstRowRs.getMeta
      ("host":: meta.map(x⇒s"${x._2}").toList).mkString("\t")
    }else{
      Seq("host","result").mkString("\t")
    }

    val bodyTab = clusterRS.map{ cur⇒
      val hostIp = cur._1
      if (cur._2 != null){
        val ds = cur._2.getData
        ds.map{row⇒
          (hostIp :: row.map{v⇒s"${v}"}.toList).mkString("\t")
        }.mkString("\n")
      }else {
        Seq(hostIp,"null").mkString("\t")
      }
    }.mkString("\n")

    val tab = List(metaTab,bodyTab).mkString("\n")
    println(s"%table  $tab" )
  }

}

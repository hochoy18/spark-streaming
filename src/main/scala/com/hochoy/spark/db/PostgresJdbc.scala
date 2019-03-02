package com.hochoy.spark.db

import java.sql.{Connection, DriverManager, ResultSet}

import scala.util.Try

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 18:57
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object PostgresJdbc {
  val conn_str = "jdbc:postgresql://192.168.1.209:5433/perf_action_db"
  val userName = "postgres"
  val pwd = "123456"

  //  classOf[org.postgresql.Driver]
  def main(args: Array[String]) {
    //classOf[org.postgresql.Driver]

    val conn = getConn(conn_str, userName, pwd)
    try {
      // Configure to be Read Only
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

      // Execute Query
      val start = System.currentTimeMillis()
      val rs = statement.executeQuery("select count(*) from perf_action_tab_1")
      val end = System.currentTimeMillis()
      println(end - start)
      val columnCount = rs.getMetaData().getColumnCount();
      // Iterate Over ResultSet
      while (rs.next) {
        for (i <- 1 to columnCount) {
          System.out.print(rs.getString(i) + "\t");
        }
        System.out.println();
      }
    } finally {
      conn.close
    }
  }

  def getConn(url: String, userName: String, pwd: String): Connection = {
    val conn = DriverManager.getConnection(url, userName, pwd)
    conn
  }
}

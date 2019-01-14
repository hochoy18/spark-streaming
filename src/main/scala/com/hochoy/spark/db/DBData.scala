package com.hochoy.spark.db

import java.sql.{Connection, DriverManager}

import org.apache.spark.SparkConf

import scala.util.Try

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 16:03
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
class DBData {
  case class DBConf(driver: String, url: String, user: String, password: String, dbPrefix: String = "hochoy_")

  def getDBConf(sparkConf: SparkConf): DBConf = {
    val driver = sparkConf.get("driver", "")
    val url = sparkConf.get("url")
    val user = sparkConf.get("username", "")
    val passwd = sparkConf.get("password", "")
    val dbPre = sparkConf.get("dbprefix", "hochoy_")
    DBConf(driver, url, user, passwd, dbPre)
  }

  def getDBConn(conf: DBConf): Try[Connection] = Try {
    Class.forName(conf.driver)
    DriverManager.getConnection(conf.url, conf.user, conf.password)
  }

}

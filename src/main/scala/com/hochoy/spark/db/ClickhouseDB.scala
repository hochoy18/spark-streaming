package com.hochoy.spark.db

import java.sql.Connection

import org.apache.spark.sql.SparkSession
/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 11:40
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object ClickhouseDB {

//  def apply(master:String, dbUrl:String, driver:String ): ClickhouseDB = {
//    val db = new ClickhouseDB()
//
//  }
  val sparksession = SparkSession.builder()
    .master("")
  

}
class ClickhouseDB(){

//  def getCHConn():Connection ={
//
//  }

}
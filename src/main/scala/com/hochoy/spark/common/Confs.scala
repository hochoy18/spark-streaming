package com.hochoy.spark.common

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 15:45
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object Confs {

  val hadoopConf = HBaseConfiguration.create()

  def setConf(conf:Configuration): Unit ={
    hadoopConf.addResource(conf)
  }
}

package com.hochoy.spark.utils

import java.util.Properties

import ru.yandex.clickhouse.ClickHouseDataSource
import ru.yandex.clickhouse.settings.ClickHouseProperties


/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 14:19
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object ClickhouseConnectionFactory {

  private val dataSource = collection.mutable.Map[(String, Int), ClickHouseDataSource]()

  def get(host: String, port: Int = 8213): ClickHouseDataSource = {
    dataSource.get((host, port)) match {
      case Some(ds) ⇒ ds
      case None ⇒
        val ds = createDataSource(host, port = port)
        dataSource += ((host,port) → ds)
        ds

    }
  }

  private def createDataSource(host: String, dbO: Option[String] = None, port: Int = 8123): ClickHouseDataSource = {
    val url = s"jdbc:clickhouse://$host:$port"
    val props = new Properties()
    dbO map (db ⇒ props.setProperty("database", db))
    val clickHouseProperties = new ClickHouseProperties(props)
    val ds = new ClickHouseDataSource(url, clickHouseProperties);
    ds
  }

}

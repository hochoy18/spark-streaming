package com.hochoy.spark.common

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.hadoop.hbase.exceptions.IllegalArgumentIOException
import org.joda.time.{DateTime, Hours}
import org.joda.time.format.DateTimeFormat

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 17:00
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object Utils {


  /**
    * Get the number of hours from the start time to the specified time
    *
    * @param time
    * @param format
    * @return
    */
  def getHourNum(time: String, format: String = Constants.DEFAULT_TIME_FORMAT): Int = {
    val start: DateTime = Constants.START_DATE_TIME
    val end: DateTime = DateTimeFormat.forPattern(Constants.DEFAULT_TIME_FORMAT).parseDateTime(time)
    Hours.hoursBetween(start, end).getHours
  }

  def dateIsValid(date: String): Boolean = (try {
    val pdt = DateTimeFormat.forPattern(Constants.DEFAULT_TIME_FORMAT)
    Some(pdt.parseDateTime(date.substring(0, 19)))
  } catch {
    case e: IllegalArgumentIOException ⇒
      println(e)
      None
    case e1: Exception ⇒
      println(e1)
      None
  }).nonEmpty

  def getNowTime :String={
    val now =new Date
    val date = new SimpleDateFormat(Constants.DEFAULT_TIME_FORMAT).format(now)
    date
  }

}

package com.hochoy.spark.common

import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.write
/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 10:15
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object JSONUtil {
  def usinglogToListMap(j: String): List[Map[String, String]] = {
    (parse(j) \ "data").children.map(dvalue ⇒{
      val appkey :String = (dvalue \"appkey").values.toString
      val devicename:String = (dvalue \ "devicename").values.toString
      val version :String = (dvalue \ "version").values.toString

      val activities:String = (dvalue \"activities").values.toString
      val sd:String = (dvalue \ "sd").values.toString
      val duration:String = (dvalue \ "duration").values.toString
      val acList = activities.split("|")
      val sdList = sd.split("|")




    })


    null
  }

}

package com.hochoy.spark

import com.hochoy.spark.UserRepository.{findById, findFNameMatch}
import org.scalatest.FunSuite
import com.hochoy.spark.hadoop._

import scala.util.{Success, Try}


/** Describe:https://windor.gitbooks.io/beginners-guide-to-scala/content/chp5-the-option-type.html
  * Created by IntelliJ IDEA.
  * Time: 16:34
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
class BasicScala extends FunSuite {

  test("applydemo") {
    val app = applyDemo.apply("key1111", "value2221", 2)
    println(s"key: ${app.key}  , value:  ${app.value},  ${app}")

    val app2 = applyDemo("123", "12345")
    println(app2.key, app2.value)
  }

  test("ip location") {
  }

  test("option test for"){
    val map = Map("France"→"Paris","Japan"→"Tokyo","China"→"Beijing")
    for(x← map){
      println(x._1  ,  x._2)
    }
  }

  test("option test") {
    val capitals = Map("France"→"Paris","Japan"→"Tokyo","China"→"Beijing")
    val cap = capitals get "France" orElse Option("Nanjing")
    val c = cap match {
      case Some(cap) ⇒ cap
      case None ⇒ "Beijing"
    }
    println(cap)
    println(c)
  }

  test(s"User for option test") {
    val user = User(99999, "Johanna", "Doe", 30, true)
    println(Try(10 / 0))
    println("....  ",findFNameMatch(2))


    println(findById(1).getOrElse(user))
    println(findById(1).isDefined)
    println(findById(2).getOrElse(user))
    println(findById(3).getOrElse(user))
    println(findById(3).isDefined)
    println(findById(1).nonEmpty)
  }
  test("json test "){
    import org.json4s.jackson.JsonMethods._
    val s1 = """{"appkey": "df5bb2206def11e8a8e044a8422584eb", "devicename": "iPhone", "activities": "AboutviewController|LoginviewController|ChatviewController|BlogviewControllerGroup|FileviewController|CameraviewController|ChatviewController|RegistviewController|FileviewController|CameraviewController|MainviewController|MainviewController|ChatviewController", "start_millis": "2018-10-11 15:10:36", "session_id": "06db512c343e43c89336d617cecc975f", "version": "2.2", "deviceid": "AA-BB-CC:DD:EE:FF-615-232", "duration": "574000", "end_millis": "2018-10-11 15:20:10", "sd": "64000|52000|41000|7000|47000|34000|66000|18000|63000|21000|59000|47000|55000"}"""
    val v = parse(s1).merge(parse("""{"nihao":"hello"}"""))
    println(v)
    println(v.values)

  }
}

case class User(val id: Int, val fName: String,val lName: String, val age: Int, val gender: Boolean)

object UserRepository {
  val users = Map(1 → User(1, "1", "1", 1, true), 2 → User(2, "2", "2", 2, false))

  def findById(id: Int): Option[User] = {
    users.get(id)
  }
  def findFNameMatch(id:Int):User={
    val v = users.get(id)
    v  match {
      case Some(v) ⇒ v
      case None ⇒ null
    }
  }


}
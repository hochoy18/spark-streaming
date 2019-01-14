package com.hochoy.spark

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


  test("option test") {

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

  def main(args: Array[String]): Unit = {
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
}
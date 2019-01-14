package com.hochoy.spark

import org.scalatest.FunSuite
import com.hochoy.spark.hadoop._

/** Describe:
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
    println(app2.key,app2.value)
  }

  test("ip location") {
  }

}

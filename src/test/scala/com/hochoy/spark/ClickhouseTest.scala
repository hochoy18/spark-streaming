package com.hochoy.spark

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 15:13
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */

import java.io.PrintWriter

import com.hochoy.spark.utils.ClickHouseUtil
import org.scalatest.{FlatSpec, FunSuite, Matchers}

class ClickhouseTest extends FlatSpec with Matchers  {

 "case 1" should "ok" in{
   var f = false
   case class Mock(){
     def print():Unit={
       println("mock unit")
     }
     def close():Unit ={
       f =true
     }
   }
   ClickHouseUtil.using(Mock()){
     mock⇒mock.print()
   }
   assert(f.equals(true))
 }



//  test("ClickhouseUtil using function") {
//    ClickHouseUtil.using(new PrintWriter("sample.txt")) { out ⇒
//      out.println("hello world")
//    }
//    //    ClickHouseUtil.using((new BufferedReader(new FileReader("in.txt")), new PrintWriter("out.txt"))){
//    //      (in, out) =>
//    //      out.println(in.readLIne)
//    //    }
//  }

}

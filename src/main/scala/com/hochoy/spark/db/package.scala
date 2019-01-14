package com.hochoy.spark


package object db {

  val pack = "..............   "

  def packageTest(v: String = "fad") {
    println(v)
  }


}
package people {

  object mainO {
    def main(args: Array[String]): Unit = {
      db.packageTest(db.pack)
    }
  }

}

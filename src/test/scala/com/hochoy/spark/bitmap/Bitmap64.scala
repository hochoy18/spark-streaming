package com.hochoy.spark.bitmap

import org.roaringbitmap.longlong._

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 16:17
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object Bitmap64 {

  def main(args: Array[String]): Unit = {
    val r = Roaring64NavigableMap.bitmapOf(1, 2, 100, 300)
    r.addLong(1234)
    println(r.contains(1))
    println(r.contains(5))
    val i = r.getLongIterator
    while (i.hasNext) {
      println(i.next())
    }
  }

}

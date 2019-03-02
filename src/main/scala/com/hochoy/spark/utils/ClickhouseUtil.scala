package com.hochoy.spark.utils

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 11:59
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object ClickHouseUtil {

  def using[A, B <: {def close() : Unit}](closeable: B)(f: B ⇒ A):A={
    try {
      f(closeable)
    }finally {
      closeable.close()
    }
  }

}

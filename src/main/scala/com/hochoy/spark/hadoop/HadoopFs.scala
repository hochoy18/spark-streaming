package com.hochoy.spark.hadoop

import com.hochoy.spark.common.Confs
import org.apache.hadoop.fs.FileSystem

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 16:23
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object HadoopFs {

  sys.addShutdownHook(close())

  private lazy val fs = FileSystem.get(Confs.hadoopConf)

  /**
    * apply 方法通常称为注入方法，在伴生对象里做一些初始化的操作
    * apply 方法的参数列表不需要和构造器的参数列表一致
    * apply 方法 会被隐式调用
    *
    * @return
    */
  def apply: FileSystem = fs

  def close(): Unit = fs.close()
}

class applyDemo(val key: String, val value: String) {}

object applyDemo {
  var face: Int = _

  def apply(key: String, value: String, otherParam: Int): applyDemo = {
    face = otherParam
    new applyDemo(key, value)
  }

  def apply(key: String, value: String): applyDemo = new applyDemo(key, value)
}
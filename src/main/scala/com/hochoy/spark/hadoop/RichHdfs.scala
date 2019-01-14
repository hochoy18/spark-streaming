package com.hochoy.spark.hadoop


import java.nio.ByteBuffer

import org.apache.hadoop.fs.{FileSystem, Path}

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 16:58
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
trait RichHdfs {

  implicit class RichFS(fs: FileSystem) {
    def readAllBytes(fileName: String): Array[Byte] = {
      val path = new Path(fileName)
      val in = fs.open(path)
      val byteBuf = ByteBuffer.allocate(fs.getFileStatus(new Path(fileName)).getLen.toInt)
      var readBytesLength: Int = 0
      val chunk: Array[Byte] = new Array[Byte](4096)
      while (in.available() > 0) {
        readBytesLength = in.read(chunk)
        byteBuf.put(chunk, 0, readBytesLength)
      }
      in.close()
      byteBuf.array()
    }
  }

}

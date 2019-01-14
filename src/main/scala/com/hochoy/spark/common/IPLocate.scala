package com.hochoy.spark.common

import java.nio.charset.Charset

import org.apache.hadoop.fs.FileSystem

/**
 * Find the location of IP address with 17monipdb.<p>
 *
 * 1. file structure<p>
 * -----<p>
 * | 4 |													data offset(big endian)<p>
 * -----------<p>
 * | 1024		| 									first(head) index, 4 * 256<p>
 * ----------------------<p>
 * | offset - 1024 - 4  |				ip index, 8 byte each index block<p>
 * ----------------------<p>
 * | 1028 |											empty ???? why<p>
 * |------------------------<p>
 * | file_size - offset    |			ip info, data block<p>
 * -------------------------<p>
 * <p>
 * 2. ip index<p>
 * --------------<p>
 * |     4      |								ip int(big endian)<p>
 * --------------<p>
 * |   3     |										data pos(offset) (little endian)<p>
 * -----------<p>
 * | 1 |													ip detail len<p>
 * ----
 *
 * @author jianghe.cao
 */
class IPLocate(data: Array[Byte]) extends Serializable {

  protected case class IpIndex(ip: Long, offset: Int, len: Int)

  var fs: FileSystem = _

  private val indexLength = readLong(data, 0).toInt

  private val firstIndexes: Array[Int] = (for (i <- 0 until 256)
    yield readLong(data, 4 + i * 4, bigEndian = false).toInt).toArray

  private val ipIndexes: Array[IpIndex] = {
    val offset = 1024 + 4
    (for (i <- 0 until (indexLength - offset - 1024) / 8) yield {
      val ip = readLong(data, offset + i * 8)
      val posAndLen = readLong(data, offset + i * 8 + 4, bigEndian = false)
      IpIndex(ip, (posAndLen & 0xFFFFFF).toInt, (posAndLen >>> 24).toInt)
    }).toArray
  }

  def locate(ip: String): IPLoc = {
    val regex = ("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$").r
    ip match {
      case regex(part1, part2, part3, part4) =>
        val ipValue =
          (part1.toLong << 24) + (part2.toLong << 16) + (part3.toLong << 8) + part4.toLong

        var pos = firstIndexes((ipValue >>> 24).toInt)
        var found: IpIndex = null
        while (pos < ipIndexes.length && found == null) {
          val idx = ipIndexes(pos)

          if (idx.ip >= ipValue)
            found = idx

          pos += 1
        }

        if (found != null) readIp(found).split("\t") match {
          case Array(country, region, city) => IPLoc(
            country = country,
            region = region,
            city = city
          )
          case Array(country, region) => IPLoc(
            country = country,
            region = region
          )
          case _ => IPLoc()
        }
        else IPLoc()
      case _ => IPLoc()
    }
  }

  @inline protected def readIp(index: IpIndex): String = new String(data, index.offset + indexLength - 1024,
    index.len, Charset.forName("UTF-8"))

  protected def readLong(bytes: Array[Byte], idx: Int, bigEndian: Boolean = true): Long = {
    val range = if (bigEndian) Seq(0, 1, 2, 3) else Seq(3, 2, 1, 0)

    (0L /: range) { (l, i) =>
      (l << 8) | (bytes(idx + i) & 0xFF)
    }
  }

  @inline protected def ipString(ip: Long) =
    s"${(ip >>> 24) & 0xFF}.${(ip >>> 16) & 0xFF}.${(ip >>> 8) & 0xFF}.${ip & 0xFF}"
}

object IPLocate {

  def apply(data: Array[Byte]): IPLocate = new IPLocate(data)

}

case class IPLoc(country: String = Constants.UNKNOWN,
                 region: String = Constants.UNKNOWN,
                 city: String = Constants.UNKNOWN)

package com.hochoy.spark.common

/** Describe:
  * Created by IntelliJ IDEA.
  * Time: 15:41
  *
  * @author hochoy <hochoy18@sina.com> 
  * @Version V1.0.0
  */
object ScalaType {
  /**
    * (rowkey,family,qualifier)
    */
  type HBaseKey = (String,String,String)

  /**
    * (row,family,qualifier,timestamp)
    */
  type HBaseKeyWithTime = (String,String,String,Long)

  /**
    * (table,row,family,qualifier,timestamp)
    */
  type HBaseKeyWithTab = (String,String,String,String)

  /**
    * (qualifier, value)
    */
  type HBaseQualifierValue = (String,String)
  /**
    * (qualifier,values)
    */
  type HBaseQualifierValueLong = (String,Long)


}

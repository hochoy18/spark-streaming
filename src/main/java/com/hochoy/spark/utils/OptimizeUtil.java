package com.hochoy.spark.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Describe:
 *
 * @author hochoy <hochoy18@sina.com>
 * @version V1.0.0
 * @date 2019/1/10
 */
public class OptimizeUtil {

    private static Logger logger = LoggerFactory.getLogger(OptimizeUtil.class);

    private OptimizeUtil() {
    }

    public static String md5Hashing(MessageDigest md,String text){
        byte[] secretBytes = null;
        MessageDigest md5 = md;
        if (md5 != null){
            md5.reset();
        }else {
            try {
                //生成一个MD5 加密计算摘要
                md5= MessageDigest.getInstance("MD5");
            }catch (NoSuchAlgorithmException e){
                logger.error("没有md5这个算法！Exception: ",e);
            }
        }
        // 对字符串 text 进行加密
        md5.update(text.getBytes());
        //获得加密后的数据
        secretBytes = md5.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < secretBytes.length; i++) {
            sb.append(Integer.toString((secretBytes[i] & 0xff)+ 0x100 ,16)).substring(1);

        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception{
        String s = md5Hashing(null,"1234");
        System.out.println(s);
        MessageDigest md = MessageDigest.getInstance("MD5");
        s = md5Hashing(null,"1234");
        System.out.println(s);
        md = MessageDigest.getInstance("MD5");
        s = md5Hashing(md,"1234");
        System.out.println(s);

    }
}

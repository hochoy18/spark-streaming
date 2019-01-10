package com.hochoy.spark.common;

import com.hochoy.spark.utils.OptimizeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * Describe:
 *
 * @author hochoy <hochoy18@sina.com>
 * @version V1.0.0
 * @date 2019/1/10
 */
public class Formatter {
    private static Logger logger = LoggerFactory.getLogger(Formatter.class);
    public static String genPrefix(MessageDigest md, String key, int length) {
        String rowKey = null;
        String md5Key;
        try {
            md5Key = OptimizeUtil.md5Hashing(md, key);
            rowKey = md5Key.substring(0, length - 1) + "_" + key;
        } catch (Exception e) {
            logger.error("Exception: " , e);
        }
        return rowKey;
    }
}

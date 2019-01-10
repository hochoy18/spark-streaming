package com.hochoy.spark.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Describe:
 *
 * @author hochoy <hochoy18@sina.com>
 * @version V1.0.0
 * @date 2019/1/10
 */
public class KerberosUtil {
    static final String AUTHENTICATION = "hadoop.security.authentication";
    static final String AUTHENTICATION_METHOD = "kerberos";
    static Logger logger = LoggerFactory.getLogger(KerberosUtil.class);

    private KerberosUtil() {
    }

    public static boolean kinit(Configuration conf, String userName, String keyTab) {
        conf.set(AUTHENTICATION, AUTHENTICATION_METHOD);
        UserGroupInformation.setConfiguration(conf);
        try {
            UserGroupInformation.loginUserFromKeytab(userName, keyTab);
            logger.info("Connected to hbase with kerberos : ", userName, ",", keyTab);
            return true;
        } catch (IOException e) {
            logger.error("kerberos init error ", e);
        }
        return false;
    }
    public static boolean kinit(Configuration conf){
        String kerberosEnabled = conf.get("kerberos.enable");
        if (kerberosEnabled == null || "false".equals(kerberosEnabled)){
            return true;
        }else {
            String name = conf.get("kerberos.username");
            String keytab = conf.get("kerberos.keytab");
            return kinit(conf,name,keytab);
        }
    }
}

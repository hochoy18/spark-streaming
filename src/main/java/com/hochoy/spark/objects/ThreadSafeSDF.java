package com.hochoy.spark.objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Describe:
 *
 * @author hochoy <hochoy18@sina.com>
 * @version V1.0.0
 * @date 2019/1/10
 */
public class ThreadSafeSDF {
    Logger logger = LoggerFactory.getLogger(ThreadSafeSDF.class);

    private final DateFormat df;

    public ThreadSafeSDF(String format) {
        this.df = new SimpleDateFormat(format);
    }

    public synchronized String format(Date date) {
        return df.format(date);
    }

    public synchronized Date parse(String dateString) {
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            logger.error("ParseException: ", e);
        }
        return null;
    }
}

package com.legacy.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class Utils {

    private static final Logger LOGGER = LogManager.getLogger();

    private static Properties properties;
    private static Date date;

    private Utils() {}

    public static Properties getProperties() {
        return properties;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void setProperties(Properties p) {
        properties = p;
    }

    private static Date getDate() {
        return date;
    }

    private static void setDate(Date d) {
        date = d;
    }

    public static String getFormattedDate() {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(ZonedDateTime.of(Constants.DATE_TIME, ZoneId.of("UTC-8")));
    }

    public static int rand(int min, int max) {
        return (int) rand((double) min, max);
    }

    public static double rand(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public static String capitalize(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }

//    public static String displayMoney(Money money) {
//        return money.toString();
//    }

    public static Properties loadProperties(String fileName) throws IOException {
        LOGGER.debug("Loading properties from file: " + fileName);
        Properties properties = new Properties();
        try (InputStream input = Utils.class.getClassLoader().getResourceAsStream(fileName)) {
            assert input != null;
            properties.load(input);
        }
        return properties;
    }
}

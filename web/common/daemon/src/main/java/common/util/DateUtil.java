package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by zhaoxuliang on 16/1/15.
 */
public class DateUtil {
    public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DAY_FORMAT = "yyyy-MM-dd";
    public final static String SLASH_DATE_FORMAT = "yyyy/MM/dd";

    public final static long SECOND_OF_ONE_DAY = 86400000;

    public static String getBeforeDay(String format) {
        return date2String(getBeforeDay(), format);
    }

    public static Date getBeforeDay() {
        Date date = new Date();
        return new Date(date.getTime() - (SECOND_OF_ONE_DAY));
    }

    public static String date2String(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (format == null || format.isEmpty()) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String date2String(Date date) {
        return date2String(date, DEFAULT_FORMAT);
    }

    public static Date string2Date(String dateStr, String format) throws ParseException {
        if (dateStr == null) {
            return null;
        }
        if (format == null || format.isEmpty()) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }

    public static Date string2Date(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        if (dateStr.indexOf("-") > -1) {
            Calendar cal = Calendar.getInstance();
            String[] orderDateStrs = dateStr.split("-");
            if (orderDateStrs.length == 3) {
                String yyyy = orderDateStrs[0];
                String MM = orderDateStrs[1];
                String dd = orderDateStrs[2];
                if (yyyy.length() < 4) {
                    yyyy = String.valueOf(cal.get(cal.YEAR)).substring(0, yyyy.length()) + yyyy;
                }
                if (MM.length() < 2) {
                    MM = String.valueOf(cal.get(cal.MONTH)).substring(0, MM.length()) + MM;
                }
                if (dd.length() < 2) {
                    dd = String.valueOf(cal.get(cal.DAY_OF_MONTH)).substring(0, dd.length()) + dd;
                }
                dateStr = yyyy + "-" + MM + "-" + dd;
                if (dateStr.indexOf(":") > -1) {
                    String[] orderTimeStrs = dateStr.split("-");
                    if (orderTimeStrs.length == 3) {
                        return string2Date(dateStr, DEFAULT_FORMAT);
                    } else {
                        return null;
                    }
                }
                return string2Date(dateStr, DAY_FORMAT);
            } else {
                return null;
            }

        }
        if (dateStr.indexOf("/") > -1) {
            return string2Date(dateStr, SLASH_DATE_FORMAT);
        }
        return null;
    }

    public static Date getTommorrow() {
        Date date = new Date();
        return getTommorrow(date);
    }

    public static Date getTommorrow(Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime() + (SECOND_OF_ONE_DAY));
    }

    /**
     * 以23:59:59.999 填充时间格式的 HH:mm:ss.SSS
     *
     * @param date
     * @return
     */
    public static Date completeDateTimeEnd(Date date){
        if(date==null){
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
        Pattern pattern = Pattern.compile("^[0]{2}(:[0]{2}){2}\\.[0]{3}$");
        if(!pattern.matcher(df.format(date)).matches()){
            return date;
        }
        long dateTime = date.getTime();
        //86399999 = 24*60*60*1000-1
        Date result = new Date(dateTime + 86399999);
        return result;
    }

}

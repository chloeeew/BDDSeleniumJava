package com.framework.utils;

import org.apache.log4j.Logger;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class DataUtils {
    static Logger logger = Logger.getLogger(DataUtils.class);

    public static String format(Date date,String format){
        String result="";
        try{
            if(date!=null){
                DateFormat dateFormat = new SimpleDateFormat(format);
                result = dateFormat.format(date);
            }
        }catch(Exception e){
            logger.error("fail to format date with format:"+format);
            logger.error(e);
            throw e;
        }
        return result;
    }

    public static String getDateString(Date date){
        return String.valueOf(getYear(date))+String.valueOf(getMonth(date))+String.valueOf(getDay(date));
    }
    public static String getTimeString(Date date){
        return String.valueOf(getHour(date))+String.valueOf(getMinute(date))+String.valueOf(getSecond(date));
    }
    public static int getYear(Date date){
        return setTimeCalendar(date).get(Calendar.YEAR);
    }

    public static int getMonth(Date date){
        return setTimeCalendar(date).get(Calendar.MONTH)+1;
    }

    public static int getDay(Date date){
        return setTimeCalendar(date).get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Date date){
        return setTimeCalendar(date).get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date){
        return setTimeCalendar(date).get(Calendar.MINUTE);
    }

    public static int getSecond(Date date){
        return setTimeCalendar(date).get(Calendar.SECOND);
    }

    private static Calendar setTimeCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}

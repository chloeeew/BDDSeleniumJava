package com.framework.utils;

import org.apache.log4j.Logger;

public class DataUtil {
    static Logger logger = Logger.getLogger(DataUtil.class);
    static String baseCharacter = "abcdefghijklmnopqrstuvwxyz0123456789";
    static String baseNameCharacter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String[] email_suffix="@gmail.com,@yahoo.com,@msn.com,@hotmail.com,,@qq.com".split(",");

    public static int getNum(int start,int end){
        if((end-start)<0){
            logger.error("end num cannot smaller than start num in getNum method");
            return 0;
        }
        return (int)(Math.random()*(end-start+1)+start);
    }

    public static String getRandomEmail(int min,int max){
        int length = getNum(min,max);
        StringBuilder buffer = new StringBuilder();
        for(int i=0;i<length;i++){
            int num = (int)(Math.random()*baseCharacter.length());
            buffer.append(baseCharacter.charAt(num));
        }
        buffer.append(email_suffix[(int)(Math.random()*email_suffix.length)]);
        return buffer.toString();
    }

    public static String getRandomString(int min,int max){
        int length = getNum(min,max);
        StringBuilder buffer = new StringBuilder();
        for(int i=0;i<length;i++){
            int num = (int)(Math.random() *baseNameCharacter.length());
            buffer.append(baseNameCharacter.charAt(num));
        }
        return buffer.toString();
    }
}

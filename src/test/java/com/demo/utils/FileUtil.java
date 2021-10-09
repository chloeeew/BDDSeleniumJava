package com.demo.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class);


    public static boolean createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if(file.exists()){
            logger.info("file ["+fileName+"] already exist!");
            return false;
        }
        if(fileName.endsWith(File.separator)){
            logger.info("["+fileName+"] cannot be directory name");
            return false;
        }
        if(!file.getParentFile().exists()){
            logger.info("parent file doesn't exist, ready to create");
            if(!file.getParentFile().mkdir()){
                logger.info("fail to create parent file");
                return false;
            }
        }
        try {
            if(file.createNewFile()){
                logger.info("create file ["+fileName+"] successfully");
                return true;
            }else {
                logger.warn("fail to create file ["+fileName+"]");
                return false;
            }
        }catch (IOException ioException){
            logger.error("fail to create file ["+fileName+"] by error ");
            logger.error(ioException);
            throw ioException;
        }
    }


    public static void createDir(String dirName){
        File dir = new File(dirName);
        if(dir.exists()){
            logger.info("directory ["+dirName+"] already exists");
        }else {
            if (dir.mkdir()) {
                logger.info("create directory [" + dirName + "] successfully");
            } else {
                logger.warn("fail to create directory [" + dirName + "]");
            }
        }
    }
}

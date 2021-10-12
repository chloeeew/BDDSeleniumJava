package com.framework.utils;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    static Logger logger = Logger.getLogger(ExcelUtil.class);
    public static List<Map<String,String>> extractDataToStringFromSheet(String filepath, String sheetName) throws Exception{
        Workbook workbook = getWorkbook(filepath);
        Sheet sheet = workbook.getSheet(sheetName);
        logger.info("get data from sheet "+sheetName);
        int numRows = sheet.getLastRowNum()+1;
        int numColumns = sheet.getRow(0).getLastCellNum();
        List<Map<String,String>> dataList = new ArrayList<>(numRows);

        for(int i=1;i<numRows;i++){
            Map<String,String> map = new HashMap<>(numColumns);
            for(int j=0;j<numColumns;j++){
                Cell keyCell = sheet.getRow(0).getCell(j);
                String key="";
                if(keyCell.getCellTypeEnum()==CellType.NUMERIC){
                    key = NumberToTextConverter.toText(keyCell.getNumericCellValue());
                }else{
                    key = keyCell.getStringCellValue();
                }

                Cell valueCell = sheet.getRow(i).getCell(j);
                String value="";
                if(valueCell.getCellTypeEnum()==CellType.NUMERIC){
                    value = NumberToTextConverter.toText(valueCell.getNumericCellValue());
                }else {
                    value = valueCell.getStringCellValue();
                }
                map.put(key,value);
            }
            dataList.add(i-1,map);
        }
        return dataList;
    }

    private static Workbook getWorkbook(String filePath) throws Exception {
        FileInputStream fis;
        Workbook workbook;
        try {
            fis = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fis);
        }catch (InvalidFormatException invalidFormatException){
            logger.error("fail to create workbook");
            logger.error(invalidFormatException);
            throw invalidFormatException;
        }catch (IOException fileNotFoundException){
            logger.error("cannot find file in ["+ filePath+"]");
            logger.error(fileNotFoundException);
            throw fileNotFoundException;
        }
        logger.info("get workbook from ["+filePath+"]");
        fis.close();
        return workbook;
    }


//    public static void main(String[] args) throws Exception {
//        // for test
//        List<Map<String,String>> results = extractDataToStringFromSheet(Constants.AUTHORIZE_EXCEL_FILE_PATH,
//                "Login");
//        System.out.println(results);
//        for (Map<String, String> map : results) {
//            System.out.println(map);
//        }
//
//    }

}

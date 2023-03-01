package com.bms.bms.helper;

import com.bms.bms.entity.FlatNumber;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }

    //convert excel to list of products

    public static List<FlatNumber> convertExcelToListOfProduct(InputStream is) {
        List<FlatNumber> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            Iterator<Row> iterator = sheet.iterator();
            List<Long> numbers = new ArrayList<>();

            iterator.next();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Long num = (long) nextRow.getCell(4).getNumericCellValue();
                if(numbers.contains(num)){
                    throw new Exception("Duplicate Number Found, "+num);
                }else{
                    numbers.add(num);
                    FlatNumber flat = new FlatNumber();
                    flat.setRegstriationNo((int) nextRow.getCell(0).getNumericCellValue());
                    flat.setFirstname(nextRow.getCell(1).getStringCellValue());
                    flat.setLastName(nextRow.getCell(2).getStringCellValue());
                    flat.setEmail(nextRow.getCell(3).getStringCellValue());
                    flat.setMobNo((long) nextRow.getCell(4).getNumericCellValue());
                    flat.setPwd(nextRow.getCell(5).getStringCellValue());
                    list.add(flat);
                }
            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}

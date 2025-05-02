package com.xasale.excel2JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class ExcelProcessingHelper {
    public static String convertExcelRows(MultipartFile file) {

        // convert multipart file into excel workbook object
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            System.out.println("Το όνομα του αρχείου είναι: " + file.getName());

            // get current active sheet number
            // int sheetIndex = workbook.getActiveSheetIndex();

            //get sheet
            XSSFSheet sheet = workbook.getSheetAt(1);

            System.out.println("Το current active sheet number είναι: " + sheet);

            // using for loop

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();

            System.out.println("rows: " + (rows+1));
            System.out.println("cols: " + cols);

            ArrayList<String> data = new ArrayList<>();

            for (int r = 0; r <= rows; r++) {
                XSSFRow row = sheet.getRow(r);
                for (int c = 0; c <= cols; c++) {
                    XSSFCell cell = row.getCell(c);
                    if (cell != null) {
                        data.add(cell.toString());
                    }

                    System.out.print(cell + " ");

                }
                System.out.println();

            }

            for (int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i));
            }

            //βάζω τα static data
            Map<String, Object> xartis = new LinkedHashMap<>();
            xartis.put("agent", 1000);
            xartis.put("user", "USER");
            xartis.put("password", "123456789");


            //προσθέτω τα κελιά του excel
            String val = "tickets";
            xartis.put(val, data.toString().replace("[","").replace("]","").replace(" ", ""));


            //μετατρέπω το αρχείο σε JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(xartis);

            System.out.println(json);


            return json;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

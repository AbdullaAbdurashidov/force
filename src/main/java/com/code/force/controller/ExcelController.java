package com.code.force.controller;

import com.code.force.domain.Users;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelController {
    public static String TYPE="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = { "userID", "firstName", "lastName", "userName","password","role","blocked"};
    static String SHEET="Users";

    public static ByteArrayInputStream userToExcel(List<Users> users) {
        try(Workbook workbook=new XSSFWorkbook(); ByteArrayOutputStream out=new ByteArrayOutputStream();){
            Sheet sheet=workbook.createSheet(SHEET);

            //Header
            Row headerRow=sheet.createRow(0);

            for(int col=0;col<HEADERS.length;col++){
                Cell cell= headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }

            int rowIndex=1;
            for(Users user:users){
                Row row=sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(user.getUserID());
                row.createCell(1).setCellValue(user.getFirstName());
                row.createCell(2).setCellValue(user.getLastName());
                row.createCell(3).setCellValue(user.getUserName());
                row.createCell(4).setCellValue(user.getPassword());
                row.createCell(5).setCellValue(user.getRole());
                row.createCell(6).setCellValue(user.getBlocked());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }catch(IOException e){
            System.out.println(e);
            throw new RuntimeException("Error occurred");
        }

    }


}

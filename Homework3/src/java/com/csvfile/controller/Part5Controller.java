/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csvfile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dedhi
 */
public class Part5Controller extends HttpServlet{
    
//    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
//        String excelFilePath = "C:\\Users\\dedhi\\work\\WEB_DEV\\Homework2\\files\\store.xls";
//        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
//        Workbook workbook = new HSSFWorkbook(inputStream);
//        int i = 0;
//        while(i < 3){
//            Sheet sheet = workbook.getSheetAt(i);
//            String sheetName = sheet.getSheetName();
//            Iterator<Row> iterator = sheet.iterator();
//            while (iterator.hasNext()) {
//            Row nextRow = iterator.next();
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING:
//                        out.println("<td>"+cell.getStringCellValue()+"</td>");
//                        break;
//                    case Cell.CELL_TYPE_BOOLEAN:
//                        out.println("<td>"+cell.getBooleanCellValue()+"</td>");
//                        break;
//                    case Cell.CELL_TYPE_NUMERIC:
//                        out.println("<td>"+cell.getNumericCellValue()+"</td>");
//                        break;
//                }
//            }
//        i++;
//        }
//        inputStream.close();
//    }
}

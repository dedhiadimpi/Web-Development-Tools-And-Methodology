<%-- 
    Document   : newjsppart9
    Created on : Feb 4, 2020, 9:57:46 PM
    Author     : dedhi
--%>

<%@page import="org.apache.poi.hssf.usermodel.HSSFWorkbook"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.poi.ss.usermodel.Cell"%>
<%@page import="org.apache.poi.ss.usermodel.Row"%>
<%@page import="org.apache.poi.ss.usermodel.Sheet"%>
<%@page import="org.apache.poi.ss.usermodel.Workbook"%>
<%@page import="org.apache.poi.xssf.usermodel.XSSFWorkbook"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>table,th,td{border: 1px solid black} th{text-align:left} th,td{padding: 5px}</style>
    </head>
    <body>
        <h3>Excel data</h3>
        <%
        String excelFilePath = "C:\\Users\\dedhi\\work\\WEB_DEV\\Homework2\\files\\store.xls";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new HSSFWorkbook(inputStream);
        int i = 0;
        while(i < 3){
            Sheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            Iterator<Row> iterator = sheet.iterator();
            out.println("<h4>Sheet Name: "+sheetName+"</h4>");
            out.println("<table>");
            while (iterator.hasNext()) {
            %>
            <tr>
                <%
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        out.println("<td>"+cell.getStringCellValue()+"</td>");
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        out.println("<td>"+cell.getBooleanCellValue()+"</td>");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        out.println("<td>"+cell.getNumericCellValue()+"</td>");
                        break;
                }
            }
            System.out.println();
            %>
            </tr>
        <%}
        out.println("<table>");
        i++;

        }
        inputStream.close();
        %>
        
        </body>
</html>

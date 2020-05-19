<%-- 
    Document   : part5View
    Created on : Feb 13, 2020, 5:44:24 PM
    Author     : dedhi
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.poi.ss.usermodel.Cell"%>
<%@page import="org.apache.poi.ss.usermodel.Row"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
        <%
            for(Iterator iterator : (ArrayList<Iterator>)request.getAttribute("iteratorList")){
                while (iterator.hasNext()) {
                Row nextRow = (Row)iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                out.println("<tr>");
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
                out.println("</tr>");
                }
            }
       %>
        </table>
    </body>
</html>

<%-- 
    Document   : part4-view-page
    Created on : Feb 13, 2020, 5:00:50 PM
    Author     : dedhi
--%>

<%@page import="com.csvfile.model.Part4"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.csvfile.controller.Part4Controller"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table,th,td{
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
        <table>
            <tr>
                <th>SalesOrderID</th>
                <th>RevisionNumber</th>
                <th>OrderDate</th>
                <th>DueDate</th>
                <th>ShipDate</th>
                <th>Status</th>
                <th>OnlineOrderFlag</th>
                <th>SalesOrderNumber</th>
                <th>PurchaseOrderNumber</th>
                <th>AccountNumber</th>
                <th>CustomerID</th>
                <th>SalesPersonID</th>
                <th>TerritoryID</th>
                <th>BillToAddressID</th>
                <th>ShipToAddressID</th>
                <th>ShipMethodID</th>
                <th>CreditCardID</th>
                <th>CreditCardApprovalCode</th>
                <th>CurrencyRateID</th>
                <th>SubTotal</th>
                <th>TaxAmt</th>
                <th>Freight</th>
                <th>TotalDue</th>
                <th>Comment</th>
                <th>ModifiedDate</th>
            </tr>
                <%--
                    ArrayList<Part4> results = (ArrayList<Part4>) request.getAttribute("results");
                    for(Part4 p : results){
                        out.println("<tr>");
                        out.println("<td>"+p.getSalesOrderID()+"</td>");
                        out.println("<td>"+p.getRevisionNumber()+"</td>");
                        out.println("<td>"+p.getOrderDate()+"</td>"); 
                        out.println("<td>"+p.getDueDate()+"</td>"); 
                        out.println("<td>"+p.getShipDate()+"</td>"); 
                        out.println("<td>"+p.getStatus()+"</td>"); 
                        out.println("<td>"+p.getOnlineOrderFlag()+"</td>"); 
                        out.println("<td>"+p.getSalesOrderNumber()+"</td>"); 
                        out.println("<td>"+p.getPurchaseOrderNumber()+"</td>"); 
                        out.println("<td>"+p.getAccountNumber()+"</td>"); 
                        out.println("<td>"+p.getCustomerID()+"</td>"); 
                        out.println("<td>"+p.getSalesPersonID()+"</td>"); 
                        out.println("<td>"+p.getTerritoryID()+"</td>"); 
                        out.println("<td>"+p.getBillToAddressID()+"</td>"); 
                        out.println("<td>"+p.getShipToAddressID()+"</td>"); 
                        out.println("<td>"+p.getShipMethodID()+"</td>"); 
                        out.println("<td>"+p.getCreditCardID()+"</td>"); 
                        out.println("<td>"+p.getCreditCardApprovalCode()+"</td>"); 
                        out.println("<td>"+p.getCurrencyRateID()+"</td>"); 
                        out.println("<td>"+p.getSubTotal()+"</td>"); 
                        out.println("<td>"+p.getTaxAmt()+"</td>"); 
                        out.println("<td>"+p.getFreight()+"</td>"); 
                        out.println("<td>"+p.getTotalDue()+"</td>"); 
                        out.println("<td>"+p.getComment()+"</td>"); 
                        out.println("<td>"+p.getModifiedDate()+"</td>");
                        out.println("</tr>");
                    }
                --%>
            <c:forEach var="row" items="${requestScope.results}">
                <tr>
                    <td><c:out value="${row.getSalesOrderID()}" /></td>
                    <td><c:out value="${row.getRevisionNumber()}" /></td>
                    <td><c:out value="${row.getOrderDate()}" /></td>
                    <td><c:out value="${row.getDueDate()}" /></td>
                    <td><c:out value="${row.getShipDate()}" /></td>
                    <td><c:out value="${row.getStatus()}" /></td>
                    <td><c:out value="${row.getOnlineOrderFlag()}" /></td>
                    <td><c:out value="${row.getSalesOrderNumber()}" /></td>
                    <td><c:out value="${row.getPurchaseOrderNumber()}" /></td>
                    <td><c:out value="${row.getAccountNumber()}" /></td>
                    <td><c:out value="${row.getCustomerID()}" /></td>
                    <td><c:out value="${row.getSalesPersonID()}" /></td>
                    <td><c:out value="${row.getTerritoryID()}" /></td>
                    <td><c:out value="${row.getBillToAddressID()}" /></td>
                    <td><c:out value="${row.getShipToAddressID()}" /></td>
                    <td><c:out value="${row.getShipMethodID()}" /></td>
                    <td><c:out value="${row.getCreditCardID()}" /></td>
                    <td><c:out value="${row.getCreditCardApprovalCode()}" /></td>
                    <td><c:out value="${row.getCurrencyRateID()}" /></td>
                    <td><c:out value="${row.getSubTotal()}" /></td>
                    <td><c:out value="${row.getTaxAmt()}" /></td>
                    <td><c:out value="${row.getFreight()}" /></td>
                    <td><c:out value="${row.getTotalDue()}" /></td>
                    <td><c:out value="${row.getComment()}" /></td>
                    <td><c:out value="${row.getModifiedDate()}" /></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

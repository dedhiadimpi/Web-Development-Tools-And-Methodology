<%-- 
    Document   : part4
    Created on : Feb 13, 2020, 4:50:23 PM
    Author     : dedhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/part8" prefix="read" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Part 8</title>
<!--        <style>
            table,th,td{
                border: 1px solid black;
            }
        </style>-->
    </head>
    <body>
<!--        <table>
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
            </tr>-->
        <read:displayfile filename="SalesOrder"></read:displayfile>
        <!--</table>-->
    </body>
</html>

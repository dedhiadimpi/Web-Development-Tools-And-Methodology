/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csvfile.controller;

import com.csvfile.model.Part4;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dedhi
 */
public class Part4Controller extends HttpServlet{
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        try {
            //Load the driver
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            // Create a connection to directory given as first command line
            // parameter. Driver properties are passed in URL format
            // (or alternatively in a java.utils.Properties object).
            //
            // A single connection is thread-safe for use by several threads.
            String filename = req.getParameter("file");
            String url = "jdbc:relique:csv:" + "C:\\Users\\dedhi\\work\\WEB_DEV\\Homework2\\files" + "?" + "separator=,";
            Connection conn = DriverManager.getConnection(url);
            // Create a Statement object to execute the query with.
            // A Statement is not thread-safe.
            Statement stmt = conn.createStatement();
            // Select the ID and NAME columns from sample.csv
            ResultSet results = stmt.executeQuery("SELECT * FROM "+filename);
            ArrayList<Part4> data = new ArrayList<Part4>();
            while(results.next()){
                Part4 temp = new Part4();
                temp.setSalesOrderID(results.getString(1));
                temp.setRevisionNumber(results.getString(2));
                temp.setOrderDate(results.getString(3)); 
                temp.setDueDate(results.getString(4)); 
                temp.setShipDate(results.getString(5)); 
                temp.setStatus(results.getString(6)); 
                temp.setOnlineOrderFlag(results.getString(7)); 
                temp.setSalesOrderNumber(results.getString(8)); 
                temp.setPurchaseOrderNumber(results.getString(9)); 
                temp.setAccountNumber(results.getString(10)); 
                temp.setCustomerID(results.getString(11)); 
                temp.setSalesPersonID(results.getString(12)); 
                temp.setTerritoryID(results.getString(13)); 
                temp.setBillToAddressID(results.getString(14)); 
                temp.setShipToAddressID(results.getString(15)); 
                temp.setShipMethodID(results.getString(16)); 
                temp.setCreditCardID(results.getString(17)); 
                temp.setCreditCardApprovalCode(results.getString(18)); 
                temp.setCurrencyRateID(results.getString(19)); 
                temp.setSubTotal(results.getString(20)); 
                temp.setTaxAmt(results.getString(21)); 
                temp.setFreight(results.getString(22)); 
                temp.setTotalDue(results.getString(23)); 
                temp.setComment(results.getString(24)); 
                temp.setModifiedDate(results.getString(25));
                data.add(temp);
            }
            req.setAttribute("results", data);
            RequestDispatcher rd = req.getRequestDispatcher("/part4View.jsp");
            rd.forward(req, resp);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Part4Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Part4Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

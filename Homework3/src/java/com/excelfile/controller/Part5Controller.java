/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excelfile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author dedhi
 */
public class Part5Controller extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        PrintWriter out = resp.getWriter();
        //out.println("done");
        String excelFilePath = "C:\\Users\\dedhi\\work\\WEB_DEV\\Homework2\\files\\store.xls";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new HSSFWorkbook(inputStream);
        int i = 0;
        ArrayList<Iterator> iteratorList = new ArrayList<Iterator>();
        while(i < 3){
            Sheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            Iterator<Row> iterator = sheet.iterator();
            iteratorList.add(iterator);
            i++;
        }
        req.setAttribute("iteratorList", iteratorList);
        RequestDispatcher rd = req.getRequestDispatcher("part5View.jsp");
        rd.forward(req, resp);
    }
}

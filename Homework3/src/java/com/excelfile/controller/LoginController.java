/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excelfile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dedhi
 */
public class LoginController extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        processRequest(req, resp);
    }
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try{
            
        req.login(username, password);
        if(req.isUserInRole("user")){
         RequestDispatcher rd = req.getRequestDispatcher("/part5");
        rd.forward(req, resp);
        req.logout();
        }else{
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
        rd.forward(req, resp);
        }
    }catch(ServletException e){
        RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
        rd.forward(req, resp);
    }finally{
            req.logout();
        }        
    }
}

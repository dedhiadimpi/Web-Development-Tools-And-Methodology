/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.part6.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dedhi
 */
public class Part6Controller extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        int select = Integer.parseInt(req.getParameter("select"));
        if(select == 2){
            RequestDispatcher rd = req.getRequestDispatcher("part6AddMovie.jsp");
            rd.forward(req,resp);
        } else{
            RequestDispatcher rd = req.getRequestDispatcher("part6BrowseMovie.jsp");
            rd.forward(req,resp);
        }
    }
    
}

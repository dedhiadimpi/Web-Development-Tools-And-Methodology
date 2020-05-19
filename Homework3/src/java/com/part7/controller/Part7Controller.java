/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.part7.controller;

import com.database.DAO.DAO;
import com.part6.controller.Part6AddMovieController;
import com.part7.model.Books;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dedhi
 */
public class Part7Controller extends HttpServlet{
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        PrintWriter out = resp.getWriter();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        HttpSession session = req.getSession();
        int books = (int) session.getAttribute("books");
        int i=1;
        ArrayList<Books> bookList = new ArrayList<Books>();
        while(i<=books){
            Books b = new Books();
            b.setIsbn(req.getParameter("isbn"+i));
            b.setTitle(req.getParameter("title"+i));
            b.setAuthors(req.getParameter("authors"+i));
            b.setPrice(Float.parseFloat(req.getParameter("price"+i)));
            bookList.add(b);
            i++;
        }
        
        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/booksdb?"+"user=root&password=root");
            DAO dao = new DAO();
            conn = dao.getConnection("jdbc:mysql://localhost/booksdb","root","root");
            for(Books b : bookList){
                String query = "INSERT INTO BOOKS VALUES (?,?,?,?)";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, b.getIsbn());
                stmt.setString(2, b.getTitle());
                stmt.setString(3, b.getAuthors());
                stmt.setFloat(4, b.getPrice());
                int res = stmt.executeUpdate(); 
                session.setAttribute("res", res);
                out.println(query);
            }
            RequestDispatcher rd = req.getRequestDispatcher("part7-success.jsp");
            rd.forward(req, resp);
//            out.println("<h3>"+books+"</h3>");
            //String query = "INSERT INTO MOVIES VALUES (\""+movie.getTitle()+"\",\""+movie.getActor()+"\",\""+movie.getActress()+"\",\""+movie.getGenre()+"\","+movie.getYear()+")";
            //stmt.executeUpdate(query);
//            out.println("<h3>Books added successfully</h3>");
//            out.println("<a href='part7-welcome.jsp'>Click here to go back to the main page</a>");
        }
        
        catch(SQLException e){
            out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Part6AddMovieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Part7Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(rs != null)
                    rs.close();
                if(stmt != null)
                    stmt.close();
                if(conn != null)
                    conn.close();
            }catch(SQLException e){
                out.println(e.getMessage());
            }
        }
    }
}

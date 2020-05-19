/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.part6.controller;

import com.database.DAO.DAO;
import com.part6.model.Movies;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dedhi
 */
public class Part6AddMovieController extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        PrintWriter out = resp.getWriter();
        Movies movie = new Movies();
        movie.setTitle(req.getParameter("title"));
        movie.setActor(req.getParameter("actor"));
        movie.setActress(req.getParameter("actress"));
        movie.setGenre(req.getParameter("genre"));
        movie.setYear(Integer.parseInt(req.getParameter("year")));
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/moviedb?"+"user=root&password=root");
            DAO dao = new DAO();
            conn = dao.getConnection("jdbc:mysql://localhost/moviedb","root","root");
            stmt = conn.createStatement();
            String query = "INSERT INTO MOVIES VALUES (\""+movie.getTitle()+"\",\""+movie.getActor()+"\",\""+movie.getActress()+"\",\""+movie.getGenre()+"\","+movie.getYear()+")";
            stmt.executeUpdate(query);
            
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>1 Movie added successfully</h3>");
            out.println("<a href='part6-welcome.jsp'>Click here to go back to the main page</a>");
            out.println("</body>");
            out.println("</html>");
        }
        
        catch(SQLException e){
            out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Part6AddMovieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Part6AddMovieController.class.getName()).log(Level.SEVERE, null, ex);
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

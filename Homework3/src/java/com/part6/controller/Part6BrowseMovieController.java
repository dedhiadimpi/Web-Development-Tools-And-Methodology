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
import java.util.ArrayList;
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
public class Part6BrowseMovieController extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        PrintWriter out = resp.getWriter();
        String keyword = req.getParameter("keyword");
        String search = req.getParameter("search");
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        ArrayList<Movies> movieList = new ArrayList<Movies>();
        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/moviedb?"+"user=root&password=root");
            DAO dao = new DAO();
            conn = dao.getConnection("jdbc:mysql://localhost/moviedb","root","root");
            stmt = conn.createStatement();
            
            String query = "SELECT title, actor, actress, genre, year from movies where "+search+" = '"+keyword+"'";
            out.println(query);
            rs = stmt.executeQuery(query);
            while(rs.next()){
                Movies m = new Movies();
                m.setTitle(rs.getString(1));
                m.setActor(rs.getString(2));
                m.setActress(rs.getString(3));
                m.setGenre(rs.getString(4));
                m.setYear(Integer.parseInt(rs.getString(5)));
                movieList.add(m);
            }
            req.setAttribute("movieList", movieList);
            req.setAttribute("keyword", keyword);
            RequestDispatcher rd = req.getRequestDispatcher("/part6DisplayMovies.jsp");
            rd.forward(req, resp);
        }
        
        catch(SQLException e){
            out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Part6BrowseMovieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Part6BrowseMovieController.class.getName()).log(Level.SEVERE, null, ex);
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

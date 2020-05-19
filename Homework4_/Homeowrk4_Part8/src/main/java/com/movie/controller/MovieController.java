/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.controller;

import com.movie.dao.MovieDao;
import com.movie.pojo.Movie;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author dedhi
 */
public class MovieController extends AbstractController {
    public MovieController() {
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;

        String option = request.getParameter("option") == null ? "" : request.getParameter("option");
        MovieDao movieDao = new MovieDao();
        if (option == null || option == "") {
            mv = new ModelAndView("index");
            return mv;
        }else if(option.equals("browse")){
            mv = new ModelAndView("browseMovie");
            return mv;
        }else if(option.equals("add")){
            mv = new ModelAndView("addMovie");
            return mv;
        }else if(option.equals("getMovies")){
            String keyword = (String) request.getAttribute("keyword");
            String search = request.getParameter("search");
            List<Movie> movieList = movieDao.getMovie(search,keyword);
            if(movieList != null && movieList.size() > 0){
                mv = new ModelAndView("displayMovie", "movieList", movieList);
                mv.addObject("keyword", keyword);
                return mv;
            }else{
                String msg = "Movie with keyword '"+keyword+"' not found!!!";
                mv = new ModelAndView("success","msg",msg);
                return mv;
            }
        }else if(option.equals("addMovie")){
            Movie result = movieDao.addMovie((String) request.getAttribute("title"),(String)  request.getAttribute("actor"),
                   (String) request.getAttribute("actress"),(String) request.getAttribute("genre"), 
                    Integer.parseInt(request.getParameter("year")));
            if(result != null){
                String msg = "Movie with Title "+result.getTitle()+" added successfully!!!";
                mv = new ModelAndView("success","msg",msg);
                return mv;
            }else{
                String msg = "Failed to add Movie with Title "+result.getTitle()+"!!!";
                mv = new ModelAndView("success","msg",msg);
                return mv;
            }
        }else if(option.equals("delete")){
            Movie result = movieDao.deleteMovie(Long.parseLong(request.getParameter("movieId")));
            if(result != null){
                String msg = "Movie with ID "+result.getMovieId()+" deleted successfully!!!";
                mv = new ModelAndView("success","msg",msg);
                return mv;
            }else{
                String msg = "Failed to delete Movie with ID "+result.getMovieId()+"!!!";
                mv = new ModelAndView("success","msg",msg);
                return mv;
            }
            
        }else if(option.equals("update")){
            //long movieId = Long.parseLong(request.getParameter("movieId"));
            List<Movie> movieList = movieDao.getMovie("movieId",request.getParameter("movieId"));
            mv = new ModelAndView("updateMovie","movie",movieList.get(0));
            return mv;
        }else if(option.equals("updateMovie")){
            long result = movieDao.updateMovie(Long.parseLong(request.getParameter("movieId")), (String) request.getAttribute("title"),(String)  request.getAttribute("actor"),
                   (String) request.getAttribute("actress"),(String) request.getAttribute("genre"), 
                    Integer.parseInt(request.getParameter("year")));
            if(result > 0){
                String msg = "Movie with ID "+result+" has been updated successfully!!!";
                mv = new ModelAndView("success","msg",msg);
                return mv;
            }else{
                String msg = "Failed to update Movie with ID "+result+"!!!";
                mv = new ModelAndView("success","msg",msg);
                return mv;
            }
        }
        
        return null;
    }
}

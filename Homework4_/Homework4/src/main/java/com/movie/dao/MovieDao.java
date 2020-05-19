/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.dao;

import com.movie.pojo.Movie;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author dedhi
 */
public class MovieDao {
    
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Session session = null;

    private Session getSession() {
        if (session == null || !session.isOpen()) {
            session = sf.openSession();
        }
        return session;
    }

    private void beginTransaction() {
        getSession().beginTransaction();
    }

    private void commit() {
        getSession().getTransaction().commit();;
    }

    private void close() {
        getSession().close();
    }

    private void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        try {
            beginTransaction();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from movies");
            movies = q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return movies;
    }
    
    public List<Movie> getMovie(String search, String keyword){
        try{
            beginTransaction();
             //Query q = getSession().createQuery("from Movie where title='q'");
            Query q = getSession().createQuery("from Movie where "+search+" = :keyword");
            //q.setParameter("keyword", keyword);
            q.setString("keyword", keyword);
            List<Movie> movies = q.list();
            return movies;
        } catch(HibernateException ex){
            ex.printStackTrace();
            rollbackTransaction();
        } finally{
            close();
        }
        return null;
    }

    public Movie addMovie(String title, String actor, String actress, String genre, int year) {
        int result = 0;
        Movie movie = new Movie();
        try {
            movie.setTitle(title);
            movie.setActor(actor);
            movie.setActress(actress);
            movie.setGenre(genre);
            movie.setYear(year);
            beginTransaction();
            getSession().save(movie);
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return movie;
    }
    
    public long updateMovie(long movieId, String title, String actor, String actress, String genre, int year){
        int result = 0;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Movie where movieId= :movieId");
            q.setLong("movieId", movieId);
            Movie movie = (Movie) q.uniqueResult();
            movie.setTitle(title);
            movie.setActor(actor);
            movie.setActress(actress);
            movie.setGenre(genre);
            movie.setYear(year);
            getSession().save(movie);
            commit();
            result = 1;
        } catch(HibernateException ex){
            ex.printStackTrace();
            rollbackTransaction();
        } finally{
            close();
        }
        return movieId;
    }

    public Movie deleteMovie(long id) {
        int result = 0;
        Movie msgToDelete = null;
        try {
            beginTransaction();
            Query q = getSession().createQuery("from Movie where id= :id");
            q.setLong("id", id);
            msgToDelete = (Movie) q.uniqueResult();
            getSession().delete(msgToDelete);
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return msgToDelete;
    }
}

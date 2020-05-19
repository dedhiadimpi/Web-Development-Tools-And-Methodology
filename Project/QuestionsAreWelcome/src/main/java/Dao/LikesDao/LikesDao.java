/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.LikesDao;

import Models.Likes.Likes;
import Models.Question.Question;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author dedhi
 */
public class LikesDao {
    public static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    Session session = null;
    
    private Session getSession(){
        if(session == null || !session.isOpen()){
            session = sf.openSession();
        }
        return session;
    }
    
    private void beginTransaction(){
        getSession().beginTransaction();
    }
    
    private void commit(){
        getSession().getTransaction().commit();
    }
    
    private void close(){
        getSession().close();
    }
    
    private void rollback(){
        getSession().getTransaction().rollback();
    }
    
    public int addLikes(Likes likes){
        int result = 0;
        try{
            beginTransaction();
            getSession().saveOrUpdate(likes);
            commit();
            result = 1;
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
            result = 0;
        }finally {
            close();
        }
        return result;
    }
    
    public Likes getLikeQuestion(long question_id, long user_id){
        Likes like = null;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Likes where question_id = :question_id and user_id = :user_id");
            q.setLong("question_id", question_id);
            q.setLong("user_id", user_id);
            like = (Likes) q.uniqueResult();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        }finally {
            close();
        }
        return like;
        
    }
    
    public Likes getLikeAnswer(long answer_id, long user_id){
        Likes like = null;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Likes where answer_id = :answer_id and user_id = :user_id");
            q.setLong("answer_id", answer_id);
            q.setLong("user_id", user_id);
            like = (Likes) q.uniqueResult();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        }finally {
            close();
        }
        return like;
    }
    
}

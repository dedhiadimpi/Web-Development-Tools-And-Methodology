/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.UserDao;

import Models.Question.Question;
import Models.User.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author dedhi
 */
public class UserDao {
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
    
//    public int addQuestion(Question question){
//        int result = 0;
//        try{
//            beginTransaction();
//            getSession().save(question);
//            commit();
//            result = 1;
//        }catch(HibernateException e){
//            e.printStackTrace();
//            rollback();
//            result = 0;
//        }finally {
//            close();
//        }
//        return result;
//    }
//    
//    public int deleteQuestion(Question question){
//        int result = 0;
//        try{
//            beginTransaction();
//            getSession().delete(question);
//            commit();
//            result = 1;
//        }catch(HibernateException e){
//            e.printStackTrace();
//            rollback();
//            result = 0;
//        }finally{
//            close();
//        }
//        return result;
//    }
//    
    public int updateUser(long user_id, String firstname, String lastname, String gender, Date date, String address, String email, String phone){
        int result = 0;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from User where user_id= :user_id");
            q.setLong("user_id", user_id);
            User user = (User) q.uniqueResult();
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setGender(gender);
            user.setDate_of_birth(date);
            user.setAddress(address);
            user.setEmail(email);
            user.setPhone_number(phone);
            getSession().save(user);
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
    
//    public ArrayList<Question> getQuestionList(){
//        ArrayList<Question> questionList = null;
//        try{
//            beginTransaction();
//            Query q = getSession().createQuery("from Question");
//            questionList = (ArrayList<Question>) q.list();
//            commit();
//        }catch(HibernateException e){
//            e.printStackTrace();
//            rollback();
//        } finally{
//            close();
//        }
//        return questionList;
//    }
    
//    public List<Question> getQuestionList(){
//        List<Question> questionList = null;
//        try{
//            beginTransaction();
//            Query q = getSession().createQuery("from Question");
//            questionList = (List<Question>) q.list();
//            commit();
//        }catch(HibernateException e){
//            e.printStackTrace();
//            rollback();
//        } finally{
//            close();
//        }
//        return questionList;
//    }
//    
//    public List<Question> getMyQuestionList(long user_id){
//        List<Question> questionList = null;
//        try{
//            beginTransaction();
//            Query q = getSession().createQuery("from Question where user_id = "+user_id);
//            questionList = (List<Question>) q.list();
//            commit();
//        }catch(HibernateException e){
//            e.printStackTrace();
//            rollback();
//        } finally{
//            close();
//        }
//        return questionList;
//    }
//    
//    public ArrayList<Question> searchQuestionList(String search, Long id){
//        ArrayList<Question> questionList = null;
//        try{
//            beginTransaction();
//            Query q = getSession().createQuery("from Question where "+search+" = :"+id);
//            q.setLong("id", id);
//            questionList = (ArrayList<Question>) q.list();
//            commit();
//        }catch(HibernateException e){
//            e.printStackTrace();
//            rollback();
//        } finally{
//            close();
//        }
//        return questionList;
//    }
    
    public User getUser(long user_id){
        User user = null;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from User where user_id = "+user_id);
            ArrayList<User> userList = (ArrayList<User>) q.list();
            user = userList.get(0);
            commit();
        }catch(HibernateError e){
            e.printStackTrace();
            rollback();
        }finally{
            close();
        }
        return user;
    }
    
}

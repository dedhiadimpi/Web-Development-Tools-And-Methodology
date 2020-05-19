/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.DashboardDao;

import Models.Answer.Answer;
import Models.Domain.Domain;
import Models.Question.Question;
import Models.User.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author dedhi
 */
public class DashboardDao {
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
    
    private List<User> getUserList(){
        List<User> userList = new ArrayList<>();;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from User");
            userList = (List<User>) q.list();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        }finally {
            close();
        }
        return userList;
    }
    
    private List<Question> getQuestionList(){
        List<Question> questionList = new ArrayList<>();;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Question");
            questionList = (List<Question>) q.list();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        }finally {
            close();
        }
        return questionList;
    }
    
    private List<Answer> getAnswerList(){
        List<Answer> answerList = new ArrayList<>();
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Answer");
            answerList = (List<Answer>) q.list();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        }finally {
            close();
        }
        return answerList;
    }
    
    private List<Domain> getDomainList(){
        List<Domain> domainList = new ArrayList<>();
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Domain");
            domainList = (List<Domain>) q.list();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        }finally {
            close();
        }
        return domainList;
    }
    
    public Map<String,ArrayList<Object>> getUserDetailsWithQuestions(){
        Map<String,ArrayList<Object>> map = new HashMap<String, ArrayList<Object>>();
        List<User> userList = getUserList();
        for(User u: userList){
            ArrayList<Object> temp = new ArrayList<Object>();
            temp.add(0,0);
            temp.add(1,0);
            temp.add(2,0);
            temp.add(3,0);
            temp.add(4,0);
            temp.add(5,0);
            temp.add(6,u);
            map.put(u.getUsername(), temp);
            
        }
        List<Question> questionList = getQuestionList();
        for(Question q: questionList){
            User u = q.getUser();
            if(map.containsKey(u.getUsername())){
                ArrayList<Object> value = map.get(u.getUsername());
                value.set(0,(int) value.get(0)+1);
                if(q.getApprover().getFirstname().equals("DUMMY")){
                    value.set(1, (int) value.get(1)+1);
                }else if(!q.getApprover().getFirstname().equals("DUMMY") && q.getReject_comment() == null){
                    value.set(2, (int) value.get(2)+1);
                }else if(!q.getApprover().getFirstname().equals("DUMMY") && q.getReject_comment() != null){
                    value.set(3, (int) value.get(3)+1);
                }
                value.set(4, (int) value.get(4)+q.getNo_of_likes());
                value.set(5, (int) value.get(5)+q.getNo_of_dislikes());
                value.set(6,u);
                map.put(u.getUsername(), value);
            }
        }
        return map;
    }
    
    
    
    public Map<String,ArrayList<Object>> getUserDetailsWithAnswers(){
        Map<String,ArrayList<Object>> map = new HashMap<String, ArrayList<Object>>();
        List<User> userList = getUserList();
        for(User u: userList){
            ArrayList<Object> temp = new ArrayList<Object>();
            temp.add(0,0);
            temp.add(1,0);
            temp.add(2,0);
            temp.add(3,0);
            temp.add(4,0);
            temp.add(5,0);
            temp.add(6,u);
            map.put(u.getUsername(), temp);
            
        }
        List<Answer> answerList = getAnswerList();
        for(Answer q: answerList){
            User u = q.getUser();
            if(map.containsKey(u.getUsername())){
                ArrayList<Object> value = map.get(u.getUsername());
                value.set(0,(int) value.get(0)+1);
                if(q.getApprover().getFirstname().equals("DUMMY")){
                    value.set(1, (int) value.get(1)+1);
                }else if(!q.getApprover().getFirstname().equals("DUMMY") && q.getReject_comment() == null){
                    value.set(2, (int) value.get(2)+1);
                }else if(!q.getApprover().getFirstname().equals("DUMMY") && q.getReject_comment() != null){
                    value.set(3, (int) value.get(3)+1);
                }
                value.set(4, (int) value.get(4)+q.getNo_of_likes());
                value.set(5, (int) value.get(5)+q.getNo_of_dislikes());
                value.set(6,u);
                map.put(u.getUsername(), value);
            }
        }
        return map;
    }
    
    public Map<String,ArrayList<Integer>> getDomainDetails(){
        Map<String,ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        List<Domain> domainList = getDomainList();
        for(Domain d: domainList){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(0,0);
            temp.add(1,0);
            map.put(d.getDomain_name(), temp);
        }
        List<Question> questionList = getQuestionList();
        for(Question q: questionList){
            if(map.containsKey(q.getDomain().getDomain_name())){
                ArrayList<Integer> list = map.get(q.getDomain().getDomain_name());
                list.set(0, list.get(0)+1);
                int countAnswer = q.getAnswerList().size();
                list.set(1, list.get(1)+countAnswer);
                map.put(q.getDomain().getDomain_name(), list);
            }
        }
        return map;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.AnswerDao;

import Dao.ApproverDao.ApproverDao;
import Models.Answer.Answer;
import Models.Approver.Approver;
import Models.Question.Question;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author dedhi
 */
public class AnswerDao {
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
    
    public int addAnswer(Answer answer){
        int result = 0;
        try{
            beginTransaction();
            getSession().save(answer);
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
    
    public ArrayList<Answer> getMyAnswerList(long user_id){
        ArrayList<Answer> answerList = null;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Answer where user_id = "+user_id);
            answerList = (ArrayList<Answer>) q.list();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        } finally{
            close();
        }
        return answerList;
    }
    
    public int deleteAnswer(long answer_id){
        int result = 0;
        try{
            Answer answer = getAnswer(answer_id);
            if(answer != null){
                beginTransaction();
                getSession().delete(answer);
                commit();
                result = 1;
            }else{
                result = 0;
            }    
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
            result = 0;
        }finally{
            close();
        }
        return result;
    }
    
    public int updateAnswer(long answer_id, String answer_desc){
        int result = 0;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Answer where answer_id = :answerId");
            q.setLong("answerId", answer_id);
            Answer answer = (Answer) q.uniqueResult();
            answer.setAnswer_description(answer_desc);
            ApproverDao approverDao = new ApproverDao();
            Approver approver = approverDao.getDummyApprover();
            answer.setApprover(approver);
            answer.setUpdated_on(new Date());
            getSession().save(answer);
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
//            Criteria c = getSession().createCriteria(Question.class);
//            questionList = (List<Question>) c.list();
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
//    
    public Answer getAnswer(long answer_id){
        ArrayList<Answer> answerList = null;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Answer where answer_id = :answer_id");
            q.setLong("answer_id", answer_id);
            answerList = (ArrayList<Answer>) q.list();
            commit();
        }catch(HibernateError e){
            e.printStackTrace();
            rollback();
        }finally{
            close();
        }
        return answerList.get(0);
    }
    
    public int addLikes(Answer answer){
        int result = 0;
        try{
            beginTransaction();
            getSession().saveOrUpdate(answer);
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
    
    public List<Answer> checkAnswer(String desc){
        List<Answer> answers = null;
        try{
            beginTransaction();
            Criteria criteria = session.createCriteria(Answer.class);
            criteria.add(Restrictions.eq("answer_description", desc));
            answers = criteria.list();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        }finally{
            close();
        }
        return answers;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.QuestionDao;

import Dao.ApproverDao.ApproverDao;
import Dao.DomainDao.DomainDao;
import Models.Admin.Admin;
import Models.Answer.Answer;
import Models.Approver.Approver;
import Models.Domain.Domain;
import Models.Question.Question;
import Models.User.User;
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author dedhi
 */
public class QuestionDao {
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
    
    public int addQuestion(Question question){
        int result = 0;
        try{
            beginTransaction();
            getSession().save(question);
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
    
    public int updateQuestion(long question_id, String question_title, String question_Desc, long domain_id){
        int result = 0;
        try{
            DomainDao domainDao = new DomainDao();
            Domain domain = domainDao.getDomain((int) domain_id);
            Question question = getQuestion(question_id);
            question.setQuestion_title(question_title);
            question.setQuestion_description(question_Desc);
            question.setDomain(domain);
            question.setUpdated_on(new Date());
            ApproverDao approverDao = new ApproverDao();
            Approver approver = approverDao.getDummyApprover();
            question.setApprover(approver);
            beginTransaction();
            getSession().update(question);
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
    
    public int addLikes(Question question){
        int result = 0;
        try{
            beginTransaction();
            getSession().saveOrUpdate(question);
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
    
    public List<Question> getQuestionList(){
        List<Question> questionList = null;
        try{
            ApproverDao approverDao = new ApproverDao();
            long approver_id = approverDao.getDummyApprover().getApprover_id();
            beginTransaction();
            Query q = getSession().createQuery("from Question where approver_id != :approver_id and reject_comment is null");
            q.setLong("approver_id", approver_id);
            questionList = (List<Question>) q.list();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        } finally{
            close();
        }
        return questionList;
    }
    
    public List<Question> getApproverQuestionList(){
        List<Question> questionList = null;
        try{
            ApproverDao approverDao = new ApproverDao();
            long approver_id = approverDao.getDummyApprover().getApprover_id();
            beginTransaction();
            Query q = getSession().createQuery("from Question");
            questionList = (List<Question>) q.list();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        } finally{
            close();
        }
        return questionList;
    }
    
    public List<Question> getMyQuestionList(long user_id){
        List<Question> questionList = null;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Question where user_id = :user_id");
            q.setLong("user_id", user_id);
            questionList = (List<Question>) q.list();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        } finally{
            close();
        }
        return questionList;
    }
    
    public ArrayList<Question> searchQuestionList(String searchKeyword, long domain_id){
        ArrayList<Question> questionList = null;
       DomainDao domainDao = new DomainDao();
        try{
            ApproverDao approverDao = new ApproverDao();
            Approver approver = approverDao.getDummyApprover();
            beginTransaction();
            Criteria criteria = session.createCriteria(Question.class);
            if(domain_id == 0){   
                criteria.add(Restrictions.ne("approver",approver));
                criteria.add(Restrictions.isNull("reject_comment"));
                criteria.add(Restrictions.ilike("question_title",searchKeyword,MatchMode.ANYWHERE));
            }else{
                Domain domain = domainDao.getDomain((int) domain_id);
                criteria.add(Restrictions.ne("approver",approver));
                criteria.add(Restrictions.isNull("reject_comment"));
                criteria.add(Restrictions.ilike("question_title",searchKeyword,MatchMode.ANYWHERE));
                criteria.add(Restrictions.eq("domain",domain));
            }
            questionList = (ArrayList<Question>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        } finally{
            close();
        }
        return questionList;
    }
    
    public ArrayList<Question> searchApproverQuestionList(String searchKeyword, long domain_id){
        ArrayList<Question> questionList = null;
       DomainDao domainDao = new DomainDao();
        try{
            ApproverDao approverDao = new ApproverDao();
            Approver approver = approverDao.getDummyApprover();
            beginTransaction();
            Criteria criteria = session.createCriteria(Question.class);
            if(domain_id == 0){   
                criteria.add(Restrictions.ilike("question_title",searchKeyword,MatchMode.ANYWHERE));
            }else{
                Domain domain = domainDao.getDomain((int) domain_id);
                criteria.add(Restrictions.ilike("question_title",searchKeyword,MatchMode.ANYWHERE));
                criteria.add(Restrictions.eq("domain",domain));
            }
            questionList = (ArrayList<Question>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            commit();
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        } finally{
            close();
        }
        return questionList;
    }
    
    public Question getQuestion(long question_id){
        Question question = null;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Question where question_id = :question_id");
            q.setLong("question_id", question_id);
            ArrayList<Question> questionList = (ArrayList<Question>) q.list();
            question = questionList.get(0);
            commit();
        }catch(HibernateError e){
            e.printStackTrace();
            rollback();
        }finally{
            close();
        }
        return question;
    }
    
    public int deleteQuestion(long question_id){
        int result = 0;
        try{
            Question question = getQuestion(question_id);
            if(question != null){
                beginTransaction();
                getSession().delete(question);
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
    
    public List<Question> checkQuestion(String title, String desc){
        List<Question> questions = null;
        try{
            beginTransaction();
            Criteria criteria = session.createCriteria(Question.class);
            Criterion critTitle = Restrictions.eq("question_title",title);
            Criterion critDesc = Restrictions.eq("question_description", desc); 
            criteria.add(Restrictions.or(critTitle,critDesc));
            questions = criteria.list();   
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        }finally{
            close();
        }
        return questions;
    }
    
}

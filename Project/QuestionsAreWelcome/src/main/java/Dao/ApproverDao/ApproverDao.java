/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.ApproverDao;

import Dao.AnswerDao.AnswerDao;
import Dao.QuestionDao.QuestionDao;
import Models.Answer.Answer;
import Models.Approver.Approver;
import Models.Question.Question;
import Models.User.User;
import java.util.ArrayList;
import java.util.Date;
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
public class ApproverDao {
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
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
    
    private void rollback(){
        getSession().getTransaction().rollback();
    }
    
    private void close(){
        getSession().close();
    }
    
    public Approver getDummyApprover(){
        ArrayList<Approver> approverList = null;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Approver where firstname = 'DUMMY'");
            approverList = (ArrayList<Approver>) q.list();
            commit();
        }catch(HibernateError e){
            e.printStackTrace();
            rollback();
        }finally{
            close();
        }
        return approverList.get(0);
    }
    
    public int updateApprover(long approver_id, String firstname, String lastname, String gender, Date date, String address, String email, String phone){
        int result = 0;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Approver where approver_id = :approver_id");
            q.setLong("approver_id", approver_id);
            Approver approver = (Approver) q.uniqueResult();
            approver.setFirstname(firstname);
            approver.setLastname(lastname);
            approver.setGender(gender);
            approver.setDate_of_birth(date);
            approver.setAddress(address);
            approver.setEmail(email);
            approver.setPhone_number(phone);
            getSession().save(approver);
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
    
    public Question approveQuestions(long question_id, Approver approver, String reject_comment){
        int result = 0;
        Question question = null;
        try{
            QuestionDao questionDao = new QuestionDao();
            question = questionDao.getQuestion((int) question_id);
            question.setApprover(approver);
            if(!reject_comment.equals("")){
                question.setReject_comment(reject_comment);
            }
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
        return question;
    }
    
    public ArrayList<Question> getQuestionListForApproval(){
        ArrayList<Question> questionList = null;
        Approver approver = getDummyApprover();
        try{
            Approver dummy = getDummyApprover();
            long approver_id = dummy.getApprover_id();
            beginTransaction();
            Query q = getSession().createQuery("from Question where approver_id = :approverId");
            q.setLong("approverId", approver_id);
            questionList = (ArrayList<Question>) q.list();
            commit();          
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        }finally {
            close();
        }
        return questionList;
    }
    
    public ArrayList<Answer> getAnswerListForApproval(){
        ArrayList<Answer> answerList = null;
        Approver approver = getDummyApprover();
        try{
            Approver dummy = getDummyApprover();
            long approver_id = dummy.getApprover_id();
            beginTransaction();
            Query q = getSession().createQuery("from Answer where approver_id = :approverId");
            q.setLong("approverId", approver_id);
            answerList = (ArrayList<Answer>) q.list();
            commit();          
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
        }finally {
            close();
        }
        return answerList;
    }
    
    public Answer approveAnswers(long answer_id, Approver approver, String reject_comment){
        int result = 0;
        Answer answer = null;
        try{
            AnswerDao answerDao = new AnswerDao();
            answer = answerDao.getAnswer(answer_id);
            if(!reject_comment.equals("")){
                answer.setReject_comment(reject_comment);
            }
            answer.setApprover(approver);
            beginTransaction();
            getSession().merge(answer);
            commit();
            result = 1;
        }catch(HibernateException e){
            e.printStackTrace();
            rollback();
            result = 0;
        }finally {
            close();
        }
        return answer;
    }
}

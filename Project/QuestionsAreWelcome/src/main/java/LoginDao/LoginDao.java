/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginDao;

import Models.Admin.Admin;
import Models.Approver.Approver;
import Models.Domain.Domain;
import Models.User.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author dedhi
 */
public class LoginDao {
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

    public ArrayList<Admin> getAdminLoginDetails(String username, String password) {
        ArrayList<Admin> role = null;
        try {
            beginTransaction();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Admin where username = :username and password = :password");
            q.setParameter("username", username);
            q.setParameter("password", password);
            role = (ArrayList<Admin>) q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return role;
    }
    
    public ArrayList<Approver> getApproverLoginDetails(String username, String password) {
        ArrayList<Approver> role = null;
        try {
            beginTransaction();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Approver where username = :username and password = :password");
            q.setParameter("username", username);
            q.setParameter("password", password);
            role = (ArrayList<Approver>) q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return role;
    }
    
    public ArrayList<User> getUserLoginDetails(String username, String password) {
        ArrayList<User> role = null;
        try {
            beginTransaction();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from User where username = :username and password = :password");
            q.setParameter("username", username);
            q.setParameter("password", password);
            role = (ArrayList<User>) q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return role;
    }
    
    public int registerUser(String firstname, String lastname, String gender, Date date, String address, String email, String phone, String username, String password){
        int result = 0;
        try{
            beginTransaction();
            User user = new User();
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setGender(gender);
            user.setDate_of_birth(date);
            user.setAddress(address);
            user.setEmail(email);
            user.setPhone_number(phone);
            user.setUsername(username);
            user.setPassword(password);
            getSession().save(user);
            commit();
            result = 1;
        }catch(HibernateException e){
            e.printStackTrace();
            rollbackTransaction();
            result = 0;
        }finally {
            close();
        }
        return result;
    }
    
    public int addApprover(String firstname, String lastname, String gender, Date date, String address, String email, String phone, String username, String password){
        int result = 0;
        try{
            beginTransaction();
            Approver approver = new Approver();
            approver.setFirstname(firstname);
            approver.setLastname(lastname);
            approver.setGender(gender);
            approver.setDate_of_birth(date);
            approver.setAddress(address);
            approver.setEmail(email);
            approver.setPhone_number(phone);
            approver.setUsername(username);
            approver.setPassword(password);
            getSession().save(approver);
            commit();
            result = 1;
        }catch(HibernateException e){
            e.printStackTrace();
            rollbackTransaction();
            result = 0;
        }finally {
            close();
        }
        return result;
    }
    
    public int checkUser(String email, String username){
        int result = 0;
        try{
            beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            Criterion critEmail = Restrictions.eq("email",email);
            Criterion crtiUsername = Restrictions.eq("username", username); 
            criteria.add(Restrictions.or(critEmail,crtiUsername));
            List users = criteria.list();
            if (users.size() > 0) {
                result = 1;
            }else{
                result = 2;
            }
            
        }catch(HibernateException e){
            e.printStackTrace();
            rollbackTransaction();
            result = 0;
        }finally {
            close();
        }
        return result;
    }
    
    
}

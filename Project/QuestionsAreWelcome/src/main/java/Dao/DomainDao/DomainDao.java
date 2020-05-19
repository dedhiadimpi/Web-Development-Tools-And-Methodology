/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.DomainDao;

import Models.Approver.Approver;
import Models.Domain.Domain;
import java.util.ArrayList;
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
public class DomainDao {
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
    
    public Domain getDomain(int domain_id){
        ArrayList<Domain> domainList = null;
        try{
            beginTransaction();
            Query q = getSession().createQuery("from Domain where domain_id = "+domain_id);
            domainList = (ArrayList<Domain>) q.list();
            commit();
        }catch(HibernateError e){
            e.printStackTrace();
            rollback();
        }finally{
            close();
        }
        return domainList.get(0);
    }
    
    public ArrayList<Domain> getDomainList(){
        ArrayList<Domain> domainList = null;
        try {
            beginTransaction();
            //Query q = getSession().createQuery("from Message where userName= :username");
            Query q = getSession().createQuery("from Domain");
            domainList = (ArrayList<Domain>) q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return domainList;
    }
}

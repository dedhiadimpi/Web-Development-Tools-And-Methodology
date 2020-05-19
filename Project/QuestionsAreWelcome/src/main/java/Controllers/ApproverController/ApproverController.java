/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.ApproverController;

import Dao.ApproverDao.ApproverDao;
import Helper.ApproveEmail;
import Models.Answer.Answer;
import Models.Approver.Approver;
import Models.Question.Question;
import Models.User.User;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author dedhi
 */
public class ApproverController extends AbstractController{

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView mv = null;
        if(req.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        ApproverDao approverDao = new ApproverDao();
        Approver approver = (Approver) req.getSession().getAttribute("user");
        
        if(req.getParameter("option").equals("question")){
            if(req.getParameter("action").equals("Approve")){
            long question_id = Long.parseLong(req.getParameter("question_id"));
            Question result = approverDao.approveQuestions(question_id, approver,"");
            ArrayList<Question> questionList = approverDao.getQuestionListForApproval();
            mv = new ModelAndView("approve-questions");
            mv.addObject("questionList",questionList);
            
            if(result != null){
                String configEmail = "email-bean.xml";
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(configEmail);
		// @Service call
		ApproveEmail approveEmail = (ApproveEmail) context.getBean("approveEmail");
		String toAddr = result.getUser().getEmail();
		String fromAddr = "dedhiadimpi@gmail.com";
		// email subject
		String subject = "Good news from QuestonsAreWelcome!";
		// email body
		String body = "Hi "+result.getUser().getFirstname()+" "+result.getUser().getLastname()+",\n\nYour Question '"+result.getQuestion_title()+"' is Approved and Posted.";
		approveEmail.sendEmail(toAddr, fromAddr, subject, body);
                mv.addObject("error","Question Approved successfully!");
               mv.addObject("reject","");
                return mv;
            }else{
                mv.addObject("reject","");
                mv.addObject("error","Something went wrong. Please try again later!");
                return mv;
            }
            
        }else if(req.getParameter("action").equals("Reject")){
            String reject_comment = req.getParameter("reject_comment");
            if(reject_comment == null || reject_comment == "" || reject_comment.trim().length() < 5 || reject_comment.trim().length() > 4000){
                ArrayList<Question> questionList = approverDao.getQuestionListForApproval();
                mv = new ModelAndView("approve-questions");
                mv.addObject("questionList",questionList);
                mv.addObject("reject",reject_comment);
                mv.addObject("error","Please enter a valid rejection reason! Reject reason cannot be less than 5 or more than 4000 characters");
                return mv;
            }else{
                long question_id = Long.parseLong(req.getParameter("question_id"));
                Question result = approverDao.approveQuestions(question_id, approver,reject_comment);
                ArrayList<Question> questionList = approverDao.getQuestionListForApproval();
                mv = new ModelAndView("approve-questions");
                mv.addObject("questionList",questionList);
                
                if(result != null){
                    String configEmail = "email-bean.xml";
                    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(configEmail);
                    // @Service call
                    ApproveEmail approveEmail = (ApproveEmail) context.getBean("approveEmail");
                    String toAddr = result.getUser().getEmail();
                    String fromAddr = "dedhiadimpi@gmail.com";
                    // email subject
                    String subject = "Bad news from QuestonsAreWelcome!";
                    // email body
                    String body = "Hi "+result.getUser().getFirstname()+" "+result.getUser().getLastname()+",\n\nYour Question '"+result.getQuestion_title()+"' is Rejected with following reason\n"+result.getReject_comment();
                    approveEmail.sendEmail(toAddr, fromAddr, subject, body);
                    mv.addObject("reject","");
                    mv.addObject("error","Question Rejected successfully!");
                    return mv;
                }else{
                    mv.addObject("reject",reject_comment);
                    mv.addObject("error","Something went wrong. Please try again later!");
                    return mv;
                }
            }
        }
        }else if(req.getParameter("option").equals("answer")){
            if(req.getParameter("action").equals("Approve")){
            long answer_id = Long.parseLong(req.getParameter("answer_id"));
            Answer result = approverDao.approveAnswers(answer_id, approver,"");
            ArrayList<Answer> answerList = approverDao.getAnswerListForApproval();
            mv = new ModelAndView("approve-answers");
            mv.addObject("answerList",answerList);
            mv.addObject("reject","");
            if(result != null){
                String configEmail = "email-bean.xml";
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(configEmail);
		// @Service call
		ApproveEmail approveEmail = (ApproveEmail) context.getBean("approveEmail");
		String toAddr = result.getUser().getEmail();
		String fromAddr = "dedhiadimpi@gmail.com";
		// email subject
		String subject = "Good news from QuestonsAreWelcome!";
		// email body
		String body = "Hi "+result.getUser().getFirstname()+" "+result.getUser().getLastname()+",\n\nYour Answer '"+result.getAnswer_description()+"' for Question '"+result.getQuestion().getQuestion_title()+"' is Approved and Posted";
		approveEmail.sendEmail(toAddr, fromAddr, subject, body);
                mv.addObject("error","Answer Approved successfully!");
                return mv;
            }else{
               mv.addObject("error","Something went wrong. Please try again later!");
               return mv;  
            }
            
        }else if(req.getParameter("action").equals("Reject")){
            String reject_comment = req.getParameter("reject_comment");
            if(reject_comment == null || reject_comment == "" || reject_comment.trim().length() < 5 || reject_comment.trim().length() > 4000){
                ArrayList<Answer> answerList = approverDao.getAnswerListForApproval();
                mv = new ModelAndView("approve-answers");
                mv.addObject("answerList",answerList);
                mv.addObject("reject",reject_comment);
                mv.addObject("error","Please enter a rejection reason");
                return mv;
            }else{
                long answer_id = Long.parseLong(req.getParameter("answer_id"));
                Answer result = approverDao.approveAnswers(answer_id, approver,reject_comment);
                ArrayList<Answer> answerList = approverDao.getAnswerListForApproval();
                mv = new ModelAndView("approve-answers");
                mv.addObject("answerList",answerList);
                if(result != null){
                     String configEmail = "email-bean.xml";
                    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(configEmail);
                    // @Service call
                    ApproveEmail approveEmail = (ApproveEmail) context.getBean("approveEmail");
                    String toAddr = result.getUser().getEmail();
                    String fromAddr = "dedhiadimpi@gmail.com";
                    // email subject
                    String subject = "Bad news from QuestonsAreWelcome!";
                    // email body
                    String body = "Hi "+result.getUser().getFirstname()+" "+result.getUser().getLastname()+",\n\nYour Answer '"+result.getAnswer_description()+"' for Question '"+result.getQuestion().getQuestion_title()+"' is Rejected with following reason\n"+result.getReject_comment();
                    approveEmail.sendEmail(toAddr, fromAddr, subject, body);
                    mv.addObject("reject","");
                    mv.addObject("error","Answer Rejected successfully!");
               return mv;  
                }else{
                    mv.addObject("reject",reject_comment);
                    mv.addObject("error","Something went wrong. Please try again later!");
                    return mv;  
                }
            }
            
        }
        }
        
        return mv;
    }
    
}

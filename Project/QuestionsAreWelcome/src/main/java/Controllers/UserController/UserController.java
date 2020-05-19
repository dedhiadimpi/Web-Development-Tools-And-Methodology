/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.UserController;

import Dao.AnswerDao.AnswerDao;
import Dao.ApproverDao.ApproverDao;
import Dao.DomainDao.DomainDao;
import Dao.LikesDao.LikesDao;
import Dao.QuestionDao.QuestionDao;
import Models.Answer.Answer;
import Models.Approver.Approver;
import Models.Domain.Domain;
import Models.Likes.Likes;
import Models.Question.Question;
import Models.User.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author dedhi
 */
public class UserController extends AbstractController{

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        String option = request.getParameter("option");
        if(option.equals("addQuestion")){
            Question question = new Question();
            QuestionDao questionDao = new QuestionDao();
            DomainDao domainDao = new DomainDao();
            String title = request.getParameter("title").trim();
            String desc = request.getParameter("desc").trim();
            List<Question> qList = questionDao.checkQuestion(title,desc);
            if(qList.size() > 0){
                ArrayList<Domain> domainList = domainDao.getDomainList();
                mv = new ModelAndView("add-question");
                mv.addObject("domainList",domainList);
                mv.addObject("title",title);
                mv.addObject("desc",desc);
                mv.addObject("error","Question already exist!");
                return mv;
            }
            if(title.length() < 5 || title.length() > 500 || desc.length() < 10 || desc.length() > 4000){
                ArrayList<Domain> domainList = domainDao.getDomainList();
                mv = new ModelAndView("add-question");
                mv.addObject("domainList",domainList);
                mv.addObject("title",title);
                mv.addObject("desc",desc);
                mv.addObject("error","Title cannot be less than 5 and more than 500 characters! Description cannot be less than 10 or more than 4000 characters!");
                return mv;
            }
            User user = (User) request.getSession().getAttribute("user");
            question.setQuestion_title(title.trim());
            question.setQuestion_description(desc.trim());
            question.setDomain(domainDao.getDomain(Integer.parseInt(request.getParameter("domain"))));
            question.setUser(user);
            ApproverDao approverDao = new ApproverDao();
            question.setApprover(approverDao.getDummyApprover());
            question.setNo_of_likes(0);
            question.setNo_of_dislikes(0);
            question.setCreated_on(new Date());
            int result = questionDao.addQuestion(question);
            if(result == 1){
                ArrayList<Domain> domainList = domainDao.getDomainList();
                mv = new ModelAndView("add-question");
                mv.addObject("domainList",domainList);
                mv.addObject("title","");
                mv.addObject("desc","");
                mv.addObject("error","Your Question is sent for approval. You will receive an email once it's approved!");
                return mv;
            }else{
               ArrayList<Domain> domainList = domainDao.getDomainList();
                mv = new ModelAndView("add-question");
                mv.addObject("domainList",domainList);
                mv.addObject("title",title);
                mv.addObject("desc",desc);
                mv.addObject("error","Something went wrong. Please try again later!");
                return mv;
            }
        }else if(option.equals("addAnswer")){
            int question_id =Integer.parseInt(request.getParameter("question_id"));
            QuestionDao questionDao = new QuestionDao();
            AnswerDao answerDao = new AnswerDao();
            Question question = questionDao.getQuestion(question_id);
            String desc = request.getParameter("desc").trim();
            List<Answer> aList = answerDao.checkAnswer(request.getParameter("desc"));
            if(aList.size() >0){
                mv = new ModelAndView("add-answer");
                mv.addObject("question", question);
                mv.addObject("desc",desc);
                mv.addObject("error","Answer already exist!");
                return mv; 
            }
            if(desc.length() < 10 || desc.length() > 4000){
                mv = new ModelAndView("add-answer");
                mv.addObject("question", question);
                mv.addObject("desc",desc);
                mv.addObject("error","Description cannot be less than 10 or more than 4000 characters!");
                return mv; 
            }
            Answer answer = new Answer();
            answer.setAnswer_description(request.getParameter("desc"));
            answer.setUser((User) request.getSession().getAttribute("user"));
            ApproverDao approverDao = new ApproverDao();
            answer.setApprover(approverDao.getDummyApprover());
            answer.setQuestion(question);
            answer.setNo_of_likes(0);
            answer.setNo_of_dislikes(0);
            answer.setCreated_on(new Date());
            
            int result = answerDao.addAnswer(answer);
            if(result == 1){
                mv = new ModelAndView("add-answer");
                mv.addObject("question", question);
                mv.addObject("desc","");
                mv.addObject("error","Your Answer is sent for approval. You will receive an email once its approved!");
                return mv;
            }else{
                mv = new ModelAndView("add-answer");
                mv.addObject("question", question);
                mv.addObject("desc",desc);
                mv.addObject("error","Something went wrong. Please try again later!");
                return mv; 
            }
        }else if(option.equals("editAnswer")){
            AnswerDao answerDao = new AnswerDao();
            int r = 0;
            String desc = request.getParameter("desc").trim();
            List<Answer> aList = answerDao.checkAnswer(desc);
            for(Answer a: aList){
                if(a.getAnswer_id() != Long.parseLong(request.getParameter("answer_id"))){
                    r = 1;
                    break;
                }
            }
            if(r == 1){
                Answer answer = answerDao.getAnswer(Long.parseLong(request.getParameter("answer_id")));
                mv = new ModelAndView("edit-answer");
                mv.addObject("answer_id",Long.parseLong(request.getParameter("answer_id")));
                mv.addObject("answer_desc",answer.getAnswer_description());
                mv.addObject("error","Answer already exist!");
                return mv;
            }
            if(desc.length() < 10 || desc.length() > 4000){
                Answer answer = answerDao.getAnswer(Long.parseLong(request.getParameter("answer_id")));
                mv = new ModelAndView("edit-answer");
                mv.addObject("answer_id",Long.parseLong(request.getParameter("answer_id")));
                mv.addObject("answer_desc",answer.getAnswer_description());
                mv.addObject("error","Description cannot be less than 10 and greater than 4000 characters!");
                return mv;
            }
            int result = answerDao.updateAnswer(Long.parseLong(request.getParameter("answer_id")), request.getParameter("desc"));
            User user = (User) request.getSession().getAttribute("user");
            List<Answer> answers = answerDao.getMyAnswerList(user.getUser_id());
            HashMap<Question, ArrayList<Answer>> answerList = new HashMap<>();
            for(Answer a : answers){
                if(answerList.containsKey(a.getQuestion())){
                    answerList.get(a.getQuestion()).add(a);
                }else{
                    ArrayList<Answer> ans = new ArrayList<Answer>();
                    ans.add(a);
                    answerList.put(a.getQuestion(), ans);
                }
            }
            mv = new ModelAndView("view-my-answers");
            mv.addObject("answerList",answerList);
            if(result == 1){
                mv.addObject("error","Your answer is sent for approval. You will receive an email once approved!");
            }else{
               mv.addObject("error","Something went wrong. Please try again later!");
            }
            
            return mv;
            
        }else if(option.equals("editMyQuestion")){
            String title = request.getParameter("title").trim();
            String desc = request.getParameter("desc").trim();
            QuestionDao questionDao = new QuestionDao();
            List<Question> qList = questionDao.checkQuestion(title,desc);
            int r = 0;
            for(Question q: qList){
                if(q.getQuestion_id() != Long.parseLong(request.getParameter("question_id"))){
                    r = 1;
                    break;
                }
            }
            if( r == 1){
                User user = (User) request.getSession().getAttribute("user");
                Question question = questionDao.getQuestion(Long.parseLong(request.getParameter("question_id")));
                DomainDao domainDao = new DomainDao();
                ArrayList<Domain> domainList = domainDao.getDomainList();
                mv = new ModelAndView("edit-question");
                mv.addObject("question", question);
                mv.addObject("domainList", domainList);
                mv.addObject("error","Question already exist!");
                return mv;
            }
            if(title.length() < 5 || title.length() > 500 || desc.length() < 10 || desc.length() > 4000){
                Question question = questionDao.getQuestion(Long.parseLong(request.getParameter("question_id")));
                DomainDao domainDao = new DomainDao();
                ArrayList<Domain> domainList = domainDao.getDomainList();
                mv = new ModelAndView("edit-question");
                mv.addObject("question", question);
                mv.addObject("domainList", domainList);
                mv.addObject("error","Title cannot be less than 5 and more than 500 characters! Description cannot be less than 10 or more than 4000 characters");
                return mv;
            }
            int result = questionDao.updateQuestion(Long.parseLong(request.getParameter("question_id")),request.getParameter("title")
            , request.getParameter("desc"), Long.parseLong(request.getParameter("domain")));
            if(result == 1){
                User user = (User) request.getSession().getAttribute("user");
                List<Question> questionList = questionDao.getMyQuestionList(user.getUser_id());
                DomainDao domainDao = new DomainDao();
                ArrayList<Domain> domainList = domainDao.getDomainList();
                mv = new ModelAndView("view-all-questions");
                mv.addObject("questionList", questionList);
                mv.addObject("action", "myQuestions");
                mv.addObject("domainList",domainList);
//                ApproverDao approverDao = new ApproverDao();
//                Approver approver = approverDao.getDummyApprover();
//                mv.addObject("approver_id",approver.getApprover_id());
                mv.addObject("error","Your Question is sent for approval. You will receive an email once it's approved!");
                return mv;
            }else{
                User user = (User) request.getSession().getAttribute("user");
                Question question = questionDao.getQuestion(Long.parseLong(request.getParameter("question_id")));
                DomainDao domainDao = new DomainDao();
                ArrayList<Domain> domainList = domainDao.getDomainList();
                mv = new ModelAndView("edit-question");
                mv.addObject("question", question);
                mv.addObject("domainList", domainList);
                mv.addObject("error","Something went wrong. Please try again later!");
                return mv;
            }
            
        }else{
            String select = request.getParameter("option");
            User user = (User) request.getSession().getAttribute("user");
            QuestionDao questionDao = new QuestionDao();
            LikesDao likesDao = new LikesDao();
            if(select.equals("like_question")){
                int result = 0;
                long question_id = Long.parseLong(request.getParameter("question_id"));
                Question question = questionDao.getQuestion(question_id);
                Likes like = likesDao.getLikeQuestion(question_id, user.getUser_id());
                if(like == null){
                    like = new Likes();
                    like.setQuestion(question);
                    like.setUser(user);
                    like.setType("like");
                    int likes = question.getNo_of_likes();
                    question.setNo_of_likes(likes+1);
                    result = questionDao.addLikes(question);
                }else if(like.getType().equals("dislike")){
                    like.setType("like");
                    int dislikes = question.getNo_of_dislikes();
                    question.setNo_of_dislikes(dislikes-1);
                    int likes = question.getNo_of_likes();
                    question.setNo_of_likes(likes+1);
                    result = questionDao.addLikes(question);
                }
                result = likesDao.addLikes(like);
                if(result == 1){
                    List<Question> questionList = questionDao.getQuestionList();
                    mv = new ModelAndView("view-all-questions");
                    mv.addObject("questionList", questionList);
                    mv.addObject("action", "allQuestions");
                    return mv;
                }else{
                    mv = new ModelAndView("login-fail");
                    return mv;
                }
            }else if(select.equals("dislike_question")){
                int result = 0;
                long question_id = Long.parseLong(request.getParameter("question_id"));
                Question question = questionDao.getQuestion(question_id);
                Likes like = likesDao.getLikeQuestion(question_id, user.getUser_id());
                if(like == null){
                    like = new Likes();
                    like.setQuestion(question);
                    like.setUser(user);
                    like.setType("dislike");
                    int dislikes = question.getNo_of_dislikes();
                    question.setNo_of_dislikes(dislikes+1);
                    result = questionDao.addLikes(question);
                }else if(like.getType().equals("like")){
                    like.setType("dislike");
                    int dislikes = question.getNo_of_dislikes();
                    question.setNo_of_dislikes(dislikes+1);
                    int likes = question.getNo_of_likes();
                    question.setNo_of_likes(likes-1);
                    result = questionDao.addLikes(question);
                }
                result = likesDao.addLikes(like);
                if(result == 1){
                    List<Question> questionList = questionDao.getQuestionList();
                    mv = new ModelAndView("view-all-questions");
                    mv.addObject("questionList", questionList);
                    mv.addObject("action", "allQuestions");
                    return mv;
                }else{
                    mv = new ModelAndView("login-fail");
                    return mv;
                }
                
            }else if(select.equals("like_answer")){
                int result = 0;
                long answer_id = Long.parseLong(request.getParameter("answer_id"));
                AnswerDao answerDao = new AnswerDao();
                Answer answer = answerDao.getAnswer(answer_id);
                Likes like = likesDao.getLikeAnswer(answer_id, user.getUser_id());
                if(like == null){
                    like = new Likes();
                    like.setAnswer(answer);
                    like.setUser(user);
                    like.setType("like");
                    int likes = answer.getNo_of_likes();
                    answer.setNo_of_likes(likes+1);
                    result = answerDao.addLikes(answer);
                    
                }else if(like.getType().equals("dislike")){
                    like.setType("like");
                    int dislikes = answer.getNo_of_dislikes();
                    answer.setNo_of_dislikes(dislikes-1);
                    int likes = answer.getNo_of_likes();
                    answer.setNo_of_likes(likes+1);
                    result = answerDao.addLikes(answer);  
                }
                result = likesDao.addLikes(like);
                if(result == 1){
                    List<Question> questionList = questionDao.getQuestionList();
                    mv = new ModelAndView("view-all-questions");
                    mv.addObject("questionList", questionList);
                    mv.addObject("action", "allQuestions");
                    return mv;
                }else{
                    mv = new ModelAndView("login-fail");
                    return mv;
                }
                
            }else if(select.equals("dislike_answer")){
                int result = 0;
                long answer_id = Long.parseLong(request.getParameter("answer_id"));
                AnswerDao answerDao = new AnswerDao();
                Answer answer = answerDao.getAnswer(answer_id);
                Likes like = likesDao.getLikeAnswer(answer_id, user.getUser_id());
                if(like == null){
                    like = new Likes();
                    like.setAnswer(answer);
                    like.setUser(user);
                    like.setType("dislike");
                    int dislikes = answer.getNo_of_dislikes();
                    answer.setNo_of_dislikes(dislikes+1);
                    result = answerDao.addLikes(answer);
                    
                }else if(like.getType().equals("like")){
                    like.setType("dislike");
                    int dislikes = answer.getNo_of_dislikes();
                    answer.setNo_of_dislikes(dislikes+1);
                    int likes = answer.getNo_of_likes();
                    answer.setNo_of_likes(likes-1);
                    result = answerDao.addLikes(answer);  
                }
                result = likesDao.addLikes(like);
                 
                if(result == 1){
                    List<Question> questionList = questionDao.getQuestionList();
                    mv = new ModelAndView("view-all-questions");
                    mv.addObject("questionList", questionList);
                    mv.addObject("action", "allQuestions");
                    return mv;
                }else{
                    mv = new ModelAndView("login-fail");
                    return mv;
                }
            }
        }
        return mv;
    }
    
}

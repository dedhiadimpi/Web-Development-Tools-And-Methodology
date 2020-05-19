/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.RedirectController;

import Dao.AnswerDao.AnswerDao;
import Dao.ApproverDao.ApproverDao;
import Dao.DomainDao.DomainDao;
import LoginDao.LoginDao;
import Dao.QuestionDao.QuestionDao;
import Dao.UserDao.UserDao;
import Models.Answer.Answer;
import Models.Approver.Approver;
import Models.Domain.Domain;
import Models.Question.Question;
import Models.User.User;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import Helper.Validation;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author dedhi
 */
@Controller
public class RedirectController{
//    
//    @Override
//    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ModelAndView mv = null;
//        mv = new ModelAndView("add-question");
//        return mv;
//    }
    
    @RequestMapping(value = "/redirect-add-question.htm")
    protected ModelAndView getAddQuestionModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        DomainDao domainDao = new DomainDao();
        ArrayList<Domain> domainList = domainDao.getDomainList();
        mv = new ModelAndView("add-question");
        mv.addObject("domainList",domainList);
        mv.addObject("error","");
        mv.addObject("title","");
        mv.addObject("desc","");
        return mv;
    }
    
    @RequestMapping(value = "/redirect-user-home.htm")
    protected ModelAndView getUserHomeModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        mv = new ModelAndView("user-home");
        return mv;
    }
    
    @RequestMapping(value = "/redirect-admin-home.htm")
    protected ModelAndView getAdminHomeModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        mv = new ModelAndView("admin-home");
        return mv;
    }
    
    @RequestMapping(value = "/redirect-approver-home.htm")
    protected ModelAndView getApproverHomeModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        mv = new ModelAndView("approver-home");
        return mv;
    }
    
    @RequestMapping(value="/redirect-get-question-list.htm")
    protected ModelAndView getQuestionList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        QuestionDao questionDao = new QuestionDao();
        List<Question> questionList = questionDao.getQuestionList();
        DomainDao domainDao = new DomainDao();
        ArrayList<Domain> domainList = domainDao.getDomainList();
        mv = new ModelAndView("view-all-questions");
        mv.addObject("questionList", questionList);
        mv.addObject("action", "allQuestions");
        mv.addObject("domainList",domainList);
        mv.addObject("error","");
        return mv;
    }
    
    @RequestMapping(value="/redirect-get-approver-question-list.htm")
    protected ModelAndView getApproverQuestionList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        QuestionDao questionDao = new QuestionDao();
        List<Question> questionList = questionDao.getApproverQuestionList();
        DomainDao domainDao = new DomainDao();
        ArrayList<Domain> domainList = domainDao.getDomainList();
        mv = new ModelAndView("view-all-questions");
        mv.addObject("questionList", questionList);
        mv.addObject("action", "approverQuestions");
        mv.addObject("domainList",domainList);
        mv.addObject("error","");
        return mv;
    }
    
    @RequestMapping(value="/redirect-get-my-question-list.htm")
    protected ModelAndView getMyQuestionList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        User user = (User) request.getSession().getAttribute("user");
        QuestionDao questionDao = new QuestionDao();
        List<Question> questionList = questionDao.getMyQuestionList(user.getUser_id());
        DomainDao domainDao = new DomainDao();
        ArrayList<Domain> domainList = domainDao.getDomainList();
        mv = new ModelAndView("view-all-questions");
        mv.addObject("questionList", questionList);
        mv.addObject("action", "myQuestions");
        mv.addObject("domainList",domainList);
//        ApproverDao approverDao = new ApproverDao();
//        Approver approver = approverDao.getDummyApprover();
        mv.addObject("error","");
        return mv;
    }
    
    @RequestMapping(value="/redirect-search-list.htm")
    protected ModelAndView getSearchQuestionList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        long domain_id = Long.parseLong(request.getParameter("domain"));
        QuestionDao questionDao = new QuestionDao();
        List<Question> questionList = questionDao.searchQuestionList(request.getParameter("keyword"), domain_id);
        DomainDao domainDao = new DomainDao();
        ArrayList<Domain> domainList = domainDao.getDomainList();
        if(request.getParameter("action").equals("allQuestions")){
            mv = new ModelAndView("view-all-questions");
            mv.addObject("questionList", questionList);
            mv.addObject("action", "allQuestions");
            mv.addObject("domainList",domainList);
            mv.addObject("error","");
        return mv;
        }else if(request.getParameter("action").equals("myQuestions")){
            mv = new ModelAndView("view-all-questions");
            mv.addObject("questionList", questionList);
            mv.addObject("action", "myQuestions");
            mv.addObject("domainList",domainList);
            mv.addObject("error","");
        return mv;
        
        }else if(request.getParameter("action").equals("approverQuestions")){
            List<Question> questions = questionDao.searchApproverQuestionList(request.getParameter("keyword"), domain_id);
            mv = new ModelAndView("view-all-questions");
            mv.addObject("questionList", questions);
            mv.addObject("action", "approverQuestions");
            mv.addObject("domainList",domainList);
            mv.addObject("error","");
            return mv;
        }
        return mv;
       
    }
    
    @RequestMapping(value="/redirect-edit-question.htm")
    protected ModelAndView editMyQuestion(@RequestParam("question_id") long question_id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        User user = (User) request.getSession().getAttribute("user");
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.getQuestion(question_id);
        DomainDao domainDao = new DomainDao();
        ArrayList<Domain> domainList = domainDao.getDomainList();
        mv = new ModelAndView("edit-question");
        mv.addObject("question", question);
        mv.addObject("domainList", domainList);
        mv.addObject("error","");
        return mv;
    }
    
    @RequestMapping(value="/redirect-get-my-answer-list.htm")
    protected ModelAndView getMyAnswerList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        User user = (User) request.getSession().getAttribute("user");
        AnswerDao answerDao = new AnswerDao();
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
        mv.addObject("error","");
        return mv;
    }
    
    @RequestMapping(value = "redirect-add-answer.htm")
    protected ModelAndView getAddAnswerModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        QuestionDao questionDao = new QuestionDao();
        long id = Long.parseLong(request.getParameter("id"));
        Question question = questionDao.getQuestion(id);
        mv = new ModelAndView("add-answer");
        mv.addObject("question", question);
        mv.addObject("desc","");
        mv.addObject("error","");
        return mv;
    }
    
    @RequestMapping(value = "redirect-my-profile.htm")
    protected ModelAndView getMyProfileModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        String role = (String) request.getSession().getAttribute("role");
        if(role.equals("user")){
            User myProfile = (User) request.getSession().getAttribute("user");
            mv = new ModelAndView("my-profile");
            mv.addObject("myProfile", myProfile);
            mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
            mv.addObject("disabled","disabled");
            mv.addObject("view","true");
            mv.addObject("error","");
        }else if(role.equals("approver")){
            Approver myProfile = (Approver) request.getSession().getAttribute("user");
            mv = new ModelAndView("my-profile");
            mv.addObject("myProfile", myProfile);
            mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
            mv.addObject("disabled","disabled");
            mv.addObject("view","true");
            mv.addObject("error","");
        }
        return mv;
    }
    
    @RequestMapping(value = "redirect-edit-myProfile.htm")
    protected ModelAndView editMyProfileModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        String role = (String) request.getSession().getAttribute("role");
        if(role.equals("user")){
            User myProfile = (User) request.getSession().getAttribute("user");
            mv = new ModelAndView("my-profile");
            mv.addObject("myProfile", myProfile);
            mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
            mv.addObject("disabled","");
            mv.addObject("view","false");
            mv.addObject("error","");
        }else if(role.equals("approver")){
            Approver myProfile = (Approver) request.getSession().getAttribute("user");
            mv = new ModelAndView("my-profile");
            mv.addObject("myProfile", myProfile);
            mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
            mv.addObject("disabled","");
            mv.addObject("view","false");
            mv.addObject("error","");
        }
        
        return mv;
    }
    
    @RequestMapping(value = "redirect-save-myProfile.htm")
    protected ModelAndView saveMyProfileModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Validation validation = new Validation();
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        long user_id = Long.parseLong(request.getParameter("user_id"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String gender = request.getParameter("gender");
        Date date = new Date();
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int result = 0;
        if(request.getSession().getAttribute("role").equals("user")){
            User myProfile = (User) request.getSession().getAttribute("user");
            try{
                date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateofbirth").substring(0, 9));
            }catch(Exception e){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter correct date!");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }
            if(date.compareTo(new Date()) > 0){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter a date less than today's date!");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }
            if(gender.equals("")){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter your Gender!");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }else if(!validation.onlyLettersValidation(firstname) || firstname.trim().length() < 0 || firstname.trim().length() > 50){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter valid First Name! First name cannot have more than 50 letters");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }else if(!validation.onlyLettersValidation(lastname) || lastname.trim().length() < 0 || lastname.trim().length() > 50){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter valid Last Name! Last name cannot have more than 50 letters");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }else if(address.trim().length() < 8 || address.trim().length() > 100){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter valid Address! Address cannot be less than 8 letters or more than 100 letters!");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }else if(!validation.phoneNumberValidation(phone) || phone.trim().length() > 20){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter valid Phone Number!");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }
            UserDao userDao = new UserDao();
            result = userDao.updateUser(user_id, firstname, lastname, gender, date, address, email, phone);
        }else if(request.getSession().getAttribute("role").equals("approver")){
            Approver myProfile = (Approver) request.getSession().getAttribute("user");
            try{
                date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateofbirth").substring(0, 9));
            }catch(Exception e){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter correct date!");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }
            if(date.compareTo(new Date()) > 0){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter a date less than today's date!");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }
            if(gender.equals("")){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter your Gender!");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }else if(!validation.onlyLettersValidation(firstname) || firstname.trim().length() < 0 || firstname.trim().length() > 50){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter valid First Name! First name cannot have more than 50 letters");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }else if(!validation.onlyLettersValidation(lastname) || lastname.trim().length() < 0 || lastname.trim().length() > 50){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter valid Last Name! Last name cannot have more than 50 letters");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }else if(address.trim().length() < 8 || address.trim().length() > 100){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter valid Address! Address cannot be less than 8 letters or more than 100 letters!");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }else if(!validation.phoneNumberValidation(phone) || phone.trim().length() > 20){
                mv = new ModelAndView("my-profile");
                mv.addObject("error","Please enter valid Phone Number!");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","");
                mv.addObject("view","false");
                return mv;
            }
            ApproverDao approverDao = new ApproverDao();
            result = approverDao.updateApprover(user_id, firstname, lastname, gender, date, address, email, phone);
        }
            String role = (String) request.getSession().getAttribute("role");
            if(role.equals("user")){
                User myProfile = (User) request.getSession().getAttribute("user");
                
                if(result == 1){
                    myProfile.setFirstname(firstname);
                    myProfile.setLastname(lastname);
                    myProfile.setGender(gender);
                    myProfile.setDate_of_birth(date);
                    myProfile.setAddress(address);
                    myProfile.setEmail(email);
                    myProfile.setPhone_number(phone);
                }
                mv = new ModelAndView("my-profile");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","disabled");
                mv.addObject("view","true");
                if(result == 1){
                    mv.addObject("error","Profile updated successfully!");
                }else{
                    mv.addObject("error","Something went wrong. Please try again later!");
                }
            }else if(role.equals("approver")){
                Approver myProfile = (Approver) request.getSession().getAttribute("user");
                if(result == 1){
                    myProfile.setFirstname(firstname);
                    myProfile.setLastname(lastname);
                    myProfile.setGender(gender);
                    myProfile.setDate_of_birth(date);
                    myProfile.setAddress(address);
                    myProfile.setEmail(email);
                    myProfile.setPhone_number(phone);
                }
                mv = new ModelAndView("my-profile");
                mv.addObject("myProfile", myProfile);
                mv.addObject("date",new SimpleDateFormat("yyyy-MM-dd").format(myProfile.getDate_of_birth()));
                mv.addObject("disabled","disabled");
                mv.addObject("view","true");
                if(result == 1){
                    mv.addObject("error","Profile updated successfully!");
                }else{
                    mv.addObject("error","Something went wrong. Please try again later!");
                }
            }
            return mv;
    }
    
    @RequestMapping(value = "redirect-edit-answer.htm")
    protected ModelAndView editAnswerModel(@RequestParam("answer_id") long answer_id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }    
        AnswerDao answerDao = new AnswerDao();
            Answer answer = answerDao.getAnswer(answer_id);
            mv = new ModelAndView("edit-answer");
            mv.addObject("answer_id",answer_id);
            mv.addObject("answer_desc",answer.getAnswer_description());
            mv.addObject("error","");
            return mv;
        }
    
    @RequestMapping(value = "redirect-delete-answer.htm")
    protected ModelAndView deleteAnswerModel(@RequestParam("answer_id") long answer_id, HttpServletRequest request, HttpServletResponse response) throws Exception{
            ModelAndView mv = null;
            if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
            AnswerDao answerDao = new AnswerDao();
            int result = answerDao.deleteAnswer(answer_id);
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
                mv.addObject("error","Answer deleted successfully!");
            }else{
                mv.addObject("error","Something went wrong. Please try again later!");
            }
            return mv;
        }
    
    @RequestMapping(value = "redirect-delete-question.htm")
    protected ModelAndView deleteQuestionModel(@RequestParam("question_id") long question_id, HttpServletRequest request, HttpServletResponse response) throws Exception{
            ModelAndView mv = null;
            if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
            QuestionDao questionDao = new QuestionDao();
            int result = questionDao.deleteQuestion(question_id);
            if(result == 1){
                User user = (User) request.getSession().getAttribute("user");
                List<Question> questionList = questionDao.getMyQuestionList(user.getUser_id());
                DomainDao domainDao = new DomainDao();
                ArrayList<Domain> domainList = domainDao.getDomainList();
                mv = new ModelAndView("view-all-questions");
                mv.addObject("questionList", questionList);
                mv.addObject("action", "myQuestions");
                mv.addObject("domainList",domainList);
                mv.addObject("error","Question deleted successfully!");
                return mv;
            }else{
                User user = (User) request.getSession().getAttribute("user");
                List<Question> questionList = questionDao.getMyQuestionList(user.getUser_id());
                DomainDao domainDao = new DomainDao();
                ArrayList<Domain> domainList = domainDao.getDomainList();
                mv = new ModelAndView("view-all-questions");
                mv.addObject("questionList", questionList);
                mv.addObject("action", "myQuestions");
                mv.addObject("domainList",domainList);
                mv.addObject("error","Something went wrong. Please try again later!");
                return mv;
            }
        }
    
    @RequestMapping(value = "redirect-approve-questions.htm")
    protected ModelAndView approveQuestionsModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
            ApproverDao approverDao = new ApproverDao();
            ArrayList<Question> questionList = approverDao.getQuestionListForApproval();
            mv = new ModelAndView("approve-questions");
            mv.addObject("questionList",questionList);
            mv.addObject("reject","");
            if(questionList.isEmpty()){
                mv.addObject("error","No pending approvals");
            }else{
                mv.addObject("error","");
            }
            
            return mv;
        }
    
    @RequestMapping(value = "redirect-approve-answers.htm")
    protected ModelAndView approveAnswersModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
            ApproverDao approverDao = new ApproverDao();
            ArrayList<Answer> answerList = approverDao.getAnswerListForApproval();
            mv = new ModelAndView("approve-answers");
            mv.addObject("answerList",answerList);
            mv.addObject("reject","");
            if(answerList.isEmpty()){
                mv.addObject("error","No pending approvals");
            }else{
                mv.addObject("error","");
            }
            return mv;
        }
    
    @RequestMapping(value = "redirect-register-user.htm")
    protected ModelAndView registerUserModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
            mv = new ModelAndView("register-user");
            mv.addObject("firstname","");
            mv.addObject("lastname","");
            mv.addObject("date", new Date());
            mv.addObject("address","");
            mv.addObject("email","");
            mv.addObject("phone","");
            mv.addObject("username","");
            mv.addObject("password","");
            mv.addObject("error","");
            return mv;
        }
    
    @RequestMapping(value = "redirect-add-approver.htm")
    protected ModelAndView addApproverModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
            mv = new ModelAndView("add-approver");
            mv.addObject("firstname","");
            mv.addObject("lastname","");
            mv.addObject("date", new Date());
            mv.addObject("address","");
            mv.addObject("email","");
            mv.addObject("phone","");
            mv.addObject("username","");
            mv.addObject("password","");
            mv.addObject("error","");
            return mv;
        }
    
}

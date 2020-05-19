/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.AdminController;

import Dao.DashboardDao.DashboardDao;
import Models.User.User;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author dedhi
 */
public class AdminController extends AbstractController{

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
        DashboardDao dashboardDao = new DashboardDao();
            Map<String,ArrayList<Object>> questionMap = dashboardDao.getUserDetailsWithQuestions();
            Map<String,ArrayList<Object>> answerMap = dashboardDao.getUserDetailsWithAnswers();
            Map<String, ArrayList<Integer>> domainMap = dashboardDao.getDomainDetails();
        if(request.getParameter("option").equals("view")){
            mv = new ModelAndView("view-dashboard");
            mv.addObject("questionMap",questionMap);
            mv.addObject("answerMap",answerMap);
            mv.addObject("domainMap",domainMap);
        
        return mv;
        }if(request.getParameter("option").equals("pdfview")){
            mv = new ModelAndView("pdfview-dashboard");
            mv.addObject("questionMap",questionMap);
            mv.addObject("answerMap",answerMap);
            mv.addObject("domainMap",domainMap);
        
        return mv;
        }
        
        return mv;
    }
    
}

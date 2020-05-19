/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.LoginController;

import Helper.Validation;
import Models.Admin.Admin;
import Models.Approver.Approver;
import LoginDao.LoginDao;
import Models.User.User;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author dedhi
 */
public class LoginController extends AbstractController{

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        Validation validation = new Validation();
        String option = request.getParameter("option") == null ? "" : request.getParameter("option");
        if (option == null || option == "") {
            mv = new ModelAndView("login");
            return mv;
        } else if(option.equals("login")){
            String role = request.getParameter("role") == null? "none": request.getParameter("role");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            LoginDao loginDao = new LoginDao();
            if(role.equals("admin")){
                ArrayList<Admin> admin = loginDao.getAdminLoginDetails(username, password);
                if(admin.size() > 0){
                    request.getSession().setAttribute("user", admin.get(0));
                    request.getSession().setAttribute("role", "admin");
                    mv = new ModelAndView("admin-home", "role", admin.get(0));
                    return mv;
                }else{
                    mv = new ModelAndView("login");
                    mv.addObject("error","User or Password is Invalid. Kindly Register if you don't have an account!");
                    return mv;
                }
            }else if(role.equals("approver")){
                ArrayList<Approver> approver = loginDao.getApproverLoginDetails(username, password);
                if(approver.size() > 0){
                    request.getSession().setAttribute("user", approver.get(0));
                    request.getSession().setAttribute("role", "approver");
                    mv = new ModelAndView("approver-home", "role", approver.get(0));
                    return mv;
                }else{
                    mv = new ModelAndView("login");
                    mv.addObject("error","User or Password is Invalid. Kindly Register if you don't have an account!");
                    return mv;
                }
            }else if(role.equals("user")){
                ArrayList<User> user = loginDao.getUserLoginDetails(username, password);
                if(user.size() > 0){
                    request.getSession().setAttribute("user", user.get(0));
                    request.getSession().setAttribute("role", "user");                  
                    mv = new ModelAndView("user-home", "role", user.get(0));
                    return mv;
                }else{
                    mv = new ModelAndView("login");
                    mv.addObject("error","User or Password is Invalid. Kindly Register if you don't have an account!");
                    return mv;
                }
                
            }else{
                mv = new ModelAndView("login");
                mv.addObject("error","Please specify a role!");
                return mv;
            }
        }else if(option.equals("register")){
            LoginDao loginDao = new LoginDao();
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String gender = request.getParameter("gender") == null ? "" : request.getParameter("gender");
            Date date = null;
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try{
                date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateofbirth").substring(0, 9));
            }catch(Exception e){
                mv = new ModelAndView("register-user");
                mv.addObject("error","Please enter correct date!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new Date());
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }
            if(date.compareTo(new Date()) > 0){
                mv = new ModelAndView("register-user");
                mv.addObject("error","Please enter a date less than today's date!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }
            if(gender.equals("")){
                mv = new ModelAndView("register-user");
                mv.addObject("error","Please enter your Gender!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(!validation.onlyLettersValidation(firstname) || firstname.trim().length() < 0 || firstname.trim().length() > 50){
                mv = new ModelAndView("register-user");
                mv.addObject("error","Please enter valid First Name! First name cannot have more than 50 letters");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(!validation.onlyLettersValidation(lastname) || lastname.trim().length() < 0 || lastname.trim().length() > 50){
                mv = new ModelAndView("register-user");
                mv.addObject("error","Please enter valid Last Name! Last name cannot have more than 50 letters");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(address.trim().length() < 8 || address.trim().length() > 100){
                mv = new ModelAndView("register-user");
                mv.addObject("error","Please enter valid Address! Address cannot be less than 8 letters or more than 100 letters!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(!validation.phoneNumberValidation(phone) || phone.trim().length() > 20){
                mv = new ModelAndView("register-user");
                mv.addObject("error","Please enter valid Phone Number!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(username.trim().length() < 5 || username.trim().length() > 10){
                mv = new ModelAndView("register-user");
                mv.addObject("error","Username should be minimum of 5 characters and maximum of 10 characters!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(validation.usernameWithSpacesValidation(username)){
                mv = new ModelAndView("register-user");
                mv.addObject("error","Please enter valid Username!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(!validation.passwordPatternCorrect(password) || password.trim().length() < 6 || password.trim().length() > 10){
                mv = new ModelAndView("register-user");
                mv.addObject("error","Password should have 1 small-case letter, 1 Capital letter, 1 digit, 1 special character and the length should be between 6-10 characters");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }
            int r = loginDao.checkUser(email, username);
            if(r == 1){
                mv = new ModelAndView("register-user");
                mv.addObject("error","User already exist!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }
            int result = loginDao.registerUser(firstname, lastname, gender, date, address, email, phone, username, password);
            
            if(result == 1){
                mv = new ModelAndView("login","error","User registered successfully!");
                return mv;
            }else {
                mv = new ModelAndView("register-user");
                mv.addObject("error","Something went wrong. Please try again");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }
            
        }else if(option.equals("logout")){
                    if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
            request.getSession().invalidate();
            mv = new ModelAndView("login");
            return mv;
        }else if(option.equals("addApprover")){
                    if(request.getSession().getAttribute("user") == null){
            mv = new ModelAndView("login");
            return mv;
        }
            LoginDao loginDao = new LoginDao();
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String gender = request.getParameter("gender");
            Date date = new Date();
//            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateofbirth").substring(0, 9));
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            try{
                date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateofbirth").substring(0, 9));
            }catch(Exception e){
                mv = new ModelAndView("add-approver");
                mv.addObject("error","Please enter correct date!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new Date());
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }
            if(date.compareTo(new Date()) > 0){
                mv = new ModelAndView("add-approver");
                mv.addObject("error","Please enter a date less than today's date!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }
            if(gender.equals("")){
                mv = new ModelAndView("add-approver");
                mv.addObject("error","Please enter your Gender!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(!validation.onlyLettersValidation(firstname) || firstname.trim().length() < 0 || firstname.trim().length() > 50){
                mv = new ModelAndView("add-approver");
                mv.addObject("error","Please enter valid First Name! First name cannot have more than 50 letters");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(!validation.onlyLettersValidation(lastname) || lastname.trim().length() < 0 || lastname.trim().length() > 50){
                mv = new ModelAndView("add-approver");
                mv.addObject("error","Please enter valid Last Name! Last name cannot have more than 50 letters");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(address.trim().length() < 8 || address.trim().length() > 100){
                mv = new ModelAndView("add-approver");
                mv.addObject("error","Please enter valid Address! Address cannot be less than 8 letters or more than 100 letters!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(!validation.phoneNumberValidation(phone) || phone.trim().length() > 20){
                mv = new ModelAndView("add-approver");
                mv.addObject("error","Please enter valid Phone Number!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(username.trim().length() < 5 || username.trim().length() > 10){
                mv = new ModelAndView("add-approver");
                mv.addObject("error","Username should be minimum of 5 characters and maximum of 10 characters!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(validation.usernameWithSpacesValidation(username)){
                mv = new ModelAndView("add-approver");
                mv.addObject("error","Please enter valid Username!");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }else if(!validation.passwordPatternCorrect(password) || password.trim().length() < 6 || password.trim().length() > 10){
                mv = new ModelAndView("add-approver");
                mv.addObject("error","Password should have 1 small-case letter, 1 Capital letter, 1 digit, 1 special character and the length should be between 6-10 characters");
                mv.addObject("firstname",firstname);
                mv.addObject("lastname",lastname);
                mv.addObject("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
                mv.addObject("address",address);
                mv.addObject("email",email);
                mv.addObject("phone",phone);
                mv.addObject("username",username);
                mv.addObject("password",password);
                return mv;
            }
            
            int result = loginDao.addApprover(firstname, lastname, gender, date, address, email, phone, username, password);
            if(result == 1){
                mv = new ModelAndView("add-approver");
                mv.addObject("firstname","");
                mv.addObject("lastname","");
                mv.addObject("date", new Date());
                mv.addObject("address","");
                mv.addObject("email","");
                mv.addObject("phone","");
                mv.addObject("username","");
                mv.addObject("password","");
                mv.addObject("error","Approved registered successfully!");
                return mv;
            }else {
                mv = new ModelAndView("add-approver");
                mv.addObject("firstname","");
                mv.addObject("lastname","");
                mv.addObject("date", new Date());
                mv.addObject("address","");
                mv.addObject("email","");
                mv.addObject("phone","");
                mv.addObject("username","");
                mv.addObject("password","");
                mv.addObject("error","Something went wrong. Please try again later!");
                return mv;
            }
            
        }
        return mv;
    }

}
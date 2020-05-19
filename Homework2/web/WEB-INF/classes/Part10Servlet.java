
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dedhi
 */
public class Part10Servlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(true);
        String category = req.getParameter("category");
        if(category.equals("books")){
            out.println("<h3>Following items have been added to your shopping cart successfully</h3>");
            out.println("<ul>");
            String book = req.getParameter("book1");
            if(book != null){
                session.setAttribute("book1",book);
                out.println("<li>"+book+"</li>");
            }
            book = req.getParameter("book2");
            if(book != null){
                session.setAttribute("book2",book);
                out.println("<li>"+book+"</li>");
            }
            book = req.getParameter("book3");
            if(book != null){
                session.setAttribute("book3",book);
                out.println("<li>"+book+"</li>");
            }
            book = req.getParameter("book4");
            if(book != null){
                session.setAttribute("book4",book);
                out.println("<li>"+book+"</li>");
            }
            book = req.getParameter("book5");
            if(book != null){
                session.setAttribute("book5",book);
                out.println("<li>"+book+"</li>");
            }
            out.println("</ul>");
        }else if(category.equals("music")){
            out.println("<h3>Following items have been added to your shopping cart successfully</h3>");
            out.println("<ul>");
            String music = req.getParameter("music1");
            if(music != null){
                session.setAttribute("music1",music);
                out.println("<li>"+music+"</li>");
            }
            music = req.getParameter("music2");
            if(music != null){
                session.setAttribute("music2",music);
                out.println("<li>"+music+"</li>");
            }
            music = req.getParameter("music3");
            if(music != null){
                session.setAttribute("music3",music);
                out.println("<li>"+music+"</li>");
            }
            music = req.getParameter("music4");
            if(music != null){
                session.setAttribute("music4",music);
                out.println("<li>"+music+"</li>");
            }
            music = req.getParameter("music5");
            if(music != null){
                session.setAttribute("music5",music);
                out.println("<li>"+music+"</li>");
            }
            out.println("</ul>");
        }else if(category.equals("computers")){
            out.println("<h3>Following items have been added to your shopping cart successfully</h3>");
            out.println("<ul>");
            String computer = req.getParameter("computer1");
            if(computer != null){
                session.setAttribute("computer1",computer);
                out.println("<li>"+computer+"</li>");
            }
            computer = req.getParameter("computer2");
            if(computer != null){
                session.setAttribute("computer2",computer);
                out.println("<li>"+computer+"</li>");
            }
            computer = req.getParameter("computer3");
            if(computer != null){
                session.setAttribute("computer3",computer);
                out.println("<li>"+computer+"</li>");
            }
            computer = req.getParameter("computer4");
            if(computer != null){
                session.setAttribute("computer4",computer);
                out.println("<li>"+computer+"</li>");
            }
            computer = req.getParameter("computer5");
            if(computer != null){
                session.setAttribute("computer5",computer);
                out.println("<li>"+computer+"</li>");
            }
            out.println("</ul>");
        }else if(category.equals("cart")){
            out.println("<h3>My Cart</h3>");
            Enumeration keys = session.getAttributeNames();
            out.println();
            if(!keys.hasMoreElements()){
                out.println("No items in the Cart<br><br>");
            }else{
                out.println("<form action='display' method='GET'>");
                while (keys.hasMoreElements())
                {
                  String key = (String)keys.nextElement();
                  out.println("<input type='checkbox' name='"+key+"' value='"+key+"' />"+session.getAttribute(key));
                  out.println("<br><br>");
                }
                out.println("<input type='hidden' name='category' value='remove'>");
                out.println("<input type='submit' value='Remove'>");
                out.println("</form>");
            }
        }else if(category.equals("remove")){
            Enumeration<String> parameters = req.getParameterNames();
            while(parameters.hasMoreElements()){
                String parameterName = parameters.nextElement();
                session.removeAttribute(parameterName);
            }
            RequestDispatcher disp = req.getRequestDispatcher("/display?category=cart");
            disp.forward(req, resp);
        }
        out.println("[<a href='display?category=cart'>View Cart</a>]");
        out.println("[<a href='part10.jsp?category=books'>Go to Books Page </a>]");
        out.println("[<a href='part10.jsp?category=music'>Go to Music Page </a>]");
        out.println("[<a href='part10.jsp?category=computers'>Go to Computers Page </a>]");
    }
}

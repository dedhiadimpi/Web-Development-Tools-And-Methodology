import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dedhi
 */
public class DisplayPart8Servlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>CSV Data</title>");
        out.println("<style>table,th,td{border: 1px solid black} th{text-align:left} th,td{padding: 5px}</style>");
        out.println("</head>");
        out.println("<body>");
        try {
            //Load the driver
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            // Create a connection to directory given as first command line
    // parameter. Driver properties are passed in URL format
    // (or alternatively in a java.utils.Properties object).
    //
    // A single connection is thread-safe for use by several threads.
    String filename = req.getParameter("file");
    
   String url = "jdbc:relique:csv:" + "C:\\Users\\dedhi\\work\\WEB_DEV\\Homework2\\files" + "?" + "separator=,";
    Connection conn = DriverManager.getConnection(url);
    
    // Create a Statement object to execute the query with.
    // A Statement is not thread-safe.
    Statement stmt = conn.createStatement();
    out.println("<h3>"+filename+".csv data</h3>");
    // Select the ID and NAME columns from sample.csv
    ResultSet results = stmt.executeQuery("SELECT * FROM "+filename);
    out.println("<table>");
    out.println("<tr>");
    out.println("<th>SalesOrderID</th>");
    out.println("<th>RevisionNumber</th>");
    out.println("<th>OrderDate</th>");
    out.println("<th>DueDate</th>");
    out.println("<th>ShipDate</th>");
    out.println("<th>Status</th>");
    out.println("<th>OnlineOrderFlag</th>");
    out.println("<th>SalesOrderNumber</th>");
    out.println("<th>PurchaseOrderNumber</th>");
    out.println("<th>AccountNumber</th>");
    out.println("<th>CustomerID</th>");
    out.println("<th>SalesPersonID</th>");
    out.println("<th>TerritoryID</th>");
    out.println("<th>BillToAddressID</th>");
    out.println("<th>ShipToAddressID</th>");
    out.println("<th>ShipMethodID</th>");
    out.println("<th>CreditCardID</th>");
    out.println("<th>CreditCardApprovalCode</th>");
    out.println("<th>CurrencyRateID</th>");
    out.println("<th>SubTotal</th>");
    out.println("<th>TaxAmt</th>");
    out.println("<th>Freight</th>");
    out.println("<th>TotalDue</th>");
    out.println("<th>Comment</th>");
    out.println("<th>ModifiedDate</th>");
    out.println("</tr>");
    while(results.next()){
        out.println("<tr>");
        int i = 1;
        while(i <= 25){
            out.println("<td>"+results.getString(i)+"</td>");
            i++;
        }
        out.println("</tr>");
    }
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayPart8Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayPart8Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nick C
 */
public class runIsh extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {                                                                                                                   
                                                                                                                    
        response.setContentType("text/html");                                                                           
        String name = "Hello world my niggahs";                                                                         
        java.io.PrintWriter out = response.getWriter();                                                                 
        out.println("<h1>" + name + "</h1>");                                                                           
        out.flush();                                                                                                    
    }                                                                                                                                                                                                                             
}

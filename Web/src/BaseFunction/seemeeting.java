package BaseFunction;
import Tool.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class seemeeting extends HttpServlet{

    public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        user user =(user)session.getAttribute("user");
        if(user.companyname==null)
            response.sendRedirect("entercompany_interface.jsp");
        else{
            response.sendRedirect("meeting_interface.jsp");
        }

    }
}
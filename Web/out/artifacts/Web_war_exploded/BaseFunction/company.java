package BaseFunction;
import Tool.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class company extends HttpServlet{

    public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        user user =(user)session.getAttribute("user");
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true","root","7758521");
            Statement stmt = con.createStatement();
            if(user.companyname==null)
                response.sendRedirect("entercompany_interface.jsp");
            else{
            String sql = "Select * from company where name='" + user.companyname+ "'";
            ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                companylist companylist=new companylist(rs.getString(4),rs.getString(5));
                session.setAttribute("companylist",companylist);
                response.sendRedirect("seecompany_interface.jsp");
            con.close();}
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

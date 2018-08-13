package BaseFunction;

import Tool.companylist;
import Tool.user;
import Tool.meetinglist;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "mymeeting")
public class mymeeting extends HttpServlet {
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

                //如果该用户已经创建一个会议，提示该用户并且跳转到主界面
                String sql = "Select * from meeting where holder='" + user.name+ "'and company='" + user.companyname+ "'";
                ResultSet re = stmt.executeQuery(sql);
                if(re.next()) {
                    meetinglist meetinglist=new meetinglist(re.getString(3),re.getString(4),re.getString(5));
                    session.setAttribute("meetinglist", meetinglist);
                    response.sendRedirect("mymeeting_interface.jsp");
                    con.close();
                }
                else {
                    JOptionPane.showMessageDialog(null, "你还未曾创办过一个会议");
                    response.sendRedirect("function_interface.jsp");
                    con.close();
                }

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

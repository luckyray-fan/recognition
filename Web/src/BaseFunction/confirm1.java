package BaseFunction;

import Tool.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "confirm1")
public class confirm1 extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        String holder=request.getParameter("holder");
        user user=(user)session.getAttribute("user");
        //判断是否存在该主持人主持的会议
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true", "root", "7758521");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM meeting WHERE  company='" + user.companyname+ "' AND   holder='" + holder + "'";
            ResultSet rs = stmt.executeQuery(sql);
            //如果有则进入定位确认界面
            if (rs.next()) {
                session.setAttribute("holder",holder);
                response.sendRedirect("confirmlocation_interface.jsp");
            }
            else{
                JOptionPane.showMessageDialog(null, "不存在该主持者主持的会议");
                response.sendRedirect("function_interface.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
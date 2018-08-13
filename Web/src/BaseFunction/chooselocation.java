package BaseFunction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Tool.user;


public class chooselocation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        user user =(user)session.getAttribute("user");
        String location=request.getParameter("loc");
        System.out.println("经纬度是"+location);
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true", "root", "7758521");
            Statement stmt = con.createStatement();
            String sql = "Select * from meeting where company='" +user.companyname+ "'and holder='"+user.name+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                PreparedStatement psql;
                psql =con.prepareStatement("update meeting set location = ? where company='"+user.companyname+"'and holder='"+user.name+"'");
                psql.setString(1,location);
                psql.executeUpdate();
            }
        }catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        }catch (Exception e) {
        }
        response.sendRedirect("function_interface.jsp");
    }

}

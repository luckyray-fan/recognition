package BaseFunction;
import Tool.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class holdmeeting extends HttpServlet{

    public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String meeting=request.getParameter("meeting");
        String workid=request.getParameter("workid");
        String name=request.getParameter("name");
        String status="未通过";
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        //连接数据库中的关于会议的表格
        String url="jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String user="root";
        String password="7758521";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (con.isClosed()) {
                System.out.println("数据库连接失败");
            }
            Statement stm = con.createStatement();
            String sql = "Select * from meeting where meeting="+meeting+"";
            ResultSet re = stm.executeQuery(sql);
            boolean exist=false;
            while (re.next()) {
                exist=true;
            }
            //这个会议名不存在 增加这个会议
            if(!exist){
                HttpSession session=request.getSession();
                Tool.user user2 =(user)session.getAttribute("user");
                String holdername="'"+user2.name+"'";
                String holderid="'"+user2.workid+"'";
                String status2="'"+"创建者"+"'";
                String sqls="insert into meeting(meeting,idlist,memberlist,memberstatus)values("+meeting+","+holderid+","+holdername+","+status2+")";
                Statement stmt1=con.createStatement();
                stmt1.executeUpdate(sqls);
            }
            else{
                re.next();
                String newid=re.getString(2)+"，"+workid;
                String newme=re.getString(3)+"，"+name;
                String nesta=re.getString(4)+"，"+status;
                PreparedStatement psql;
                psql = con.prepareStatement("update meeting set idlist = ? and memberlistlist=? and memberstatus=? where id = ? ");
                psql.setString(4, meeting);
                psql.setString(1, newid);
                psql.setString(1, newme);
                psql.setString(1, nesta);
                psql.executeUpdate();
                psql.close();
            }
            re.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
        }






    }
}

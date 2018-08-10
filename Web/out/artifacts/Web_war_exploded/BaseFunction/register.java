package BaseFunction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class register extends HttpServlet{

    public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //从html界面获取到用户信息
        PrintWriter out = response.getWriter();
        String account="'"+request.getParameter("account")+"'";
        String pwd="'"+request.getParameter("pwd")+"'";
        String name="'"+request.getParameter("name")+"'";
        String sex="'"+request.getParameter("sex")+"'";
        String age="'"+request.getParameter("age")+"'";
        String tel="'"+request.getParameter("tel")+"'";
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retime ="'"+tempDate.format(new java.util.Date())+"'";
        //连接数据库
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        //这里我的数据库是qcl
        String url="jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String user="root";
        String password="7758521";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (con.isClosed()) {
                System.out.println("数据库连接失败");
            }
            Statement statement = con.createStatement();
            String sql = "Select * from userinfo where account="+account+"";
            ResultSet resultSet = statement.executeQuery(sql);
            boolean exist=false;
            while (resultSet.next()) {
                exist=true;
            }
            if(exist)
                out.print("该用户名已经存在");
            else{
                String sqls="insert into userinfo(account,pwd,name,sex,age,tel,retime)values("+account+","+pwd+","+name+","+sex+","+age+","+tel+","+retime+")";
                Statement stmt1=con.createStatement();
                stmt1.executeUpdate(sqls);
                response.setContentType("text/html;charset=UTF-8");
                out.print("注册成功");
            }
            resultSet.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
        }
    }
}


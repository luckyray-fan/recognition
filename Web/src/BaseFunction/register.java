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
        String mail="'"+request.getParameter("mail")+"'";
        String pwd="'"+request.getParameter("pass")+"'";
        System.out.println(mail+" "+pwd);
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retime ="'"+tempDate.format(new java.util.Date())+"'";
        //连接数据库
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        //这里我的数据库是qcl
        String url="jdbc:mysql://localhost:3306/user";
        String user="root";
        String password="";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (con.isClosed()) {
                System.out.println("数据库close");
            }
            //如果没有就创建
            Statement statement = con.createStatement();
            String sql = "select * from userinfo where id = 1";
            ResultSet tableEx = statement.executeQuery(sql);
            if(!tableEx.next())
            {
                String tableCr = "create table userinfo" +
                        "(" +
                            "id int primary key," +
                            "uname varchar(40)," +
                            "pass varchar(100),"+
                            "emp int(20)," +
                            "mail varchar(255)," +
                            "comp varchar(255)," +
                            "retime DATETIME"
                            +
                        ")";
                statement.executeUpdate(tableCr);
            }


            sql = "Select * from userinfo where mail="+mail+"";
            ResultSet resultSet = statement.executeQuery(sql);
            boolean exist=false;
            while (resultSet.next()) {
                exist=true;
            }
            if(exist)
                out.print("{'success':false}");
            else{
                String sqls="insert into userinfo(uname,pass,mail,retime)values("+mail+","+pwd+","+mail+","+retime+")";
                Statement stmt1=con.createStatement();
                stmt1.executeUpdate(sqls);
                response.setContentType("text/html;charset=UTF-8");
                out.print("{'success':true}");
            }
            resultSet.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


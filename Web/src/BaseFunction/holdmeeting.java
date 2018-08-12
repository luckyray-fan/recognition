package BaseFunction;
import Tool.user;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class holdmeeting extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Tool.user user2 = (user) session.getAttribute("user");
        String meeting = "'" + user2.companyname + "'";
        String hold = "'" + user2.name+ "'";
        String workid = request.getParameter("workid");
        String name = request.getParameter("name");
        String status = "未在场";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true","root","7758521");
            if (con.isClosed()) {
                System.out.println("数据库连接失败");
            }
            //生成会议
            String company="'"+user2.companyname+"'";
            String holder="'"+user2.name+"'";
            String idlist="'"+user2.workid+"'";
            String memberlist="'"+user2.name+"'";
            String sta1="主持人";
            String sta2="'"+sta1+"'";
            //创建会议表格
            String sql1="insert into meeting(company,holder,idlist,memberlist,memberstatus)values("+company+","+holder+","+idlist+","+memberlist+","+sta2+")";
            Statement st=con.createStatement();
            st.executeUpdate(sql1);

            String sql2 ="Select * from meeting where company="+meeting+" and  holder="+hold+" " ;
            ResultSet res = st.executeQuery(sql2);
            if(res.next()) {
                PreparedStatement psql;
                psql =con.prepareStatement("update meeting set idlist = ? ");
                psql.setString(1, res.getString(3)+"，"+workid);
                psql.executeUpdate();
                psql =con.prepareStatement("update meeting set memberlist = ? ");
                psql.setString(1, res.getString(4)+"，"+name);
                psql.executeUpdate();
                psql =con.prepareStatement("update meeting set memberstatus = ?");
                psql.setString(1, res.getString(5)+"，"+status);
                psql.executeUpdate();
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            e.printStackTrace();
        }









    }
}
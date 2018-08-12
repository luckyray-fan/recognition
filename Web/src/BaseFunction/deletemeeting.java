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

public class deletemeeting extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        user user =(user)session.getAttribute("user");
        String company="'"+user.companyname+"'";
        String holder="'"+user.name+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true","root","7758521");
            if (con.isClosed()) {
                System.out.println("数据库连接失败");
            }
            Statement st=con.createStatement();
            String sql="delete from meeting where company="+company+" and  holder="+holder+" ";
            st.executeUpdate(sql);
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

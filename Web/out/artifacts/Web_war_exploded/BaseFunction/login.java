package BaseFunction;
import Tool.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection conn;
    @Override
    public void init() throws ServletException {//初始化方法进行数据库的连接。。。
        super.init();
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e);
            System.out.println("未找到驱动程序");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true", "root", "7758521");
            Statement stmt = conn.createStatement(); // 获取Statement
            String sql = "SELECT * FROM userinfo WHERE  account='" + account+ "' AND   pwd='" + pwd + "'"; // 查询用户信息的SQL语句,在参数前加上binary 就可以使查询结果区分大小写，因为MySQL默认不区分大小写
            ResultSet rs = stmt.executeQuery(sql); // 执行查询
            if (rs.next()) { // 有数据库中存在该用户
                out.print("登陆成功");
                user user=new user(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                HttpSession session=request.getSession();
                session.setAttribute("user",user);
                response.sendRedirect("function_interface.jsp");
            }
            else{
                out.print("用户名不存在或者密码错误");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

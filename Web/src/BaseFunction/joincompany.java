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
import javax.swing.*;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

import Tool.user;

//在第1个servlet重定向代码之后,不能再调用类似 转发或重定向 的代码。否则会500异常,因为跳转之后,不能再回到此处进行跳转到别的页面
public class joincompany extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Connection con;
        String message = null;
        HttpSession session=request.getSession();
        user user =(user)session.getAttribute("user");
        String identifyingcode=request.getParameter("identifyingcode");
        String workid=request.getParameter("workid");
        String company=request.getParameter("company");
        String memberid=workid;
        String membername=user.name;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true","root","7758521");
            Statement stmt = con.createStatement();
            if(identifyingcode.equals("")){
                response.getWriter().write("验证码不能为空");
            }else if(workid.equals("")){
                response.getWriter().write("员工号不能为空");
            }else{
                String sql = "Select * from company where name='" +company+ "'";
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()){//存在该公司
                    String code=rs.getString(6);
                    if(!code.equals(identifyingcode)){//验证码输入错误
                        response.sendRedirect("entercompany_interface.jsp");
                        JOptionPane.showMessageDialog(null, "验证失败");
                    }else{//验证码正确
                        user.companyname=company;
                        user.workid=workid;
                        String sqls="update userinfo set companyname ='"+company+"',workid='"+workid+"' where account = '"+user.account+"'";
                        Statement stmts=con.createStatement();//创建一个Statement连接
                        int result=stmts.executeUpdate(sqls);//执行sql语句
                        Statement st=con.createStatement();
                        String sql2 ="Select * from company where identifyingcode='"+identifyingcode+"'";
                        ResultSet res = st.executeQuery(sql2);
                        res.next();
                        PreparedStatement psql;
                        psql =con.prepareStatement("update company set membername = ? where identifyingcode='"+identifyingcode+"'");
                        psql.setString(1, res.getString(5)+"，"+membername);
                        psql.executeUpdate();
                        psql =con.prepareStatement("update company set memberid = ? where identifyingcode='"+identifyingcode+"'");
                        psql.setString(1, res.getString(4)+"，"+memberid);
                        psql.executeUpdate();
                        response.sendRedirect("function_interface.jsp");
                        JOptionPane.showMessageDialog(null, "验证成功");
                    }
                }
                else{
                    response.sendRedirect("entercompany_interface.jsp");
                    JOptionPane.showMessageDialog(null, "该公司名不存在");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

package BaseFunction;
import Tool.user;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "confirmlocation")
public class confirmlocation extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        user user=(user)session.getAttribute("user");
        String holder =(String)session.getAttribute("holder");
        System.out.println(holder);
        String loc=request.getParameter("loc");
        double La1=Double.valueOf(loc.split("，")[0]);
        double Lo1=Double.valueOf(loc.split("，")[1]);
        System.out.println(La1+","+Lo1);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true", "root", "7758521");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM meeting WHERE  company='" + user.companyname + "' AND   holder='" + holder + "'";
            ResultSet res = stmt.executeQuery(sql);
            res.next();
            double La2 = Double.valueOf(res.getString(6).split("，")[0]);
            double Lo2 = Double.valueOf(res.getString(6).split("，")[1]);
            System.out.println(La2 + "," + Lo2);
            //位置认证成功
            if ((Math.abs(La1 - La2) <= 0.0002) && (Math.abs(Lo1 - Lo2) <= 0.0002)) {
                String[] idlist = res.getString(3).split("，");
                String[] statuslist = res.getString(5).split("，");
                for (int i = 0; i < idlist.length; i++) {
                    if (user.workid.equals(idlist[i])) {
                        if (statuslist[i].equals("00"))
                            statuslist[i] = "01";
                        if (statuslist[i].equals("10"))
                            statuslist[i] = "11";
                    }
                }
                String newstatus =statuslist[0];
                for (int i = 1; i < idlist.length; i++) {
                    newstatus =newstatus+"，"+statuslist[i];
                }
                PreparedStatement psql;
                psql = con.prepareStatement("update meeting set memberstatus = ? where   holder='" + holder + "'");
                psql.setString(1, newstatus);
                psql.executeUpdate();
                JOptionPane.showMessageDialog(null, "该次定位验证已经通过");
                response.sendRedirect("meeting_interface.jsp");
                con.close();
            }
            //没用通过验证
            else{
                JOptionPane.showMessageDialog(null, "该次定位验证未通过");
                response.sendRedirect("meeting_interface.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
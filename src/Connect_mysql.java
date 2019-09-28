

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_mysql  extends HttpServlet {
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // do nothing
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String driver = "com.mysql.jbdc.Driver";
        String url = "jbdc:mysql://localhost :3306/forum?characterEncoding=utf-8";
        String user = "root";
        String password = "root";
        Connection con;
        {
            try {
                //注册JDBC驱动
                Class.forName(driver);
                //连接数据库
                con = DriverManager.getConnection(url,user,password);
                if(!con.isClosed()){
                    System.out.println("数据库连接成功");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }



}


import com.alibaba.fastjson.JSONObject;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // do nothing
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;

            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
            if (jsonObject == null) {
                return;
            }

            connectSQL();
//            String resultData = parseData(jsonObject);
//            out.print(resultData);
//            out.flush();
//            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private boolean connectSQL() {
        String driver = "com.mysql.jbdc.Driver";
        String url = "jbdc:mysql://localhost:3306/forum?characterEncoding=utf-8";
        String user = "root";
        String password = "12345";
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed()){
                return true;
            }
        }   catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }   finally {
            if (con != null) {

            }
        }

        return false;

    }

    /**
     * return client data
     * @param jsonObject
     * @return
     */
    private String parseData(JSONObject jsonObject) {
        if (String.valueOf(jsonObject.get("cmd")).equals("0")) {
            return "HelloWorld";
        } else {
            return "";
        }
    }

}

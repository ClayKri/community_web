
import com.alibaba.fastjson.JSONObject;
import database.JDBCUtils;
import utils.CloseUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

            JDBCUtils jdbcUtils = new JDBCUtils();
            Connection connection = jdbcUtils.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement("SELECT user_id FROM all_user");
                statement.toString();
            }

//            String resultData = parseData(jsonObject);
//            out.print(resultData);
//            out.flush();
//            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


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

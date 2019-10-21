
import com.alibaba.fastjson.JSONObject;
import database.JDBCUtils;
import utils.CloseUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

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
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM all_user WHERE user_id " +
                    "IN (SELECT MAX(user_id) FROM all_user)");
            int abc = 0;
            while (resultSet.next()) {
                abc = resultSet.getInt(1);
                System.out.println(abc);
            }

            boolean isSuccess = statement.execute("INSERT INTO all_user " +
                    "VALUES (0, 'Test', '123456', '15200001111', 'HelloWorld', '1000-00-00', 1, '广东省', 1, 1, 1, 1)");
            System.out.println(isSuccess);

            jdbcUtils.close();
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

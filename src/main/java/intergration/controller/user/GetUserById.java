package intergration.controller.user;

import com.alibaba.fastjson.JSON;
import intergration.Service.UserService;
import intergration.entity.User;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jigubigu
 * @version 1.0
 * @date 2019/10/13 20:30
 */
@WebServlet(name = "user/getUserById")
public class GetUserById extends HttpServlet {
    private boolean success = false;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        UserService userService = new UserService();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        User user = null;
        try {
            user = userService.getUserById(id);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        if(user != null){
            success = true;
        }
        modelMap.put("success", success);
        modelMap.put("user", user);

        //数据转换成json向浏览器发送
        String outStr = JSON.toJSONString(modelMap);
        PrintWriter out = resp.getWriter();
        out.println(outStr);
        out.flush();
        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
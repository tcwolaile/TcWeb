package TcWeb.service;

import TcWeb.DataBase.BaseDao;
import TcWeb.DataBase.BookInformation;
import TcWeb.DataBase.Json;
import com.google.gson.JsonObject;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class query extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseDao baseDao = new BaseDao();
        PrintWriter out = response.getWriter();
        String sql = "select * from bookinformation where bookName like \'%" + request.getParameter("data") + "%\'";
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        try {
            ArrayList<BookInformation> list = (ArrayList<BookInformation>) BaseDao.query(sql, new BeanListHandler(BookInformation.class));
            JsonObject object = new JsonObject();
            object.addProperty("length", list.size());
            for (int i = 0; i < list.size(); i++) {
                object.addProperty(i + 1 + "",  list.get(i).getLocation());
            }
            out.write(object.toString());
        } catch (Exception e) {
            System.out.println("当你看到一行代码时候你就已经出错了");
        }
    }
}

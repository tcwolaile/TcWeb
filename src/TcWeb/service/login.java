package TcWeb.service;

import TcWeb.DataBase.BaseDao;
import TcWeb.DataBase.User;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String sql = "select * from user where username = ?";
        try {
            User user = (User) BaseDao.query(sql, new BeanHandler(User.class), request.getParameter("username"));
            if (user.getPassword().equals(request.getParameter("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("author", request.getParameter("username"));
                response.sendRedirect("http://localhost:8080/main/html/main.html");

            } else {
                response.sendRedirect("html/login.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

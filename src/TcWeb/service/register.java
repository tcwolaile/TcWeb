package TcWeb.service;

import TcWeb.DataBase.BaseDao;
import TcWeb.DataBase.User;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.http.HttpSession;
import java.io.IOException;


public class register extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BaseDao baseDao = new BaseDao();
        String verificationCode = new String();
        int number;
        String sql = "select * from user where username = ?";
        try {
            User user = (User) BaseDao.query(sql, new BeanHandler(User.class), request.getParameter("username"));
            if (user == null) {
                if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
                    response.sendRedirect("html/register.html?errorCode=2");
                }
                for (int i = 0; i < 4; i++) {
                    number = (int) (Math.random() * 10);
                    verificationCode = verificationCode + number;
                }

                User user1 = new User();
                user1.setPassword(request.getParameter("password"));
                user1.setUsername(request.getParameter("username"));
                System.out.println(request.getParameter("username"));
                user1.setEmail(request.getParameter("email"));
                new Email(request.getParameter("email"), verificationCode);
                HttpSession session = request.getSession();
                session.setAttribute("verificationCode", verificationCode);
                session.setAttribute("user1", user1);
                response.sendRedirect("html/mailboxVerification.html");
            } else {
                response.sendRedirect("html/register.html?errorCode=1");
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}

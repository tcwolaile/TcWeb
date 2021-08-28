package TcWeb.service;



import TcWeb.DataBase.Json;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class write extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int temple;
        String mark;
        response.setContentType("text/html;charset=utf-8");
        System.out.println(Json.readJson("D:/java/TcWeb/data/" + request.getParameter("number") + ".json"));
        JsonObject Data = new JsonParser().parse(Json.readJson("D:/java/TcWeb/data/" + request.getParameter("number") + ".json")).getAsJsonObject();
        temple = Integer.parseInt(Data.get("chapter").toString());
        Data.addProperty("chapter", ++temple);
        String newTitle = new String(request.getParameter("title").getBytes("iso-8859-1"),"utf-8");
        Data.addProperty(temple + "", newTitle);

        String newData = new String(request.getParameter("content").getBytes("iso-8859-1"),"utf-8");

        mark = newData.replaceAll("\n", "</br>");
        newData = mark.replaceAll(" ", "&nbsp;&nbsp;");
        Data.addProperty(temple + "_content", newData);
        Json.createJsonFile(Data.toString(), "D:/java/TcWeb/data", request.getParameter("number"));
        response.sendRedirect("http://localhost:8080/main/html/writeBook.html");
    }
}

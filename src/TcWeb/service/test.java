package TcWeb.service;

import TcWeb.DataBase.Json;
import com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class test extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String data = new String();
        JsonObject object = new JsonObject();
        String test = new String();
        String test_1 = new String();
        test = "　近来，很浮躁，极慵懒，很少静下心来读书。\n" +
                "\n" +
                "今天，是个例外，我静心读了十二篇关于“父亲”话题的心情文章，其中刘小念的《妈妈私奔后，我和这个老男人的故事，轰动了整条街》、张乾东的《父亲在新疆背煤》每每戳中我的泪点，读过多少次就哭过多少次。张正直的《窝囊的父亲》、李承鹏的《父亲是世上最不堪的一个斗士》又何尝不是在写我们曾经的父亲和正当父亲的我们呢！";
        test_1 = test.replaceAll("\n", "</br>");
        object.addProperty("title", "无敌的学霸");
        object.addProperty("1", "初到校园");
        object.addProperty("chapter", 100);
        object.addProperty("content", test_1);
        Json.createJsonFile(object.toString(), "D:/java/TcWeb/data", "0001");
        out.write(data);
    }
}

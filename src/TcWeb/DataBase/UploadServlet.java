package TcWeb.DataBase;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "insert into bookinformation (author, bookName, location) values (?, ?, ?)";
        BookInformation bookInformation = new BookInformation();
        try {
            response.setContentType("text/html;charset=utf-8");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            File f = new File("D://java/TcWeb/temporary");
            if (!f.exists()) {
                f.mkdirs();
            }
            factory.setRepository(f);
            ServletFileUpload fileupload = new ServletFileUpload(factory);
            fileupload.setHeaderEncoding("utf-8");
            List<FileItem> fileitems = fileupload.parseRequest(request);
            PrintWriter writer = response.getWriter();
            for (FileItem fileitem : fileitems) {
                if (fileitem.isFormField()) {
                    String name = fileitem.getFieldName();
                    if(name.equals("name")){
                        if(!fileitem.getString().equals("")){
                            String value = fileitem.getString("utf-8");
                            writer.print("上传者：" + value + "<br>");

                            System.out.println(value);
                        }
                    }
                } else {
                    String filename = fileitem.getName();
                    if(filename != null && !filename.equals("")){
                        writer.print("上传的文件名称是：" + filename + "<br>");
                        Gson gson = new Gson();
                        bookNumber number = gson.fromJson(Json.readJson("D://java/TcWeb/data/0.json"), bookNumber.class);
                        number.setTotalBook(number.getTotalBook() + 1);
                        String filepath = "D://java/TcWeb/web/html/img/" + number.getTotalBook() +".png";
                        Json.createJsonFile(gson.toJson(number), "D:/java/TcWeb/data", "0");
                        HttpSession session = request.getSession();
                        bookInformation.setBookName(request.getParameter("bookName"));
                        System.out.println(request.getParameter("bookName"));
                        bookInformation.setAuthor(session.getAttribute("author").toString());
                        System.out.println(session.getAttribute("author").toString());
                        bookInformation.setLocation(number.getTotalBook() + "");
                        System.out.println(number.getTotalBook() + "");
                        DBUtilsDao dbUtilsDao = new DBUtilsDao();
                        dbUtilsDao.insert_2(bookInformation, sql);
                        File file = new File(filepath);
                        file.getParentFile().mkdirs();
                        file.createNewFile();

                        InputStream in = fileitem.getInputStream();

                        FileOutputStream out = new FileOutputStream(file);
                        // 流的对拷
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = in.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }
                        in.close();
                        out.close();
                        fileitem.delete();
                        writer.print("上传文件成功！<br>");
                        JsonObject object = new JsonObject();
                        object.addProperty("chapter", 0);
                        Json.createJsonFile(object.toString(), "D:/java/TcWeb/data", number.getTotalBook() + "");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("http://localhost:8080/main/html/writeBook.html");
    }
}
